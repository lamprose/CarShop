package com.carshop.server.interceptor;

import com.carshop.server.dao.AdminMapper;
import com.carshop.server.dao.UserMapper;
import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.AdminService;
import com.carshop.server.service.UserService;
import net.minidev.json.JSONObject;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Interceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //第一次预检请求都过
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        setCrossDomain(response);
        return checkToken(request,response);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

    public boolean checkToken(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("Token");
        Map<String,Object> map=new HashMap<>();

        if(token==null){
            System.out.println("已经进行拦截："+request.getRequestURI());
            map.put("code",Enum.Code.ERR_TOKEN.getValue());
            returnMessage(response,JSONObject.toJSONString(map));
            return false;
        }
        User user = userMapper.selectOneByToken(token);
        Shops shop = adminMapper.selectOneByToken(token);
        if(user==null&&shop==null){
            System.out.println("已经进行拦截："+request.getRequestURI());
            map.put("code",Enum.Code.ERR_TOKEN.getValue());
            returnMessage(response,JSONObject.toJSONString(map));
            return false;
        }
        return true;
    }

    public void returnMessage(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public void setCrossDomain(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST,GET");
        response.addHeader("Access-Control-Allow-Credentials", "true");
    }
}
