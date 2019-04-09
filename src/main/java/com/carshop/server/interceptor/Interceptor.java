package com.carshop.server.interceptor;

import com.carshop.server.domain.Shops;
import com.carshop.server.domain.User;
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
import java.io.OutputStream;
import java.io.PrintWriter;

public class Interceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        //第一次预检请求都过
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        String token = request.getHeader("Token");
        if(request.getHeader("Token")==null){
            System.out.println("已经进行拦截："+request.getRequestURI());
           return false;
        }

        User user = userService.selectOneByToken(token);
        Shops shop = adminService.selectOneByToken(token);
        if(user==null&&shop==null)
            return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

}
