package com.xk.upms.filter;

import com.xk.common.base.Common;
import com.xk.common.util.XkJwtUtil;
import com.xk.upms.model.dto.JwtUserDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (Arrays.stream(Common.PERMIT_ALL).anyMatch(pattern -> pathMatcher.match(pattern, uri))) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(Common.HEADER);
        if(StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(Common.TOKEN)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorizationHeader.substring(7);
        String userName = XkJwtUtil.extractUsername(token);
        if(StringUtils.isBlank(userName)){
            filterChain.doFilter(request, response);
            return;
        }
        JwtUserDTO jwtUserDTO = XkJwtUtil.parseJwtUserFromToken(token);
        if(null != userName && null == SecurityContextHolder.getContext().getAuthentication()){
            if(XkJwtUtil.isTokenValid(token, jwtUserDTO)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        jwtUserDTO,
                        null,
                        jwtUserDTO.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
