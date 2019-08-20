package com.gemini.portal.module.sys.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.boot.framework.mybatis.po.BasePo;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * 菜单表
 *
 * @author 小明不读书
 */
@Data
@TableName("f_sys_menu")
public class SysMenuPo extends BasePo {

    /**
     * 主鍵ID
     */
    private Long id;

    /**
     * 主表id（UUID）如果为null，则为顶级
     */
    private Long pid;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单icon
     */
    private String icon;

    /**
     * 菜单类型id
     */
    private Long menuTypeId;

    /**
     * 菜单类型编码
     */
    private String menuTypeCode;

    /**
     * 菜单类型名称
     */
    private String menuTypeName;

    /**
     * 打开方式id
     */
    private Long targetId;

    /**
     * 打开方式编码
     */
    private String targetCode;

    /**
     * 打开方式名称
     */
    private String targetName;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

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
    private List<SysMenuPo> detailList;
}
