package com.carshop.server.service;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface BrandsService {

    Map<String,Object> check(Map<String,String> params);

    Map<String, Object> getBrandsListPage(Map<String,String> params);

    Map<String,Object> editBrands(Map<String,String> params);

    Map<String,Object> addBrands(Map<String,String> params);

    Map<String,Object> removeBrands(List<Map<String,String>> params);

}
