package com.carshop.server.service.impl;

import com.carshop.server.dao.CarsMapper;
import com.carshop.server.dao.EvaluationMapper;
import com.carshop.server.domain.Cars;
import com.carshop.server.domain.Evaluations;
import com.carshop.server.domain.User;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;

    @Autowired
    CarsMapper carsMapper;

    @Override
    public Map<String, Object> addEvaluation(Map<String, String> params) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      //获取评价时间
        String evaTime = sdf.format(new Date());

        User user = new User();
        user.setId(params.get("id"));

        String carId = params.get("carId");
        Cars car = new Cars();
        car.setCarId(carId);
        Integer grade = Integer.parseInt(params.get("grade"));                      //获取评分
        String evaluate = params.get("evaluate");

        Evaluations evaluation = new Evaluations(user,car,grade,evaluate,evaTime);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            evaluationMapper.insertOneByEvaluation(evaluation);
            grade = evaluationMapper.selectAvgGradeByCarId(carId);                  //计算车辆总评分
            carsMapper.updateEvaluationByCarId(carId,grade);                        //更新车辆总评分
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> getEvaluation(Map<String, String> params) {
        String id = params.get("id");
        String shopId = params.get("shopId");
        String carId = params.get("carId");
        if(id==null){
            if(shopId==null){
                //id为空shopId为空              3:通过carId拉取每辆车的所有订单的评价
            }else{
                //id为空shopId不为空            1:通过商户shopId拉取每个商户订单的评价
            }
        }else{
                //id不为空shopId为空             
        }
        return null;
    }
}
