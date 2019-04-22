package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.encrypt_decrypt.RSA;
import com.carshop.server.service.UserService;
import com.carshop.server.utils.Enum;
import com.carshop.server.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Map<String, Object> login(Map<String ,String> params,String token){

        //String token = params.get("token");
        //封装返回数据
        Map<String,Object> data=new HashMap<String, Object>();
        Map<String,Object> datas=new HashMap<String, Object>();
        User user;
        Shops shop;

        if(token!=null){                                //通过token登录
            user = userMapper.selectOneByToken(token);
            shop = adminMapper.selectOneByToken(token);
            if(user==null&&shop==null){
                data.put("code",Enum.Code.ERR_TOKEN.getValue());
            }else {
                data.put("code",Enum.Code.COMMON.getValue());
                if(user!=null){
                    userMapper.updateStatusById(user.getId(),user.getStatus());
                    user.setStatus("online");
                    datas.put("data",user);
                    datas.put("role","normal");
                    data.put("datas",datas);
                }else {
                    adminMapper.updateStatusByShopId(shop.getShopId(),shop.getStatus());
                    shop.setStatus("online");
                    datas.put("data",shop);
                    datas.put("role",shop.getShopId().equals("superAdmin")?"superAdmin":"admin");
                    data.put("datas",datas);
                }
            }
            return data;
        }

        String id = params.get("id");                //通过账号密码登录
        String password = params.get("password");
        String role = params.get("role");

        token = RSA.tokenEncrypt(id, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);    //对密码进行RSA加密，获取password

        if("normal".equals(role)) {
            user = userMapper.selectOneById(id);     //普通用户登录
            if(user==null){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_EMPTY.getValue());          //次code:空用户
            } else if(!user.getPassword().equals(password)){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_PASSWORD.getValue());       //次code:密码错误
            } else if(user.getStatus().equals("online")){
                data.put("code", Enum.Code.ERR_ONLINE.getValue());          //主code:非法token
                datas.put("code", Enum.Code.ERR_ONLINE.getValue());         //次code:用户已在线
            }else{
                userMapper.updateStatusById(id,user.getStatus());           //修改登陆状态
                userMapper.updateTokenById(id,token);                       //更新token
                user.setStatus("online");
                user.setToken(token);

                datas.put("role",role);                                      //封装数据，用户角色
                data.put("code", Enum.Code.COMMON.getValue());               //主code:合法token
                datas.put("code", Enum.Code.COMMON.getValue());              //次code:正常登录
                datas.put("data",user);
            }
        } else if("admin".equals(role)){

            shop = adminMapper.selectOneById(id);   //管理用户登录
            if(shop==null){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_EMPTY.getValue());          //次code:空用户
                System.out.println(datas.get("code"));
            } else if(!shop.getPassword().equals(password)){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_PASSWORD.getValue());       //次code:密码错误
            } else if(shop.getStatus().equals("online")){
                data.put("code", Enum.Code.ERR_ONLINE.getValue());          //主code:非法token
                datas.put("code", Enum.Code.ERR_ONLINE.getValue());         //次code:用户已在线
            }else{
                adminMapper.updateStatusByShopId(id,shop.getStatus());      //修改登陆状态
                adminMapper.updateTokenByShopId(id,token);                  //更新token
                shop.setStatus("online");
                shop.setToken(token);
                datas.put("role",shop.getShopId().equals("superAdmin")?"superAdmin":"admin");                                      //封装数据，用户角色
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.COMMON.getValue());             //次code:正常登录
                datas.put("data",shop);
            }
        }
        data.put("datas",datas);
        return data;
    }

    @Override
    public Map<String, Object> register(Map<String ,String> params) {

        String id = params.get("id");
        String password = params.get("password");
        String token = RSA.tokenEncrypt(id, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password

        User u = new User();
        u.setId(id);
        u.setPassword(password);
        u.setToken(token);
        u.setStatus("online");
        userMapper.insertOne(u);

        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> datas=new HashMap<String, Object>();
        datas.put("code",Enum.Code.COMMON.getValue());
        datas.put("data", u);
        datas.put("role", "normal");
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas",datas);
        return data;
    }

    @Override
    public Map<String,Object> check(Map<String ,String> params){

        String id = params.get("id");
        User user;
        Shops shop;
        shop= adminMapper.selectOneById(id);
        user= userMapper.selectOneById(id);
        Map<String ,Object> data = new HashMap<String, Object>();
        data.put("code",Enum.Code.COMMON.getValue());

        if(user == null&&shop == null){
            data.put("datas","success");
        }
        else {
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> getInfo(Map<String ,String> params){
        //String token = request.getQueryString().substring(0);   //解决参数中含有+问题，token => token=...
        //token = token.substring(6);                             //取去除前六个字符token=的子串，即为参数token

        String id = params.get("id");
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> datas=new HashMap<String, Object>();
        User user = new User();
        Shops shop = new Shops();
        user = userMapper.selectOneById(id);

        if(user==null){
            shop = adminMapper.selectOneById(id);
            if(shop!=null){
                datas.put("role",shop.getShopId().equals("superAdmin")?"superAdmin":"admin");
                datas.put("data",shop);
            }
        }
        else{
            datas.put("data",user);
            datas.put("role","normal");
        }
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas",datas);
        return data;
    }

    @Override
    public Map<String,Object> avatar(String id, String tempPath, MultipartFile picture){
        Map<String,Object> data = new HashMap<String, Object>();                             //用于封装返回数据
        //String path = request.getSession().getServletContext().getRealPath("/UserAvatar/");//获取文件在服务器的储存位置

        File filePath = new File(tempPath);
        System.out.println("文件的保存路径：" + tempPath);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        String originalFileName = picture.getOriginalFilename();            //获取原始文件名称(包含格式)
        String fileName = FileUtils.formatFileName(id, originalFileName);   //对文件名进行格式化命名，用户Id+当前时间,例如Peter_2019040418183840.png
        FileUtils.deleteFile(id, tempPath);                                     //若用户头像存储目录下已有该用户的头像则删除
        File targetFile = new File(tempPath, fileName);                         //在指定路径下创建一个文件

        try {
            picture.transferTo(targetFile);                                 //将文件保存到本地指定位置
            userMapper.updateAvatarById("/UserAvatar/"+fileName,id);                      //将用户头像路径保存到数据库中
            System.out.println("上传成功");

            //将文件在服务器的存储路径返回
            data.put("code",Enum.Code.COMMON.getValue());
            data.put("datas","/UserAvatar/"+fileName);

        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Map<String,Object> logout(String token){

        User user;
        Shops shop;
        user = userMapper.selectOneByToken(token);
        shop = adminMapper.selectOneByToken(token);
        Map<String, Object> data=new HashMap<String, Object>();

        if(user !=null){                                        //普通用户登录状态点击了注销
            userMapper.updateStatusById(user.getId(),user.getStatus());         //改变账号状态
        }
        else{                                                   //管理员用户登录状态点击了注销
            adminMapper.updateStatusByShopId(shop.getShopId(),shop.getStatus());//改变账号状态
        }
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas","success");
        return data;
    }

    @Override
    public Map<String, Object> checkSession(String token) {
        Map<String, Object> data=new HashMap<String, Object>();

        data.put("code",Enum.Code.COMMON.getValue());
        User user = userMapper.selectOneByToken(token);
        try{
            if(user==null){
                adminMapper.updateStatusToOnlineByToken(token);
            }else{
                userMapper.updateStatusToOnlineByToken(token);
            }
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> changePassword(Map<String, String> params, HttpServletRequest request) {

        String oldPassword = params.get("oldPassword");

        oldPassword = RSA.passwordEncrypt(oldPassword);
        String newPassword = params.get("newPassword");
        String token = request.getHeader("Token");

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        User user = userMapper.selectOneByToken(token);
        if(user==null){
            Shops shop = adminMapper.selectOneByToken(token);
            if(!shop.getPassword().equals(oldPassword)){               //验证输入的原密码是否正确，不正确返回空
                data.put("datas",null);
                return data;
            }
            token = RSA.tokenEncrypt(shop.getShopId(), newPassword);   //对账号密码字符串token进行RSA加密,获取token
            newPassword = RSA.passwordEncrypt(newPassword);            //对密码进行RSA加密，获取password

            adminMapper.updatePassAndTokenById(shop.getShopId(),newPassword,token);
            shop = adminMapper.selectOneById(shop.getShopId());
            data.put("datas",shop);
        }else {
            if(!user.getPassword().equals(oldPassword)){                //验证输入的原密码是否正确，不正确返回空
                data.put("datas",null);
                return data;
            }
            token = RSA.tokenEncrypt(user.getId(), newPassword);       //对账号密码字符串token进行RSA加密,获取token
            newPassword = RSA.passwordEncrypt(newPassword);            //对密码进行RSA加密，获取password

            userMapper.updatePassAndTokenById(user.getId(),newPassword,token);
            user = userMapper.selectOneById(user.getId());
            data.put("datas",user);
        }
        return data;
    }
}
