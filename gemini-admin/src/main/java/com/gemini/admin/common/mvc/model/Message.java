package com.gemini.admin.common.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

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
     * 状态，success成功，fail失败
     */
    private String status;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 用户要返回给浏览器的数据
     */
    private Map<String, Object> result = new HashMap<String, Object>();

    public static Message success(Object data) {
        Message message = new Message();
        message.setStatus("success");
        message.setData(data);
        return message;
    }

    public static Message fail(String msg) {
        Message message = new Message();
        message.setStatus("fail");
        message.setMessage(msg);
        return message;
    }

    public Message add(String key, Object value) {
        this.getResult().put(key, value);
        return this;
    }
}
