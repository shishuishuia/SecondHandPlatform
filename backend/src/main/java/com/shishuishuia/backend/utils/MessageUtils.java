package com.shishuishuia.backend.utils;

import com.alibaba.fastjson.JSON;
import com.shishuishuia.backend.ws.ResultMessage;

/**
 * @author 晓梦之尘
 * more about author: www.shuishuia.cn
 * @ClassName MessageUtils
 * @Description TODO
 * @date 2025/3/23 19:12
 * @Version 1.0
 */
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, String fromName, Object message) {
        ResultMessage resultMassage = new ResultMessage();
        resultMassage.setSystem(isSystemMessage);
        if( fromName != null){
            resultMassage.setFromName(fromName);
        }
        resultMassage.setMessage(message);
        return JSON.toJSONString(resultMassage);
    }

}
