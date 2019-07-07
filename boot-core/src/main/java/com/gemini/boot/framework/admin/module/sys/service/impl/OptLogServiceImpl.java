package com.gemini.boot.framework.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.boot.framework.admin.module.sys.mapper.OptLogMapper;
import com.gemini.boot.framework.admin.module.sys.model.OptLog;
import com.gemini.boot.framework.admin.module.sys.service.OptLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements OptLogService {

}
