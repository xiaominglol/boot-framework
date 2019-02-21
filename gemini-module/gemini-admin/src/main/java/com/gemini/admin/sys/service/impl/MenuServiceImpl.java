package com.gemini.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gemini.admin.sys.dao.MenuDao;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.entity.Menu;
import com.gemini.admin.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author gemini
 * @date 2017-12-12
 */
@Service
@CacheConfig(cacheNames = "menuCache")
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    @Cacheable(key = "#id")
    public Menu getById(Serializable id) {
        System.out.println("通过ID：" + id + "查询");
        return menuDao.selectById(id);
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
    public IPage<Menu> getList(IPage<Menu> page, Wrapper<Menu> queryWrapper) {
        System.out.println("11111111111111111111getList");
        return menuDao.selectPage(page, queryWrapper);
    }

    @Override
    public List<Menu> selectList(Wrapper<Menu> queryWrapper) {
        return menuDao.selectList(queryWrapper);
    }

    /**
     * @param menu
     */
    @Override
    //@CachePut(key = "'MENU_CACHE_GETLIST'")
    public Menu add(Menu menu) {
        menuDao.insert(menu);
        return menu;
    }

    /**
     * @param menu
     */
    @Override
    @CachePut(key = "#result.id")
    public Menu update(Menu menu) {
        menuDao.updateById(menu);
        return menu;
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Serializable id) {
        menuDao.deleteById(id);
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
        return menuDao.getByAccount(account);
    }

    @Override
    public void deleteMenuAut(Integer id) {
        menuDao.deleteMenuAut(id);
    }

}
