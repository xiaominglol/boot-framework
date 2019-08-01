package com.gemini.portal.risk.console.sMenu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.console.mapper.SMenuMapper;
import com.uepay.corebusiness.risk.console.po.SMenuPo;
import com.uepay.corebusiness.risk.console.service.SMenuService;
import com.uepay.corebusiness.risk.console.vo.SMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 菜单表
 * @author wenge.cai
 */
@Service
public class SMenuServiceImpl extends BaseServiceImpl<SMenuVo, SMenuPo, SMenuMapper> implements SMenuService {

    @Override
    public QueryWrapper<SMenuPo> wrapper(SMenuVo vo) {
        return super.wrapper(vo)
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getUrl()), "url", vo.getUrl())
                .eq(!StringUtils.isEmpty(vo.getIcon()), "icon", vo.getIcon())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeId()), "menu_type_id", vo.getMenuTypeId())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeCode()), "menu_type_code", vo.getMenuTypeCode())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeName()), "menu_type_name", vo.getMenuTypeName())
                .eq(!StringUtils.isEmpty(vo.getTargetId()), "target_id", vo.getTargetId())
                .eq(!StringUtils.isEmpty(vo.getTargetCode()), "target_code", vo.getTargetCode())
                .eq(!StringUtils.isEmpty(vo.getTargetName()), "target_name", vo.getTargetName())
                .eq(!StringUtils.isEmpty(vo.getPermissionId()), "permission_id", vo.getPermissionId())
                .eq(!StringUtils.isEmpty(vo.getPermissionCode()), "permission_code", vo.getPermissionCode())
                .eq(!StringUtils.isEmpty(vo.getPermissionName()), "permission_name", vo.getPermissionName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime())
                .eq(!StringUtils.isEmpty(vo.getPid()), "pid", vo.getPid())
                .eq(!StringUtils.isEmpty(vo.getName()), "name", vo.getName())
                .eq(!StringUtils.isEmpty(vo.getUrl()), "url", vo.getUrl())
                .eq(!StringUtils.isEmpty(vo.getIcon()), "icon", vo.getIcon())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeId()), "menu_type_id", vo.getMenuTypeId())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeCode()), "menu_type_code", vo.getMenuTypeCode())
                .eq(!StringUtils.isEmpty(vo.getMenuTypeName()), "menu_type_name", vo.getMenuTypeName())
                .eq(!StringUtils.isEmpty(vo.getTargetId()), "target_id", vo.getTargetId())
                .eq(!StringUtils.isEmpty(vo.getTargetCode()), "target_code", vo.getTargetCode())
                .eq(!StringUtils.isEmpty(vo.getTargetName()), "target_name", vo.getTargetName())
                .eq(!StringUtils.isEmpty(vo.getPermissionId()), "permission_id", vo.getPermissionId())
                .eq(!StringUtils.isEmpty(vo.getPermissionCode()), "permission_code", vo.getPermissionCode())
                .eq(!StringUtils.isEmpty(vo.getPermissionName()), "permission_name", vo.getPermissionName())
                .eq(!StringUtils.isEmpty(vo.getSort()), "sort", vo.getSort())
                .eq(!StringUtils.isEmpty(vo.getStateId()), "state_id", vo.getStateId())
                .eq(!StringUtils.isEmpty(vo.getStateCode()), "state_code", vo.getStateCode())
                .eq(!StringUtils.isEmpty(vo.getStateName()), "state_name", vo.getStateName())
                .eq(!StringUtils.isEmpty(vo.getModifyId()), "modify_id", vo.getModifyId())
                .eq(!StringUtils.isEmpty(vo.getModifyName()), "modify_name", vo.getModifyName())
                .eq(!StringUtils.isEmpty(vo.getModifyTime()), "modify_time", vo.getModifyTime());
    }
}