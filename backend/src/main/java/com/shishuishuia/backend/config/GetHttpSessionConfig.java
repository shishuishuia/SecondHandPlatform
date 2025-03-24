package com.shishuishuia.backend.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName GetHttpSessionConfig
 * @Description TODO
 * @date 2025/3/23 19:27
 * @Version 1.0
 */

@Configuration
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        //获取httpSession对象

        HttpSession httpSession = (HttpSession) request.getHttpSession();

        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
        super.modifyHandshake(sec, request, response);
    }
}
