package servlet;

import dao.StudentDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:xiang
 * @Date:2020/7/13 22:07
 */
@WebServlet("/student/delete")
public class StudentDeleteServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String[] ids=req.getParameterValues("ids");
        int num= StudentDao.delete(ids);
        return null;
    }
}
