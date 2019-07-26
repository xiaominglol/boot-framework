package com.gemini.boot.framework.admin.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.admin.common.annotation.SysLog;
import com.gemini.boot.framework.admin.module.sys.model.ExcpLog;
import com.gemini.boot.framework.admin.module.sys.model.LoginLog;
import com.gemini.boot.framework.admin.module.sys.model.OptLog;
import com.gemini.boot.framework.admin.module.sys.service.ExcpLogService;
import com.gemini.boot.framework.admin.module.sys.service.LoginLogService;
import com.gemini.boot.framework.admin.module.sys.service.OptLogService;
import com.gemini.boot.framework.common.web.mvc.controller.BaseController;
import com.gemini.boot.framework.common.web.mvc.model.LayUiPage;
import com.gemini.boot.framework.common.web.mvc.model.Message;
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
public class LogController extends BaseController {

    @Autowired
    ExcpLogService excpLogService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private OptLogService optLogService;

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
    @SysLog("查询登陆日志列表")
    @GetMapping("/loginLog")
    @ResponseBody
    public Message getLoginLog(LayUiPage layUiPage, LoginLog loginLog) {
        try {
            QueryWrapper<LoginLog> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(loginLog.getUserName())) {
                qw.like("user_name", loginLog.getUserName());
            }
            if (!StringUtils.isEmpty(loginLog.getStatus())) {
                qw.eq("status", loginLog.getStatus());
            }
            qw.orderByDesc("login_time");
            IPage<LoginLog> list = loginLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    @SysLog("查询操作日志列表")
    @GetMapping("/optLog")
    @ResponseBody
    public Message getOptLog(LayUiPage layUiPage, OptLog optLog) {
        try {
            QueryWrapper<OptLog> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(optLog.getDescription())) {
                qw.like("description", optLog.getDescription());
            }
            qw.orderByDesc("opt_time");
            IPage<OptLog> list = optLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    @SysLog("查询异常日志列表")
    @GetMapping("/excpLog")
    @ResponseBody
    public Message getExcpLog(LayUiPage layUiPage, ExcpLog excpLog) {
        try {
            QueryWrapper<ExcpLog> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(excpLog.getUserName())) {
                qw.like("user_name", excpLog.getUserName());
            }
            qw.orderByDesc("excp_time");
            IPage<ExcpLog> list = excpLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

}