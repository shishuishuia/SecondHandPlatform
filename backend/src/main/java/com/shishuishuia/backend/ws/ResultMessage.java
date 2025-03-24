package com.shishuishuia.backend.ws;

import lombok.Data;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName ResultMessage
 * @Description TODO
 * @date 2025/3/23 20:04
 * @Version 1.0
 */

@Data
public class ResultMessage {

    private boolean isSystem;
    private String fromName;
    private Object message;//如果是系统消息是数组
}