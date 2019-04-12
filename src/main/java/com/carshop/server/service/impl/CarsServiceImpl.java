package com.carshop.server.service.impl;

import com.carshop.server.dao.CarsMapper;
import com.carshop.server.domain.Brands;
import com.carshop.server.domain.Cars;
import com.carshop.server.domain.Parameters;
import com.carshop.server.service.CarsService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    CarsMapper carsMapper;

    @Override
    public Map<String, Object> getCarListPage(Map<String, String> params) {

        Integer page = Integer.parseInt(params.get("page"));//页码
        String name = params.get("name");//名字查询
        String brandId = params.get("brandId");

        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<Cars> carsList;
        List<Cars> subCarsList;

        if(name.equals("")){
            carsList = carsMapper.selectAllByBrandId(brandId);
            subCarsList = carsList.subList((page-1)*20,page*20<carsList.size()?page*20:carsList.size());
            dataTemp.put("data",subCarsList);
        }
        else{
            carsList = carsMapper.selectAllNameSimilar(name,brandId);
            subCarsList = carsList.subList((page-1)*20,page*20<carsList.size()?page*20:carsList.size());
            dataTemp.put("data",subCarsList);
        }

        dataTemp.put("total", carsList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());
        return data;
    }

    @Override
    public Map<String, Object> editCar(Map<String, String> params) {

        String carId = params.get("carId");
        String carName = params.get("carName");
        double price = Double.parseDouble(params.get("price"));
        String brandId = params.get("brandId");
        String paraId = params.get("paraId");

        Brands brand = new Brands();
        brand.setBrandId(brandId);
        Parameters parameters = new Parameters();
        parameters.setParaId(paraId);
        Cars car = new Cars();
        car.setCarId(carId);
        car.setCarName(carName);
        car.setPrice(price);
        car.setBrand(brand);
        car.setParam(parameters);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            carsMapper.updateOneByCarId(car);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;

    }

    @Override
    public Map<String, Object> checkCarId(Map<String, String> params) {
        String carId = params.get("carId");

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        Cars car = carsMapper.selectOneByCarId(carId);
        if(car==null){

            data.put("datas","success");
        }
        else{
            data.put("datas","fail");
        }
        return data;

    }

    @Override
    public Map<String, Object> addCar(Map<String, String> params) {
        String carId = params.get("carId");
        String carName = params.get("carName");
        double price = Double.parseDouble(params.get("price"));
        String brandId = params.get("brandId");
        String paraId = params.get("paraId");

        Brands brand = new Brands();
        brand.setBrandId(brandId);
        Parameters parameters = new Parameters();
        parameters.setParaId(paraId);
        Cars car = new Cars();
        car.setCarId(carId);
        car.setCarName(carName);
        car.setPrice(price);
        car.setBrand(brand);
        car.setParam(parameters);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            carsMapper.insertOneByCars(car);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;

    }

    @Override
    public Map<String, Object> removeCars(List<Map<String, String>> params) {
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            carsMapper.deleteCarsByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}
