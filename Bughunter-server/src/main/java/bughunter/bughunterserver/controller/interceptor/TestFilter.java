//package bughunter.bughunterserver.controller.interceptor;
//
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/*")
//public class TestFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse resp = (HttpServletResponse)servletResponse;
//        //"*"存在风险，建议指定可信任的域名来接收响应信息，如"http://www.sosoapi.com"
//        resp.addHeader("Access-Control-Allow-Origin", "*");
//        //如果存在自定义的header参数，需要在此处添加，逗号分隔
//        resp.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, "
//                + "If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, "
//                + "Content-Type, X-E4M-With");
//        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//        resp.addHeader("Access-Control-Allow-Credentials", "true");
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
