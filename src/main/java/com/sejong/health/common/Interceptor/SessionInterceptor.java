package com.sejong.health.common.Interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;


public class SessionInterceptor implements HandlerInterceptor {

    private List<String> excludeUrls = List.of(
            "/open-api/*",
            "/index",
            "/css/**",
            "/js/**"
    );

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sessionId") == null) {
            response.sendRedirect("/open-api/login");
            return false;
        }
        return true;

    }
}
