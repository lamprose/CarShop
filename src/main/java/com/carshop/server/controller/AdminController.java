package com.carshop.server.controller;

import com.carshop.server.domain.User;
import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @GetMapping("/getUserListPage")
    public Map<String, Object> getUserListPage(HttpServletRequest request){

        Integer page = Integer.parseInt(request.getParameter("page"));//页码
        String name = request.getParameter("name");//名字查询

        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<User> userList;
        List<User> subUserList;

        if(name.equals("")){
            userList = userService.selectAllUser();
            subUserList = userList.subList((page-1)*20,page*20<userList.size()?page*20:userList.size());
            dataTemp.put("data",subUserList);
        }
        else{
            userList = userService.selectAllNameSimilar(name);
            subUserList = userList.subList((page-1)*20,page*20<userList.size()?page*20:userList.size());
            dataTemp.put("data",subUserList);
        }

        dataTemp.put("total", userList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());

        return data;
    }


}
