package com.carshop.server.dao;

import com.carshop.server.domain.SecurityQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface SecurityMapper {
    void insertThreeQuestions(List<SecurityQuestion> securityQuestions);

    List<SecurityQuestion> selectAllByUserId(String id);
}
