package com.gemini.admin.common.mvc.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 增删查改Service
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
public interface CrudService<T> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 获取所有数据
     *
     * @param t
     * @return
     */
    List<T> getList(T t);

    /**
     * 获取所有数据
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<T> getPage(IPage<T> page, Wrapper<T> queryWrapper);

    /**
     * 插入数据
     *
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 更新数据
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    void delete(Serializable id);


}
