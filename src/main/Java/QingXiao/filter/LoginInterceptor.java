package QingXiao.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static QingXiao.util.statusCode.DOMAIN;

/**
 * Created by xpb on 2018/5/8.
 */
@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

    //@Autowired
   // public WebTokenService tokenService;
    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest httpRequest = request;
        final HttpServletResponse httpResponse = response;
        final String authHeaderVal = httpRequest.getHeader("token");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Cookie[] cookies = request.getCookies();
        Cookie cookie=null;
        if(cookies != null){
            for(int i = 0 ; i < cookies.length ; i++){
                System.out.println("Cookie name:" + cookies[i].getName());
                System.out.println("Cookie value:" + cookies[i].getValue());
                if(cookies[i].getName().equals("username")) {
                    cookie = cookies[i];
                    //调用doFilter方法，执行下一个Filter或Servlet

                }
            }
            if(!cookie.equals(null)) {
                if (cookie.getName().equals("username")) {
                   // filterChain.doFilter(request, response);
                }
            }
        }else{
            System.out.println("No cookie.");
            response.sendRedirect("http://"+DOMAIN+"/QingXiao/app/index/login.jsp");
            response.setHeader("result","1");
            response.addHeader("Content-Type","application/json; charset=utf-8");
            response.addHeader("Accept-Encoding","gzip");
            response.setContentType("text/plain;charset=utf-8" );
            response.setCharacterEncoding("UTF-8");
            Map<String, Object> map = new HashMap<>();
            map.put("RESULT_KEY", 1);
            String result = JSON.toJSONString(map);
            System.out.println("结果为" + result);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(result);
            printWriter.close();
            //request.getRequestDispatcher("/QingXiao/app/jsp/login.jsp").forward(request,response);
            // request.getRequestDispatcher("/QingXiao/wEditor.html").forward(request,response);

        }

        System.out.println("========"+""+"===>LoginInterceptor preHandle 没加验证注解放行");
        return true;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor postHandle");
    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor afterCompletion");
    }
}
