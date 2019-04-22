package com.carshop.server.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{shopId}")
@Component
public class
ChatService {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的CumWebSocket对象。
     */
    private static CopyOnWriteArraySet<ChatService> webSocketSet = new CopyOnWriteArraySet<ChatService>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private static Map<String,Session> sessionPool = new HashMap<String,Session>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,@PathParam(value="shopId")String shopId) {
        if(!sessionPool.containsKey(shopId)){
            this.session = session;
            //加入set中
            webSocketSet.add(this);
            sessionPool.put(shopId, session);
            //添加在线人数
            System.out.println("新连接id:"+shopId+"接入。当前在线人数为：" + getOnlineCount());
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam(value="shopId")String shopId) {
        //从set中删除
        webSocketSet.remove(this);
        sessionPool.remove(shopId);
        System.out.println("有连接关闭。当前在线人数为：" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("客户端发送的消息：" + message);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("----websocket-------有异常啦");
        error.printStackTrace();
    }

    /**
     * 当前在线人数
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return sessionPool.size();
    }

    /**
     * 发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        //获取session远程基本连接发送文本消息
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发
     *
     * @param message
     */
    private static void sendAll(String message) {
        Arrays.asList(webSocketSet.toArray()).forEach(item -> {
            ChatService ChatService = (ChatService) item;
            //群发
            try {
                ChatService.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 暴露给外部的群发
     *
     * @param message
     * @throws IOException
     */
    public static void sendInfo(String message) throws IOException {
        sendAll(message);
    }

    // 此为单点消息
    public static String sendOneMessage(String sendFrom ,String sendTo, String message) {
        Session sessionTo = sessionPool.get(sendTo);
        System.out.println(sendFrom+"#"+sendTo+"#"+message);
        if (sessionTo != null) {
            System.out.println(sessionTo);
            try {
                sessionTo.getAsyncRemote().sendText(sendFrom+"&&"+message);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else
            return "offline";
        return "error";
    }
}
