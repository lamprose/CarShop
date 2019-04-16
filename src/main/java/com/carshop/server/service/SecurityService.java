package com.carshop.server.service;

import java.util.Map;

public interface SecurityService {
    Map<String,Object> changePassword(Map<String,String> params);

    Map<String,Object> checkCorrectSecret(Map<String,String> params);

    Map<String,Object> checkHaveSecret(Map<String,String> params);

    Map<String,Object> setSecret(Map<String,String> params);
}
