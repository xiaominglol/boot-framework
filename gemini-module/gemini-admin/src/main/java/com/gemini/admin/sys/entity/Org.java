package com.gemini.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gemini.admin.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 组织架构  t_sys_org
 *
 * @author 小明不读书
 * @date 2018-06-12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@TableName("t_sys_org")
public class Org extends BaseEntity<Org> {

    /**
     * 自增主键：组织架构Id
     */
    private Integer id;

    /**
     * 上级架构，如果为null，则为顶级架构
     */
    private Integer pid;

    /**
     * 组织架构名称
     */
    private String name;

    /**
     * 组织架构类型(集团O,公司C,部门D)
     */
    private String type;

    /**
     * 排序
     */
    private Byte sort;

}