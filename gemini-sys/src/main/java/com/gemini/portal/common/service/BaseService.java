package com.gemini.portal.common.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.po.BasePo;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.boot.framework.mybatis.utils.StringUtils;
import com.gemini.portal.enums.StateEnum;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 单表 - 增删查改
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface BaseService<Po extends BasePo, Mapper extends BaseMapper<Po>> {

    /**
     * 获取uuid
     *
     * @return
     */
    Long uid();

    /**
     * 获取日志对象
     *
     * @return
     */
    Logger log();

    /**
     * 获取主mapper对象
     *
     * @return
     */
    Mapper mapper();


    /**
     * 通过id查询一条记录
     *
     * @param id
     * @return
     */
    default Po getById(Long id) {
        return mapper().selectById(id);
    }

    /**
     * 通过条件查询一条记录
     *
     * @param po
     * @return
     */
    default Po getByParam(Po po) {
        return mapper().selectOne(wrapper(po));
    }

    /**
     * 非分页查询
     *
     * @param qw
     * @return
     */
    default List<Po> list(QueryWrapper<Po> qw) {
        return mapper().selectList(qw);
    }


    /**
     * 分页查询
     *
     * @param page
     * @param qw
     * @return
     */
    default IPage<Po> page(Page<Po> page, QueryWrapper<Po> qw) {
        return mapper().selectPage(page, qw);
    }

    default QueryWrapper<Po> wrapper(Po po) {
        QueryWrapper<Po> wrapper = wrapper();
        try {
            String ascs = (String) BeanUtils.invoke(po, "getAscs");
            String descs = (String) BeanUtils.invoke(po, "getDescs");
            if (ascs != null) {
                wrapper.orderByAsc(StringUtils.toUnderScoreCase(ascs).split(","));
            }
            if (descs != null) {
                wrapper.orderByDesc(StringUtils.toUnderScoreCase(descs).split(","));
            }
        } finally {
            return wrapper;
        }
    }

    /**
     * 构建主表查询对象
     *
     * @return
     */
    default QueryWrapper<Po> wrapper() {
        return new QueryWrapper<>();
    }

    /**
     * 插入之前
     * 设置主表基本信息
     *
     * @param po
     */
    default void insertBefore(Po po) {
        // 主键id
        BeanUtils.invoke(po, "setId", uid());
        // 用户信息
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(po, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(po, "setModifyUserName", currentUser.getName());
        // 状态信息
        BeanUtils.setDict(StateEnum.Enable, po);
    }

    /**
     * 插入-异步
     *
     * @param po     插入po
     * @param isBase 是否基础po
     */
    @Async
    default void insertAsync(Po po, Boolean isBase) {
        log().info("新增数据：{}", po);
        if (isBase) {
            insertBefore(po);
        }
        mapper().insert(po);
    }

    /**
     * 插入-同步
     *
     * @param po     插入po
     * @param isBase 是否基础po
     * @return 1-成功，0-失败
     */
    default int insertSync(Po po, Boolean isBase) {
        log().info("新增数据：{}", po);
        if (isBase) {
            insertBefore(po);
        }
        int result = mapper().insert(po);
        return result;
    }

    /**
     * 更新之前
     * 设置主表基本信息
     *
     * @param po
     */
    default void updateBefore(Po po) {
        // 用户信息
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(po, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(po, "setModifyUserName", currentUser.getName());
    }

    /**
     * 更新-异步
     *
     * @param po     插入po
     * @param isBase 是否基础po
     */
    @Async
    default void updateAsync(Po po, Boolean isBase) {
        log().info("更新数据：{}", po);
        if (isBase) {
            updateBefore(po);
        }
        mapper().updateById(po);
    }

    /**
     * 更新-同步
     *
     * @param po     插入po
     * @param isBase 是否基础po
     * @return 1-成功，0-失败
     */
    default int updateSync(Po po, Boolean isBase) {
        log().info("更新数据：{}", po);
        if (isBase) {
            updateBefore(po);
        }
        int result = mapper().updateById(po);
        return result;
    }

    /**
     * 删除之前
     *
     * @param id
     */
    default void deleteBefore(Long id) {
    }

    /**
     * 删除-异步
     *
     * @param id
     */
    @Async
    default void deleteByIdAsync(Long id) {
        deleteBefore(id);
        mapper().deleteById(id);
    }

    /**
     * 删除-同步
     *
     * @param id
     * @return
     */
    default int deleteByIdSync(Long id) {
        deleteBefore(id);
        int result = mapper().deleteById(id);
        return result;
    }

    /**
     * 批量删除 （待实现）
     */

}
