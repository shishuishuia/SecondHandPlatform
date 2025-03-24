package com.shishuishuia.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName WebSocketConfig
 * @Description websocket的配置类
 * @date 2025/3/23 19:07
 * @Version 1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
