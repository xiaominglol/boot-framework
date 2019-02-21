package com.gemini.admin.common.controller;

import com.gemini.admin.sys.service.ExcpLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制层
 *
 * @author 小明
 * @date 2017-12-12
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 异常日志
     */
    @Autowired
    public ExcpLogService excpLogService;

}
