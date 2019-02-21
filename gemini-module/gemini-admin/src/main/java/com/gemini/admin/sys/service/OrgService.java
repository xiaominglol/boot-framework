package com.gemini.admin.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gemini.admin.common.service.CrudService;
import com.gemini.admin.sys.entity.Org;

import java.util.List;

/**
 * @author 小明不读书
 * @date 2018-07-30
 */
public interface OrgService extends CrudService<Org> {
    List<Org> selectList(Wrapper<Org> queryWrapper);
}
