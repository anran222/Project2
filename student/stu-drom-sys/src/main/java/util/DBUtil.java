package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author:xiang
 * @Date:2020/7/12 9:56
 * 数据库连接工具类
 */
public class DBUtil {

    private static volatile DataSource DS;
    private static final String URL="jdbc:mysql://localhost:3306/stu_dorm?useUnicode=true&characterEncoding=UTF-8";
    private static final String USENAME="root";
    private static final String PASSWORD="";

    private DBUtil(){}

    private static DataSource getDS(){
        if (DS==null){
            synchronized (DBUtil.class){
                if (DS==null){
                    DS=new MysqlDataSource();
                    ((MysqlDataSource) DS).setURL(URL);
                    ((MysqlDataSource) DS).setUser(USENAME);
                    ((MysqlDataSource) DS).setPassword(PASSWORD);
                }
            }
        }
        return DS;
    }

    public static Connection getConnection(){
        try {
            return getDS().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败");
        }
    }

    public static void close(Connection c,Statement s){
        close(c,s,null);
    }

    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if (r!=null){
                r.close();
            }
            if (s!=null){
                s.close();
            }
            if (c!=null){
                c.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("释放数据库资源失败",e);
        }
    }
}
