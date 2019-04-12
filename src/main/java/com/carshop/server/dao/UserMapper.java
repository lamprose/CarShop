package com.carshop.server.dao;

import com.carshop.server.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
@Repository
public interface UserMapper {



    User selectOneById(String id);

    User selectOneByToken(String token);

    List<User> selectAll();

    List<User> selectAllNameSimilar(String name);

    void updateStatusById(@Param("id")String id, @Param("status") String status);

    //多个参数需要用@Param
    void updateAvatarById(@Param("avatarPath")String avatarPath, @Param("id") String id);

    void updateStatusToOnlineByToken(String token);

    void updateTokenById(@Param("id") String id, @Param("token") String token);

    void updateUserById(User user);

    void insertOne(User user);

    void deleteUsersByIds(List<Map<String,String>> params);
}
