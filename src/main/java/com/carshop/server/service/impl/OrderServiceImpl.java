package com.carshop.server.service.impl;

import com.carshop.server.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Map<String, Object> getOrderListPage(Map<String, String> params) {

        String brandId = params.get("brandId");

        return null;
    }
}
