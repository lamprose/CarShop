package com.carshop.server.controller;

import com.carshop.server.dao.BrandsMapper;
import com.carshop.server.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/brand",produces = { "application/json;charset=UTF-8"})
public class BrandsController {
    //依赖注入
    @Autowired
    BrandsService brandsService;

    //检查brandId是否重复
    @PostMapping("/check")
    public Map<String ,Object> check(@RequestBody Map<String ,String> params){

        String brandId = params.get("brandId");
        return brandsService.check(brandId);
    }
}
