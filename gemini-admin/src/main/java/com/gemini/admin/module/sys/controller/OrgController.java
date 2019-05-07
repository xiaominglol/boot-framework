package com.gemini.admin.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.admin.common.annotation.SysLog;
import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.CommonFailInfo;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.Org;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.OrgService;
import com.gemini.admin.module.sys.utils.TreeSelectUtil;
import com.gemini.admin.module.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 组织架构-控制层
 *
 * @author 小明不读书
 * @date 2018-06-09
 */
@Controller
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 跳转到列表
     */
    @GetMapping("/org/gotoList")
    public String gotoList() {
        return "module/sys/org/org_list";
    }

    /**
     * 树形表格列表
     */
    @SysLog("查询组织架构列表")
    @GetMapping("/org")
    @ResponseBody
    public Message getTreeTableList(Org org) {
        try {
            QueryWrapper<Org> qw = new QueryWrapper<>();
            if(!StringUtils.isEmpty(org.getId())){
                qw.eq("id",org.getId()).or().eq("pid",org.getId());
            }
            if(!StringUtils.isEmpty(org.getName())){
                qw.like("name",org.getName());
            }
            List<Org> list = orgService.list(qw);
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
    @GetMapping("/org/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                Org org = orgService.getById(id);
                return Message.success(org);
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
     * @param org 组织架构
     */
    @SysLog("添加组织架构")
    @PostMapping("/org")
    @ResponseBody
    public Message add(Org org) {
        try {
            if (org.getId() == null) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.save(org);
                return Message.success(org);
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
     * @param org 组织架构
     * @return
     */
    @SysLog("更新组织架构")
    @PutMapping("/org")
    @ResponseBody
    public Message update(Org org) {
        try {
            if (!StringUtils.isEmpty(org.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.updateById(org);
                return Message.success(org);
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
    @SysLog("删除组织架构")
    @DeleteMapping("/org/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.removeById(id);
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
     * 获取组织架构下拉树
     *
     * @return
     */
    @GetMapping("/org/treeSelect")
    @ResponseBody
    public Message getOrg() {
        try {
            List<Org> orgList = orgService.list();
            List<Map<String, Object>> list = TreeSelectUtil.getTreeSelect(orgList);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}