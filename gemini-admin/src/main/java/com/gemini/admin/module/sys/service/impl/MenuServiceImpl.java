package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.module.sys.mapper.MenuMapper;
import com.gemini.admin.module.sys.model.Menu;
import com.gemini.admin.module.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author gemini
 * @date 2017-12-12
 */
@Service
@Transactional(rollbackFor=Exception.class)
@CacheConfig(cacheNames = "menuCache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    @Cacheable(key = "#id")
    public Menu getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return menuMapper.selectById(id);
    }

    /**
     * 如果不加（condition="#page.pageSize == 0"），进到页面查询时会执行分页查询，只能缓存到10条数据
     * 导致后面无论查询第一页还是第二页时，都只是显示第一页的10条，只能加了条件缓存全部数据，缓存不了分页
     * -----------------待解决------------------------
     *
     * @param page
     * @param o
     * @return
     */
    @Override
    public IPage<Menu> getPage(IPage<Menu> page, Wrapper<Menu> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return menuMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Menu> getList(Menu menu) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        return menuMapper.selectList(qw);
    }

    /**
     * @param menu
     */
    @Override
    //@CachePut(key = "'MENU_CACHE_GETLIST'")
    public Menu add(Menu menu) {
        menuMapper.insert(menu);
        return menu;
    }

    /**
     * @param menu
     */
    @Override
    @CachePut(key = "#result.id")
    public Menu update(Menu menu) {
        menuMapper.updateById(menu);
        return menu;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        menuMapper.deleteById(id);
    }

    /**
     * 每次新增的时候，怎么更新这个缓存？待解决
     *
     * @param account
     * @return
     */
    @Override
    //@Cacheable(key = "'MENU_CACHE_GETBYACCOUNT_'+#account")
    public List<Menu> getByAccount(String account) {
        System.out.println("-----------------调用了MENU_CACHE_GETBYACCOUNT_--------------------");
        return menuMapper.getByAccount(account);
    }

    @Override
    public void deleteMenuAut(Integer id) {
        menuMapper.deleteMenuAut(id);
    }

}
