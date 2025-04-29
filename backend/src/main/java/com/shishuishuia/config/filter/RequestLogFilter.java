package com.shishuishuia.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName a
 * @Description TODO
 * @date 2025/4/29 16:28
 * @Version 1.0
 */
@Component
public class RequestLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException, ServletException {

        // 缓存可重复读取的请求
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        // 执行后续处理
        filterChain.doFilter(wrappedRequest, response);

        // 打印请求信息（建议异步处理）
        System.out.println("\n=== 请求日志 ===");
        System.out.println("URL: " + request.getRequestURL());
        System.out.println("Method: " + request.getMethod());
        System.out.println("Headers: " + getHeaders(request));
        System.out.println("Parameters: " + getParameters(request));
        System.out.println("Body: " + new String(wrappedRequest.getContentAsByteArray(),
                request.getCharacterEncoding()));
    }

    private String getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .map(header -> header + "=" + request.getHeader(header))
                .collect(Collectors.joining(", "));
    }

    private String getParameters(HttpServletRequest request) {
        return request.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining(", "));
    }
}