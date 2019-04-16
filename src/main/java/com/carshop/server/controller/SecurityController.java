package com.carshop.server.controller;

import com.carshop.server.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/security",produces = { "application/json;charset=UTF-8"})
public class SecurityController {

    @Autowired
    SecurityService securityService;

    //检查用户是否设置密保问题
    @PostMapping("/checkHaveSecret")
    public Map<String,Object> checkHaveSecret(@RequestBody Map<String,String> params){
        return securityService.checkHaveSecret(params);
    }

    //检查密保问题答案是否正确
    @PostMapping("/checkCorrectSecret")
    public Map<String,Object> checkCorrectSecret(@RequestBody Map<String,String> params){
        return securityService.checkCorrectSecret(params);
    }

    //设置密保问题
    @PostMapping("/setSecret")
    public Map<String,Object> setSecret(@RequestBody Map<String,String> params){
        return securityService.setSecret(params);
    }

    //通过密保问题修改密码
    @PostMapping("/changePassword")
    public Map<String,Object> changePassword(@RequestBody Map<String,String> params){
        return securityService.changePassword(params);
    }
}
