package com.gemini.admin.common.dao;

import java.util.List;

/**
 * 增删查改Dao
 *
 * @author 小明
 * @date 2018-02-11
 */
public interface CrudDao<T> {

    /**
     * 通过ID（主键）获取单条数据
     *
     * @param id
     * @return
     */
    T getById(Object id);

    /**
     * 通过entity获取所有数据
     *
     * @return
     */
    List<T> getList(Object o);

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    void add(T entity);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    void update(T entity);

    /**
     * 通过ID（主键）删除数据
     *
     * @param id
     * @return
     */
    void delete(Object id);
}
