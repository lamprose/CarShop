package com.carshop.server.service;

import com.carshop.server.domain.Cars;

import java.util.List;
import java.util.Map;

public interface CarsService {
    Map<String,Object> addCar(Map<String, String> params);

    Map<String,Object> checkCarId(Map<String,String> params);

    Map<String,Object> editCar(Cars cars);

    Map<String,Object> getCarInfo(Map<String,String> params);

    Map<String,Object> getCarListPage(Map<String,String> params);

    Map<String,Object> getHotSearch();

    Map<String,Object> queryStringByText(Map<String,String> params);

    Map<String,Object> removeCars(List<Map<String,String>> params);

    Map<String,Object> searchByText(Map<String,String> params);
}
