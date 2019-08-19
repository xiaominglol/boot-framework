package com.gemini.portal.module.sys.service;

import com.gemini.portal.common.service.BootCrudService;
import com.gemini.portal.module.sys.mapper.SysMenuMapper;
import com.gemini.portal.module.sys.po.SysMenuPo;

import java.util.List;

/**
 * 菜单表
 *
 * @author wenge.cai
 */
public interface SysMenuService extends BootCrudService<SysMenuPo, SysMenuMapper> {

    /**
     * 通过用户ID查询所有列表（不带分页）
     *
     * @param account
     * @return
     */
    List<SysMenuPo> getByAccount(Long account);

    boolean removeById(Long id);
}
