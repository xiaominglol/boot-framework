package com.gemini.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.Menu;
import com.gemini.admin.sys.entity.Org;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.OrgService;
import com.gemini.admin.sys.utils.UserUtils;
import com.gemini.admin.utils.TreeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Message getTreeTableList(LayUiPage layUiPage, Org org) {
        Message message = new Message("success", "", "", null);
        try {
            List<Org> list = orgService.selectList(null);
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("Org", org);
            excpLogService.add(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }


    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/org/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                Org org = orgService.getById(id);
                message.setStatus("success");
                message.setData(org);
            } else {
                message.setStatus("fail");
                message.setMessage("查询失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 添加
     *
     * @param org 组织架构
     */
    @PostMapping("/org")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message add(Org org) {
        Message message = new Message();
        try {
            if (org.getId() == null) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.add(org);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,id已存在");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Org", org);
            excpLogService.add(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 更新
     *
     * @param org 组织架构
     * @return
     */
    @PutMapping("/org")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message update(Org org) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(org.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                org.setOptId(currentUser.getAccount());
                org.setOptName(currentUser.getName());
                orgService.update(org);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("更新失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Org", org);
            excpLogService.add(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return
     */
    @DeleteMapping("/org/{id}")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                orgService.delete(id);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("删除失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/org", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 获取组织架构下拉树
     *
     * @return
     */
    @GetMapping("/org/treeSelect")
    @ResponseBody
    public List<Map<String, Object>> getOrg() {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            List<Org> orgList = orgService.selectList(null);
            list = TreeSelectUtil.getTreeSelect(orgList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            excpLogService.add(ExcpLog.addResponseResult("/org", null, e.getMessage()));
        }
        return list;
    }
}
