package bughunter.bughunterserver.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * urlPatterns为需要检查的url正则表达式列表。
 * excludedPatterns为需要排除的url正则表达式。
 * urlPatterns与excludedPatterns都为空时检查所有url。
 * 当一者为空时另一个生效。当两者都不为空时，urlPatterns生效。
 * <p>
 * Created by alpaca on 17-6-3.
 */
@Component
public class AuthFilter implements Filter {


    //private String[] excludePaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("initFilter");
//        excludePaths = new String[]{"/api/user/noLogin", "/api/user/tokenError", "/api/user/loginForeground",
//                "/api/user/loginBackground", "/api/user/inCorrectUserId"};
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //这里填写你允许进行跨域的主机ip
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        System.out.println("destroy method");
    }

}
