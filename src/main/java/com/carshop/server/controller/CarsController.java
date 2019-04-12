package com.carshop.server.controller;

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
    public Map<String,Object> editCar(@RequestBody Map<String,String> params){
        return carsService.editCar(params);
    }

    //添加车辆
    @PostMapping("/addCar")
    public Map<String,Object> addCar(@RequestBody Map<String,String> params){
        return carsService.addCar(params);
    }

    //添加车辆时检查车辆carId是否已存在
    @PostMapping("/checkCarId")
    public Map<String,Object> checkCarId(@RequestBody Map<String,String> params){
        return carsService.checkCarId(params);
    }

    //批量移除车辆
    @PostMapping("/removeCars")
    public Map<String,Object> removeCars(@RequestBody List<Map<String,String>> params){
        return carsService.removeCars(params);
    }

}
