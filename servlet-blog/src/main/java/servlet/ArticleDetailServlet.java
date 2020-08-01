package servlet;

import com.Article;
import dao.ArticleDao;
import exception.BusinessException;
import exception.ClientException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author:xiang
 * 文章详情
 */
@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求中的数据的id
        String id=req.getParameter("id");
        Integer articleId;
        try {
            articleId= Integer.parseInt(id);
        }catch (Exception e){
            throw new ClientException("001","请求参数错误：[id="+id+"]");
        }
        Article article= ArticleDao.query(articleId);
        if (article==null){
            throw new BusinessException("002","查询不到文章详情id="+articleId);
        }
        return article;
    }
}
