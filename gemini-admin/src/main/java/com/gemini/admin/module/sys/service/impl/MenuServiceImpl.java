package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gemini.admin.module.sys.mapper.MenuMapper;
import com.gemini.admin.module.sys.model.Menu;
import com.gemini.admin.module.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author 小明不读书
 * @date 2017-12-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "menuCache")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    /**
     * 每次新增的时候，怎么更新这个缓存？待解决
     *
     * @param account
     * @return
     */
    @Override
    //@Cacheable(key = "'MENU_CACHE_GETBYACCOUNT_'+#account")
    public List<Menu> getByAccount(String account) {
        return menuMapper.getByAccount(account);
    }

    @Override
    public boolean removeById(Serializable id) {
        //删除角色权限
        menuMapper.deleteMenuAut(id);
        //删除角色
        return SqlHelper.delBool(menuMapper.deleteById(id));
    }

}
