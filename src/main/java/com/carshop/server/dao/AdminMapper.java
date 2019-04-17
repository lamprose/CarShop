package com.carshop.server.dao;

import com.carshop.server.domain.Shops;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface AdminMapper {

    Shops selectOneByBrandId(String brandId);

    Shops selectOneById(String ShopId);

    Shops selectOneByToken(String token);

    void insertOneShop(Shops shop);

    void updatePassAndTokenById(@Param("shopId") String shopId,@Param("password") String password, @Param("token") String token);

    void updateStatusByShopId(@Param("shopId")String shopId, @Param("status")String status);

    void updateStatusToOnlineByToken(String token);

    void updateTokenByShopId(@Param("shopId") String shopId, @Param("token") String token);

    List<Shops> selectAll();

    List<Shops> selectAllNameSimilar(String shopName);

    void deleteShopsByIds(List<Map<String,String>> params);

}
