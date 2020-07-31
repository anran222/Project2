package dao;

import model.Student;
import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/15 18:50
 */
public class UserDao {
    public static User query(User user) {
        User queryUser=null;
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            c= DBUtil.getConnection();
            String sql="select id,nickname,email from user where username=? and password=?";
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps=c.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                queryUser=user;
                queryUser.setId(rs.getInt("id"));
                queryUser.setNickname(rs.getString("nickname"));
                queryUser.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            throw new RuntimeException("登录检验用户名密码出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
        return queryUser;
    }
}
