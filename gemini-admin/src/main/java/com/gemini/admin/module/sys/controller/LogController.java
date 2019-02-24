package com.gemini.admin.module.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.LayUiPage;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.LoginLog;
import com.gemini.admin.module.sys.model.OptLog;
import com.gemini.admin.module.sys.service.LoginLogService;
import com.gemini.admin.module.sys.service.OptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@Controller
public class LogController extends BaseController {

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
    @GetMapping("/loginLog")
    @ResponseBody
    public Message getLoginLog(LayUiPage layUiPage, LoginLog loginLog) {
        try {
            IPage<LoginLog> list = loginLogService.page(new Page<LoginLog>(layUiPage.getPageNum(), layUiPage.getPageSize()), null);

            return Message.success(list);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("LoginLog", loginLog);
            excpLogService.save(ExcpLog.addResponseResult("/loginLog", map, e.getMessage()));
            logger.error(e.getMessage());
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
    public Message getOptLog(LayUiPage layUiPage, OptLog optLog) {
        try {
            IPage<OptLog> list = optLogService.page(new Page<OptLog>(layUiPage.getPageNum(), layUiPage.getPageSize()), null);

            return Message.success(list);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("OptLog", optLog);
            excpLogService.save(ExcpLog.addResponseResult("/optLog", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 跳转到操作日志页面
     */
    @GetMapping("/excpLog/gotoList")
    public String excpLogPage() {
        return "module/sys/log/excp_log_list";
    }

    /**
     * 操作日志分页列表
     */
    @GetMapping("/excpLog")
    @ResponseBody
    public Message getExcpLog(LayUiPage layUiPage, ExcpLog excpLog) {
        try {
            IPage<ExcpLog> list = excpLogService.page(new Page<ExcpLog>(layUiPage.getPageNum(), layUiPage.getPageSize()), null);

            return Message.success(list);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("ExcpLog", excpLog);
            excpLogService.save(ExcpLog.addResponseResult("/excpLog", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

}