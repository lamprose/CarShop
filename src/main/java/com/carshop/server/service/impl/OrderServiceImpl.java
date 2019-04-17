package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.CartMapper;
import com.carshop.server.dao.OrderMapper;
import com.carshop.server.domain.*;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    CartMapper cartMapper;

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
    public Map<String, Object> getUserOrderListPage(Map<String, String> params) {
        String id = params.get("id");
        Map<String, Object> data=new HashMap<String, Object>();
        data.put("code",Enum.Code.COMMON.getValue());
        List<Orders> orderList = orderMapper.selectAllByUserId(id);
        data.put("datas",orderList);
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
    public Map<String, Object> addOrder(List<Map<String, String>> params) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");      //获取下单时间
        String date = sdf.format(new Date());

        Integer flag = 0;
        List<Orders> ordersList = new ArrayList<>();
        List<Map<String,String>> cartRemoveList = new ArrayList<>();
        for(int i=0; i<params.size(); i++){

            if(params.get(i).get("type").equals("1")){          //type:添加类型 1 表示从购物车里添加订单 0 表示直接购买无需移出购物车
                flag = 1;
                Map<String,String> temp = new HashMap<>();
                temp.put("cartId",params.get(i).get("cartId"));
                cartRemoveList.add(temp);
            }

            User user = new User();
            user.setId(params.get(i).get("id"));
            Cars car = new Cars();
            car.setCarId(params.get(i).get("carId"));
            Integer amount = Integer.parseInt(params.get(i).get("amount"));

            Orders order = new Orders(user,car,amount,"unpaid",date);
            ordersList.add(order);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            orderMapper.insertOrders(ordersList);                   //添加订单
            if(flag==1){
                cartMapper.deleteCartsByIds(cartRemoveList);            //移除购物车内容
            }

            List<Map<String,Object>> datas = new ArrayList<>();

            for (int i=0; i<ordersList.size(); i++) {
                Map<String,Object> temp = new HashMap<>();
                temp.put("orderId", ordersList.get(i).getOrderId());
                datas.add(temp);
            }
            data.put("datas",datas);
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas",null);
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

    @Override
    public Map<String, Object> changeOrderStatus(List<Map<String, String>> params) {
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            orderMapper.updateOrderStatusByOrderIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}

