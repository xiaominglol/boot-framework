package com.gemini.admin.common.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * 增删查改Service
 *
 * @author 小明
 * @date 2018-02-11
 */
//public interface CrudService<T> extends IService<T>{
public interface CrudService<T>{

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 获取所有数据
     * @param page
     * @param queryWrapper
     * @return
     */
    //List<T> getList(Page page, Object o);
    IPage<T> getList(IPage<T> page, Wrapper<T> queryWrapper);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    T add(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    void delete(Serializable id);


}
