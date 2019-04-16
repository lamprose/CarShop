package com.carshop.server.controller;

import com.carshop.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/shoppingCart",produces = { "application/json;charset=UTF-8"})
public class CartController {

    @Autowired
    CartService cartService;

    //通过用户id拉取用户的购物车信息
    @PostMapping("/getCartInfo")
    public Map<String,Object> getCartInfo(@RequestBody Map<String,String> params){
        return cartService.getCartInfo(params);
    }

    //编辑购物车数量
    @PostMapping("/editCart")
    public Map<String,Object> editCart(@RequestBody Map<String,String> params){
        return cartService.editCart(params);
    }

    //加入购物车
    @PostMapping("/addCart")
    public Map<String,Object> addCart(@RequestBody Map<String,String> params){
        return cartService.addCart(params);
    }

    //批量移除购物车商品
    @PostMapping("/removeCart")
    public Map<String,Object> removeCart(@RequestBody List<Map<String,String>> params){
        return cartService.removeCart(params);
    }
}
