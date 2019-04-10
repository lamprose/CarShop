package com.carshop.server.service;


import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface UserService {
    Map<String,Object> login(String id, String password, String role);

    Map<String,Object> register(String id, String password);

    Map<String,Object> check(String id);

    Map<String,Object> getInfo(String token);

    Map<String,Object> avatar(String id, String tempPath, MultipartFile picture);

    Map<String,Object> logout(String token);
}
