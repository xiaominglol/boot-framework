package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BaseSubPo;
import lombok.Data;

/**
 * 组织架构表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_org")
public class SysOrgPo extends BaseSubPo<SysOrgPo> {

    /**
     * 组织架构名称
     */
    private String name;

    /**
     * 组织架构类型id
     */
    private Long orgTypeId;

    /**
     * 组织架构类型编码
     */
    private String orgTypeCode;

    /**
     * 组织架构类型名称
     */
    private String orgTypeName;

    /**
     * 排序
     */
    private String sort;
}
