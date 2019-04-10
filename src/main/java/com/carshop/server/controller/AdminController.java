package com.carshop.server.controller;

import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin",produces = { "application/json;charset=UTF-8"})
public class AdminController {
    //依赖注入
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    //获取全部用户信息
    @PostMapping("/getUserListPage")
    public Map<String, Object> getUserListPage(@RequestBody Map<String,String> params){

        Integer page = Integer.parseInt(params.get("page"));//页码
        String name = params.get("name");//名字查询
        return adminService.getUserListPage(page,name);
    }

    //编辑用户
    @PostMapping("/editUser")
    public Map<String,Object> editUser(@RequestBody Map<String,String> params){
        return adminService.editUser(params);
    }

    //增加用户
    @PostMapping("/addUser")
    public Map<String,Object> addUser(@RequestBody Map<String,String> params){
        return adminService.addUser(params);
    }

    //移除用户
    @PostMapping("/removeUser")
    public Map<String,Object> removeUser(@RequestBody List<Map<String,String>> params){
        System.out.println(params.size());
        return null;
    }

    //获取全部商户
    @PostMapping("/getShopListPage")
    public Map<String,Object> getShopListPage(@RequestBody Map<String,String> params){
        return null;//adminService.getShopListPage(params);
    }
}
