package com.tusofia.todos.filter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Exclude login and public endpoints
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.startsWith("/auth/login") || requestURI.startsWith("/styles/styles.css")) {
            chain.doFilter(request, response); // Allow request to proceed
            return;
        }

        // Check if user is logged in
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            httpResponse.sendRedirect("/auth/login"); // Redirect to login page
            return;
        }

        chain.doFilter(request, response); // Continue to the next filter or endpoint
    }

    @Override
    public void destroy() {
        
    }
}
