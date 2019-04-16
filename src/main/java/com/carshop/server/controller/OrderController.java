package com.carshop.server.controller;


import com.carshop.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/order",produces = { "application/json;charset=UTF-8"})
public class OrderController {

    //依赖注入
    @Autowired
    OrderService orderService;


    //获取订单，根据brandId,拉取商铺订单信息
    @PostMapping("getOrderListPage")
    public Map<String, Object> getOrdeListPage(@RequestBody Map<String,String> params){
        return orderService.getOrderListPage(params);
    }

    //获取订单，根据用户id,拉取用户个人订单信息
    @PostMapping("getUserOrderListPage")
    public Map<String, Object> getUserOrdeListPage(@RequestBody Map<String,String> params){
        return orderService.getUserOrderListPage(params);
    }

    //获取订单，根据brandId,拉取订单信息,编辑订单状态
    @PostMapping("editOrder")
    public Map<String, Object> editOrder(@RequestBody Map<String,String> params){
        return orderService.editOrder(params);
    }

    //普通用户添加订单，未支付
    @PostMapping("addOrder")
    public Map<String, Object> addOrder(@RequestBody List<Map<String,String>> params){
        return orderService.addOrder(params);
    }

    //支付完成修改订单状态
    @PostMapping("/changeOrderStatus")
    public Map<String,Object> changeOrderStatus(@RequestBody List<Map<String,String>> params){
        return orderService.changeOrderStatus(params);
    }

    //移除订单
    @PostMapping("removeOrder")
    public Map<String, Object> removeOrder(@RequestBody List<Map<String,String>> params){
        return orderService.removeOrder(params);
    }
}
