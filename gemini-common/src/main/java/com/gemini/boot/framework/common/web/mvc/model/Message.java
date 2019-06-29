package com.gemini.boot.framework.common.web.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 封装返回结果集
 *
 * @author 小明不读书
 * @date 2017-10-10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Message {

    /**
     * 状态：true成功，false失败
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public static Message success(Object data) {
        Message message = new Message();
        message.setSuccess(true);
        message.setData(data);
        return message;
    }

    public static Message fail(String msg) {
        Message message = new Message();
        message.setSuccess(false);
        message.setMessage(msg);
        return message;
    }
}
