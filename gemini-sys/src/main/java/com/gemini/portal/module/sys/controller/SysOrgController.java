package com.gemini.portal.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.po.SysOrgPo;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.service.SysOrgService;
import com.gemini.portal.module.sys.utils.TreeSelectUtil;
import com.gemini.portal.module.sys.utils.UserUtils;
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
public class SysOrgController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysOrgService orgService;

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
    @GetMapping("/org")
    @ResponseBody
    public Message getTreeTableList(SysOrgPo orgPo) {
        try {
            QueryWrapper<SysOrgPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(orgPo.getId())) {
                qw.eq("id", orgPo.getId()).or().eq("pid", orgPo.getId());
            }
            if (!StringUtils.isEmpty(orgPo.getName())) {
                qw.like("name", orgPo.getName());
            }
            List<SysOrgPo> list = orgService.list(qw);
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
    @GetMapping("/org/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                SysOrgPo orgPo = orgService.getById(id);
                return Message.success(orgPo);
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
     * @param orgPo 组织架构
     */
    @SysLog("添加组织架构")
    @PostMapping("/org")
    @ResponseBody
    public Message add(SysOrgPo orgPo) {
        try {
            if (orgPo.getId() == null) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                orgPo.setModifyUserId(currentUser.getId());
                orgPo.setModifyUserName(currentUser.getName());
                orgService.insert(orgPo);
                return Message.success(orgPo);
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
     * @param orgPo 组织架构
     * @return
     */
    @SysLog("更新组织架构")
    @PutMapping("/org")
    @ResponseBody
    public Message update(SysOrgPo orgPo) {
        try {
            if (!StringUtils.isEmpty(orgPo.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                orgPo.setModifyUserId(currentUser.getId());
                orgPo.setModifyUserName(currentUser.getName());
                orgService.update(orgPo);
                return Message.success(orgPo);
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
    @SysLog("删除组织架构")
    @DeleteMapping("/org/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.deleteById(id);
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
     * 获取组织架构下拉树
     *
     * @return
     */
    @GetMapping("/org/treeSelect")
    @ResponseBody
    public Message getOrg() {
        try {
            QueryWrapper<SysOrgPo> qw = new QueryWrapper<>();
            List<SysOrgPo> orgList = orgService.list(qw);
            List<Map<String, Object>> list = TreeSelectUtil.getTreeSelect(orgList);
            return Message.success(list);
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }
}