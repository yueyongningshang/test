package com.zFrame.control;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.TestExportExcel;
import com.zFrame.util.ExportExcel;

@Controller
@RequestMapping("/testexport")
public class TestExcelControl {

    @Autowired
    protected HttpServletRequest request;

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        OutputStream out = null;
        try {
            String testStr = "[{'name':'汉字','no':'no0'},{'name':'test1','no':'no1'},{'name':'test2','no':'no2'}]";
            String className = "com.zFrame.entity.Test";// 获取类名称
            Class clazz = Class.forName(className);
            Field[] fieldArray = clazz.getDeclaredFields();

            String fileName = "测试.xls";
            ArrayList<?> dataList = (ArrayList<?>) JSONObject.parseArray(testStr, clazz);
            String str = JSONObject.toJSONString(dataList);
            System.out.println("str***********************" + str);
            // 对应的实体类的字段值
            String[] fields = { "name", "no" };
            // 对应的excel表格的
            String[] headers = { "名称headers", "性别headers" };
            // 对应的excel宽度
            Integer[] widthArray = { 10000, 10000 };
            int[] columnWidths = ArrayUtils.toPrimitive(widthArray);

            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();

            new ExportExcel().exportExcel(fileName, headers, fields, columnWidths, dataList, out,
                    "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/export1", method = RequestMethod.GET)
    public void export(HttpServletResponse response) throws IOException {
        List<TestExportExcel> queryAll = new ArrayList<TestExportExcel>();
        // 工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("测试表");
        // 设置导出的表的名字
        String fileName = "ceshi.xls";
        // 设置头
        int rowNum = 1;
        String[] headers = { "学号", "姓名", "创建时间" };
        HSSFRow row = sheet.createRow(0);
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);

        }

        for (TestExportExcel testExportExcel : queryAll) {
            HSSFRow rown = sheet.createRow(rowNum);
            rown.createCell(0).setCellValue(testExportExcel.getId());
            rown.createCell(1).setCellValue(testExportExcel.getName());
            HSSFCell createCell = rown.createCell(2);
            createCell.setCellValue(testExportExcel.getCreatTime());
            createCell.setCellStyle(style);
            rowNum++;
        }
        response.setContentType("multipart/form-data");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
        workbook.close();

    }

}