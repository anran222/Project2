package dao;

import com.Article;
import exception.SystemException;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:xiang
 */
//jdbc数据库文章操作
public class ArticleDao {

    //显示文章列表
    public static List<Article> list() {
        Connection c= DBUtil.getConnection();
        PreparedStatement p=null;
        ResultSet r=null;
        try {
            List<Article> articles=new ArrayList<>();
            String sql="select id,title,content,user_id,create_time from article";
            p=c.prepareStatement(sql);
            r=p.executeQuery();
            while (r.next()){
                Article article=new Article();
                article.setId(r.getInt("id"));
                article.setTitle(r.getString("title"));
                article.setContent(r.getString("content"));
                article.setUserId(r.getInt("user_id"));
                article.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
                articles.add(article);
            }
            return articles;
        } catch (SQLException e) {
            throw new SystemException("000","查询文章列表出错",e);
        }
        finally {
            DBUtil.close(c,p,r);
        }
    }

    //根据用户表的name（传入userAccout）
    //添加新文章
    public static int insert(Article article) {
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        try {
            String sql="insert into article (user_id, title, content, create_time)" +
                    "   select id, ?,?,?" +
                    "   from user where name = ?";
            ps=c.prepareStatement(sql);
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setTimestamp(3,new Timestamp(new Date().getTime()));
            ps.setString(4,article.getUserAccout());
            return ps.executeUpdate();
        }catch (SQLException e){
            throw new SystemException("002","新增文章出错");
        }finally {
            DBUtil.close(c,ps);
        }
    }

    //查询文章详情
    public static Article query(Integer articleId) {
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        ResultSet r=null;
        Article article=null;
        try {
            String sql="select id,title,content,user_id,create_time"+
                    "    from article where id=?";
            ps=c.prepareStatement(sql);
            ps.setInt(1,articleId);
            r=ps.executeQuery();
            while (r.next()){
                article=new Article();
                article.setId(articleId);
                article.setTitle(r.getString("title"));
                article.setContent(r.getString("content"));
                article.setUserId(r.getInt("user_id"));
                article.setCreateTime(new Date(r.getTimestamp("create_time").getTime()));
            }
            return article;
        } catch (SQLException e) {
            throw new SystemException("003","查询文章详情出错",e);
        } finally {
            DBUtil.close(c,ps,r);
        }
    }

    //修改文章
    public static int update(Article article) {
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        try {
            String sql="update article set title=?,content=? where id=?";
            ps=c.prepareStatement(sql);
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getContent());
            ps.setInt(3,article.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SystemException("004","修改文章出错",e);
        } finally {
            DBUtil.close(c,ps);
        }

    }

    //删除文章
    public static int delete(int[] ids) {
        Connection c=DBUtil.getConnection();
        PreparedStatement ps=null;
        try {
            StringBuilder sql=new StringBuilder("delete from article where id in (");
            for (int i = 0; i <ids.length ; i++) {
                if (i>0){
                    sql.append(",");
                }
                sql.append("?");
            }
            sql.append(")");
            ps=c.prepareStatement(sql.toString());
            for (int i = 0; i <ids.length ; i++) {
                ps.setInt(i+1,ids[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SystemException("005","删除文章出错",e);
        } finally {
            DBUtil.close(c,ps);
        }
    }
}
