package com.carshop.server.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    Map<String,Object> changePassword(Map<String, String> params, HttpServletRequest request);

    Map<String,Object> checkSession(String token);

    Map<String,Object> login(Map<String ,String> params,String token);

    Map<String,Object> register(Map<String ,String> params);

    Map<String,Object> check(Map<String ,String> params);

    Map<String,Object> getInfo(Map<String ,String> params);

    Map<String,Object> avatar(String id, String tempPath, MultipartFile picture);

    Map<String,Object> logout(String token);
}
