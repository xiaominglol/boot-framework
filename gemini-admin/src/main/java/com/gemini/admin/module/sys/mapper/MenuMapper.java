package com.gemini.admin.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gemini.admin.module.sys.model.Menu;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单Dao层
 *
 * @author 小明不读书
 * @date 2018-02-11
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param account
     * @return
     */
    List<Menu> getByAccount(String account);

    /**
     * 删除菜单权限
     *
     * @param id
     */
    void deleteMenuAut(Serializable id);
}