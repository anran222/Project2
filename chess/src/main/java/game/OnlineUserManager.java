package game;
/*
 *@Author:anran
 *@Date:2020/8/13
 *@Version 1.0
 */

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

//通过这个类管理在线用户
public class OnlineUserManager {

    //使用一个hash表来保存在线用户信息，使用ConcurrentHashMap保证线程安全
    //key就是userId，value就是该用户对应的websocket的session
    private ConcurrentHashMap<Integer, Session> users=new ConcurrentHashMap<>();

    public void onLine(int userId,Session session){
        users.put(userId,session);
    }

    public void offLine(int userId){
        users.remove(userId);
    }

    public Session getSession(int userId){
        return users.get(userId);
    }

    private static volatile OnlineUserManager instance=null;

    private OnlineUserManager(){}

    public static OnlineUserManager getInstance(){
        if (instance==null){
            synchronized (OnlineUserManager.class){
                if (instance==null){
                    instance=new OnlineUserManager();
                }
            }
        }
        return instance;
    }
}
