package com.xk.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if(uri.equals("/login")){
            Date now = new Date();
            String userAgent = request.getHeader("User-Agent");
            System.out.println("使用者於 "+ now +" 嘗試從 " +userAgent+" 登入" );
        }

        filterChain.doFilter(request,response);
    }
}
