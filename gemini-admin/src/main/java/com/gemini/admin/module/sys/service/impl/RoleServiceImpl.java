package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gemini.admin.module.sys.mapper.RoleMapper;
import com.gemini.admin.module.sys.model.Role;
import com.gemini.admin.module.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
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
    public void save(Role role, Integer[] ids) {
        //添加角色
        roleMapper.insert(role);
        //添加角色权限
        roleMapper.addAut(role.getId(), ids);
    }

    @Override
    public void updateById(Role role, Integer[] ids) {
        //更新角色
        roleMapper.updateById(role);
        //删除角色权限
        roleMapper.deleteAut(role.getId());
        if (!StringUtils.isEmpty(ids)) {
            //添加角色权限
            roleMapper.addAut(role.getId(), ids);
        }
    }

    @Override
    public boolean removeById(Serializable id) {
        //删除角色权限
        roleMapper.deleteAut(id);
        //删除角色
        return SqlHelper.delBool(roleMapper.deleteById(id));
    }

}
