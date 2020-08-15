package game;
/*
 *@Author:anran
 *@Date:2020/8/14
 *@Version 1.0
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.websocket.Session;
import java.io.IOException;
import java.security.PublicKey;
import java.util.UUID;

//这个类表示一个房间：两个玩家构成一个房间
public class Room {

    public static class PutChessResponse{
        public String type="putChess";
        public int userId;
        public int row;
        public int col;
        public int winner;
    }

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

    public void putChess(GameAPI.Request request) throws IOException {
        //进行落子
        int chess=request.userId==playerId1?1:2;
        int row=request.row;
        int col=request.col;
        if (chessBoard[row][col]!=0){
            //此时说明该位置已经有子了
            System.out.println("落子位置有误!"+request);
            return;
        }
        chessBoard[row][col]=chess;
        printChessBoard();
        //检查游戏是否结束
        int winner=checkWinner(row,col,chess);
        //把响应写回给客户端
        PutChessResponse response=new PutChessResponse();
        response.userId=request.userId;
        response.row=row;
        response.col=col;
        response.winner=winner;
        //给玩家写数据
        OnlineUserManager manager=OnlineUserManager.getInstance();
        Session session1=manager.getSession(playerId1);
        Session session2=manager.getSession(playerId2);
        //如果玩家掉线，则另一玩家获胜
        if (session1==null){
            response.winner=playerId2;
        }
        if (session2==null){
            response.winner=playerId1;
        }
        String respJson=gson.toJson(response);
        if (session1!=null){
            session1.getBasicRemote().sendText(respJson);
        }
        if (session2!=null){
            session2.getBasicRemote().sendText(respJson);
        }
        //如果胜负已分，销毁房间
        if (response.winner!=0){
            RoomManager.getInstance().removeRoom(roomId);
            System.out.println("游戏结束，房间已经销毁："+roomId);
        }
    }

    private void printChessBoard(){
        System.out.println("打印棋盘信息");
        System.out.println("=================");
        for (int i = 0; i <MAX_ROW ; i++) {
            for (int j = 0; j <MAX_COL ; j++) {
                System.out.print(chessBoard[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    private int checkWinner(int row,int col,int chess) {
        //是否分出胜负
        boolean done=false;
        //检查所有行
        for (int c = col - 4; c <= col; c++) {
            if (c < 0 || c >= MAX_COL) {
                continue;
            }
            if (chessBoard[row][c] == chess
                    && chessBoard[row][c+1] == chess
                    && chessBoard[row][c+2] == chess
                    && chessBoard[row][c+3] == chess
                    && chessBoard[row][c+4] == chess
            ) {
                done = true;
            }
        }
        // 2. 检查所有列
        for (int r = row - 4; r <= row; r++) {
            if (r < 0 || r >= MAX_ROW) {
                continue;
            }
            if (chessBoard[r][col] == chess
                    && chessBoard[r+1][col] == chess
                    && chessBoard[r+2][col] == chess
                    && chessBoard[r+3][col] == chess
                    && chessBoard[r+4][col] == chess
            ) {
                done = true;
            }
        }
        // 3. 判断左对角线
        for (int r = row - 4, c = col - 4; r <= row && c <= col; r++, c++) {
            if (r < 0 || r >= MAX_ROW || c < 0 || c >= MAX_COL) {
                continue;
            }
            if (chessBoard[r][c] == chess
                    && chessBoard[r+1][c+1] == chess
                    && chessBoard[r+2][c+2] == chess
                    && chessBoard[r+3][c+3] == chess
                    && chessBoard[r+4][c+4] == chess
            ) {
                done = true;
            }
        }
        // 4. 判断右对角线
        for (int r = row - 4, c = col + 4; r <= row && c >= col; r++, c--) {
            if (r < 0 || r >= MAX_ROW || c < 0 || c >= MAX_COL) {
                continue;
            }
            if (chessBoard[r][c] == chess
                    && chessBoard[r+1][c-1] == chess
                    && chessBoard[r+2][c-2] == chess
                    && chessBoard[r+3][c-3] == chess
                    && chessBoard[r+4][c-4] == chess
            ) {
                done = true;
            }
        }
        if (!done) {
            return 0;
        }
        return chess == 1 ? playerId1 : playerId2;
    }
}
