package com.carshop.server.dao;


import com.carshop.server.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface OrderMapper {
    void deleteOrdersByIds(List<Map<String,String>> params);

    void insertOneByOrder(Orders order);

    void insertOrders(List<Orders> ordersList);

    List<Orders> selectAll(String brandId);

    List<Orders> selectAllByUserId(String id);

    List<Orders> selectAllNameSimilar(@Param("name") String name, @Param("brandId") String brandId);

    void updateOrderStatusById(@Param("orderId") String orderId, @Param("orderStatus") String orderStatus);

    void updateOrderStatusByOrderIds(List<Map<String,String>> params);
}
