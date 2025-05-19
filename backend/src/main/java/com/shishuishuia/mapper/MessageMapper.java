package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Message;

import java.util.List;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName MessageMapper
 * @Description TODO
 * @date 2025/5/11 20:21
 * @Version 1.0
 */
public interface MessageMapper {
    public List<Message> getMessageBySenderId(int id);
}
