package com.carshop.server.controller;

import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Map<String, Object> getUserListPage(@RequestBody Map<String,String> params, HttpServletResponse response){
        Integer page = Integer.parseInt(params.get("page"));//页码
        String name = params.get("name");//名字查询
        return adminService.getUserListPage(page,name);
    }

    //编辑用户
    @PostMapping("/editUser")
    public Map<String,Object> editUser(@RequestBody Map<String,String> params, HttpServletResponse response){
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
        return adminService.removeUser(params);
    }

    //获取全部商户
    @PostMapping("/getShopListPage")
    public Map<String,Object> getShopListPage(@RequestBody Map<String,String> params){
        return adminService.getShopListPage(params);
    }

    //新增商户
    @PostMapping("/addShop")
    public Map<String,Object> addShop(@RequestBody Map<String,String> params){
        return adminService.addShop(params);
    }

    //移除商户
    @PostMapping("/removeShop")
    public Map<String,Object> removeShop(@RequestBody List<Map<String,String>> params){
        return adminService.removeShop(params);
    }

    //通过brandId获取商户信息
    @PostMapping("/getShopInfo")
    public Map<String,Object> getShopInfo(@RequestBody Map<String,String> params){
        return adminService.getShopInfo(params);
    }

    //通过一列brandId，获取每个品牌中前三的车
    @PostMapping("/getEachShopTopNumberCars")
    public Map<String,Object> getEachShopTopNumberCars(@RequestBody List<Map<String,String>> params){
        return adminService.getEachShopTopNumberCars(params);
    }
}
