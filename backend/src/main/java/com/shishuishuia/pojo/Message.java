package com.shishuishuia.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName Message
 * @Description TODO
 * @date 2025/3/23 19:51
 * @Version 1.0
 */

@Data
public class Message {
    private Integer id;           // 消息ID
    private Integer senderId;     // 发送者ID
    private Integer receiverId;   // 接收者ID
    private String content;       // 消息内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime sendTime;

}
