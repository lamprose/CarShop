package com.carshop.server.service;

import com.carshop.server.domain.Shops;

import java.util.Map;

public interface AdminService {

    Map<String,Object> getUserListPage(Integer page,String name);

}
