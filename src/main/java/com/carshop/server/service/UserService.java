package com.carshop.server.service;

import com.carshop.server.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User selectOneById(String id);

    User selectOneByToken(String token);

    List<User> selectAllUser();

    List<User> selectAllNameSimilar(String name);

    void updateStatusById(String id, String status);

    void updateAvatarById(String avatarPath, String id);

    User insertOneUser(User u);
}
