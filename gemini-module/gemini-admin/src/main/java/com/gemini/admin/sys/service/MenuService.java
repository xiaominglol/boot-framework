package com.gemini.admin.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gemini.admin.common.service.CrudService;
import com.gemini.admin.sys.entity.Menu;
import com.gemini.admin.sys.entity.User;

import java.util.List;

/**
 * 菜单服务层接口
 *
 * @author 小明
 * @date 2017-12-25
 */
public interface MenuService extends CrudService<Menu> {
    List<Menu> selectList(Wrapper<Menu> queryWrapper);


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
    void deleteMenuAut(Integer id);
}
