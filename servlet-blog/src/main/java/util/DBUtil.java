package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import exception.SystemException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author:xiang
 * JDBC连接工具类
 */
public class DBUtil {

    private static final String URL="jdbc:mysql://localhost:3306/blog?useUnicode=true&amp;characterEncoding=utf-8";
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static volatile DataSource DS;

    private static DataSource getDataSorce(){
        if (DS==null){
            synchronized (DBUtil.class){
                if (DS==null){
                    DS=new MysqlDataSource();
                    ((MysqlDataSource) DS).setURL(URL);
                    ((MysqlDataSource) DS).setUser(USERNAME);
                    ((MysqlDataSource) DS).setPassword(PASSWORD);
                }
            }
        }
        return DS;
    }

    //获取数据库连接
    public static Connection getConnection(){
        try {
            return getDataSorce().getConnection();
        } catch (SQLException e) {
            throw new SystemException("000","获取数据库连接失败",e);
        }
    }

    //释放资源：连接、处理
    public static void close(Connection c, Statement s){
        close(c,s,null);
    }

    //释放资源：连接、处理、结果集
    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if (c!=null){
                c.close();
            }
            if (s!=null){
                s.close();
            }
            if (r!=null){
                r.close();
            }
        } catch (SQLException e) {
           throw new SystemException("000","释放资源失败",e);
        }
    }
}
