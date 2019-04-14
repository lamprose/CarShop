package com.carshop.server.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String,Object> addOrder(Map<String,String> params);

    Map<String,Object> editOrder(Map<String,String> params);

    Map<String,Object> getOrderListPage(Map<String,String> params);

    Map<String,Object> removeOrder(List<Map<String,String>> params);
}
