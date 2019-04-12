package com.carshop.server.dao;


import com.carshop.server.domain.Parameters;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface ParametersMapper {
    Parameters selectOneById(String paraId);
}
