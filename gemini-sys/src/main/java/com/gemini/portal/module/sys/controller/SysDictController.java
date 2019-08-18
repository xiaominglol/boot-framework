package com.gemini.portal.module.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.mybatis.entity.CommonFailInfo;
import com.gemini.boot.framework.mybatis.entity.LayUiPage;
import com.gemini.boot.framework.mybatis.entity.Message;
import com.gemini.boot.framework.mybatis.utils.BeanUtils;
import com.gemini.portal.common.annotation.SysLog;
import com.gemini.portal.enums.StateEnum;
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
@RequestMapping("/sys/dict")
public class SysDictController {

    @Autowired
    SysErrorLogService errorLogService;
    @Autowired
    SysDictService dictService;

    @GetMapping("/gotoList")
    public String gotoList() {
        return "module/sys/dict/dict_list";
    }

    @GetMapping
    @ResponseBody
    public Message list(LayUiPage layUiPage, SysDictPo dictPo) {
        try {
            QueryWrapper<SysDictPo> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(dictPo.getName())) {
                qw.like("name", dictPo.getName()).or().like("code", dictPo.getCode());
            }
            if (!StringUtils.isEmpty(dictPo.getPid())) {
                qw.eq("pid", dictPo.getPid());
            }
            if (!StringUtils.isEmpty(dictPo.getCode())) {
                qw.eq("code", dictPo.getCode());
                qw.eq("state_code", "Enable");
            }
            if (layUiPage.getPageNum() != 0 && layUiPage.getPageSize() != 0) {
                IPage<SysDictPo> list = dictService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
                return Message.success(list);
            } else {
                List<SysDictPo> list = dictService.list(qw);
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

    @SysLog("添加字典")
    @PostMapping
    @ResponseBody
    public Message add(@RequestBody SysDictPo dictPo) {
        try {
            if (StringUtils.isEmpty(dictPo.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                BeanUtils.setDict(StateEnum.Enable, dictPo);
                dictPo.setModifyUserId(currentUser.getId());
                dictPo.setModifyUserName(currentUser.getName());
                dictService.insert(dictPo);
                return Message.success(dictPo);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("更新字典")
    @PutMapping
    @ResponseBody
    public Message update(@RequestBody SysDictPo dictPo) {
        try {
            if (!StringUtils.isEmpty(dictPo.getId())) {
                SysUserPo currentUser = UserUtils.getCurrentUser();
                dictPo.setModifyUserId(currentUser.getId());
                dictPo.setModifyUserName(currentUser.getName());
                dictService.update(dictPo);
                return Message.success(dictPo);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
//            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    @SysLog("删除字典")
    @DeleteMapping("/{id}")
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