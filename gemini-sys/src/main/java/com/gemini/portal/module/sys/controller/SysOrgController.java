package com.gemini.portal.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.po.SysOrgPo;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.service.SysOrgService;
import com.gemini.portal.module.sys.utils.TreeSelectUtil;
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
@RequestMapping("/sys/org")
public class SysOrgController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysOrgService orgService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "module/sys/org/org_list";
    }

    /**
     * 构建下拉树
     *
     * @return
     */
    @GetMapping("/treeSelect")
    @ResponseBody
    public Message treeSelect() {
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

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, SysOrgPo orgPo) {
        try {
            QueryWrapper<SysOrgPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(orgPo.getName())) {
                qw.like("name", orgPo.getName());
            }
            if (!StringUtils.isEmpty(orgPo.getOrgTypeCode())) {
                qw.like("org_type_code", orgPo.getOrgTypeCode());
            }
            if (!StringUtils.isEmpty(orgPo.getPid())) {
                qw.eq("pid", orgPo.getPid());
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<SysOrgPo> list = orgService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<SysOrgPo> list = orgService.list(qw);
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

    @SysLog("添加组织架构")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody SysOrgPo orgPo) {
        try {
            if (StringUtils.isEmpty(orgPo.getId())) {
                orgService.insertAsync(orgPo, orgPo.getDetailList(), true);
                return Message.success(orgPo);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新组织架构")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody SysOrgPo orgPo) {
        try {
            if (!StringUtils.isEmpty(orgPo.getId())) {
                orgService.updateAsync(orgPo, orgPo.getDetailList(), true);
                return Message.success(orgPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除组织架构")
    @DeleteMapping("/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.deleteByIdAsync(id);
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