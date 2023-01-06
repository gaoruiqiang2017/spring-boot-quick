package com.rest.template.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author he peng
 * @date 2018/10/2
 */

//@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("开始");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        BodyCachingHttpServletRequestWrapper requestWrapper =
                new BodyCachingHttpServletRequestWrapper((HttpServletRequest) request);

        byte[] requestBody = requestWrapper.getBody();
        String s = new String(requestBody);

        // TODO do something
        BodyCachingHttpServletResponseWrapper responseWrapper =
                new BodyCachingHttpServletResponseWrapper((HttpServletResponse) response);

        chain.doFilter(requestWrapper , responseWrapper);

        byte[] responseBody = responseWrapper.getBody();
        String s1 = new String(responseBody);
        // TODO do something
        System.out.printf("s=="+s);
        System.out.printf("s1=="+s1);
    }

    @Override
    public void destroy() {

    }
}
