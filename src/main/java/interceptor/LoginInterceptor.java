package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author XQL
 * @version 1.0.0
 * @ClassName LoginInterceptor.java
 * @Description 登陆拦截器
 * @createTime 2022年04月05日 19:54:00
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*
        preHandle方法如果返回值为true代表放行，如果返回false代表不放行
         */
        Object user = httpServletRequest.getSession().getAttribute("user");
        System.out.println("preHandle getRemoteAddr=" + httpServletRequest.getRemoteAddr());
        System.out.println("preHandle getLocalAddr=" + httpServletRequest.getLocalAddr());
        System.out.println("preHandle getRequestURL=" + httpServletRequest.getRequestURL());
        System.out.println("preHandle getRequestURI=" + httpServletRequest.getRequestURI());
        System.out.println(o);
        System.out.println("user=" + user);
        if(user == null) {
            httpServletResponse.sendRedirect("/html/login.html");  // 这种演示要保证跳转的页面在当前服务器中
            // {code:250, msg:"请登录之后再访问", url:"/login.html"}
            return false;
        }
        else {
            return true;    // 放行
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
