package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.Brands;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.encrypt_decrypt.RSA;
import com.carshop.server.service.AdminService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String,Object> getUserListPage(Integer page,String name){
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<User> userList;
        List<User> subUserList;

        if(name.equals("")){
            userList = userMapper.selectAll();
            subUserList = userList.subList((page-1)*20,page*20<userList.size()?page*20:userList.size());
            dataTemp.put("data",subUserList);
        }
        else{
            userList = userMapper.selectAllNameSimilar(name);
            subUserList = userList.subList((page-1)*20,page*20<userList.size()?page*20:userList.size());
            dataTemp.put("data",subUserList);
        }

        dataTemp.put("total", userList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());

        return data;
    }

    @Override
    public Map<String,Object> editUser(Map<String,String> params){
        String id = params.get("id");
        String name = params.get("name");
        String sex = params.get("sex");
        String phone = params.get("phone");
        String loc = params.get("loc");
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSex(sex);
        user.setPhone(phone);
        user.setLoc(loc);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            userMapper.updateUserById(user);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
       return data;
    }

    @Override
    public Map<String,Object> addUser(Map<String,String> params){
        String id = params.get("id");
        String password = id;               //管理员新增用户默认用户名和密码一致
        String name = params.get("name");
        String sex = params.get("sex");
        String phone = params.get("phone");
        String loc = params.get("loc");

        String token = RSA.tokenEncrypt(id, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password

        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setSex(sex);
        user.setPhone(phone);
        user.setLoc(loc);
        user.setToken(token);
        user.setStatus("offline");

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            userMapper.insertOne(user);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> removeUser(List<Map<String,String>> params){
        Map<String,Object> data = new HashMap<>();
        System.out.println(params);
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            userMapper.deleteUsersByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> getShopListPage(Map<String,String> params){
        Integer page = Integer.parseInt(params.get("page"));//页码
        String name = params.get("name");//名字查询
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<Shops> shopsList;
        List<Shops> subShopsList;

        if(name.equals("")){
            shopsList = adminMapper.selectAll();
            subShopsList = shopsList.subList((page-1)*20,page*20<shopsList.size()?page*20:shopsList.size());
            dataTemp.put("data",subShopsList);
        }
        else{
            shopsList = adminMapper.selectAllNameSimilar(name);
            subShopsList = shopsList.subList((page-1)*20,page*20<shopsList.size()?page*20:shopsList.size());
            dataTemp.put("data",subShopsList);
        }

        dataTemp.put("total", shopsList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());
        return data;
    }

    @Override
    public Map<String,Object> addShop(Map<String,String> params){
        String shopId = params.get("shopId");
        String shopName = params.get("shopName");
        String password = shopId;                       //初始密码默认为id
        String phone = params.get("phone");
        String brandId = params.get("brandId");

        String token = RSA.tokenEncrypt(shopId, password);      //对账号密码字符串token进行RSA加密,获取token
        password = RSA.passwordEncrypt(password);           //对密码进行RSA加密，获取password

        Shops shop = new Shops();
        Brands brand = new Brands();
        shop.setShopId(shopId);
        shop.setShopName(shopName);
        shop.setPassword(password);
        shop.setPhone(phone);
        shop.setToken(token);
        shop.setStatus("offline");
        brand.setBrandId(brandId);
        shop.setBrand(brand);

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            adminMapper.insertOneShop(shop);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> removeShop(List<Map<String,String>> params){
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            adminMapper.deleteShopsByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}
