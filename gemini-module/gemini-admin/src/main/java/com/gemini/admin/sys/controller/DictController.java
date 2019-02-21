package com.gemini.admin.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.sys.entity.Dict;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.DictService;
import com.gemini.admin.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/dict")
    @ResponseBody
    public Message getTreeTableList(LayUiPage layUiPage, Dict dict) {
        Message message = new Message("success", "", "", null);
        try {
            List<Dict> list = dictService.selectList(null);
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("Dict", dict);
            excpLogService.add(ExcpLog.addResponseResult("/dict", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/dict/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                Dict dict = dictService.getById(id);
                message.setStatus("success");
                message.setData(dict);
            } else {
                message.setStatus("fail");
                message.setMessage("查询失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/dict/{id}", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 添加
     *
     * @param dict
     */
    @PostMapping("/dict")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message add(Dict dict) {
        Message message = new Message();
        try {
            if (StringUtils.isEmpty(dict.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                dict.setOptId(currentUser.getAccount());
                dict.setOptName(currentUser.getName());
                dictService.add(dict);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,id已存在");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Dict", dict);
            excpLogService.add(ExcpLog.addResponseResult("/dict", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 更新
     *
     * @param dict
     * @return
     */
    @PutMapping("/dict")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message update(Dict dict) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(dict.getId())) {
                User currentUser = UserUtils.getCurrentUser();
                dict.setOptId(currentUser.getAccount());
                dict.setOptName(currentUser.getName());
                dictService.update(dict);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("更新失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Dict", dict);
            excpLogService.add(ExcpLog.addResponseResult("/dict", map, e.getMessage()));
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
    @DeleteMapping("/dict/{id}")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                dictService.delete(id);
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
            excpLogService.add(ExcpLog.addResponseResult("/dict/{id}", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }
}
