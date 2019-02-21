package com.gemini.admin.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.Role;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.RoleService;
import com.gemini.admin.sys.utils.UserUtils;
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
    @GetMapping("/role")
    @ResponseBody
    public Message getPageList(LayUiPage layUiPage, Role role) {
        Message message = new Message("success", "", "", null);
        try {
            IPage<Role> list = roleService.getList(new Page<Role>(layUiPage.getPageNum(),layUiPage.getPageSize()), null);
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("Role", role);
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/role/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                Role role = roleService.getById(id);
                message.setStatus("success");
                message.setData(role);
            } else {
                message.setStatus("fail");
                message.setMessage("查询失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 添加
     *
     * @param role 角色
     */
    @PostMapping("/role")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message add(Role role, @RequestParam(value = "ids[]", required = false) Integer[] ids) {
        Message message = new Message();
        try {
            if (StringUtils.isEmpty(role.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                role.setOptId(currentUser.getAccount());
                role.setOptName(currentUser.getName());
                roleService.add(role);
                if (!StringUtils.isEmpty(ids)) {
                    roleService.addAut(role.getId(), ids);
                }
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,id已存在");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Role", role);
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 更新
     *
     * @param role
     * @return
     */
    @PutMapping("/role")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message update(Role role, @RequestParam(value = "ids[]", required = false) Integer[] ids) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(role.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                role.setOptId(currentUser.getAccount());
                role.setOptName(currentUser.getName());
                roleService.update(role);
                roleService.deleteAut(role.getId());
                if (!StringUtils.isEmpty(ids)) {
                    roleService.addAut(role.getId(), ids);
                }
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("更新失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Role", role);
            map.put("ids[]", ids);
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 删除
     *
     * @param id 角色主键id
     * @return
     */
    @DeleteMapping("/role/{id}")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                roleService.delete(id);
                roleService.deleteAut(id);
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
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
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
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                List<Map<String, String>> list = roleService.getAut(id);
                List<String> idList = new ArrayList<>();
                for (Map<String, String> map : list) {
                    idList.add(map.get("menuId"));
                }
                message.setData(idList);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("查询失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/role", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }
}
