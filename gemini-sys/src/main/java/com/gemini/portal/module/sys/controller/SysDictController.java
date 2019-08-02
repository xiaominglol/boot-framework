package com.gemini.portal.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.module.sys.dto.SysDictDto;
import com.gemini.portal.module.sys.po.SysDictPo;
import com.gemini.portal.module.sys.po.SysUserPo;
import com.gemini.portal.module.sys.service.SysDictService;
import com.gemini.portal.module.sys.service.SysErrorLogService;
import com.gemini.portal.module.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制层
 *
 * @author 小明不读书
 * @date 2018-10-24
 */
@Controller
public class SysDictController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysDictService dictService;

    /**
     * 跳转到列表
     */
    @RequestMapping("/dict/gotoList")
    public String gotoList() {
        return "module/sys/dict/dict_list";
    }

    /**
     * 树形表格列表
     */
    @SysLog("查询字典列表")
    @GetMapping("/dict")
    @ResponseBody
    public Message getTreeTableList(SysDictPo dictPo) {
        try {
            QueryWrapper<SysDictPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(dictPo.getName())) {
                qw.like("name", dictPo.getName());
            }
            if (!StringUtils.isEmpty(dictPo.getCode())) {
                qw.eq("code", dictPo.getCode());
            }
            if (!StringUtils.isEmpty(dictPo.getStateId())) {
                qw.eq("state_id", dictPo.getStateId());
            }
            qw.orderByAsc("sort");
            List<SysDictPo> list = dictService.list(qw);
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
    @GetMapping("/dict/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                SysDictPo dictPo = dictService.getById(id);
                return Message.success(dictPo);
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
     * @param dict
     */
    @SysLog("添加字典")
    @PostMapping("/dict")
    @ResponseBody
    public Message add(SysDictPo dict) {
        try {
            if (StringUtils.isEmpty(dict.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                dict.setModifyId(currentUser.getId());
                dict.setModifyName(currentUser.getName());
                dictService.insert(dict);

                return Message.success(dict);
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
     * @param dict
     * @return
     */
    @SysLog("更新字典")
    @PutMapping("/dict")
    @ResponseBody
    public Message update(SysDictPo dict) {
        try {
            if (!StringUtils.isEmpty(dict.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                dict.setModifyId(currentUser.getId());
                dict.setModifyName(currentUser.getName());
                dictService.update(dict);
                return Message.success(dict);
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
     * @param id 角色主键id
     * @return
     */
    @SysLog("删除字典")
    @DeleteMapping("/dict/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Long id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                dictService.deleteById(id);
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