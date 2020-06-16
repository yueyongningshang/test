package com.zFrame.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zFrame.entity.ExcelInvoiceVo;
import com.zFrame.util.BillInfo;
import com.zFrame.util.HttpUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/export")
public class ExcelControl {

    @Autowired
    protected HttpServletRequest request;

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        OutputStream out = null;
        String params = request.getParameter("params");
        JSONObject obj = JSONObject.fromObject(params);
        try {
            String url = obj.getString("url");
            JSONArray cols = obj.getJSONArray("cols");
            String jsonRes = HttpUtil.post(url, putParamsToMap(obj), request.getSession().getId(),
                    request.getSession().getAttribute("userId").toString());
            JSONObject result = JSONObject.fromObject(jsonRes);
            JSONArray array = result.getJSONArray("rows");
            String className = obj.getString("className");// 获取类名称
            String fileName = obj.getString("fileName") + ".xls";
            HashMap<String, Object> maps = new HashMap<String, Object>();
            if (obj.containsKey("charset")) {
                fileName = new String(obj.getString("fileName").getBytes("ISO-8859-1"), obj.getString("charset"))
                        + ".xls";// 文件名称
                maps = getHeadersMap(cols, obj.getString("charset"));
            } else {
                maps = getHeadersMap(cols, "");
            }
            ArrayList<?> dataList = jsonToList(className, array);
            String[] fields = (String[]) maps.get("headerArray");
            String[] headers = (String[]) maps.get("nameArray");
            Integer[] widthArray = (Integer[]) maps.get("widthArray");
            int[] columnWidths = ArrayUtils.toPrimitive(widthArray);

            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();

            // new ExportExcel().exportExcel(fileName, headers, fields,
            // columnWidths, dataList, out,
            // "yyyy-MM-dd HH:mm:ss");
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

    // 构建参数map
    @SuppressWarnings("unchecked")
    private HashMap<String, String> putParamsToMap(JSONObject obj) {
        HashMap<String, String> parameterMap = new HashMap<String, String>();
        String page = "1";
        String rows = "60000";
        if (obj.containsKey("page")) {
            page = obj.getString("page");
        }
        if (obj.containsKey("rows")) {
            rows = obj.getString("rows");
        }
        parameterMap.put("page", page);
        parameterMap.put("rows", rows);
        if (obj.containsKey("queryParams")) {
            JSONObject paramsArray = obj.getJSONObject("queryParams");
            Iterator<String> keys = paramsArray.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = paramsArray.getString(key);
                parameterMap.put(key, value);
            }
        }
        return parameterMap;
    }

    /**
     * 
     * @Title: jsonToList 
     * @Description: json转list  排除没用的字段
     * @param className
     * @param array
     * @param cols
     * @throws ClassNotFoundException
     * @author zhaojiyan
     * @throws
     */
    private ArrayList<?> jsonToList(String className, JSONArray array) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        ArrayList<?> list = (ArrayList<?>) JSONArray.toCollection(array, clazz);
        return list;
    }

    /**
     * @throws UnsupportedEncodingException 
     * 获得excel前置参数
     * @Title: getHeadersMap 
     * @Description: (描述方法的作用，若该方法包含复杂业务判断处理，需注明逻辑或思路) 
     * @param cols
     * @return
     * @author zhaojiyan
     * @throws
     */
    private HashMap<String, Object> getHeadersMap(JSONArray cols, String charset) throws UnsupportedEncodingException {
        HashMap<String, Object> maps = new HashMap<String, Object>();
        List<String> headerList = new LinkedList<String>();
        List<String> nameList = new LinkedList<String>();
        List<Integer> widthList = new LinkedList<Integer>();
        for (int i = 0; i < cols.size(); i++) {
            String[] value = cols.getString(i).split("#");
            String header = value[0];
            String name = value[1];
            if (StringUtils.isNotEmpty(charset)) {
                name = new String(name.getBytes("ISO-8859-1"), charset);
            }
            int width = 5000;
            headerList.add(header);
            nameList.add(name);
            widthList.add(width);
        }
        String[] headerArray = headerList.toArray(new String[headerList.size()]);
        String[] nameArray = nameList.toArray(new String[nameList.size()]);
        Integer[] widthArray = widthList.toArray(new Integer[widthList.size()]);
        maps.put("headerArray", headerArray);
        maps.put("nameArray", nameArray);
        maps.put("widthArray", widthArray);

        return maps;
    }

    @ApiOperation(value = "导出开票管理列表", notes = "选择数据才可导出")
    @PostMapping("/v1/invoiceManager/excelInvoiceManager")
    public void exportExcel(
            @RequestBody @ApiParam(name = "开票管理主键id 以,隔开", value = "传入json格式", required = true) String jiIdes,
            HttpServletResponse response) {
        OutputStream out = null;
        try {
            // 数据集合
            String json = "";
            json = com.alibaba.fastjson.JSONObject.toJSONString(new ArrayList<com.zFrame.entity.ExcelInvoiceVo>());
            // 对应的实体类的字段值
            Class stuClass = ExcelInvoiceVo.class;
            // 2.获取字段
            Field[] fieldArray = stuClass.getDeclaredFields();
            String[] modelName = new String[fieldArray.length - 2];
            for (int i = 2; i < fieldArray.length; i++) {
                modelName[i - 2] = fieldArray[i].getName();
            }

            String fileName = BillInfo.EXCEL_INVOICE_FILE_NAME;
            // 数据封装
            ArrayList<?> dataList = (ArrayList<?>) com.alibaba.fastjson.JSONObject.parseArray(json, BillInfo.class);
            // 对应的excel表格的头部名称
            String[] headers = BillInfo.EXCEL_INVOICE_HEADERS;

            response.setContentType("multipart/form-data");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            out = response.getOutputStream();

            new ExportExcel().exportExcel(BillInfo.EXCEL_INVOICE_FILE_NAME, BillInfo.EXCEL_INVOICE_HEADERS, modelName,
                    dataList, out, "", BillInfo.EXCEL_INVOICE_TITLE_NAME);
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
}
