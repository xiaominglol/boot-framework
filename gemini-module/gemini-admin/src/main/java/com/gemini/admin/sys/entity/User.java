package com.gemini.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户表 	t_sys_user
 *
 * @author 小明不读书
 * @date 2017-11-09
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_user")
public class User extends BaseEntity<User> {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 主键:用户账号（姓名拼音）
     */
    private String account;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 所属部门
     */
    private Integer orgId;

    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    private Date createDate = new Date();

    /**
     * 导入信息，不入库，默认为ok
     */
    @TableField(exist = false)
    private String importStatus = "ok";

}
