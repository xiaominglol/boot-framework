package com.gemini.admin.common.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 封装分页
 *
 * @author 小明不读书
 * @date 2017-11-09
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class LayUiPage implements Serializable {

    /**
     * 分页页码
     */
    private int pageNum;
    /**
     * 分页大小
     */
    private int pageSize;
    /**
     * 排序方式
     */
    private String order;
    /**
     * 排序字段
     */
    private String sort;
    /**
     * 搜索关键字
     */
    private String search;

}
