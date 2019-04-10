package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
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
}
