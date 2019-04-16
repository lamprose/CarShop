package com.carshop.server.service.impl;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.SecurityQuestion;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.encrypt_decrypt.RSA;
import com.carshop.server.utils.Enum;
import com.carshop.server.dao.SecurityMapper;
import com.carshop.server.service.SecurityService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SecurityMapper securityMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Map<String, Object> changePassword(Map<String, String> params) {

        String newPassword = params.get("newPassword");
        String id = params.get("id");
        String token;
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> datas = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        User user = userMapper.selectOneById(id);
        if(user==null){
            Shops shop = adminMapper.selectOneById(id);
            token = RSA.tokenEncrypt(id, newPassword);                 //对账号密码字符串token进行RSA加密,获取token
            newPassword = RSA.passwordEncrypt(newPassword);            //对密码进行RSA加密，获取password

            adminMapper.updatePassAndTokenById(id,newPassword,token);
            userMapper.updateStatusToOnlineByToken(token);
            shop = adminMapper.selectOneById(shop.getShopId());
            datas.put("data",shop);
            datas.put("role",shop.getShopId().equals("superAdmin")?"superAdmin":"admin");
        }else {
            token = RSA.tokenEncrypt(id, newPassword);                 //对账号密码字符串token进行RSA加密,获取token
            newPassword = RSA.passwordEncrypt(newPassword);            //对密码进行RSA加密，获取password

            userMapper.updatePassAndTokenById(id,newPassword,token);
            userMapper.updateStatusToOnlineByToken(token);
            user = userMapper.selectOneById(user.getId());
            datas.put("data",user);
            datas.put("role","normal");
        }
        data.put("datas",datas);
        return data;
    }

    @Override
    public Map<String, Object> checkCorrectSecret(Map<String, String> params) {
        String id = params.get("id");
        String question1 = params.get("question1");
        String question2 = params.get("question2");
        String question3 = params.get("question3");
        String answer1 = params.get("answer1");
        String answer2 = params.get("answer2");
        String answer3 = params.get("answer3");

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        Integer flag = 1;       //密保问题标记，1 correct, 0 wrong
        List<SecurityQuestion> securityQuestions = securityMapper.selectAllByUserId(id);
        for(int i=0; i<securityQuestions.size(); i++){
            SecurityQuestion temp = securityQuestions.get(i);
            if(temp.getQuestionType().equals(question1)){           //若为问题一
                if(!temp.getAnswer().equals(answer1)){
                    flag = 0;
                }
            }else if(temp.getQuestionType().equals(question2)){     //若为问题二
                if(!temp.getAnswer().equals(answer2)){
                    flag = 0;
                }
            }else if(temp.getQuestionType().equals(question3)){     //若为问题三
                if(!temp.getAnswer().equals(answer3)){
                    flag = 0;
                }
            }
        }
        if(flag==1){
            data.put("datas","success");
        }else {
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> checkHaveSecret(Map<String, String> params) {

        String id = params.get("id");
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        List<SecurityQuestion> securityQuestions = securityMapper.selectAllByUserId(id);
        if(securityQuestions.size()==0){
            data.put("datas","fail");
        } else{
            data.put("datas","success");
        }
        return data;
    }

    @Override
    public Map<String, Object> setSecret(Map<String, String> params) {

        String id = params.get("id");
        String question1 = params.get("question1");
        String question2 = params.get("question2");
        String question3 = params.get("question3");
        String answer1 = params.get("answer1");
        String answer2 = params.get("answer2");
        String answer3 = params.get("answer3");

        User user = new User();
        user.setId(id);
        SecurityQuestion temp1 = new SecurityQuestion(user,question1,answer1);      //初始化密保问题1
        SecurityQuestion temp2 = new SecurityQuestion(user,question2,answer2);      //初始化密保问题2
        SecurityQuestion temp3 = new SecurityQuestion(user,question3,answer3);      //初始化密保问题3

        List<SecurityQuestion> securityQuestions = new ArrayList<SecurityQuestion>();
        securityQuestions.add(temp1);
        securityQuestions.add(temp2);
        securityQuestions.add(temp3);
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());

        try{
            securityMapper.insertThreeQuestions(securityQuestions);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

}
