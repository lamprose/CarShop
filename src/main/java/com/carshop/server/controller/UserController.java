package com.carshop.server.controller;


import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public Map<String,Object> login(@RequestBody Map<String ,String> params, HttpServletRequest request){
        String token=request.getHeader("Token");
        return userService.login(params,token);
    }

    //用户注册
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody Map<String ,String> params){
        return userService.register(params);
    }

    //检测注册用户是否已存在
    @PostMapping("/check")
    public Map<String ,Object> check(@RequestBody Map<String ,String> params){
        return userService.check(params);
    }

    //获取用户(普通用户，商户，超管)信息
    @PostMapping("/getInfo")
    public Map<String,Object> getInfo(@RequestBody Map<String ,String> params){
        return userService.getInfo(params);
    }

    //静态资源路径
    @Value("${web.upload-path}")
    private String path;
    //用户上传头像
    @PostMapping("/avatar")
    public Map<String,Object> avatar(@RequestParam(value = "picture", required = false) MultipartFile picture, HttpServletRequest request){

        String id = request.getParameter("id");
        String tempPath = path+"/UserAvatar/";
        return userService.avatar(id,tempPath,picture);
    }

    //用户注销
    @PostMapping("/logout")
    public Map<String,Object>  logout(HttpServletRequest request){

        String token = request.getHeader("Token");           //获取当前登录账户的token
        HttpSession session = request.getSession();             //获取当前session
        session.invalidate();                                   //关闭session

        return userService.logout(token);
    }

    //检查token是否合法
    @PostMapping("/checkSession")
    public Map<String,Object> checkSession(HttpServletRequest request){
        String token = request.getHeader("Token");
        return userService.checkSession(token);
    }
}