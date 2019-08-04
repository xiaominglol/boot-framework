package com.gemini.portal.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.module.sys.po.SysErrorLogPo;
import com.gemini.portal.module.sys.po.SysLoginLogPo;
import com.gemini.portal.module.sys.po.SysOptLogPo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.service.SysLoginLogService;
import com.gemini.portal.module.sys.service.SysOptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@Controller
public class SysLogController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysLoginLogService loginLogService;
    @Autowired
    SysOptLogService optLogService;

    /**
     * 跳转到登陆日志页面
     */
    @GetMapping("/loginLog/gotoList")
    public String loginLogPage() {
        return "module/sys/log/login_log_list";
    }

    /**
     * 登陆日志分页列表
     */
    @GetMapping("/loginLog")
    @ResponseBody
    public Message getLoginLog(LayUiPage layUiPage, SysLoginLogPo loginLog) {
        try {
            QueryWrapper<SysLoginLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(loginLog.getUserName())) {
                qw.like("user_name", loginLog.getUserName());
            }
            if (!StringUtils.isEmpty(loginLog.getLoginStateCode())) {
                qw.eq("state_code", loginLog.getLoginStateCode());
            }
            qw.orderByDesc("create_datetime");
            IPage<SysLoginLogPo> list = loginLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 跳转到操作日志页面
     */
    @GetMapping("/optLog/gotoList")
    public String optLogPage() {
        return "module/sys/log/opt_log_list";
    }

    /**
     * 操作日志分页列表
     */
    @GetMapping("/optLog")
    @ResponseBody
    public Message getOptLog(LayUiPage layUiPage, SysOptLogPo optLogPo) {
        try {
            QueryWrapper<SysOptLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(optLogPo.getDescription())) {
                qw.like("description", optLogPo.getDescription());
            }
            qw.orderByDesc("create_datetime");
            IPage<SysOptLogPo> list = optLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 跳转到异常日志页面
     */
    @GetMapping("/excpLog/gotoList")
    public String excpLogPage() {
        return "module/sys/log/excp_log_list";
    }

    /**
     * 异常日志分页列表
     */
    @GetMapping("/excpLog")
    @ResponseBody
    public Message getExcpLog(LayUiPage layUiPage, SysErrorLogPo errorLogPo) {
        try {
            QueryWrapper<SysErrorLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(errorLogPo.getUserName())) {
                qw.like("user_name", errorLogPo.getUserName());
            }
            qw.orderByDesc("create_datetime");
            IPage<SysErrorLogPo> list = errorLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

}