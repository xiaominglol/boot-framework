package com.gemini.admin.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gemini.admin.module.sys.mapper.LoginLogMapper;
import com.gemini.admin.module.sys.mapper.OptLogMapper;
import com.gemini.admin.module.sys.model.LoginLog;
import com.gemini.admin.module.sys.model.OptLog;
import com.gemini.admin.module.sys.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * @author 小明不读书
 * @date 2018-10-18
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LoingLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

}
