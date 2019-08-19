package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gemini.portal.common.service.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysRoleMapper;
import com.gemini.portal.module.sys.po.SysRolePo;
import com.gemini.portal.module.sys.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author wenge.cai
 */
@Service
public class SysRoleServiceImpl extends BootCrudServiceImpl<SysRolePo, SysRoleMapper> implements SysRoleService {

    @Override
    public QueryWrapper<SysRolePo> wrapper(SysRolePo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }

    @Override
    public List<Map<String, String>> getAut(Long id) {
        return mapper.getAut(id);
    }

    @Override
    public void save(SysRolePo role, Long[] ids) {
        //添加角色
        mapper.insert(role);
        //添加角色权限
        mapper.addAut(role.getId(), ids);
    }

    @Override
    public void updateById(SysRolePo role, Long[] ids) {
        //更新角色
        mapper.updateById(role);
        //删除角色权限
        mapper.deleteAut(role.getId());
        if (!StringUtils.isEmpty(ids)) {
            //添加角色权限
            mapper.addAut(role.getId(), ids);
        }
    }

    @Override
    public boolean removeById(Long id) {
        //删除角色权限
        mapper.deleteAut(id);
        //删除角色
        return SqlHelper.delBool(mapper.deleteById(id));
    }
}
