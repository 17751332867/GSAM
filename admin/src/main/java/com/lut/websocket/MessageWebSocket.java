package com.lut.websocket;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@ServerEndpoint(value = "/messageSocket/{userId}")
public class MessageWebSocket {

    private static final Logger logger = LoggerFactory.getLogger(MessageWebSocket.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * key: userId value: sessionIds
     */
    private static ConcurrentHashMap<Integer, Session> userSessionMap =  new ConcurrentHashMap<>();

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, MessageWebSocket> websocketMap = new ConcurrentHashMap<>();

    /**
     * key: sessionId value: userId
     */
    private static ConcurrentHashMap<String, Integer> sessionUserMap = new ConcurrentHashMap<>();

    /**
     * 当前连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
//        System.out.println(session.getId());
//        System.out.println(applicationContext);
        try {
            this.session = session;
            session.getBasicRemote().sendText(session.getId());
            String sessionId = session.getId();
            //建立userId和sessionId的关系
            if(userSessionMap.containsKey(userId)) {
                userSessionMap.remove(userId);
                userSessionMap.put(userId,session);
            }else{
                userSessionMap.put(userId, session);
            }
            sessionUserMap.put(sessionId, userId);
            //建立sessionId和websocket引用的关系
            if(!websocketMap.containsKey(sessionId)){
                websocketMap.put(sessionId, this);
                addOnlineCount();           //在线数加1
            }
            System.out.println(websocketMap);
            System.out.println(sessionUserMap);
            System.out.println(userSessionMap);
        }catch (Exception e){
            logger.error("连接失败");
//            String es = ExceptionUtils.getFullStackTrace(e);
//            logger.error(es);
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String sessionId = this.session.getId();
        //移除userId和sessionId的关系
        Integer userId = sessionUserMap.get(sessionId);
        sessionUserMap.remove(sessionId);
        if(userId != null) {
            userSessionMap.remove(userId);

        }
        //移除sessionId和websocket的关系
        if (websocketMap.containsKey(sessionId)) {
            websocketMap.remove(sessionId);
            subOnlineCount();           //在线数减1
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param messageStr 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String messageStr, Session session, @PathParam("userId") Integer userId) throws IOException {
   
    }

    /**
     *
     * @param session
     * @param error 当连接发生错误时的回调
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();;
//        logger.error(es);
    }


    /**
     * 实现服务器主动推送
     */
    public Session getSocket(Integer userId){
        if(userId != null){
            Session session = userSessionMap.get(userId);
            return session;
        }else{
            logger.error("未找到接收用户连接，该用户未连接或已断开");
        }
        return null;
    }

    @SneakyThrows
    public void sendMessage(String message, MessageWebSocket socket) throws IOException {
        if(message.trim().length()==0){
            socket.session.getBasicRemote().sendText(message);
        }
    }


    public void sendMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(message);
    }

     /**
    *获取在线人数
    */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
     /**
    *在线人数加一
    */
    public static synchronized void addOnlineCount() {
        MessageWebSocket.onlineCount++;
    }
    /**
    *在线人数减一
    */
    public static synchronized void subOnlineCount() {
        MessageWebSocket.onlineCount--;
    }
}