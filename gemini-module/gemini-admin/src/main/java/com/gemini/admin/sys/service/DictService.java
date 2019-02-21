package com.gemini.admin.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gemini.admin.common.service.CrudService;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.entity.Menu;

import java.util.List;

/**
 * @author 小明不读书
 * @date 2018-10-24
 */
public interface DictService extends CrudService<Dict> {
    List<Dict> selectList(Wrapper<Dict> queryWrapper);
}
