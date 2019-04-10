package com.carshop.server.controller;

import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/getUserListPage")
    public Map<String, Object> getUserListPage(HttpServletRequest request){

        Integer page = Integer.parseInt(request.getParameter("page"));//页码
        String name = request.getParameter("name");//名字查询
        return adminService.getUserListPage(page,name);
    }


}
