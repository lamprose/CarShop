package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.CarsMapper;
import com.carshop.server.dao.ParametersMapper;
import com.carshop.server.domain.Brands;
import com.carshop.server.domain.Cars;
import com.carshop.server.domain.Parameters;
import com.carshop.server.domain.Shops;
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

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    ParametersMapper parametersMapper;

    @Override
    public Map<String, Object> getCarListPage(Map<String, String> params) {

        Integer page = Integer.parseInt(params.get("page"));//页码
        String name = params.get("name");//名字查询
        String shopId = params.get("shopId");
        Shops shop = adminMapper.selectOneById(shopId);
        String brandId = shop.getBrand().getBrandId();

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
    public Map<String, Object> editCar(Cars car) {

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        try{
            carsMapper.updateOneByCarId(car);
            parametersMapper.updateOneById(car.getParam());
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
            System.out.println(e);
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

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        String shopId = params.get("shopId");
        Shops shop = adminMapper.selectOneById(shopId);
        String brandId = shop.getBrand().getBrandId();
        Brands brand  = new Brands();
        brand.setBrandId(brandId);

        Parameters paramTemp = new Parameters();
        paramTemp.setCarSize(params.get("carSize"));
        paramTemp.setEngine(params.get("engine"));
        paramTemp.setLevel(params.get("level"));
        paramTemp.setStructure(params.get("structure"));
        paramTemp.setAssurance(params.get("assurance"));
        paramTemp.setOilWear(Double.parseDouble(params.get("oilWear")));
        paramTemp.setManufacturer(params.get("manufacturer"));
        paramTemp.setGearbox(params.get("gearbox"));
        paramTemp.setMaxSpeed(Integer.parseInt(params.get("maxSpeed")));

        Cars car = new Cars();
        car.setCarId(params.get("carId"));
        car.setCarName(params.get("carName"));
        car.setPrice(Double.parseDouble(params.get("price")));
        car.setBrand(brand);

        try{
            parametersMapper.insertOneByParam(paramTemp);
            car.setParam(paramTemp);
            carsMapper.insertOneByCars(car);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
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
