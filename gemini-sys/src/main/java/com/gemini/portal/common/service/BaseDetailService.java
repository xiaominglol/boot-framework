package com.gemini.portal.common.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.portal.enums.StateEnum;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.utils.UserUtils;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 主子表 - 增删查改
 * <p>
 * 1、子表是自己
 * 2、子表是其他
 * 3、多个子表
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface BaseDetailService<Po, DetailPo, Mapper extends BaseMapper<Po>, DetailMapper extends BaseMapper<DetailPo>> extends BaseService<Po, Mapper> {

    /**
     * 获取子mapper对象
     *
     * @return
     */
    DetailMapper detailMapper();

    /**
     * 构建子表查询对象
     *
     * @return
     */
    default QueryWrapper<DetailPo> detailWrapper() {
        return new QueryWrapper<>();
    }

    /**
     * 插入之前
     * 设置子表基础信息
     *
     * @param detailPo
     */
    default void insertDetailBefore(DetailPo detailPo) {
        // 主键id
        BeanUtils.invoke(detailPo, "setId", uid());
        // 用户信息
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(detailPo, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(detailPo, "setModifyUserName", currentUser.getName());
        // 状态信息
        BeanUtils.setDict(StateEnum.Enable, detailPo);
    }

    /**
     * 插入之后，还需要插入子表
     *
     * @param po        主表po
     * @param detailPos 插入的子表po
     * @param isBase    是否基础po
     */
    default void insertAfter(Po po, List<DetailPo> detailPos, Boolean isBase) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (DetailPo detailPo : detailPos) {
                if (isBase) {
                    insertDetailBefore(detailPo);
                }
                BeanUtils.invoke(detailPo, "setPid", BeanUtils.invoke(po, "getId"));
                detailMapper().insert(detailPo);
            }
        }
    }

    /**
     * 插入-异步
     *
     * @param po        主表po
     * @param detailPos 子表pos
     * @param isBase    是否基础po
     */
    @Async
    default void insertAsync(Po po, List<DetailPo> detailPos, Boolean isBase) {
        log().info("新增数据：{}", po);
        insertBefore(po);
        mapper().insert(po);
        insertAfter(po, detailPos, isBase);
    }

    /**
     * 插入-同步
     *
     * @param po        主表po
     * @param detailPos 子表pos
     * @param isBase    是否基础po
     * @return 1-成功，0-失败
     */
    default int insertSync(Po po, List<DetailPo> detailPos, Boolean isBase) {
        log().info("新增数据：{}", po);
        insertBefore(po);
        int result = mapper().insert(po);
        insertAfter(po, detailPos, isBase);
        return result;
    }

    /**
     * 更新之前
     * 设置子表基本信息
     *
     * @param detailPo
     */
    default void updateDetailBefore(DetailPo detailPo) {
        // 用户信息
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(detailPo, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(detailPo, "setModifyUserName", currentUser.getName());
    }

    /**
     * 更新之后，还需要更新子表
     *
     * @param detailPos 子表pos
     * @param isBase    是否基础po
     */
    default void updateAfter(List<DetailPo> detailPos, Boolean isBase) {
        if (null != detailPos && 0 < detailPos.size()) {
            for (DetailPo detailPo : detailPos) {
                if (isBase) {
                    updateDetailBefore(detailPo);
                }
                detailMapper().updateById(detailPo);
            }
        }
    }

    /**
     * 更新-异步
     *
     * @param po        主表po
     * @param detailPos 子表pos
     * @param isBase    是否基础po
     */
    @Async
    default void updateAsync(Po po, List<DetailPo> detailPos, Boolean isBase) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        mapper().updateById(po);
        updateAfter(detailPos, isBase);
    }

    /**
     * 更新-异步
     *
     * @param po        主表po
     * @param detailPos 子表pos
     * @param isBase    是否基础po
     * @return 1-成功，0-失败
     */
    default int updateSync(Po po, List<DetailPo> detailPos, Boolean isBase) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        int result = mapper().updateById(po);
        updateAfter(detailPos, isBase);
        return result;
    }


    /**
     * 删除之前，还需要删除子表
     *
     * @param id 主表id
     */
    default void deleteBefore(Long id) {
        detailMapper().delete(detailWrapper().eq("pid", id));
    }

}
