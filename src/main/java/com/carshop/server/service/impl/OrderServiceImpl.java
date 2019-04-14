package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.OrderMapper;
import com.carshop.server.domain.Cars;
import com.carshop.server.domain.Orders;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Map<String, Object> getOrderListPage(Map<String, String> params) {

        String shopId = params.get("shopId");
        Shops shop = adminMapper.selectOneById(shopId);
        //通过brandId，为每个商户拉取旗下的订单
        String brandId = shop.getBrand().getBrandId();
        Integer page = Integer.parseInt(params.get("page"));    //页码
        String name = params.get("name");                       //根据订单号和用户名，查询订单
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> dataTemp=new HashMap<String, Object>();
        List<Orders> ordersList;
        List<Orders> subOrdersList;

        if(name.equals("")){
            ordersList = orderMapper.selectAll(brandId);
            subOrdersList = ordersList.subList((page-1)*20,page*20<ordersList.size()?page*20:ordersList.size());
            dataTemp.put("data",subOrdersList);
        }
        else{
            ordersList = orderMapper.selectAllNameSimilar(name,brandId);    //根据订单号和用户名，查询订单
            subOrdersList = ordersList.subList((page-1)*20,page*20<ordersList.size()?page*20:ordersList.size());
            dataTemp.put("data",subOrdersList);
        }

        dataTemp.put("total", ordersList.size());
        data.put("datas", dataTemp);
        data.put("code", Enum.Code.COMMON.getValue());
        return data;
    }

    @Override
    public Map<String, Object> editOrder(Map<String, String> params) {
        String orderStatus = params.get("orderStatus");
        String orderId = params.get("orderId");
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        try{
            orderMapper.updateOrderStatusById(orderId,orderStatus);
            data.put("datas","success");
        }
        catch (Exception e){
            data.put("datas","fail");
            System.out.println(e);
        }
        return data;
    }

    @Override
    public Map<String, Object> addOrder(Map<String, String> params) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      //获取下单时间
        String date = sdf.format(new Date());
        String id = params.get("id");                                                //获取下单用户id
        String carId = params.get("carId");                                          //获取车辆carId

        User user = new User();
        user.setId(id);

        Cars car = new Cars();
        car.setCarId(carId);
        Orders order = new Orders();
        order.setAmount(Integer.parseInt(params.get("amount")));                     //获取订单数量
        order.setOrderTime(date);
        order.setUser(user);
        order.setCar(car);

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            orderMapper.insertOneByOrder(order);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> removeOrder(List<Map<String, String>> params) {
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            orderMapper.deleteOrdersByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}

