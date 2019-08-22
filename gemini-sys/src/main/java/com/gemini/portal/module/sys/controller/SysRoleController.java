package com.gemini.portal.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.po.SysRolePo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 小明不读书
 * @date 2017-11-03
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysRoleService roleService;

    @RequestMapping("/gotoList")
    public String gotoList() {
        return "module/sys/role/role_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, SysRolePo rolePo) {
        try {
            QueryWrapper<SysRolePo> qw = new QueryWrapper<>();
            //如果是分页查询
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                if (!StringUtils.isEmpty(rolePo.getName())) {
                    qw.like("name", rolePo.getName()).or().like("code", rolePo.getName());
                }
                IPage<SysRolePo> list = roleService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {//否则查询全部有效
                qw.eq("state_code", "Enable");
                List<SysRolePo> list = roleService.list(qw);
                return Message.success(list);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Message detail(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                SysRolePo rolePo = roleService.getById(id);
                return Message.success(rolePo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("添加角色")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody SysRolePo rolePo) {
        try {
            if (StringUtils.isEmpty(rolePo.getId())) {
                roleService.insertAsync(rolePo, rolePo.getDetailList(), false);
                return Message.success(rolePo);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新角色")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody SysRolePo rolePo) {
        try {
            if (!StringUtils.isEmpty(rolePo.getId())) {
                roleService.updateAsync(rolePo, rolePo.getDetailList(), false);
                return Message.success(rolePo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除角色")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                roleService.deleteByIdAsync(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 获得权限
     *
     * @param id 角色主键id
     * @return
     */
    @GetMapping("/aut/{id}")
    @ResponseBody
    public Message getAut(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                List<Map<String, String>> list = roleService.getAut(id);
                List<String> idList = new ArrayList<>();
                for (Map<String, String> map : list) {
                    idList.add(map.get("menuId"));
                }
                return Message.success(idList);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}