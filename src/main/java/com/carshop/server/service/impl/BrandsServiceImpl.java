package com.carshop.server.service.impl;

import com.carshop.server.dao.BrandsMapper;
import com.carshop.server.domain.Brands;
import com.carshop.server.service.BrandsService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    BrandsMapper brandsMapper;

    @Override
    public Map<String,Object> check(Map<String,String> params){
        Brands brands;
        String brandId = params.get("brandId");
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

    @Override
    public Map<String,Object> getBrandsListPage(Map<String,String> params){
        Integer page = Integer.parseInt(params.get("page"));//页码
        String brandName = params.get("name");//名字查询
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<Brands> brandsList;
        List<Brands> subBrandsList;

        if(brandName.equals("")){
            brandsList = brandsMapper.selectAll();
            subBrandsList = brandsList.subList((page-1)*20,page*20<brandsList.size()?page*20:brandsList.size());
            dataTemp.put("data",subBrandsList);
        }
        else{
            brandsList = brandsMapper.selectAllNameSimilar(brandName);
            subBrandsList = brandsList.subList((page-1)*20,page*20<brandsList.size()?page*20:brandsList.size());
            dataTemp.put("data",subBrandsList);
        }

        dataTemp.put("total", brandsList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());

        return data;
    }

    @Override
    public Map<String,Object> editBrands(Map<String,String> params){
        String brandId = params.get("brandId");
        String brandName = params.get("brandName");
        String display = params.get("display");
        String logo = params.get("logo");
        Brands brands = new Brands();
        brands.setBrandId(brandId);
        brands.setBrandName(brandName);
        brands.setDisplay(display);
        brands.setLogo(logo);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            brandsMapper.updateBrandsById(brands);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> addBrands(Map<String,String> params){
        String brandId = params.get("brandId");
        String brandName = params.get("brandName");
        String display = params.get("display");
        String logo = params.get("logo");

        Brands brands = new Brands();
        brands.setBrandId(brandId);
        brands.setBrandName(brandName);
        brands.setDisplay(display);
        brands.setLogo(logo);

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            brandsMapper.insertOne(brands);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String,Object> removeBrands(List<Map<String,String>> params){
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            brandsMapper.deleteUsersByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}
