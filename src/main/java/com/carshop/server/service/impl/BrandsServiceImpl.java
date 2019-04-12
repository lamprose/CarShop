package com.carshop.server.service.impl;

import com.carshop.server.dao.BrandsMapper;
import com.carshop.server.domain.Brands;
import com.carshop.server.service.BrandsService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    BrandsMapper brandsMapper;

    @Override
    public Map<String,Object> check(String brandId){
        Brands brands;
        brands= brandsMapper.selectOneById(brandId);
        Map<String ,Object> data = new HashMap<String, Object>();
        data.put("code",Enum.Code.COMMON.getValue());
        if(brands == null){
            data.put("datas","success");
        }
        else{
            data.put("datas","fail");
        }
        return data;
    }
}
