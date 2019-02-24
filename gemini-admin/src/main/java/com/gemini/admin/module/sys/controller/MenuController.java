package com.gemini.admin.module.sys.controller;


import com.gemini.admin.common.mvc.controller.BaseController;
import com.gemini.admin.common.mvc.model.CommonFailInfo;
import com.gemini.admin.common.mvc.model.Message;
import com.gemini.admin.module.sys.model.ExcpLog;
import com.gemini.admin.module.sys.model.Menu;
import com.gemini.admin.module.sys.model.User;
import com.gemini.admin.module.sys.service.MenuService;
import com.gemini.admin.module.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    public Message getTreeTableList(Menu menu) {
        try {
            List<Menu> list = menuService.getList(menu);
            return Message.success(list);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Menu", menu);
            excpLogService.save(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 菜单列表（不带分页）
     */
    @GetMapping("/menu/list")
    @ResponseBody
    public Message list() {
        try {
            String account = UserUtils.getCurrentUser().getAccount();
            List<Menu> list = menuService.getByAccount(account);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.addResponseResult("/menu/list", null, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/menu/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                Menu menu = menuService.getById(id);
                return Message.success(menu);
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
     * @param menu 菜单
     */
    @PostMapping("/menu")
    @ResponseBody
    public Message add(Menu menu) {
        try {
            if (StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.add(menu);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Menu", menu);
            excpLogService.save(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @param menu 菜单
     * @return
     */
    @PutMapping("/menu")
    @ResponseBody
    public Message update(Menu menu) {
        try {
            if (!StringUtils.isEmpty(menu.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                menu.setOptId(currentUser.getAccount());
                menu.setOptName(currentUser.getName());
                menuService.update(menu);
                return Message.success(menu);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("Menu", menu);
            excpLogService.save(ExcpLog.addResponseResult("/menu", map, e.getMessage()));
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
    @DeleteMapping("/menu/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                menuService.delete(id);
                menuService.deleteMenuAut(id);
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.save(ExcpLog.addResponseResult("/menu/{id}", map, e.getMessage()));
            logger.error(e.getMessage());
            return Message.fail(e.getMessage());
        }
    }
}