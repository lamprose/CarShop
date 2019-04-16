package com.carshop.server.dao;


import com.carshop.server.domain.Cars;
import com.carshop.server.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface CarsMapper {
    void deleteCarsByIds(List<Map<String,String>> params);

    void insertOneByCars(Cars car);

    List<Cars> selectAllByBrandId(String brandId);

    List<Cars> selectAllNameSimilar(@Param("carName") String carName, @Param("brandId") String brandId);

    Cars selectOneByCarId(String carId);

    void updateEvaluationByCarId(@Param("carId") String carId, @Param("evaluation") Integer evaluation);

    void updateOneByCarId(Cars car);
}
