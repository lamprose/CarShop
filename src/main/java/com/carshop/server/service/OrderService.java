package com.carshop.server.service;

import java.util.Map;

public interface OrderService {
    Map<String,Object> getOrderListPage(Map<String,String> params);
}
