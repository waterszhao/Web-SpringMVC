package com.waters.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    private String filterName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterName = filterConfig.getFilterName();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(filterName);
        HttpServletRequest req = (HttpServletRequest) request;
        if (isLogin(req)){
            System.out.println("已登录");
            chain.doFilter(request, response);
        }else {
            System.out.println("未登录");
            ((HttpServletResponse) response).sendRedirect("/user/toLogin");
        }
    }

    private boolean isLogin(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userID")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
