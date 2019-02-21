package com.gemini.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.LoginLog;
import com.gemini.admin.sys.entity.OptLog;
import com.gemini.admin.sys.service.LoginLogService;
import com.gemini.admin.sys.service.OptLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
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
        Message message = new Message("success", "", "", null);
        try {
            IPage<LoginLog> list = loginLogService.getList(new Page<LoginLog>(layUiPage.getPageNum(),layUiPage.getPageSize()), null);
            //第四步：返回结果
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("LoginLog", loginLog);
            excpLogService.add(ExcpLog.addResponseResult("/loginLog", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
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
        Message message = new Message("success", "", "", null);
        try {
            IPage<OptLog> list = optLogService.getList(new Page<OptLog>(layUiPage.getPageNum(),layUiPage.getPageSize()), null);
            //第四步：返回结果
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("OptLog", optLog);
            excpLogService.add(ExcpLog.addResponseResult("/optLog", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
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
        Message message = new Message("success", "", "", null);
        try {
            IPage<ExcpLog> list = excpLogService.getList(new Page<ExcpLog>(layUiPage.getPageNum(),layUiPage.getPageSize()), null);
            //第四步：返回结果
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("ExcpLog", excpLog);
            excpLogService.add(ExcpLog.addResponseResult("/excpLog", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

}
