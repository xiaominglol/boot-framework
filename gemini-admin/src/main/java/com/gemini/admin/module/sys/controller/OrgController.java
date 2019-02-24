package com.gemini.admin.module.sys.controller;

import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.CommonFailInfo;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.Org;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.OrgService;
import com.gemini.admin.module.sys.utils.UserUtils;
import com.gemini.admin.utils.TreeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @GetMapping("/org")
    @ResponseBody
    public Message getTreeTableList(Org org) {
        try {
            List<Org> list = orgService.getList(org);
            return Message.success(list);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Org", org);
            excpLogService.save(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
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
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.save(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param org 组织架构
     */
    @PostMapping("/org")
    @ResponseBody
    public Message add(Org org) {
        try {
            if (org.getId() == null) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.add(org);
                return Message.success(org);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Org", org);
            excpLogService.save(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @param org 组织架构
     * @return
     */
    @PutMapping("/org")
    @ResponseBody
    public Message update(Org org) {
        try {
            if (!StringUtils.isEmpty(org.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.update(org);
                return Message.success(org);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Org", org);
            excpLogService.save(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return
     */
    @DeleteMapping("/org/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.delete(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.save(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
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
            List<Org> orgList = orgService.getList(new Org());
            List<Map<String, Object>> list = TreeSelectUtil.getTreeSelect(orgList);
            return Message.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            excpLogService.save(ExcpLog.addResponseResult("/org", null, e.getMessage()));
            return Message.fail(e.getMessage());
        }
    }
}