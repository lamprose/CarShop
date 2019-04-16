package com.carshop.server.service;

import java.util.List;
import java.util.Map;

public interface CartService {
    Map<String,Object> addCart(Map<String,String> params);

    Map<String,Object> editCart(Map<String,String> params);

    Map<String,Object> getCartInfo(Map<String,String> params);

    Map<String,Object> removeCart(List<Map<String,String>> params);
}
