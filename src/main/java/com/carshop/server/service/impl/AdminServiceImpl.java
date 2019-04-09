package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.domain.Shops;
import com.carshop.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Shops selectOneById(String shopId){
        return adminMapper.selectOneById(shopId);
    }

    @Override
    public Shops selectOneByToken(String token){
        return adminMapper.selectOneByToken(token);
    }

    @Override
    public void updateStatusByShopId(String shopId){
        adminMapper.updateStatusByShopId(shopId);
    }
}
