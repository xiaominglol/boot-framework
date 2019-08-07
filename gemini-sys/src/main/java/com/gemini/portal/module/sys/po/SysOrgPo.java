package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 组织架构表
 *
 * @author wenge.cai
 */
@Data
@TableName("f_sys_org")
public class SysOrgPo extends BasePo {

    /**
     * 主鍵ID
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
     * 状态编码
     */
    private String stateCode;

    /**
     * 状态名称
     */
    private String stateName;

    /**
     * 修改人id
     */
    private Long modifyUserId;

    /**
     * 修改人名称
     */
    private String modifyUserName;

    /**
     * 修改时间(YYYY-MM-DD HH:MM:SS)
     */
    private Timestamp modifyTime;

    @TableField(exist = false)
    List<SysOrgPo> detailList;
}
