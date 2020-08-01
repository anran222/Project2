package servlet;

import com.Article;
import dao.ArticleDao;
import exception.BusinessException;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Author:xiang
 * 修改文章
 */
@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //文章修改：id、title、content
        InputStream is=req.getInputStream();
        Article article= JSONUtil.deserialize(is,Article.class);
        System.out.println("======================\n"+article);
        int num= ArticleDao.update(article);
        if (num!=1){
            throw new BusinessException("004","文章修改错误");
        }
        return null;
    }
}
