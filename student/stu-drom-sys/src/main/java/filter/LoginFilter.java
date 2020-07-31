package filter;

import com.sun.net.httpserver.HttpServer;
import model.Response;
import util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;

/**
 * @Author:xiang
 * @Date:2020/7/15 19:15
 */
//过滤器：http请求的url匹配过滤器路径规则，才会过滤（调用filter的方法）
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain Chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)Request;
        HttpServletResponse res=(HttpServletResponse)Response;
        /*页面的静态资源，后台服务
        *需要处理的敏感资源:
        *1、首页：/public/page/main.html,没有登录重定向到登录界面
        *2、后端服务资源：除登陆接口/user/login以外，其他接口，没有登录的时候返回没有登录的json信息
         */
        //获取session信息
        HttpSession session=req.getSession(false);
        if (session==null){//没有登录
            //获取当前http请求的路径
            String uri=req.getServletPath();
            if ("/public/page/main.html".equals(uri)){//首页没有登录就重定向
                String schema=req.getScheme();
                String host=req.getServerName();
                int port=req.getServerPort();
                String contextPath=req.getContextPath();
                String basePath=schema+"://"+host+":"+port+contextPath;
                res.sendRedirect(basePath+"/public/index.html");
                return;
            }else if (!"/user/login".equals(uri)&&!uri.startsWith("/public/")&&!uri.startsWith("/static/")){
                req.setCharacterEncoding("utf-8");//设置请求数据的编码格式
                res.setCharacterEncoding("utf-8");//设置响应数据的编码格式
                res.setContentType("application/json");//设置响应的数据格式
                Response r=new Response();
                r.setCode("301");
                r.setMessage("未授权的http请求");
                PrintWriter pw=res.getWriter();
                pw.println(JSONUtil.write(r));
                pw.flush();
                return;
            }
        }
        Chain.doFilter(Request,Response);//过滤器向下调用，再次过滤
    }

    @Override
    public void destroy() {

    }
}
