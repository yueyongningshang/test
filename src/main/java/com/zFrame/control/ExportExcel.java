package com.zFrame.control;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * POI动态导出EXCEL文档
 * 
 * @version v1.0
 * @param <T>
 *   应用泛型，代表任意一个符合javabean风格的类
 *   注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *   byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> {
    // 分割符
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /**
     * 
     * @param title 表格标题名
     * @param headers 表格属性列名数组
     * @param columnWidths 表格列的宽度数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public void exportExcel(String fileName, String[] headers, String[] fields, Collection<T> dataset, OutputStream out,
            String pattern, String title) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(fileName);

        // 字体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);// 设置字体大小

        // 设置数字的显示样式，防止数字过长显示科学计数法
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setFont(font);

        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cellStyle2.setFont(font);

        // 产生表格主标题行
        HSSFRow row1 = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9)); // 创建一个居中格式
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue(title);
        cell1.setCellStyle(cellStyle);

        HSSFRow row2 = sheet.createRow(1);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7)); // 创建一个居中格式
        HSSFCell cell2 = row2.createCell(5);
        cell2.setCellValue("税款所属期：");
        cell2.setCellStyle(cellStyle2);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9)); // 创建一个居中格式
        HSSFCell cell21 = row2.createCell(8);
        cell21.setCellValue("金额：");
        cell21.setCellStyle(cellStyle2);

        HSSFRow row3 = sheet.createRow(2);
        HSSFCell cell3 = row3.createCell(0);
        cell3.setCellValue("填票单位：");
        cell3.setCellStyle(cellStyle2);

        HSSFCell cell31 = row3.createCell(8);
        cell31.setCellValue("纳税人识别号：");
        cell31.setCellStyle(cellStyle2);

        // 产生表格标题行
        HSSFRow row4 = sheet.createRow(3);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row4.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle2);
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row4 = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性属性名称，获得得到属性值
            Class<?> cla = t.getClass();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row4.createCell(i);
                try {
                    String name = fields[i];
                    if (cla.getName().contains("cglib")) {
                        name = "$cglib_prop_" + fields[i];
                    }
                    Field field = cla.getDeclaredField(name);
                    field.setAccessible(true);
                    // 取得obj对象这个Field上的值
                    Object value = field.get(t);
                    // 判断值的类型后进行强制类型转换
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Double || value instanceof BigDecimal) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        textValue = df.format(value);
                        if (textValue != null && !"".equals(textValue)) {
                            // 设置Double类型显示样式
                            cell.setCellStyle(cellStyle);
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        }

                    } else if (value instanceof Integer) {
                        textValue = value.toString();
                        cell.setCellValue(Integer.parseInt(textValue));
                    } else if (value != null) {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                        cell.setCellValue(textValue);
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        try {
            // 编写函数
            // row.getCell(2).setCellFormula("SUM(M2:M"+index+")");
            workbook.setForceFormulaRecalculation(true);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}