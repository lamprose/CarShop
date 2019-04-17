package com.carshop.server.service.impl;

import com.carshop.server.dao.CarsMapper;
import com.carshop.server.dao.EvaluationMapper;
import com.carshop.server.dao.OrderMapper;
import com.carshop.server.domain.Evaluations;
import com.carshop.server.domain.Orders;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationMapper evaluationMapper;

    @Autowired
    CarsMapper carsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Map<String, Object> addEvaluation(Map<String, String> params) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      //获取评价时间
        String evaTime = sdf.format(new Date());

        String orderId = params.get("orderId");
        Orders order = orderMapper.selectOneByOrderId(orderId);
        Integer evaId = Integer.parseInt(orderId);

        Integer grade = Integer.parseInt(params.get("grade"));                      //获取评分
        String evaluate = params.get("evaluate");

        Evaluations evaluation = new Evaluations(evaId,order.getUser(),order.getCar(),grade,evaluate,evaTime);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            String carId = order.getCar().getCarId();
            evaluationMapper.insertOneByEvaluation(evaluation);
            Double AvgGrade = evaluationMapper.selectAvgGradeByCarId(carId);           //计算车辆平均评分
            carsMapper.updateEvaluationByCarId(carId,AvgGrade);                        //更新车辆平均评分
            orderMapper.updateOrderStatusById(orderId,"evaluated");
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> getEvaluationListPage(Map<String, String> params) {


        String orderId = params.get("orderId");
        String carId = params.get("carId");
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> datas = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        List<Evaluations> evaluationsList = new ArrayList<Evaluations>();
        List<Evaluations> subEvaluationsList = new ArrayList<Evaluations>();
        if(orderId==null){
            Integer page = Integer.parseInt(params.get("page"));//页码
            evaluationsList = evaluationMapper.selectAllByCarId(carId);
            subEvaluationsList = evaluationsList.subList((page-1)*20,page*20<evaluationsList.size()?page*20:evaluationsList.size());
            datas.put("data",subEvaluationsList);
            datas.put("total",evaluationsList.size());
            data.put("datas",datas);
        }else {
            String evaId = orderId;
            Evaluations temp = evaluationMapper.selectOneByEvaId(evaId);
            data.put("datas",temp);
        }
        return data;
    }

    @Override
    public Map<String, Object> editEvaluation(Map<String, String> params) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      //获取评价时间
        String evaTime = sdf.format(new Date());

        String orderId = params.get("orderId");
        Orders order = orderMapper.selectOneByOrderId(orderId);
        Integer evaId = Integer.parseInt(orderId);

        Integer grade = Integer.parseInt(params.get("grade"));                      //获取评分
        String evaluate = params.get("evaluate");

        Evaluations evaluation = new Evaluations(evaId,order.getUser(),order.getCar(),grade,evaluate,evaTime);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            String carId = order.getCar().getCarId();
            evaluationMapper.updateOneByEvaluation(evaluation);
            Double AvgGrade = evaluationMapper.selectAvgGradeByCarId(carId);           //计算车辆平均评分
            carsMapper.updateEvaluationByCarId(carId,AvgGrade);                        //更新车辆平均评分
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> removeEvaluation(List<Map<String, String>> params){
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        String evaId = params.get(0).get("evaId");
        Evaluations temp = evaluationMapper.selectOneByEvaId(evaId);
        String carId = temp.getCar().getCarId();
        List<Map<String, String>> orderIdStatus = new ArrayList<>();

        for(int i=0; i<params.size(); i++){
            Map<String,String> idStatus = new HashMap<>();
            idStatus.put("orderId",params.get(i).get("evaId"));
            idStatus.put("orderStatus","deleted");
            orderIdStatus.add(idStatus);
        }
        try{
            evaluationMapper.deleteEvaluationsByIds(params);
            Double AvgGrade = evaluationMapper.selectAvgGradeByCarId(carId);           //计算车辆平均评分
            carsMapper.updateEvaluationByCarId(carId,AvgGrade);                        //更新车辆平均评分
            orderMapper.updateOrderStatusByOrderIds(orderIdStatus);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

}
