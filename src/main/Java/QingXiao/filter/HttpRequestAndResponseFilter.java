package QingXiao.filter;

/**
 * Created by xpb on 2018/4/15.
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpRequestAndResponseFilter implements Filter {
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
        System.out.println("HttpRequestAndResponseFilterBefore");
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String servletPath= request.getServletPath();
        String requestURI=request.getRequestURI();
        System.out.println("servletPath:"+servletPath);
        System.out.println("requestURI:"+requestURI);
        System.out.println("111开始拦截过滤");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",
                "origin, content-type, accept, x-requested-with, sid, mycustom, smuser,Authentication");

        System.out.println("111正在拦截过滤");
        //调用doFilter方法，执行下一个Filter或Servlet
        filterChain.doFilter(request, response);
        System.out.println("111拦截过滤结束");
        System.out.println("HttpRequestAndResponseFilterAfter");
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化过滤器的方法");
        //logger.info("初始化过滤器的方法");
        this.filterConfig = config;
    }

}
