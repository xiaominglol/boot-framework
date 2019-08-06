package com.gemini.portal.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.po.SysMenuPo;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.service.SysMenuService;
import com.gemini.portal.module.sys.utils.UserUtils;
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
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysMenuService menuService;

    /**
     * 跳转到列表
     */
    @GetMapping("/gotoList")
    public String gotoList() {
        return "module/sys/menu/menu_list";
    }

    /**
     * 树形表格列表
     */
    @GetMapping("/menu")
    @ResponseBody
    public Message getTreeTableList(SysMenuPo menuPo) {
        try {
            QueryWrapper<SysMenuPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(menuPo.getId())) {
                qw.eq("id", menuPo.getId()).or().eq("pid", menuPo.getId());
            }
            if (!StringUtils.isEmpty(menuPo.getName())) {
                qw.like("name", menuPo.getName());
            }
            List<SysMenuPo> list = menuService.list(qw);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 菜单列表（不带分页）
     */
    @GetMapping("/list")
    @ResponseBody
    public Message list() {
        try {
            Long sysUserId = UserUtils.getCurrentUser().getId();
            List<SysMenuPo> list = menuService.getByAccount(sysUserId);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    public Message getById(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                SysMenuPo menu = menuService.getById(id);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    public Message add(SysMenuPo menuPo) {
        try {
            if (StringUtils.isEmpty(menuPo.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                menuPo.setModifyUserId(currentUser.getId());
                menuPo.setModifyUserName(currentUser.getName());
                menuService.insert(menuPo);
                return Message.success(menuPo);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    public Message update(SysMenuPo menuPo) {
        try {
            if (!StringUtils.isEmpty(menuPo.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                menuPo.setModifyUserId(currentUser.getId());
                menuPo.setModifyUserName(currentUser.getName());
                menuService.update(menuPo);
                return Message.success(menuPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                menuService.removeById(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}