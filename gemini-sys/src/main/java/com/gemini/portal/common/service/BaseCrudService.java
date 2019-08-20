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
 * 增删查改Service
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface BaseCrudService<Po extends BasePo, DetailPo, Mapper extends BaseMapper<Po>, DetailMapper extends BaseMapper<DetailPo>> {

    /**
     * 獲取uid
     *
     * @return Long
     */
    Long uid();

    /**
     * 獲取log
     *
     * @return Logger
     */
    Logger log();

    /**
     * 獲取mapper
     *
     * @return Mapper
     */
    Mapper mapper();

    DetailMapper detailMapper();

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
    default Po getOneByParam(Po po) {
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
     * 构建查询条件
     *
     * @return
     */
    default QueryWrapper<Po> wrapper() {
        return new QueryWrapper<>();
    }

    default QueryWrapper<DetailPo> detailWrapper() {
        return new QueryWrapper<>();
    }

    /**
     * 插入之前
     * <p>
     * 设置基本信息
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
     * 插入之后
     * <p>
     * 如果是主表结构，那么插入之后无需操作
     * 如果是主子表结构，那么要重写，插入子表
     *
     * @param detailPos
     * @param id
     */
    default void insertAfter(List<DetailPo> detailPos, Long id) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (DetailPo detailPo : detailPos) {
                BeanUtils.invoke(detailPo, "setPid", id);
                detailMapper().insert(detailPo);
            }
        }
    }

    /**
     * 插入-异步
     *
     * @param po
     * @param detailPos
     * @param id
     */
    @Async
    default void insertAsync(Po po, List<DetailPo> detailPos, Long id) {
        log().info("新增数据：{}", po);
        insertBefore(po);
        mapper().insert(po);
        insertAfter(detailPos, id);
    }

    /**
     * 插入-同步
     *
     * @param po
     * @param detailPos
     * @param id
     * @return 1-成功，0-失败
     */
    default int insertSync(Po po, List<DetailPo> detailPos, Long id) {
        log().info("新增数据：{}", po);
        insertBefore(po);
        int result = mapper().insert(po);
        insertAfter(detailPos, id);
        return result;
    }

    /**
     * 更新之前
     * <p>
     * 设置基本信息
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
     * 更新之后
     * <p>
     * 如果是主表结构，那么无需操作
     * 如果是主子表结构，那么需要重写，更新子表
     *
     * @param detailPos
     */
    default void updateAfter(List<DetailPo> detailPos) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (DetailPo detailPo : detailPos) {
                detailMapper().updateById(detailPo);
            }
        }
    }

    /**
     * 更新-异步
     *
     * @param po
     */
    @Async
    default void updateAsync(Po po, List<DetailPo> detailPos) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        mapper().updateById(po);
        updateAfter(detailPos);
    }

    @Async
    default void updateAsync(Po po) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        mapper().updateById(po);
    }

    /**
     * 更新-同步
     *
     * @param po
     */
    default int updateSync(Po po, List<DetailPo> detailPos) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        int result = mapper().updateById(po);
        updateAfter(detailPos);
        return result;
    }


    /**
     * 删除之前
     * <p>
     * 如果是主表结构，那么无需操作
     * 如果是主子表结构，那么需要重写，删除子表
     *
     * @param id
     */
    default void deleteBefore(Long id) {
        detailMapper().delete(detailWrapper().eq("pid", id));
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
