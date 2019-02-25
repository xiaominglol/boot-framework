package com.gemini.admin.module.sys.controller;


import com.gemini.admin.common.annotation.SysLog;
import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.CommonFailInfo;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.Dict;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.DictService;
import com.gemini.admin.module.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典控制层
 *
 * @author 小明不读书
 * @date 2018-10-24
 */
@Controller
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

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
    public Message getTreeTableList(HttpServletRequest request,Dict dict) {
        try {
            List<Dict> list = dictService.getList(dict);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"()", e.getMessage(),logger));
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
    public Message getById(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                Dict dict = dictService.getById(id);
                return Message.success(dict);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"()", e.getMessage(),logger));
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
    public Message add(Dict dict) {
        try {
            if (StringUtils.isEmpty(dict.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                dict.setOptId(currentUser.getAccount());
                dict.setOptName(currentUser.getName());
                dictService.add(dict);

                return Message.success(dict);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"()", e.getMessage(),logger));
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
    public Message update(Dict dict) {
        try {
            if (!StringUtils.isEmpty(dict.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                dict.setOptId(currentUser.getAccount());
                dict.setOptName(currentUser.getName());
                dictService.update(dict);
                return Message.success(dict);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"()", e.getMessage(),logger));
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
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                dictService.delete(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"()", e.getMessage(),logger));
            return Message.fail(e.getMessage());
        }
    }
}