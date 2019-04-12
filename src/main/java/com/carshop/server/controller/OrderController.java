package com.carshop.server.controller;


import com.carshop.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/order",produces = { "application/json;charset=UTF-8"})
public class OrderController {

    //依赖注入
    @Autowired
    OrderService orderService;


    //获取订单，根据brandId,拉取订单信息
    @PostMapping("getOrderListPage")
    public Map<String, Object> getOrdeListPage(@RequestBody Map<String,String> params){
        return orderService.getOrderListPage(params);
    }
}
