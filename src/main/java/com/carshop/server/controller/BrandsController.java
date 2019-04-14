package com.carshop.server.controller;

import com.carshop.server.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/brands",produces = { "application/json;charset=UTF-8"})
public class BrandsController {
    //依赖注入
    @Autowired
    BrandsService brandsService;

    //检查brandId是否重复
    @PostMapping("/check")
    public Map<String ,Object> check(@RequestBody Map<String ,String> params){
        return brandsService.check(params);
    }

    //获取全部品牌信息
    @PostMapping("/getBrandListPage")
    public Map<String, Object> getBrandsListPage(@RequestBody Map<String,String> params){
        return brandsService.getBrandsListPage(params);
    }

    //编辑用户
    @PostMapping("/editBrand")
    public Map<String,Object> editBrands(@RequestBody Map<String,String> params){
        return brandsService.editBrands(params);
    }

    //增加用户
    @PostMapping("/addBrand")
    public Map<String,Object> addBrands(@RequestBody Map<String,String> params){
        return brandsService.addBrands(params);
    }

    //移除用户
    @PostMapping("/removeBrand")
    public Map<String,Object> removeBrands(@RequestBody List<Map<String,String>> params){
        return brandsService.removeBrands(params);
    }

}
