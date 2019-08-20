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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysLoginLogService loginLogService;
    @Autowired
    SysOptLogService optLogService;

    @GetMapping("/loginLog/gotoList")
    public String loginLogGotoList() {
        return "module/sys/log/login_log_list";
    }

    @GetMapping("/loginLog")
    @ResponseBody
    public Message loginLogList(LayUiPage layUiPage, SysLoginLogPo loginLog) {
        try {
            QueryWrapper<SysLoginLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(loginLog.getUserName())) {
                qw.like("user_name", loginLog.getUserName());
            }
            if (!StringUtils.isEmpty(loginLog.getLoginStateCode())) {
                qw.eq("state_code", loginLog.getLoginStateCode());
            }
            qw.orderByDesc("create_datetime");
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<SysLoginLogPo> list = loginLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<SysLoginLogPo> list = loginLogService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping("/optLog/gotoList")
    public String optLogGotoList() {
        return "module/sys/log/opt_log_list";
    }

    @GetMapping("/optLog")
    @ResponseBody
    public Message optLogList(LayUiPage layUiPage, SysOptLogPo optLogPo) {
        try {
            QueryWrapper<SysOptLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(optLogPo.getDescription())) {
                qw.like("description", optLogPo.getDescription());
            }
            qw.orderByDesc("create_datetime");
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<SysOptLogPo> list = optLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<SysOptLogPo> list = optLogService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping("/errorLog/gotoList")
    public String errorLogGotoList() {
        return "module/sys/log/error_log_list";
    }

    @GetMapping("/errorLog")
    @ResponseBody
    public Message errorLogList(LayUiPage layUiPage, SysErrorLogPo errorLogPo) {
        try {
            QueryWrapper<SysErrorLogPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(errorLogPo.getUserName())) {
                qw.like("user_name", errorLogPo.getUserName());
            }
            qw.orderByDesc("create_datetime");
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<SysErrorLogPo> list = errorLogService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<SysErrorLogPo> list = errorLogService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

}