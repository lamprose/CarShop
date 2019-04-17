package com.carshop.server.dao;


import com.carshop.server.domain.Evaluations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface EvaluationMapper {
    void deleteEvaluationsByIds(List<Map<String,String>> params);

    void insertOneByEvaluation(Evaluations evaluation);

    List<Evaluations> selectAllByCarId(String carId);

    List<Evaluations> selectAllByUserIdAndCarId(@Param("id") String id, @Param("carId") String carId);

    Double selectAvgGradeByCarId(String carId);

    Evaluations selectOneByEvaId(String evaId);

    void updateOneByEvaluation(Evaluations evaluation);
}
