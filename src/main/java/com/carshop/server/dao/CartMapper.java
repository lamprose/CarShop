package com.carshop.server.dao;


import com.carshop.server.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface CartMapper {
    void deleteCartsByIds(List<Map<String,String>> params);

    void insertOneByShoppingCart(ShoppingCart cart);

    List<ShoppingCart> selectAllCartByUserId(String id);

    ShoppingCart selectOneByUserIdAndCarId(@Param("id") String id, @Param("carId") String carId);

    void updateAmountByUserId(@Param("amount") Integer amount, @Param("id") String id);
}
