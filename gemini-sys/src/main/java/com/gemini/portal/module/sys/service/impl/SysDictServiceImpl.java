package com.gemini.portal.module.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.portal.common.service.BootCrudServiceImpl;
import com.gemini.portal.module.sys.mapper.SysDictMapper;
import com.gemini.portal.module.sys.po.SysDictPo;
import com.gemini.portal.module.sys.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 字典表
 *
 * @author 小明不读书
 */
@Service
public class SysDictServiceImpl extends BootCrudServiceImpl<SysDictPo, SysDictPo, SysDictMapper, SysDictMapper> implements SysDictService {

    @Override
    public QueryWrapper<SysDictPo> wrapper(SysDictPo po) {
        return super.wrapper(po)
                .eq(!StringUtils.isEmpty(po.getPid()), "pid", po.getPid())
                .eq(!StringUtils.isEmpty(po.getCode()), "code", po.getCode())
                .eq(!StringUtils.isEmpty(po.getName()), "name", po.getName())
                .eq(!StringUtils.isEmpty(po.getDescription()), "description", po.getDescription())
                .eq(!StringUtils.isEmpty(po.getStateId()), "state_id", po.getStateId())
                .eq(!StringUtils.isEmpty(po.getStateCode()), "state_code", po.getStateCode())
                .eq(!StringUtils.isEmpty(po.getStateName()), "state_name", po.getStateName())
                .eq(!StringUtils.isEmpty(po.getModifyUserId()), "modify_user_id", po.getModifyUserId())
                .eq(!StringUtils.isEmpty(po.getModifyUserName()), "modify_user_name", po.getModifyUserName())
                .eq(!StringUtils.isEmpty(po.getModifyTime()), "modify_time", po.getModifyTime());
    }
}
