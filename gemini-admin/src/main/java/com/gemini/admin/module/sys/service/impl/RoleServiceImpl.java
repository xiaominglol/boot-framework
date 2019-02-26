package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.admin.module.sys.mapper.RoleMapper;
import com.gemini.admin.module.sys.model.Role;
import com.gemini.admin.module.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 角色service层
 *
 * @author 小明不读书
 * @date 2018-02-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
@CacheConfig(cacheNames = "roleCache")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Map<String, String>> getAut(Integer id) {
        return roleMapper.getAut(id);
    }

    @Override
    public void addAut(Integer id, Integer[] ids) {
        roleMapper.addAut(id, ids);
    }

    @Override
    public void deleteAut(Integer id) {
        roleMapper.deleteAut(id);
    }

}
