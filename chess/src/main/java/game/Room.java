package game;
/*
 *@Author:anran
 *@Date:2020/8/14
 *@Version 1.0
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.UUID;

//这个类表示一个房间：两个玩家构成一个房间
public class Room {

    private String roomId;
    private int playerId1;
    private int playerId2;

    //二位数组表示棋盘状态
    private static final int MAX_ROW=15;
    private static final int MAX_COL=15;
    //为0表示该位置是空的，为1表示该位置是玩家1的子，为2表示是玩家2的子
    private int[][] chessBoard=new int[MAX_ROW][MAX_COL];

    private Gson gson=new GsonBuilder().create();

    public Room(){
        //生成房间id，房间id是唯一的
        roomId= UUID.randomUUID().toString();
    }

    public int getPlayerId1() {
        return playerId1;
    }

    public void setPlayerId1(int playerId1) {
        this.playerId1 = playerId1;
    }

    public int getPlayerId2() {
        return playerId2;
    }

    public void setPlayerId2(int playerId2) {
        this.playerId2 = playerId2;
    }

    public String getRoomId() {
        return roomId;
    }
}
