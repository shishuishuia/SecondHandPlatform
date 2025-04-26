package com.shishuishuia.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName WebConfig
 * @Description TODO
 * @date 2025/4/25 18:58
 * @Version 1.0
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
