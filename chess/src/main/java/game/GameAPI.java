package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/*
 *@Author:anran
 *@Date:2020/8/13
 *@Version 1.0
 */
//处理websocket的相关通信逻辑
@ServerEndpoint(value = "/game/{userId}")
public class GameAPI {

    //表示服务器收到的websocket请求
    static class Request{
        public String type;
        public int userId;
        public String roomId;
        public int row;
        public int col;
    }

    private int userId;

    @OnOpen
    public void onOpen(@PathParam("userId") String userIdStr, Session session){
        userId=Integer.parseInt(userIdStr);
        System.out.println("玩家建立连接："+userId);

        //把玩家加入到在线玩家列表中
        OnlineUserManager.getInstance().onLine(userId,session);
    }

    @OnClose
    public void onClose(){
        System.out.println("玩家断开连接："+userId);

        //把玩家从在线玩家列表中删除
        OnlineUserManager.getInstance().offLine(userId);
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("玩家断开连接："+userId);

        //把玩家从在线玩家列表中删除
        OnlineUserManager.getInstance().offLine(userId);
        error.printStackTrace();
    }

    //可能是匹配请求，也可能是落子请求
    //根据请求的type类型来区分
    //如果type是startMatch，处理匹配请求
    //如果type是puChess，处理落子请求
    @OnMessage
    public void onMessage(String message,Session session) throws InterruptedException {
        System.out.printf("收到玩家%d的消息：%s\n",userId,message);

        //实例化Gson对象
        Gson gson=new GsonBuilder().create();
        //将json格式的字符串转换为Request对象
        Request request= gson.fromJson(message,Request.class);
        //后续的处理请求的逻辑
        if(request.type.equals("startMatch")){
            //执行匹配逻辑
            //引入新的数据结构，实现一个匹配队列
            Matcher.getInstance().addMatchQueue(request);
        }else if (request.type.equals("putChess")){
            //执行落子请求
        }else {
            System.out.println("非法的type的值！"+request.type);
        }
    }
}
