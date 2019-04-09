package com.carshop.server.service;

import com.carshop.server.domain.Shops;

public interface AdminService {
    Shops selectOneById(String shopId);

    Shops selectOneByToken(String token);

    void updateStatusByShopId(String shopId, String status);


}
