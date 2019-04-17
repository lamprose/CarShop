package com.carshop.server.controller;

import com.carshop.server.domain.Cars;
import com.carshop.server.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cars",produces = { "application/json;charset=UTF-8"})
public class CarsController {

    //依赖注入
    @Autowired
    CarsService carsService;

    //获取所有车辆页面
    @PostMapping("/getCarListPage")
    public Map<String, Object> getCarListPage(@RequestBody Map<String,String> params){
        return carsService.getCarListPage(params);
    }

    //编辑车辆信息,以brandIdji
    @PostMapping("/editCar")
    public Map<String,Object> editCar(@RequestBody Cars car){
        return carsService.editCar(car);
    }

    //添加车辆
    @PostMapping("/addCar")
    public Map<String,Object> addCar(@RequestBody Map<String, String> params){
        return carsService.addCar(params);
    }

    //添加车辆时检查车辆carId是否已存在
    @PostMapping("/check")
    public Map<String,Object> checkCarId(@RequestBody Map<String,String> params){
        return carsService.checkCarId(params);
    }

    //批量移除车辆
    @PostMapping("/removeCar")
    public Map<String,Object> removeCars(@RequestBody List<Map<String,String>> params){
        return carsService.removeCars(params);
    }

    //通过carId获取车辆的信息
    @PostMapping("getCarInfo")
    public Map<String,Object> getCarInfo(@RequestBody Map<String,String> params){
        return carsService.getCarInfo(params);
    }

    //根据输入进行商铺名，品牌名，车名搜索
    @PostMapping("searchByText")
    public Map<String,Object> searchByText(@RequestBody Map<String,String> params){
        return carsService.searchByText(params);
    }

    //根据输入返回含有该字段的字符串
    @PostMapping("queryStringByText")
    public Map<String,Object> queryStringByText(@RequestBody Map<String,String> params){
        return carsService.queryStringByText(params);
    }

    //获取评分最高的5两车
    @PostMapping("getHotSearch")
    public Map<String,Object> getHotSearch(){
        return carsService.getHotSearch();
    }
}
