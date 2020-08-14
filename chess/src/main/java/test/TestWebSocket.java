package test;
/*
 *@Author:anran
 *@Date:2020/8/12
 *@Version 1.0
 */

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/webSocketTest/{userId}")
public class TestWebSocket {

    private int userId;

    //是在客户端建立连接时调用
    //类似于servlet中的doGet/doPost
    //通过@PathParam引入userId
    //session是Websocket中的session
    @OnOpen//没有这个注解，onOpen无法被正确调用
    public void onOpen(@PathParam("userId") String userIdStr, Session session){//连接建立时自动被调用
        System.out.println("建立连接："+userIdStr);
        this.userId=Integer.parseInt(userIdStr);

        //创建一个线程，通过这个线程，循环反复的给客户端发送数据
        Thread t=new Thread(){
            @Override
            public void run() {
                while (true){
                    String resp=""+System.currentTimeMillis();
                    try {
                        session.getBasicRemote().sendText(resp);
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    @OnClose
    public void onClose(){//连接断开的时候被自动调用
        System.out.println("断开连接："+userId);
    }

    @OnError
    public void onError(Session session,Throwable error){//连接异常时被自动调用
        System.out.println("连接异常："+userId);
        System.out.println(error.getMessage());
    }

    @OnMessage
    public void onMessage(String message,Session session) throws Exception{//收到消息时被自动调用
        System.out.println("收到消息：message="+message);
        String resp=""+System.currentTimeMillis();
        session.getBasicRemote().sendText(resp);
    }
}
