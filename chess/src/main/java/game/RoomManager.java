package game;
/*
 *@Author:anran
 *@Date:2020/8/14
 *@Version 1.0
 */

import java.util.concurrent.ConcurrentHashMap;

//单例类，管理若干个房间
public class RoomManager {

    //使用hash表将房间信息保存起来
    private ConcurrentHashMap<String,Room> rooms=new ConcurrentHashMap<>();

    public void addRoom(Room room){
        rooms.put(room.getRoomId(),room);
    }

    public void removeRoom(String roomId){
        rooms.remove(roomId);
    }

    public Room getRoom(String roomId){
        return rooms.get(roomId);
    }

    private static volatile RoomManager instance=null;

    private RoomManager(){}

    public static RoomManager getInstance(){
        if(instance==null){
            synchronized (RoomManager.class){
                if (instance==null){
                    instance=new RoomManager();
                }
            }
        }
        return instance;
    }
}
