package com.gemini.admin.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.Menu;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.MenuService;
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
 * 菜单控制层
 *
 * @author 小明
 * @date 2017-12-12
 */
@Controller
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    /**
     * 跳转到列表
     */
    @GetMapping("/menu/gotoList")
    public String gotoList() {
        return "module/sys/menu/menu_list";
    }

    /**
     * 树形表格列表
     */
    @GetMapping("/menu")
    @ResponseBody
    public Message getTreeTableList(LayUiPage layUiPage, Menu menu) {
        Message message = new Message("success", "", "", null);
        try {
            List<Menu> list = menuService.selectList(null);
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("Menu", menu);
            excpLogService.add(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 菜单列表（不带分页）
     */
    @GetMapping("/menu/list")
    @ResponseBody
    public Message list() {
        List<Menu> menuList = new ArrayList<>();
        try {
            String account = UserUtils.getCurrentUser().getAccount();
            menuList = menuService.getByAccount(account);
        } catch (Exception e) {
            excpLogService.add(ExcpLog.addResponseResult("/menu/list", null, e.getMessage()));
            logger.error(e.getMessage());
        }
        return Message.success().add("menuList", menuList);
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/menu/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                Menu menu = menuService.getById(id);
                message.setStatus("success");
                message.setData(menu);
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
     * @param menu 菜单
     */
    @PostMapping("/menu")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message add(Menu menu) {
        Message message = new Message();
        try {
            if (StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.add(menu);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,id已存在");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Menu", menu);
            excpLogService.add(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 更新
     *
     * @param menu 菜单
     * @return
     */
    @PutMapping("/menu")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message update(Menu menu) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.update(menu);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("更新失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Menu", menu);
            excpLogService.add(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
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
    @DeleteMapping("/menu/{id}")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                menuService.delete(id);
                menuService.deleteMenuAut(id);
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
            excpLogService.add(ExcpLog.addResponseResult("/menu/{id}", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }
}
