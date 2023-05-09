package com.rest.template.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String bodyString = getBodyString(httpServletRequest);
        System.out.println(bodyString);
        //        BodyCachingHttpServletRequestWrapper requestWrapper =
//                new BodyCachingHttpServletRequestWrapper((HttpServletRequest) request);
//
//        byte[] requestBody = requestWrapper.getBody();
//        String s = new String(requestBody);
//        //System.out.printf("s=="+ s);
//        // TODO do something
//        BodyCachingHttpServletResponseWrapper responseWrapper =
//                new BodyCachingHttpServletResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request , response);
//
//        byte[] responseBody = responseWrapper.getBody();
//        String s1 = new String(responseBody);
//        // TODO do something
        //System.out.printf("s1=="+s1);
    }

    @Override
    public void destroy() {

    }

    public static String getBodyString(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
//            byte[] bytes = IOUtils.toByteArray(inputStream);
//            String s1 = new String(bytes);

            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
