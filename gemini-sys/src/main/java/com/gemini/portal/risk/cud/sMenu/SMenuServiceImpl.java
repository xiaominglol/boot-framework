package com.gemini.portal.risk.cud.sMenu;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uepay.corebusiness.risk.base.service.impl.BaseServiceImpl;
import com.uepay.corebusiness.risk.cud.facade.dto.SMenuDto;
import com.uepay.corebusiness.risk.cud.service.mapper.SMenuMapper;
import com.uepay.corebusiness.risk.cud.service.po.SMenuPo;
import com.uepay.corebusiness.risk.cud.service.service.SMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 菜单表
 * @author wenge.cai
 */
@Service
public class SMenuServiceImpl extends BaseServiceImpl<SMenuDto, SMenuPo, SMenuMapper> implements SMenuService {

    @Override
    public QueryWrapper<SMenuPo> wrapper(SMenuDto dto) {
        return super.wrapper(dto)
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getUrl()), "url", dto.getUrl())
                .eq(!StringUtils.isEmpty(dto.getIcon()), "icon", dto.getIcon())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeId()), "menu_type_id", dto.getMenuTypeId())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeCode()), "menu_type_code", dto.getMenuTypeCode())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeName()), "menu_type_name", dto.getMenuTypeName())
                .eq(!StringUtils.isEmpty(dto.getTargetId()), "target_id", dto.getTargetId())
                .eq(!StringUtils.isEmpty(dto.getTargetCode()), "target_code", dto.getTargetCode())
                .eq(!StringUtils.isEmpty(dto.getTargetName()), "target_name", dto.getTargetName())
                .eq(!StringUtils.isEmpty(dto.getPermissionId()), "permission_id", dto.getPermissionId())
                .eq(!StringUtils.isEmpty(dto.getPermissionCode()), "permission_code", dto.getPermissionCode())
                .eq(!StringUtils.isEmpty(dto.getPermissionName()), "permission_name", dto.getPermissionName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime())
                .eq(!StringUtils.isEmpty(dto.getPid()), "pid", dto.getPid())
                .eq(!StringUtils.isEmpty(dto.getName()), "name", dto.getName())
                .eq(!StringUtils.isEmpty(dto.getUrl()), "url", dto.getUrl())
                .eq(!StringUtils.isEmpty(dto.getIcon()), "icon", dto.getIcon())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeId()), "menu_type_id", dto.getMenuTypeId())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeCode()), "menu_type_code", dto.getMenuTypeCode())
                .eq(!StringUtils.isEmpty(dto.getMenuTypeName()), "menu_type_name", dto.getMenuTypeName())
                .eq(!StringUtils.isEmpty(dto.getTargetId()), "target_id", dto.getTargetId())
                .eq(!StringUtils.isEmpty(dto.getTargetCode()), "target_code", dto.getTargetCode())
                .eq(!StringUtils.isEmpty(dto.getTargetName()), "target_name", dto.getTargetName())
                .eq(!StringUtils.isEmpty(dto.getPermissionId()), "permission_id", dto.getPermissionId())
                .eq(!StringUtils.isEmpty(dto.getPermissionCode()), "permission_code", dto.getPermissionCode())
                .eq(!StringUtils.isEmpty(dto.getPermissionName()), "permission_name", dto.getPermissionName())
                .eq(!StringUtils.isEmpty(dto.getSort()), "sort", dto.getSort())
                .eq(!StringUtils.isEmpty(dto.getStateId()), "state_id", dto.getStateId())
                .eq(!StringUtils.isEmpty(dto.getStateCode()), "state_code", dto.getStateCode())
                .eq(!StringUtils.isEmpty(dto.getStateName()), "state_name", dto.getStateName())
                .eq(!StringUtils.isEmpty(dto.getModifyId()), "modify_id", dto.getModifyId())
                .eq(!StringUtils.isEmpty(dto.getModifyName()), "modify_name", dto.getModifyName())
                .eq(!StringUtils.isEmpty(dto.getModifyTime()), "modify_time", dto.getModifyTime());
    }
}
