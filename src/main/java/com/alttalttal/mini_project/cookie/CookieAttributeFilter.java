//package com.alttalttal.mini_project.cookie;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//
//import java.io.IOException;
//import java.util.Collection;
//
//public class CookieAttributeFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        chain.doFilter(request, response);
//        addSameSite(httpServletResponse, "");
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException{
//    }
//
//    @Override
//    public void destroy(){
//    }
//
//    private void addSameSite(HttpServletResponse response, String sameSite){
//        Collection<String> headers = response.getHeaders(httpHeaders.SET_COOKIE);
//        boolean firstHeader = true;
//        for(String header : headers){
//            if(firstHeader){
//                response.setHeader(httpHeader.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + sameSite));
//                firstHeader = false;
//                continue;
//            }
//            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + sameSite));
//        }
//    }
//
//}
