package com.gemini.portal.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.portal.module.sys.service.ExcpLogService;
import com.gemini.portal.module.sys.service.MenuService;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.model.ExcpLog;
import com.gemini.portal.module.sys.model.Menu;
import com.gemini.portal.module.sys.model.User;
import com.gemini.portal.module.sys.utils.UserUtils;
import com.gemini.boot.framework.web.mvc.controller.BaseController;
import com.gemini.boot.framework.web.mvc.model.CommonFailInfo;
import com.gemini.boot.framework.web.mvc.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制层
 *
 * @author 小明不读书
 * @date 2017-12-12
 */
@Controller
public class MenuController extends BaseController {

    @Autowired
    ExcpLogService excpLogService;
    @Autowired
    MenuService menuService;

    /**
     * 跳转到列表
     */
    @GetMapping("/menu/gotoList")
    public String gotoList() {
        return "module/sys/menu/menu_list";
    }

    /**
     * 树形表格列表
     */
    @SysLog("查询菜单列表")
    @GetMapping("/menu")
    @ResponseBody
    public Message getTreeTableList(Menu menu) {
        try {
            QueryWrapper<Menu> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(menu.getId())) {
                qw.eq("id", menu.getId()).or().eq("pid", menu.getId());
            }
            if (!StringUtils.isEmpty(menu.getName())) {
                qw.like("name", menu.getName());
            }
            List<Menu> list = menuService.list(qw);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 菜单列表（不带分页）
     */
    @GetMapping("/menu/list")
    @ResponseBody
    public Message list() {
        try {
            String account = UserUtils.getCurrentUser().getAccount();
            List<Menu> list = menuService.getByAccount(account);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/menu/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                Menu menu = menuService.getById(id);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param menu 菜单
     */
    @SysLog("添加菜单")
    @PostMapping("/menu")
    @ResponseBody
    public Message add(Menu menu) {
        try {
            if (StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.save(menu);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @param menu 菜单
     * @return
     */
    @SysLog("更新菜单")
    @PutMapping("/menu")
    @ResponseBody
    public Message update(Menu menu) {
        try {
            if (!StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.updateById(menu);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return
     */
    @SysLog("删除菜单")
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                menuService.removeById(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}