package com.gemini.portal.module.sys.vo;

import com.gemini.boot.framework.mybatis.vo.BaseVo;
import lombok.Data;

/**
 * 字典表
 *
 * @author wenge.cai
 */
@Data
public class SysDictVo extends BaseVo {

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

    /**
     * 主表id（UUID）如果为null，则为顶级
     */
    private Long pid;

    /**
     * 字典编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 备注
     */
    private String description;

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