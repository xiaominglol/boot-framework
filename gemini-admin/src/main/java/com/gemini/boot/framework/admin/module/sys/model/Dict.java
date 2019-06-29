package com.gemini.boot.framework.admin.module.sys.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.common.web.mvc.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 字典表  t_sys_dict
 *
 * @author 小明不读书
 * @date 2018-06-12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_dict")
public class Dict extends BaseEntity<Dict> {

    /**
     * 字典主键（自增ID）
     */
    private Integer id;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 上级字典编码
     */
    private String parentCode;

    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典值
     */
    private String value;
    /**
     * 字典类型
     */
    private Integer type;
    /**
     * 是否默认
     */
    private String isDefault;
    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Byte sort;

}