package com.kr.jikim.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CustomGenerFilter extends GenericFilterBean {

    private static final Logger log = LoggerFactory.getLogger(CustomGenerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("CustomGenerFilter doFilter");
        filterChain.doFilter(request, response);
    }
}
