package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gemini.boot.framework.mybatis.service.impl.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysMenuMapper;
import com.gemini.portal.module.sys.po.SysMenuPo;
import com.gemini.portal.module.sys.service.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 菜单表
 * @author wenge.cai
 */
@Service
public class SysMenuServiceImpl extends BootCrudServiceImpl<SysMenuPo, SysMenuMapper> implements SysMenuService {

    @Override
    public QueryWrapper<SysMenuPo> wrapper(SysMenuPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getPid()), "pid", po.getPid())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getUrl()), "url", po.getUrl())
                .eq(!StringUtils.isEmpty(po.getIcon()), "icon", po.getIcon())
                .eq(!StringUtils.isEmpty(po.getMenuTypeId()), "menu_type_id", po.getMenuTypeId())
                .eq(!StringUtils.isEmpty(po.getMenuTypeCode()), "menu_type_code", po.getMenuTypeCode())
                .eq(!StringUtils.isEmpty(po.getMenuTypeName()), "menu_type_name", po.getMenuTypeName())
                .eq(!StringUtils.isEmpty(po.getTargetId()), "target_id", po.getTargetId())
                .eq(!StringUtils.isEmpty(po.getTargetCode()), "target_code", po.getTargetCode())
                .eq(!StringUtils.isEmpty(po.getTargetName()), "target_name", po.getTargetName())
                .eq(!StringUtils.isEmpty(po.getPermissionId()), "permission_id", po.getPermissionId())
                .eq(!StringUtils.isEmpty(po.getPermissionCode()), "permission_code", po.getPermissionCode())
                .eq(!StringUtils.isEmpty(po.getPermissionName()), "permission_name", po.getPermissionName())
                .eq(!StringUtils.isEmpty(po.getSort()), "sort", po.getSort())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyId()), "modify_id", po.getModifyId())
                .eq(!StringUtils.isEmpty(po.getModifyName()), "modify_name", po.getModifyName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }

    @Override
    public List<SysMenuPo> getByAccount(Long account) {
        return mapper.getByAccount(account);
    }

    @Override
    public boolean removeById(Long id) {
        //删除角色权限
        mapper.deleteMenuAut(id);
        //删除角色
        return SqlHelper.delBool(mapper.deleteById(id));
    }
}