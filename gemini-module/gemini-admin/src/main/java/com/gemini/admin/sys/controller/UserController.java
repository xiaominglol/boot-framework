package com.gemini.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.admin.common.controller.BaseController;
import com.gemini.admin.common.entity.LayUiPage;
import com.gemini.admin.common.entity.Message;
import com.gemini.admin.properties.GeminiProperties;
import com.gemini.admin.sys.entity.ExcpLog;
import com.gemini.admin.sys.entity.User;
import com.gemini.admin.sys.service.UserService;
import com.gemini.admin.sys.utils.UserUtils;
import com.gemini.admin.utils.DateUtils;
import com.gemini.admin.utils.ExcelImportUtils;
import com.gemini.admin.utils.MD5Util;
import com.gemini.admin.utils.Poi314ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 小明
 * @date 2017-11-03
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private GeminiProperties geminiProperties;

    /**
     * 跳转到列表
     */
    @GetMapping("/user/gotoList")
    //@RequiresPermissions("3333")
    public String gotoList() {
        return "module/sys/user/user_list";
    }

    /**
     * 获取分页列表
     */
    @GetMapping("/user")
    @ResponseBody
    public Message getPageList(LayUiPage layUiPage, User user) {
        Message message = new Message("success", "", "", null);
        try {
            IPage<User> list = userService.getList(new Page<User>(layUiPage.getPageNum(),layUiPage.getPageSize()), null);
            message.setStatus("success");
            message.setData(list);
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("Page", layUiPage);
            map.put("User", user);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/user/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") String id) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(id)) {
                User user = userService.getById(id);
                message.setStatus("success");
                message.setData(user);
            } else {
                message.setStatus("fail");
                message.setMessage("查询失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 保存
     *
     * @param user 用户
     */
    @PostMapping("/user")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message save(User user, @RequestParam(value = "ids[]") String[] ids) {
        Message message = new Message();
        try {
            if (StringUtils.isEmpty(user.getId())) {
                String pwd = MD5Util.encryption(user.getPassword(), user.getAccount());
                user.setPassword(pwd);
                User currentUser = UserUtils.getCurrentUser();
                user.setOptId(currentUser.getAccount());
                user.setOptName(currentUser.getName());
                userService.add(user);
                userService.addUserRole(user.getAccount(), ids);
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,id已存在");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("User", user);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 批量保存
     *
     * @param userList 用户列表
     */
    @PostMapping("/user/batchSave")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message batchSave(@RequestBody(required = false) List<User> userList) {
        Message message = new Message();
        try {
            if (userList != null && userList.size() > 0) {
                for (User user : userList) {
                    //初始化密码123456
                    user.setPassword("123456");
                    String pwd = MD5Util.encryption(user.getPassword(), user.getAccount());
                    user.setPassword(pwd);
                    User currentUser = UserUtils.getCurrentUser();
                    user.setOptId(currentUser.getAccount());
                    user.setOptName(currentUser.getName());
                    user.setPicture("/img/icon/64/default_picture.png");
                    userService.add(user);
                }
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("添加失败,列表为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("UserList", userList);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
            //如果放开，message无法返回前台，如果不放开@Transactional注解无效，
            // 后面又测试，无论放开或者不放开，事务注解都无效待解决
//            throw e;
        }
        return message;
    }

    /**
     * 更新
     *
     * @param user 用户
     * @return
     */
    @PutMapping("/user")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message update(User user, @RequestParam(value = "ids[]", required = false) String[] ids) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(user.getAccount())) {
                User currentUser = UserUtils.getCurrentUser();
                user.setOptId(currentUser.getAccount());
                user.setOptName(currentUser.getName());

                User oldUser = userService.getById(user.getAccount());
                //如果密码不一样，证明在修改密码，所以要加密保存，如果密码一样，则不需要再加密
                if (!user.getPassword().equals(oldUser.getPassword())) {
                    String pwd = MD5Util.encryption(user.getPassword(), user.getAccount());
                    user.setPassword(pwd);
                }

                userService.update(user);
                if (ids != null) {
                    userService.deleteUserRole(user.getAccount());
                    userService.addUserRole(user.getAccount(), ids);
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
            map.put("User", user);
            map.put("ids[]", ids);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/user/{ids}")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message delete(@PathVariable("ids") String[] ids) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(ids)) {
                for (String id : ids) {
                    userService.delete(id);
                    userService.deleteUserRole(id);
                }
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("删除失败,id不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("String[]", ids);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 获得用户角色
     *
     * @param account 用户账号
     * @return
     */
    @GetMapping("/userRole/{account}")
    @ResponseBody
    public Message getUserRole(@PathVariable("account") String account) {
        Message message = new Message();
        try {
            if (!StringUtils.isEmpty(account)) {
                List<Map<String, String>> list = userService.getUserRole(account);
                List<String> idList = new ArrayList<>();
                for (Map<String, String> map : list) {
                    idList.add(map.get("roleId"));
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
            map.put("account", account);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }

    /**
     * 上传图片
     */
    @PostMapping("/user/picture/upload")
    @ResponseBody
    public Message upload(@RequestPart("file") MultipartFile picture) {
        Message message = new Message();

        if (picture.isEmpty() || StringUtils.isEmpty(picture.getOriginalFilename())) {
            message.setStatus("fail");
            message.setMessage("文件不能为空！");
            return message;
        }
        String pictureName = picture.getOriginalFilename();
        //String pictureName = UUID.randomUUID().toString() + ".jpg";
        try {
            String fileSavePath = geminiProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage("上传失败！");
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        message.setStatus("success");
        message.setMessage("上传成功！");
        message.setData(pictureName);
        return message;
    }

    /**
     * 用户导入
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/user/import")
    @ResponseBody
    public Message batchImport(@RequestParam(value = "file") MultipartFile file) {
        Message message = new Message();
        try {
            //判断文件是否为空
            if (file == null) {
                message.setStatus("fail");
                message.setMessage("文件不能为空！");
                return message;
            }

            //获取文件名
            String fileName = file.getOriginalFilename();

            //验证文件名是否合格
            if (!ExcelImportUtils.validateExcel(fileName)) {
                message.setStatus("fail");
                message.setMessage("文件必须是excel格式！");
                return message;
            }

            //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
            long size = file.getSize();
            if (StringUtils.isEmpty(fileName) || size == 0) {
                message.setStatus("fail");
                message.setMessage("文件内容不能为空！");
                return message;
            }

            if (!fileName.matches(Poi314ExcelUtils.xls) && !fileName.matches(Poi314ExcelUtils.xlsx)) {
                message.setStatus("fail");
                message.setMessage("上传文件格式不正确！");
                return message;
            }
            boolean isExcel2003 = true;
            if (fileName.matches(Poi314ExcelUtils.xlsx)) {
                isExcel2003 = false;
            }
            InputStream is = file.getInputStream();
            Workbook wb;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);
            User user;
            List<User> userList = new ArrayList<>();
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                StringBuffer sb = new StringBuffer();
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }

                user = new User();

                //用户账号
                if (StringUtils.isEmpty(row.getCell(0))) {
                    sb.append("用户账号不能为空。");
                } else {
                    if (row.getCell(0).getCellType() != 1) {
                        logger.error("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
                    }
                    String account = row.getCell(0).getStringCellValue();
                    user.setAccount(account);
                }

                //用户名称
                if (StringUtils.isEmpty(row.getCell(1))) {
                    sb.append("用户名称不能为空。");
                } else {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String name = row.getCell(1).getStringCellValue();
                    user.setName(name);
                }
                if (!StringUtils.isEmpty(sb.toString())) {
                    user.setImportStatus(sb.toString());
                }
                userList.add(user);
            }
            message.setStatus("success");
            message.setData(userList);
        } catch (Exception e) {
            e.printStackTrace();
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("MultipartFile", file);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }

        return message;
    }


    /**
     * 用户导出
     *
     * @return
     */
    @GetMapping("/user/export")
    public void export(HttpServletResponse response) {
        try {
            // 第一步：定义文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
            String fileName = "用户导出-" + sdf.format(new Date()) + ".xlsx";
            // 第二步：定义工作簿名称
            String sheetName = "用户导出";
            // 第三步：设置表头名称
            String[] headNames = {"用户账号", "用户名称", "创建时间"};
            // 第四步：设置字段名称
            String[] fieldNames = {"account", "name", "createDate"};
            // 第五步：设置列宽
            Short[] columnWidths = {5000, 6000, 7000};
            // 第六步：组装数据
            List<Map<String, Object>> dataList = new ArrayList<>();
            List<User> list = userService.selectList(null);
            for (User user : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("account", user.getAccount());
                map.put("name", user.getName());
                map.put("createDate", DateUtils.getDateTime(user.getCreateDate()));
                dataList.add(map);
            }
            // 第七步：调用工具类
            Poi314ExcelUtils.exportExcel(fileName, sheetName, headNames, fieldNames, columnWidths, dataList, response, Poi314ExcelUtils.EXCEL_2010, -1);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("HttpServletResponse", response);
            excpLogService.add(ExcpLog.addResponseResult("/user/export", map, e.getMessage()));
            logger.error(e.getMessage());
        }

    }

    /**
     * 重置密码
     *
     * @param userList 用户列表
     * @return
     */
    @PutMapping("/user/restPwd")
    @Transactional(rollbackFor = {Exception.class})
    @ResponseBody
    public Message restPwd(@RequestBody List<User> userList) {
        Message message = new Message();
        try {
            if (userList != null && userList.size() > 0) {
                for (User user : userList) {
                    String pwd = MD5Util.encryption("123456", user.getAccount());
                    user.setPassword(pwd);
                    userService.update(user);
                }
                message.setStatus("success");
            } else {
                message.setStatus("fail");
                message.setMessage("更新失败,用户列表不能为空");
            }
        } catch (Exception e) {
            message.setStatus("fail");
            message.setMessage(e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("List<User>", userList);
            excpLogService.add(ExcpLog.addResponseResult("/user", map, e.getMessage()));
            logger.error(e.getMessage());
        }
        return message;
    }


}
