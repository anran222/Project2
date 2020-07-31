package servlet;

import dao.StudentDao;
import model.Page;
import model.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:xiang
 * @Date:2020/7/12 14:35
 */
@WebServlet("/student/query")
public class StudentQueryServlet extends AbstractBaseServlet{
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Page p=Page.parse(req);
        List<Student> students= StudentDao.query(p);
        return students;
    }
}
