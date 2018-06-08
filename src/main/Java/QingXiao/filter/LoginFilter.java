package QingXiao.filter;

import QingXiao.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static QingXiao.util.statusCode.DOMAIN;

/**
 * Created by xpb on 2018/5/8.
 */

/*
 * 登录验证拦截器，使用的是这一个
 * */
public class LoginFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void destroy() {
        System.out.println("销毁过滤器方法");
        //logger.info("销毁过滤器方法")
    }

    /*
     response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers",
                "origin, content-type, accept, x-requested-with, sid, mycustom, smuser,Authentication");
    * */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        resp.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
        System.out.println("登录检查过滤器");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        System.out.println("servletPath:" + servletPath);
        System.out.println("requestURI:" + requestURI);
        System.out.println("111开始拦截过滤");

        //判断是否是安卓客户端
        String userAgent = request.getHeader("User-Agent");
        System.out.println("User-Agent: " + userAgent);
        if (userAgent.contains("QingXiao")) {//是安卓客户端端访问的
            System.out.println("是通过安卓端访问的");
            String accessToken = request.getHeader("accessToken");
            if (accessToken == null || "".equals(accessToken)) {
                System.out.println("请求头为空");//说明是登陆前的操作，直接放行
                filterChain.doFilter(request, response);
                return;
            } else {
                System.out.println("请求头不为空，跳转到");
            }
        } else {//是网页端访问的
            //先判断路径，如果是index/index.jsp，则直接返回到登录页
            if (requestURI.contains("/login.jsp")
                    || requestURI.contains("/register.jsp")
                    || requestURI.contains(".css")
                    || requestURI.contains(".js")
                    || requestURI.contains(".jpg")
                    || requestURI.contains("/LoginWeb")
                    || requestURI.contains("/Register")
                    ||requestURI.contains("/GetVerifyCode")
                    || requestURI.contains(".ico")
                    || requestURI.contains(".ttf")
                    || requestURI.contains(".woff")
                    || requestURI.contains(".woff2")) {
                System.out.println("转到请求页");
                filterChain.doFilter(request, response);
                return;
            }
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            if (cookies != null) {
                System.out.println("cookies.length:" + cookies.length);
                for (int i = 0; i < cookies.length; i++) {
                    System.out.println("Cookie name:" + cookies[i].getName());
                    System.out.println("Cookie value:" + cookies[i].getValue());
                    if (cookies[i].getName().equals("username")) {
                        cookie = cookies[i];
                        System.out.println("cookies1111");
                        //调用doFilter方法，执行下一个Filter或Servlet
                    }
                }
                System.out.println("cookies22225555");

                System.out.println("cookies33335555");
                if (cookie != null) {
                    if (cookie.getName().equals("username")) {
                        System.out.println("cookies3333");
                        filterChain.doFilter(request, response);

                    } else {
                        System.out.println("cookie error.");
                        response.sendRedirect("http://" + DOMAIN + "/login.jsp");
                        return;
                    }
                } else {
                    System.out.println("cookie error.");
                    response.sendRedirect("http://" + DOMAIN + "/login.jsp");
                    return;
                }
            } else {
                System.out.println("No cookie.");
                response.sendRedirect("http://" + DOMAIN + "/login.jsp");
                response.setHeader("result", "1");
                response.addHeader("Content-Type", "application/json; charset=utf-8");
                response.addHeader("Accept-Encoding", "gzip");
                response.setContentType("text/plain;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                Map<String, Object> map = new HashMap<>();
                map.put("RESULT_KEY", 1);
                String result = JSON.toJSONString(map);
                System.out.println("结果为" + result);
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(result);
                printWriter.close();
                return;
                //request.getRequestDispatcher("/QingXiao/app/jsp/login.jsp").forward(request,response);
                // request.getRequestDispatcher("/QingXiao/wEditor.html").forward(request,response);

            }
        }
        System.out.println("111拦截过滤结束");
        System.out.println("登录拦截过滤结束");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化过滤器的方法");
        //logger.info("初始化过滤器的方法");
        this.filterConfig = config;
    }

}


