package com.gemini.admin.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.annotation.SysLog;
import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.CommonFailInfo;
import com.gemini.admin.common.mvc.model.CommonStatus;
import com.gemini.admin.common.mvc.model.LayUiPage;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.Role;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.RoleService;
import com.gemini.admin.module.sys.utils.UserUtils;
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
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到列表
     */
    @RequestMapping("/role/gotoList")
    public String gotoList() {
        return "module/sys/role/role_list";
    }

    /**
     * 获取分页列表
     */
    @SysLog("查询角色列表")
    @GetMapping("/role")
    @ResponseBody
    public Message getPageList(LayUiPage layUiPage, Role role) {
        try {
            QueryWrapper<Role> qw = new QueryWrapper<>();
            //如果是分页查询
            if(layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0){
                if(!StringUtils.isEmpty(role.getName())){
                    qw.like("name",role.getName()).or().like("code",role.getName());
                }
                IPage<Role> list = roleService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            }else{//否则查询全部有效
                qw.eq("status", CommonStatus.STATUS_VALIDITY);
                List<Role> list = roleService.list(qw);
                return Message.success(list);
            }
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
    @GetMapping("/role/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                Role role = roleService.getById(id);
                return Message.success(role);
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
     * @param role 角色
     */
    @SysLog("添加角色")
    @PostMapping("/role")
    @ResponseBody
    public Message add(Role role, @RequestParam(value = "ids[]", required = false) Integer[] ids) {
        try {
            if (StringUtils.isEmpty(role.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                role.setOptId(currentUser.getAccount());
                role.setOptName(currentUser.getName());
                roleService.save(role);
                if (!StringUtils.isEmpty(ids)) {
                    roleService.addAut(role.getId(), ids);
                }
                return Message.success(role);
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
     * @param role
     * @return
     */
    @SysLog("更新角色")
    @PutMapping("/role")
    @ResponseBody
    public Message update(Role role, @RequestParam(value = "ids[]", required = false) Integer[] ids) {
        try {
            if (!StringUtils.isEmpty(role.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                role.setOptId(currentUser.getAccount());
                role.setOptName(currentUser.getName());
                roleService.updateById(role);
                roleService.deleteAut(role.getId());
                if (!StringUtils.isEmpty(ids)) {
                    roleService.addAut(role.getId(), ids);
                }
                return Message.success(role);
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
     * @param id 角色主键id
     * @return
     */
    @SysLog("删除角色")
    @DeleteMapping("/role/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                roleService.removeById(id);
                roleService.deleteAut(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
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
    public Message getAut(@PathVariable("id") Integer id) {
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
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}