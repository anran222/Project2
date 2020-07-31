package servlet;

import dao.BuildingDao;
import model.DictionaryTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/12 15:40
 */
@WebServlet("/building/queryAsDict")
public class BuildingQueryServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<DictionaryTag> tags= BuildingDao.query();
        return tags;
    }
}
