package servlet;

import com.Article;
import dao.ArticleDao;
import exception.BusinessException;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author:xiang
 * 添加新文章
 */
@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //文章新增：title、content、userAccout
        InputStream is=req.getInputStream();
        Article article= JSONUtil.deserialize(is,Article.class);
        int num= ArticleDao.insert(article);
        if (num!=1){
            throw new BusinessException("001","插入文章错误");
        }
        return null;
    }
}
