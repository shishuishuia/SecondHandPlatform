package com.shishuishuia.mapper;

import com.shishuishuia.pojo.Message;
import com.shishuishuia.pojo.User;
import org.apache.ibatis.annotations.Param;

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

    //获取消息记录
    public List<Message> getMessageBySenderId(@Param("senderId") int senderId,
                                              @Param("receiverId") int receiverId);
    //保存消息
    public int saveMessage(Message message);

    //获取聊天用户列表
    public List<User> getUserlistBySenderId(int senderId);
    public List<User> getUserlistByReceiverId(int receiverId);
}
