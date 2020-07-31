package servlet;

import dao.BuildingDao;
import dao.DormDao;
import model.DictionaryTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/12 15:40
 */
@WebServlet("/dorm/queryAsDict")
public class DormQueryServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String key=req.getParameter("dictionaryKey");
        List<DictionaryTag> tags= DormDao.query(Integer.parseInt(key));
        return tags;
    }
}
