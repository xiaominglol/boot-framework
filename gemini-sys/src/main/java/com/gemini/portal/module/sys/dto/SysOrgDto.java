package com.gemini.portal.module.sys.dto;

import com.gemini.boot.framework.mybatis.dto.BaseDto;
import lombok.Data;

/**
 * 组织架构表
 *
 * @author wenge.cai
 */
@Data
public class SysOrgDto extends BaseDto {

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

    /**
     * 主表id（UUID）如果为null，则为顶级
     */
    private Long pid;

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

    /**
     * 状态id
     */
    private Long stateId;

    /**
     * 状态编码（Enable：启用，Disable：禁用）
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 修改人id
     */
    private Long modifyId;

    /**
     * 修改人名称
     */
    private String modifyName;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private String modifyTime;
}
