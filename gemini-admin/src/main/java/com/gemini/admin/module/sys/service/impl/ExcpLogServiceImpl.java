package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.admin.module.sys.mapper.ExcpLogMapper;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.service.ExcpLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ExcpLogServiceImpl extends ServiceImpl<ExcpLogMapper, ExcpLog> implements ExcpLogService {

}
