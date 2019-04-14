package com.carshop.server.dao;

import com.carshop.server.domain.Brands;
import com.carshop.server.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface BrandsMapper {

    Brands selectOneById(String brandId);
    List<Brands> selectAll();

    List<Brands> selectAllNameSimilar(String brandName);

    void updateBrandsById(Brands brands);

    void insertOne(Brands brands);

    void deleteUsersByIds(List<Map<String,String>> params);

}
