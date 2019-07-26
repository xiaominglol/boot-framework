package com.gemini.boot.framework.admin.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gemini.boot.framework.admin.common.annotation.SysLog;
import com.gemini.boot.framework.admin.common.properties.GeminiProperties;
import com.gemini.boot.framework.admin.module.sys.model.ExcpLog;
import com.gemini.boot.framework.admin.module.sys.model.User;
import com.gemini.boot.framework.admin.module.sys.service.ExcpLogService;
import com.gemini.boot.framework.admin.module.sys.service.UserService;
import com.gemini.boot.framework.admin.module.sys.utils.UserUtils;
import com.gemini.boot.framework.common.web.mvc.controller.BaseController;
import com.gemini.boot.framework.common.web.mvc.model.CommonFailInfo;
import com.gemini.boot.framework.common.web.mvc.model.LayUiPage;
import com.gemini.boot.framework.common.web.mvc.model.Message;
import com.gemini.boot.framework.common.web.utils.DateUtils;
import com.gemini.boot.framework.common.web.utils.ExcelImportUtils;
import com.gemini.boot.framework.common.web.utils.MD5Util;
import com.gemini.boot.framework.common.web.utils.Poi314ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @author 小明不读书
 * @date 2017-11-03
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    ExcpLogService excpLogService;
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
    @SysLog("查询用户列表")
    @GetMapping("/user")
    @ResponseBody
    public Message getPageList(LayUiPage layUiPage, User user) {
        try {
            QueryWrapper<User> qw = new QueryWrapper<>();
            if (!StringUtils.isEmpty(user.getName())) {
                qw.like("name", user.getName()).or().like("account", user.getName());
            }
            if (!StringUtils.isEmpty(user.getOrgId())) {
                qw.eq("org_id", user.getOrgId());
            }
            IPage<User> list = userService.page(new Page<>(layUiPage.getPageNum(), layUiPage.getPageSize()), qw);
            return Message.success(list);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 通过ID获取
     *
     * @param id 主键ID
     */
    @GetMapping("/user/{id}")
    @ResponseBody
    public Message getById(@PathVariable("id") String id) {
        try {
            if (!StringUtils.isEmpty(id)) {
                User user = userService.getById(id);
                return Message.success(user);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 保存
     *
     * @param user 用户
     */
    @SysLog("添加用户")
    @PostMapping("/user")
    @ResponseBody
    public Message save(User user, @RequestParam(value = "ids[]") String[] ids) {
        try {
            if (StringUtils.isEmpty(user.getId())) {
                String pwd = MD5Util.encryption(user.getPassword(), user.getAccount());
                user.setPassword(pwd);
                User currentUser = UserUtils.getCurrentUser();
                user.setOptId(currentUser.getAccount());
                user.setOptName(currentUser.getName());
                userService.save(user);
                userService.addUserRole(user.getAccount(), ids);
                return Message.success(user);
            } else {
                return Message.fail(CommonFailInfo.Id_ALREADY_EXIST);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 批量保存
     *
     * @param userList 用户列表
     */
    @SysLog("批量添加用户")
    @PostMapping("/user/batchSave")
    @ResponseBody
    public Message batchSave(@RequestBody(required = false) List<User> userList) {
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
                    userService.save(user);
                }
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.USER_LIST_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 更新
     *
     * @param user 用户
     * @return
     */
    @SysLog("更新用户")
    @PutMapping("/user")
    @ResponseBody
    public Message update(User user, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
            if (!StringUtils.isEmpty(user.getAccount())) {
                User currentUser = UserUtils.getCurrentUser();
                user.setOptId(currentUser.getAccount());
                user.setOptName(currentUser.getName());

                User oldUser = userService.getById(user.getId());
                //如果密码不一样，证明在修改密码，所以要加密保存，如果密码一样，则不需要再加密
                if (!user.getPassword().equals(oldUser.getPassword())) {
                    String pwd = MD5Util.encryption(user.getPassword(), user.getAccount());
                    user.setPassword(pwd);
                }
                userService.updateById(user);
                if (ids != null) {
                    userService.deleteUserRole(user.getAccount());
                    userService.addUserRole(user.getAccount(), ids);
                }
                return Message.success(user);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @SysLog("删除用户")
    @DeleteMapping("/user/{ids}")
    @ResponseBody
    public Message delete(@PathVariable("ids") String[] ids) {
        try {
            if (!StringUtils.isEmpty(ids)) {
                for (String id : ids) {
                    userService.removeById(id);
                    userService.deleteUserRole(id);
                }
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
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
        try {
            if (!StringUtils.isEmpty(account)) {
                List<Map<String, String>> list = userService.getUserRole(account);
                List<String> idList = new ArrayList<>();
                for (Map<String, String> map : list) {
                    idList.add(map.get("roleId"));
                }
                return Message.success(idList);
            } else {
                return Message.fail(CommonFailInfo.Id_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 上传图片
     */
    @PostMapping("/user/picture/upload")
    @ResponseBody
    public Message upload(@RequestPart("file") MultipartFile picture) {
        if (picture.isEmpty() || StringUtils.isEmpty(picture.getOriginalFilename())) {
            return Message.fail(CommonFailInfo.FILE_CAN_NOT_BE_EMPTY);
        }
        String pictureName = picture.getOriginalFilename();
        try {
            String fileSavePath = geminiProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
            return Message.success(pictureName);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

    /**
     * 用户导入
     *
     * @param file
     * @return
     * @throws IOException
     */
    @SysLog("用户导入")
    @PostMapping("/user/import")
    @ResponseBody
    public Message batchImport(@RequestParam(value = "file") MultipartFile file) {
        try {
            //判断文件是否为空
            if (file == null) {
                return Message.fail(CommonFailInfo.FILE_CAN_NOT_BE_EMPTY);
            }

            //获取文件名
            String fileName = file.getOriginalFilename();

            //验证文件名是否合格
            if (!ExcelImportUtils.validateExcel(fileName)) {
                return Message.fail(CommonFailInfo.FILE_MUST_BE_EXCEL_FORMAT);
            }

            //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
            long size = file.getSize();
            if (StringUtils.isEmpty(fileName) || size == 0) {
                return Message.fail(CommonFailInfo.FILE_CONTENT_CAN_NOT_BE_EMPTY);
            }

            if (!fileName.matches(Poi314ExcelUtils.xls) && !fileName.matches(Poi314ExcelUtils.xlsx)) {
                return Message.fail(CommonFailInfo.FILE_FORMAT_INCORRECT);
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
            return Message.success(userList);
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }


    /**
     * 用户导出
     *
     * @return
     */
    @SysLog("用户导出")
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
            List<User> list = userService.list();
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
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
        }

    }

    /**
     * 重置密码
     *
     * @param userList 用户列表
     * @return
     */
    @SysLog("重置密码")
    @PutMapping("/user/restPwd")
    @ResponseBody
    public Message restPwd(@RequestBody List<User> userList) {
        try {
            if (userList != null && userList.size() > 0) {
                for (User user : userList) {
                    String pwd = MD5Util.encryption(MD5Util.INIT_PASSWORD, user.getAccount());
                    user.setPassword(pwd);
                    userService.updateById(user);
                }
                return Message.success(null);
            } else {
                return Message.fail(CommonFailInfo.USER_LIST_CAN_NOT_BE_EMPTY);
            }
        } catch (Exception e) {
            excpLogService.save(ExcpLog.saveExcpLog(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "()", e.getMessage(), logger));
            return Message.fail(e.getMessage());
        }
    }

}