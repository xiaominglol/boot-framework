package com.gemini.portal.risk.console.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.uepay.corebusiness.risk.base.po.BasePo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 字典表
 * @author wenge.cai
 */
@Data
@TableName("f_sys_dict")
public class SDictPo extends BasePo {

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