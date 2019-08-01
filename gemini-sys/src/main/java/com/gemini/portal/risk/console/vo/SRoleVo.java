package com.gemini.portal.risk.console.vo;

import com.uepay.corebusiness.risk.base.dict.Dicts;
import com.uepay.corebusiness.risk.base.vo.BaseVo;
import com.uepay.framework.normalize.Dict;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 角色表
 * @author wenge.cai
 */
@Data
public class SRoleVo extends BaseVo {

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

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

    /**
     * 逻辑主键（UUID）
     */
    private Long id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

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