package com.carshop.server.controller;

import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.encrypt_decrypt.RSA;
import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import com.carshop.server.utils.Enum;

import com.carshop.server.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user",produces = { "application/json;charset=UTF-8"})
public class UserController {
    //依赖注入
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    //用户登录
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String ,String> params){


        String id = params.get("id");
        String password = params.get("password");
        String role = params.get("role");

        String token = RSA.tokenEncrypt(id, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password

        User user;
        Shops shop;

        //封装返回数据
        Map<String,Object> data=new HashMap<String, Object>();
        Map<String,Object> datas=new HashMap<String, Object>();

        if("normal".equals(role)) {
            user = userService.selectOneById(id);   //普通用户登录
            if(user==null){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_EMPTY.getValue());          //次code:空用户
            } else if(!user.getPassword().equals(password)){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_PASSWORD.getValue());       //次code:密码错误
            } else if(user.getStatus().equals("online")){
                data.put("code", Enum.Code.ERR_ONLINE.getValue());          //主code:非法token
                datas.put("code", Enum.Code.ERR_ONLINE.getValue());         //次code:用户已在线
            } else if(user.getToken()==token){
                data.put("code", Enum.Code.ERR_TOKEN.getValue());           //主code:非法token
                datas.put("code", Enum.Code.ERR_TOKEN.getValue());          //次code:非法token
            } else{
                userService.updateStatusById(user.getId(),user.getStatus());                 //修改登陆状态
                user.setStatus("online");

                datas.put("role",role);                                      //封装数据，用户角色
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.COMMON.getValue());             //次code:正常登录
                datas.put("data",user);
                data.put("datas",datas);
            }
        } else if("admin".equals(role)){
            shop = adminService.selectOneById(id);   //管理用户登录
            if(shop==null){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_EMPTY.getValue());          //次code:空用户
            } else if(!shop.getPassword().equals(password)){
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.ERR_PASSWORD.getValue());       //次code:密码错误
            } else if(shop.getStatus().equals("online")){
                data.put("code", Enum.Code.ERR_ONLINE.getValue());          //主code:非法token
                datas.put("code", Enum.Code.ERR_ONLINE.getValue());         //次code:用户已在线
            } else if(shop.getToken()==token){
                data.put("code", Enum.Code.ERR_TOKEN.getValue());           //主code:非法token
                datas.put("code", Enum.Code.ERR_TOKEN.getValue());          //次code:非法token
            } else{
                adminService.updateStatusByShopId(shop.getShopId(),shop.getStatus());        //修改登陆状态
                shop.setStatus("online");

                datas.put("role",shop.getShopId().equals("superAdmin")?"superAdmin":"admin");                                      //封装数据，用户角色
                data.put("code", Enum.Code.COMMON.getValue());              //主code:合法token
                datas.put("code", Enum.Code.COMMON.getValue());             //次code:正常登录
                datas.put("data",shop);
                data.put("datas",datas);
            }
        }

        return data;
    }

    //用户注册
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody Map<String ,String> params){


        String id = params.get("id");
        String password = params.get("password");

        String token = RSA.tokenEncrypt(id, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password

        User u = new User();
        u.setId(id);
        u.setPassword(password);
        u.setToken(token);
        u.setStatus("online");
        userService.insertOneUser(u);

        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> datas=new HashMap<String, Object>();
        datas.put("code",Enum.Code.COMMON.getValue());
        datas.put("data", u);
        datas.put("role", "normal");
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas",datas);
        return data;
    }

    //检测注册用户是否已存在
    @PostMapping("/check")
    public Map<String ,Object> check(@RequestBody Map<String ,String> params){


        String id = params.get("id");
        User user;
        Shops shop;
        shop= adminService.selectOneById(id);
        user= userService.selectOneById(id);
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

    //获取用户(普通用户，商户，超管)信息
    @PostMapping("/getInfo")
    public Map<String,Object> getInfo(@RequestBody Map<String ,String> params){

        String token = params.get("token");
        //String token = request.getQueryString().substring(0);   //解决参数中含有+问题，token => token=...
        //token = token.substring(6);                             //取去除前六个字符token=的子串，即为参数token

        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> datas=new HashMap<String, Object>();
        User user = userService.selectOneByToken(token);
        if(user==null){
            Shops shop = adminService.selectOneByToken(token);
            if(shop.getShopId().equals("superAdmin")){
                datas.put("role","superAdmin");
            }
            else{
                datas.put("role","admin");
            }
            datas.put("data",shop);
        }
        else{
            datas.put("data",user);
            datas.put("role","normal");
        }
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas",datas);
        return data;
    }

    //静态资源路径
    @Value("${web.upload-path}")
    private String path;
    //用户上传头像
    @PostMapping("/avatar")
    public Map<String,Object> avatar(@RequestParam(value = "picture", required = false) MultipartFile picture, HttpServletRequest request){

        String id = request.getParameter("id");
        System.out.println(id);
        Map<String,Object> data = new HashMap<String, Object>();                             //用于封装返回数据
        //String path = request.getSession().getServletContext().getRealPath("/UserAvatar/");//获取文件在服务器的储存位置

        String tempPath = path+"/UserAvatar/";
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
            userService.updateAvatarById(fileName,id);                      //将用户头像路径保存到数据库中
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

    //用户注销
    @PostMapping("/logout")
    public Map<String,Object>  logout(HttpServletRequest request){

        String token = request.getHeader("Token");          //获取当前登录账户的token
        User user;
        Shops shop;
        user = userService.selectOneByToken(token);
        shop = adminService.selectOneByToken(token);
        Map<String, Object> data=new HashMap<String, Object>();

        HttpSession session = request.getSession();             //获取当前session
        session.invalidate();                                   //关闭session
        if(user !=null){                                        //普通用户登录状态点击了注销
            userService.updateStatusById(user.getId(),user.getStatus());         //改变账号状态
        }
        else{                                                   //管理员用户登录状态点击了注销
            adminService.updateStatusByShopId(shop.getShopId(),shop.getStatus());//改变账号状态
        }
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("datas","success");
        return data;
    }
}