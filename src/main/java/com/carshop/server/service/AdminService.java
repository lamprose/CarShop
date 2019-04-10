package com.carshop.server.service;

import com.carshop.server.domain.Shops;

import java.util.Map;

public interface AdminService {

    Map<String,Object> getUserListPage(Integer page,String name);

    Map<String,Object> editUser(Map<String,String> params);

    Map<String,Object> addUser(Map<String,String> params);

    //Map<String,Object> getShopListPage(Integer page,String name);
}
