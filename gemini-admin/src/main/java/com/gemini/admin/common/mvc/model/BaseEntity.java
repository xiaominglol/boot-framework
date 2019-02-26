package com.gemini.admin.common.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础字段
 *
 * @author 小明不读书
 * @date 2017-12-11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态(0=无效,1=有效)
     */
    private Integer status;

    /**
     * 上一次更新人ID
     */
    private String optId;

    /**
     * 上一次更新人
     */
    private String optName;

    /**
     * 上一次更新时间(YYYY-MM-DD HH:MM:SS)
     */
    private Date optTime = new Date();

}
