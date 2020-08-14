package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/*
 *@Author:anran
 *@Date:2020/8/14
 *@Version 1.0
 */
//实现匹配功能，内部管理一个匹配队列
public class Matcher {

    private Gson gson=new GsonBuilder().create();

    public static class MatchResponse{
        public String type;
        public String roomId;
        public boolean isWhite;
        public int otherUserId;
    }

    //实现一个匹配队列
    private BlockingQueue<GameAPI.Request> queue=new LinkedBlockingDeque<>();

    //插入阻塞队列
    public void addMatchQueue(GameAPI.Request request) throws InterruptedException {
        queue.put(request);
    }

    //创建一个扫描线程，进行匹配
    private Matcher(){
        Thread t=new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        handlerMatch();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private void handlerMatch() throws IOException {
        //实现一次匹配
        try {
            GameAPI.Request player1=queue.take();
            GameAPI.Request player2=queue.take();
            System.out.println("匹配到两个玩家："+player1.userId+","+player2.userId);
            OnlineUserManager manager=OnlineUserManager.getInstance();
            Session session1=manager.getSession(player1.userId);
            Session session2=manager.getSession(player2.userId);
            if (session1==null){
                queue.put(player2);
                System.out.println("玩家2 不在线");
                return;
            }
            if (session2==null){
                queue.put(player1);
                System.out.println("玩家1 不在线");
                return;
            }
            if (session1==session2){
                queue.put(player1);
                System.out.println("自己匹配到自己!!");
                return;
            }
            Room room=new Room();
            room.setPlayerId1(player1.userId);
            room.setPlayerId2(player2.userId);
            RoomManager.getInstance().addRoom(room);
            System.out.println("玩家进入房间成功! roomId: " + room.getRoomId());
            MatchResponse response1=new MatchResponse();
            response1.type="startMatch";
            response1.roomId=room.getRoomId();
            response1.isWhite=true;
            response1.otherUserId=player2.userId;
            String respJson1=gson.toJson(response1);
            session1.getBasicRemote().sendText(respJson1);
            System.out.println("给玩家1 响应: " + respJson1);
            MatchResponse response2=new MatchResponse();
            response2.type="startMatch";
            response2.roomId=room.getRoomId();
            response2.isWhite=false;
            response2.otherUserId=player1.userId;
            String respJson2= gson.toJson(response2);
            session2.getBasicRemote().sendText(respJson2);
            System.out.println("给玩家2 响应: " + respJson2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static volatile Matcher instance=null;

    public static Matcher getInstance(){
        if (instance==null){
            synchronized (Matcher.class){
                if (instance==null){
                    instance=new Matcher();
                }
            }
        }
        return instance;
    }
}
