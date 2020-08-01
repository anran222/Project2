package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:xiang
 * 统计访问数量
 */
@WebServlet("/count")
public class CountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter pw=resp.getWriter();
        pw.println("<p>synchronized map</p>");
        ConcurrentMap<String,Integer> map1=AbstractBaseServlet.getMAP();
        for (Map.Entry<String,Integer> entry:map1.entrySet()) {
            pw.println("<p>");
            pw.println("路径："+entry.getKey()+"访问量："+entry.getValue());
            pw.println("</p>");
        }
//        pw.println("<p>atomic map</p>");
//        ConcurrentMap<String, AtomicInteger> map2=AbstractBaseServlet.getMAP2();
//        for (Map.Entry<String,AtomicInteger> entry:map2.entrySet()) {
//            pw.println("<p>");
//            pw.println("路径："+entry.getKey()+"访问量："+entry.getValue());
//            pw.println("</p>");
//        }
        pw.flush();
    }
}
