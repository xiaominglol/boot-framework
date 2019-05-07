package com.gemini.common.utils;//package com.gemini.base.utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletResponse;
//
//import jxl.Workbook;
//import jxl.biff.DisplayFormat;
//import jxl.format.Border;
//import jxl.format.BorderLineStyle;
//import jxl.format.Colour;
//import jxl.format.UnderlineStyle;
//import jxl.format.VerticalAlignment;
//import jxl.write.Label;
//import jxl.write.Number;
//import jxl.write.NumberFormats;
//import jxl.write.WritableCell;
//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;
//
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.util.StringUtils;
//
//
///**
// * Excel工具类，封装了常用的Excel导入/导出的操作
// * 依赖组件Jxl(JavaExcelApi)/POI的实现
// *
// * @author 小明不读书
// */
//public class ExcelUtils {
//
//    public static final int EXCEL_2010 = 2010;
//    public static final int EXCEL_2003 = 2003;
//
//    public static final int EXCEL_2010_MAX_ROW = 65536;
//    public static final int EXCEL_2003_MAX_ROW = 1048576;
//
//    /**
//     * 工作薄名称
//     */
//    private String sheetName = "sheet1";
//    /**
//     * 导出的字段名称
//     */
//    private String[] exportFieldName;
//    /**
//     * 要导出的字段名(即数据库字段名)
//     */
//    private String[] exportField;
//    /**
//     * 下载路径
//     */
//    private String path;
//    private String excelPath = File.separator + "excel";
//
//    /**
//     * 构造器
//     *
//     * @param path            Excel存放路径
//     * @param sheetName       工作薄名称
//     * @param exportFieldName 导出的字段名称数组
//     * @param exportField     数据库中要导出的字段名数组
//     */
//    public ExcelUtils(String path, String sheetName, String[] exportFieldName, String[] exportField) {
//        this.path = path;
//        this.sheetName = sheetName;
//        this.exportFieldName = exportFieldName;
//        this.exportField = exportField;
//    }
//
//
//    /**
//     * 导出excel,支持2003、2010版，支持限制每个sheet行数
//     *
//     * @param dataList     需要导入的数据
//     * @param excelVersion excel版本：2003、2010
//     * @param sheetName    sheet名称
//     * @param displayNames 表头
//     * @param fieldNames   写入对象的属性
//     * @param columnWidths 列宽
//     * @param sheetSize    每个sheet行数
//     * @param out          输出流
//     * @throws
//     */
//    public static void exportExcel(List dataList, int excelVersion,String sheetName,
//                                   String[] displayNames, String[] fieldNames,
//                                   Short[] columnWidths, int sheetSize, OutputStream out) {
//        //工作薄
//        org.apache.poi.ss.usermodel.Workbook wb;
//        org.apache.poi.ss.usermodel.Sheet sheet;
//        org.apache.poi.ss.usermodel.Row row;
//        org.apache.poi.ss.usermodel.Cell cell;
//
//        if (excelVersion != EXCEL_2003 && excelVersion != EXCEL_2010) {
//            excelVersion = EXCEL_2010;
//        }
//
//        int sheetNum;
//        if (excelVersion == EXCEL_2003) {
//            wb = new HSSFWorkbook();
//            sheetSize = sheetSize <= 0 ? EXCEL_2003_MAX_ROW : sheetSize;
//            sheetNum = dataList.size() % EXCEL_2003_MAX_ROW == 0 ? dataList.size() / EXCEL_2003_MAX_ROW : dataList.size() / EXCEL_2003_MAX_ROW + 1;
//        } else {
//            wb = new XSSFWorkbook();
//            sheetSize = sheetSize <= 0 ? EXCEL_2010_MAX_ROW : sheetSize;
//            sheetNum = dataList.size() % EXCEL_2010_MAX_ROW == 0 ? dataList.size() / EXCEL_2010_MAX_ROW : dataList.size() / EXCEL_2010_MAX_ROW + 1;
//        }
//
//        //表头样式
//        CellStyle headStyle = wb.createCellStyle();
//        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//
//        headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        headStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
//
//        headStyle.setBorderTop(CellStyle.BORDER_THIN);
//        headStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        headStyle.setBorderRight(CellStyle.BORDER_THIN);
//
//        CellStyle bodyStyle = wb.createCellStyle();
//        bodyStyle.setAlignment(CellStyle.ALIGN_LEFT);
//        bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//
//        bodyStyle.setBorderTop(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderRight(CellStyle.BORDER_THIN);
//        //设置自动换行
//        bodyStyle.setWrapText(true);
//
//
//        for (int index = 0; index < sheetNum; index++) {
//            sheet = wb.createSheet();
//            wb.setSheetName(index, sheetName + index);
//            row = sheet.createRow(0);
//
//            //设置表头高度
//            row.setHeight((short) 750);
//
//            //写入表头
//            for (int i = 0; i < displayNames.length; i++) {
//                cell = row.createCell(i);
//                cell.setCellStyle(headStyle);
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                cell.setCellValue(displayNames[i]);
//            }
//
//            int startNo = index * sheetSize;
//            int endNo = Math.min(startNo + sheetSize, dataList.size());
//
//            /*写数据*/
//            for (int i = startNo; i < endNo; i++) {
//                row = sheet.createRow(i + 1 - startNo);
//                HashMap map = (HashMap) dataList.get(i);
//                for (int j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(bodyStyle);
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
//
//                    Object value = map.get(fieldNames[j]);
//                    if (value == null) {
//                        cell.setCellValue("");
//                    } else {
//                        if ((value instanceof BigDecimal && isNumber(((BigDecimal) value).toString()))) {
//                            //添加带有formatting的Number对象
//                            cell.setCellValue(((BigDecimal) value).doubleValue());
//                        } else {
//                            //cell.setCellValue(map.get(fieldNames[j]).toString());
//                            //20160621增加强制换行支持:在需要换行的地方加上"\r\n"
//                            if (excelVersion == EXCEL_2003) {
//                                cell.setCellValue(new HSSFRichTextString(map.get(fieldNames[j]).toString()));
//                            } else {
//                                cell.setCellValue(new XSSFRichTextString(map.get(fieldNames[j]).toString()));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//            //设置列宽
//            if (columnWidths != null && columnWidths.length > 0) {
//                for (int i = 0; i < columnWidths.length; i++) {
//                    sheet.setColumnWidth(i, columnWidths[i]);
//                }
//            }
//        }
//
//        try {
//            out.flush();
//            wb.write(out);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Output is closed ");
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//
//    /**
//     * 将数据集导出到Excel文件
//     * 如果Excel一个工作薄Sheet超过了最大行数maxRow则自动新建一个工作薄
//     *
//     * @param list   数据集
//     * @param maxRow 一个工作薄的容纳的最大行;如果为0，容纳所有行不分页
//     * @return 返回生成的EXCEL文件名, 如果导出失败返回NULL;
//     * @throws Exception
//     */
//    public String exportToExcel(List list, int maxRow) throws Exception {
//        String fileName = null;    //生成的excel文件名
//        int pageIndex = 0;        //工作薄索引
//
//        if (list != null) {
//
//            File foder = new File(path + excelPath);
//            if (!foder.exists()) {
//                foder.mkdirs();
//            }
//
//            fileName = checkPath(path + excelPath) + "查询时间" + newRandomFile();
//            File f = new File(fileName + ".xls");
//            if (!f.exists()) {
//                f.createNewFile();
//            }
//            WritableWorkbook wwb = Workbook.createWorkbook(f);
//            WritableSheet ws = wwb.createSheet(sheetName, pageIndex);
//
//            setFirstRow(ws);
//            int rows = 1;
//            for (Iterator it = list.iterator(); it.hasNext(); rows++) {
//                Map m = (Map) it.next();
//                int cols = 0;
//                for (int i = 0; i < exportField.length; i++, cols++) {
//                    ws.addCell(getCellObject(cols, rows, m.get(exportField[i])));
//                }
//                //创建分页
//                if (maxRow > 0 && rows == maxRow) {
//                    rows = 0;
//                    pageIndex++;
//                    ws = wwb.createSheet(sheetName + pageIndex, pageIndex);
//                    setFirstRow(ws);
//                }
//
//
//            }
//            wwb.write();
//            wwb.close();
//        }
//
//        return fileName + ".xls";
//
//    }
//
//    private WritableCell getCellObject(int col, int row, Object _object) throws WriteException {
//        WritableCell wc = null;
//        if (_object == null) {
//            //wc=new Blank(col,row);
//            WritableFont Bwf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
//            jxl.write.WritableCellFormat CBwcfF = new jxl.write.WritableCellFormat(Bwf);
//            try {
//                //设置垂直对齐为居中对齐
//                CBwcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
//                CBwcfF.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
//            } catch (WriteException e) {
//                e.printStackTrace();
//            }
//            wc = new Label(col, row, "", CBwcfF);
//        } else {
//            if ((_object instanceof BigDecimal && isNumber(((BigDecimal) _object).toString()))) {
//                //添加带有formatting的Number对象
//                WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
//                DisplayFormat displayFormat = NumberFormats.FLOAT;
//                WritableCellFormat format = new WritableCellFormat(wf, displayFormat);
//                format.setAlignment(jxl.format.Alignment.LEFT);
//                format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
//
//                wc = new Number(col, row, ((BigDecimal) _object).doubleValue(), format);
//
//                //添加带有formatting的Number对象
//                /*NumberFormat nf = new NumberFormat("#.##");
//                WritableCellFormat wcfN = new WritableCellFormat(nf);
//				wc = new Number(col, row, ((java.lang.Number)_object).doubleValue(), wcfN);*/
//            } else {
//                //WritableFont Bwf = new WritableFont(WritableFont.createFont("楷体 _GB2312"), 10, WritableFont.NO_BOLD);
//                WritableFont Bwf = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.NO_BOLD, false);
//                //WritableFont  Bwf = new WritableFont(WritableFont.TIMES,10,WritableFont.BOLD,true);
//                jxl.write.WritableCellFormat CBwcfF = new jxl.write.WritableCellFormat(Bwf);
//                try {
//                    //设置垂直对齐为居中对齐
//                    CBwcfF.setVerticalAlignment(VerticalAlignment.CENTRE);
//
//                    CBwcfF.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
//
//                    //设置顶部边框线为实线(默认是黑色－－也可以设置其他颜色)
//                    //CBwcfF.setBorder(jxl.format.Border.TOP, BorderLineStyle.MEDIUM);
//                    if ("已撤销".equals(_object.toString().trim())) {
//                        CBwcfF.setBackground(Colour.RED);
//                    }
//
//                } catch (WriteException e) {
//                    e.printStackTrace();
//                }
//                wc = new Label(col, row, _object.toString(), CBwcfF);
//            }
//
//        }
//
//        return wc;
//    }
//
//    /**
//     * 设置第一行字段名
//     *
//     * @param ws
//     * @throws Exception
//     */
//    private void setFirstRow(WritableSheet ws) throws Exception {
//        for (int i = 0; i < exportFieldName.length; i++) {
//            //添加带有字体颜色Formatting的对象
//            WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
//                    UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
//            WritableCellFormat wcfFC = new WritableCellFormat(wfc);
//            Label _name = new Label(i, 0, exportFieldName[i], wcfFC);
//            ws.addCell(_name);
//        }
//
//    }
//
//    /**
//     * 导出结果集到Excel(不分页)
//     *
//     * @param list 结果集ArrayList 对象
//     * @return
//     * @throws Exception
//     */
//    public String exportToExcel(ArrayList list) throws Exception {
//        return exportToExcel(list, 0);
//    }
//
//    /**
//     * 将数据集导出到Excel并且压缩生成Zip文件
//     *
//     * @param list   数据集
//     * @param maxRow 每页(工作薄)最大行数，为0表示不分页
//     * @return Zip文件名称，如果压缩失败返回NULL
//     * @throws Exception
//     */
//    public String exportToExcelAndZip(ArrayList list, int maxRow) throws Exception {
//        String zipFile = null;
//        String fileName = "";
//        try {
//            fileName = this.exportToExcel(list, maxRow);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        if (fileName != null) {
//            zipFile = fileName + ".zip";
//            String excelFile = fileName + ".xls";
//            //ZipUtil.zipFile(zipFile, excelFile);
//            //删除excel文件
//            try {
//                File f = new File(excelFile);
//                if (f.exists()) {
//                    f.delete();
//                }
//            } catch (Exception e) {
//            }
//        }
//        return zipFile;
//    }
//
//    public String exportToExcelAndZip(ArrayList list) throws Exception {
//        return exportToExcelAndZip(list, 0);
//    }
//
//    public void importToDb() {
//
//    }
//
//    public void setExportFieldName(String[] exportFieldName) {
//        this.exportFieldName = exportFieldName;
//
//    }
//
//    public void setSheetName(String sheetName) {
//        this.sheetName = sheetName;
//
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//
//    }
//
//    public void setExportField(String[] exportField) {
//        this.exportField = exportField;
//    }
//
//    public String getExcelPath() {
//        return excelPath;
//    }
//
//    public void setExcelPath(String excelPath) {
//        this.excelPath = excelPath;
//    }
//
//    /**
//     * 以日期时间字符串生成文件名
//     *
//     * @return
//     */
//    private String newRandomFile() {
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
//        String fileName = sdf.format(new Date());
//        return fileName;
//    }
//
//    /**
//     * 判断Excel存放路径path是否以'/'结尾
//     *
//     * @param path excel路径
//     * @return 返回以'/'结尾的路径名
//     */
//    private String checkPath(String path) {
//        if (!path.endsWith(File.separator)) {
//            path += File.separator;
//        }
//        return path;
//
//    }
//
//
//    /**
//     * 判断字符串是否由纯数字组成
//     *
//     * @param str 源字符串
//     * @return true是，false否
//     */
//    private static boolean isNumeric(String str) {
//        if (StringUtils.isEmpty(str)) {
//            return false;
//        }
//        Pattern pattern = Pattern.compile("[0-9]*");
//        return pattern.matcher(str).matches();
//    }
//
//    /**
//     * 判断字符串是否是数字
//     *
//     * @param str 源字符串
//     * @return true是，false否
//     */
//    private static boolean isNumber(String number) {
//        //判断参数
//        if (StringUtils.isEmpty(number)) {
//            return false;
//        }
//        //查看是否有小数点
//        int index = number.indexOf(".");
//        if (index < 0) {
//            return isNumeric(number);
//        } else {
//            //如果有多个".",则不是数字
//            if (number.indexOf(".") != number.lastIndexOf(".")) {
//                return false;
//            }
//            String num1 = number.substring(0, index);
//            String num2 = number.substring(index + 1);
//            return isNumeric(num1) && isNumeric(num2);
//        }
//    }
//
//
//    /**
//     * 导出一个excel，根据OutputStream类型决定是写在本地还是返回输出流到浏览器
//     * 当OutputStream为response.getOutputStream()时是返回输出流到浏览器
//     * 当OutputStream为FileOutputStream(excelPath)时是写到本地
//     * 导出excel,支持2003、2010版，支持限制每个sheet行数
//     *
//     * @param dataList     需要导入的数据
//     * @param excelVersion excel版本：2003、2010
//     * @param displayNames 表头
//     * @param fieldNames   写入对象的属性
//     * @param columnWidths 列宽
//     * @param sheetSize    每个sheet行数
//     * @param out          输出流
//     * @throws
//     */
//    public static void exportExcelToLocal(@SuppressWarnings("rawtypes") List dataList, int excelVersion,
//                                          String sheetName, String[] displayNames, String[] fieldNames, Short[] columnWidths, int sheetSize, OutputStream out) {
//        //工作薄
//        org.apache.poi.ss.usermodel.Workbook wb;
//        org.apache.poi.ss.usermodel.Sheet sheet;
//        org.apache.poi.ss.usermodel.Row row;
//        org.apache.poi.ss.usermodel.Cell cell;
//
//        if (excelVersion != 2003 && excelVersion != 2010) {
//            excelVersion = 2010;
//        }
//
//        int sheetNum = 1;
//        if (excelVersion == 2003) {
//            wb = new HSSFWorkbook();
//            sheetSize = sheetSize <= 0 ? EXCEL_2003_MAX_ROW : sheetSize;
//            sheetNum = dataList.size() % EXCEL_2003_MAX_ROW == 0 ? dataList.size() / EXCEL_2003_MAX_ROW : dataList.size() / EXCEL_2003_MAX_ROW + 1;
//        } else {
//            wb = new XSSFWorkbook();
//            sheetSize = sheetSize <= 0 ? EXCEL_2010_MAX_ROW : sheetSize;
//            sheetNum = dataList.size() % EXCEL_2010_MAX_ROW == 0 ? dataList.size() / EXCEL_2010_MAX_ROW : dataList.size() / EXCEL_2010_MAX_ROW + 1;
//        }
//
//        //表头样式
//        CellStyle headStyle = wb.createCellStyle();
//        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//
//        headStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
//        headStyle.setBorderTop(CellStyle.BORDER_THIN);
//        headStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        headStyle.setBorderRight(CellStyle.BORDER_THIN);
//
//        CellStyle bodyStyle = wb.createCellStyle();
//        bodyStyle.setAlignment(CellStyle.ALIGN_LEFT);
//        bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        bodyStyle.setBorderTop(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderRight(CellStyle.BORDER_THIN);
//        //设置自动换行20160621加入
//        bodyStyle.setWrapText(true);
//
//
//        for (int index = 0; index < sheetNum; index++) {
//            sheet = wb.createSheet();
//            wb.setSheetName(index, sheetName + index);
//            row = sheet.createRow(0);
//
//            //设置表头高度
//            row.setHeight((short) 750);
//
//            //写入表头
//            for (int i = 0; i < displayNames.length; i++) {
//                cell = row.createCell(i);
//                cell.setCellStyle(headStyle);
//                cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
//                cell.setCellValue(displayNames[i]); //给表头赋值
//            }
//
//            int startNo = index * sheetSize;
//            int endNo = Math.min(startNo + sheetSize, dataList.size());
//
//            //写数据
//            for (int i = startNo; i < endNo; i++) {
//                row = sheet.createRow(i + 1 - startNo);
//                @SuppressWarnings("rawtypes")
//                HashMap map = (HashMap) dataList.get(i);
//                for (int j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(bodyStyle);
//                    cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
//
//                    Object value = map.get(fieldNames[j]);
//                    if (value == null) {
//                        cell.setCellValue("");
//                    } else {
//                        if ((value instanceof BigDecimal && isNumber(((BigDecimal) value).toString()))) {
//                            //添加带有formatting的Number对象
//                            cell.setCellValue(((BigDecimal) value).doubleValue());
//                        } else {
//                            //cell.setCellValue(map.get(fieldNames[j]).toString());
//                            //20160621增加强制换行支持:在需要换行的地方加上"\r\n"
//                            if (excelVersion == 2003) {
//                                cell.setCellValue(new HSSFRichTextString(map.get(fieldNames[j]).toString()));
//                            } else {
//                                cell.setCellValue(new XSSFRichTextString(map.get(fieldNames[j]).toString()));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//            //设置列宽
//            if (columnWidths != null && columnWidths.length > 0) {
//                for (int i = 0; i < columnWidths.length; i++) {
//                    sheet.setColumnWidth(i, columnWidths[i]);
//                }
//            }
//        }
//
//        try {
//            out.flush();
//            wb.write(out);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Output is closed ");
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 导出一个excel多个sheet
//     * 导出excel,支持2003、2010版，支持限制每个sheet行数
//     *
//     * @param dataList     需要导入的数据
//     * @param excelVersion excel版本：2003、2010
//     * @param displayNames 表头
//     * @param fieldNames   写入对象的属性
//     * @param columnWidths 列宽
//     * @param sheetSize    每个sheet行数
//     * @param out          输出流
//     * @throws
//     */
//    public static void exportExcelMoreSheet(List<Map<String, Object>> data, int excelVersion, int sheetSize, OutputStream out) {
//        //工作薄
//        org.apache.poi.ss.usermodel.Workbook wb;
//        org.apache.poi.ss.usermodel.Sheet sheet;
//        org.apache.poi.ss.usermodel.Row row;
//        org.apache.poi.ss.usermodel.Cell cell;
//
//        if (excelVersion != 2003 && excelVersion != 2010) {
//            excelVersion = 2010;
//        }
//        if (excelVersion == 2003) {
//            wb = new HSSFWorkbook();
//        } else {
//            wb = new XSSFWorkbook();
//        }
//        //表头样式
//        CellStyle headStyle = wb.createCellStyle();
//        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
//
//        headStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
//        headStyle.setBorderTop(CellStyle.BORDER_THIN);
//        headStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        headStyle.setBorderRight(CellStyle.BORDER_THIN);
//
//        CellStyle bodyStyle = wb.createCellStyle();
//        bodyStyle.setAlignment(CellStyle.ALIGN_LEFT);
//        bodyStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//        bodyStyle.setBorderTop(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderBottom(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderLeft(CellStyle.BORDER_THIN);
//        bodyStyle.setBorderRight(CellStyle.BORDER_THIN);
//        //设置自动换行20160621加入
//        bodyStyle.setWrapText(true);
//
//        int totalSheetNum = 0;
//        if (data != null && data.size() > 0) {
//            for (Map<String, Object> sheetMap : data) {
//                String sheetName = (String) sheetMap.get("sheetName");
//                List<Map<String, Object>> dataList = (List<Map<String, Object>>) sheetMap.get("dataList");
//                List<String> displayNames = (List<String>) sheetMap.get("displayNames");
//                List<String> fieldNames = (List<String>) sheetMap.get("fieldNames");
//                List<Short> columnWidths = (List<Short>) sheetMap.get("columnWidths");
//                if (dataList != null && dataList.size() > 0) {
//                    int sheetNum = 1;
//                    if (excelVersion == 2003) {
//                        sheetSize = sheetSize <= 0 ? EXCEL_2003_MAX_ROW : sheetSize;
//                        sheetNum = dataList.size() % EXCEL_2003_MAX_ROW == 0 ? dataList.size() / EXCEL_2003_MAX_ROW : dataList.size() / EXCEL_2003_MAX_ROW + 1;
//                    } else {
//                        sheetSize = sheetSize <= 0 ? EXCEL_2010_MAX_ROW : sheetSize;
//                        sheetNum = dataList.size() % EXCEL_2010_MAX_ROW == 0 ? dataList.size() / EXCEL_2010_MAX_ROW : dataList.size() / EXCEL_2010_MAX_ROW + 1;
//                    }
//
//                    for (int index = 0; index < sheetNum; index++) {
//                        sheet = wb.createSheet();
//
//                        wb.setSheetName(totalSheetNum + index, sheetName + (totalSheetNum + index));
//                        row = sheet.createRow(0);
//
//                        //设置表头高度
//                        row.setHeight((short) 750);
//
//                        //写入表头
//                        for (int i = 0; i < displayNames.size(); i++) {
//                            cell = row.createCell(i);
//                            cell.setCellStyle(headStyle);
//                            cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
//                            cell.setCellValue(displayNames.get(i)); //给表头赋值
//                        }
//
//                        int startNo = index * sheetSize;
//                        int endNo = Math.min(startNo + sheetSize, dataList.size());
//
//                        //写数据
//                        for (int i = startNo; i < endNo; i++) {
//                            row = sheet.createRow(i + 1 - startNo);
//                            @SuppressWarnings("rawtypes")
//                            HashMap map = (HashMap) dataList.get(i);
//                            for (int j = 0; j < fieldNames.size(); j++) {
//                                cell = row.createCell(j);
//                                cell.setCellStyle(bodyStyle);
//                                cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
//
//                                Object value = map.get(fieldNames.get(j));
//                                if (value == null) {
//                                    cell.setCellValue("");
//                                } else {
//                                    if ((value instanceof BigDecimal && isNumber(((BigDecimal) value).toString()))) {
//                                        //添加带有formatting的Number对象
//                                        cell.setCellValue(((BigDecimal) value).doubleValue());
//                                    } else {
//                                        //cell.setCellValue(map.get(fieldNames[j]).toString());
//                                        //20160621增加强制换行支持:在需要换行的地方加上"\r\n"
//                                        if (excelVersion == 2003) {
//                                            cell.setCellValue(new HSSFRichTextString(map.get(fieldNames.get(j)).toString()));
//                                        } else {
//                                            cell.setCellValue(new XSSFRichTextString(map.get(fieldNames.get(j)).toString()));
//                                        }
//
//                                    }
//                                }
//                            }
//                        }
//
//                        //设置列宽
//                        if (columnWidths != null && columnWidths.size() > 0) {
//                            for (int i = 0; i < columnWidths.size(); i++) {
//                                sheet.setColumnWidth(i, columnWidths.get(i));
//                            }
//                        }
//                    }
//                    totalSheetNum = totalSheetNum + sheetNum;
//                }
//            }
//        }
//
//        try {
//            out.flush();
//            wb.write(out);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Output is closed ");
//        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static OutputStream setOutputStream(HttpServletResponse response,
//                                               String fileName) {
//        response.setContentType("octets/stream");
//        response.addHeader("Content-Type", "text/html; charset=utf-8");
//        String downLoadName = "";
//        try {
//            downLoadName = new String(fileName.getBytes("utf-8"), "iso8859-1");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            System.out.println("文件名转码出错");
//        }
//        response.addHeader("Content-Disposition", "attachment;filename=" + downLoadName);
//
//        OutputStream out = null;
//        try {
//            out = response.getOutputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return out;
//    }
//
//    public static void main(String[] args) throws Exception {
//
//
//        String sql = "select * from t_sys_user ";
//
//        String[] exportFields = new String[]{"account", "name", "create_date"};
//        String path = "d:/";
//        String sheetName = "代理商信息";
//        String[] exportName = new String[]{"用户账号", "用户名称", "创建时间"};
//
//        ExcelUtils excel = new ExcelUtils(path, sheetName, exportName, exportFields);
//        ArrayList list = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            Map map = new HashMap();
//            for (String field : exportFields) {
//                map.put(field, field + "" + i);
//            }
//            list.add(map);
//        }
//
//        excel.exportToExcel(list, 5000);
//
//    }
//
//
//}
