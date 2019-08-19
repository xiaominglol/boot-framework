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
public interface BootCrudService<Po extends BasePo, Mapper extends BaseMapper<Po>> {

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

    default void insertBefore(Po po) {
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(po, "setId", uid());
        BeanUtils.invoke(po, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(po, "setModifyUserName", currentUser.getName());

        BeanUtils.setDict(StateEnum.Enable, po);
    }

    default void insertAfter(Po po) {
    }

    default void updateBefore(Po po) {
        SysUserPo currentUser = UserUtils.getCurrentUser();
        BeanUtils.invoke(po, "setModifyUserId", currentUser.getId());
        BeanUtils.invoke(po, "setModifyUserName", currentUser.getName());
    }

    default void updateAfter(Po po) {
    }

    default void deleteBefore(Long id) {
    }

    default Po getById(Long id) {
        return mapper().selectById(id);
    }

    default List<Po> list(QueryWrapper<Po> qw) {
        return mapper().selectList(qw);
    }

    @Async
    default void insert(Po po) {
        log().info("新增数据：{}", po);
        insertBefore(po);
        mapper().insert(po);
        insertAfter(po);
    }

    @Async
    default void batchInsert(List<Po> poList) {
        log().info("批量新增数据：{}", poList);
        if (null != poList && 0 < poList.size()) {
            for (Po po : poList) {
                insert(po);
            }
        }
    }

    @Async
    default void update(Po po) {
        log().info("更新数据：{}", po);
        updateBefore(po);
        mapper().updateById(po);
        updateAfter(po);
    }

    @Async
    default void batchUpdate(List<Po> poList) {
        log().info("批量更新数据：{}", poList);
        if (null != poList && 0 < poList.size()) {
            for (Po po : poList) {
                update(po);
            }
        }
    }

    default void deleteById(Long id) {
        deleteBefore(id);
        mapper().deleteById(id);
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

    default QueryWrapper<Po> wrapper() {
        return new QueryWrapper<>();
    }


    default IPage<Po> page(Page<Po> page, QueryWrapper<Po> qw) {
        return mapper().selectPage(page, qw);
    }
}
