package com.carshop.server.controller;


import com.carshop.server.service.ChatService;
import com.carshop.server.utils.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class ChatController {

    @RequestMapping("/sendMsg")
    @ResponseBody()
    public Map<String, Object> sendOneWebSocket(@RequestParam("sendFrom") String sendFrom,@RequestParam("sendTo") String sendTo,@RequestParam("msg") String msg) {
        //封装返回数据
        Map<String,Object> data=new HashMap<String, Object>();
        data.put("code",Enum.Code.COMMON.getValue());
        data.put("data",ChatService.sendOneMessage(sendFrom,sendTo, msg));
        return data;
    }
}
