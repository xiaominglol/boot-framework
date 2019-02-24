package com.gemini.admin.module.sys.service;

import com.gemini.admin.common.mvc.service.CrudService;
import com.gemini.admin.module.sys.model.Menu;

import java.util.List;

/**
 * 菜单服务层接口
 *
 * @author 小明
 * @date 2017-12-25
 */
public interface MenuService extends CrudService<Menu> {

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
