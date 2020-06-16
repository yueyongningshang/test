package com.zFrame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
    /** 
     * 使用URLConnection实现GET请求 
     *  
     * 1.实例化一个java.net.URL对象； 2.通过URL对象的openConnection()方法得到一个java.net.URLConnection; 
     * 3.通过URLConnection对象的getInputStream()方法获得输入流； 4.读取输入流； 5.关闭资源； 
     */
    public static String get(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection(); // 打开连接

        // System.out.println(urlConnection.getURL().toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8")); // 获取输入流
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        // System.out.println(sb.toString());
        return sb.toString();
    }

    /** 
     * 使用HttpURLConnection实现POST请求 
     *  
     * 1.实例化一个java.net.URL对象； 2.通过URL对象的openConnection()方法得到一个java.net.URLConnection; 
     * 3.通过URLConnection对象的getOutputStream()方法获得输出流； 4.向输出流中写数据； 5.关闭资源； 
     */
    public static String post(String urlStr, Map<String, String> parameterMap, String sessionId, String userId)
            throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);// 允许连接提交信息
        connection.setRequestMethod("POST");// 网页提交方式“GET”、“POST”
        if (!CommonUtil.isEmpty(sessionId)) {
            connection.setRequestProperty("Cookie", "JSESSIONID=" + sessionId + "; userId=" + userId);
        }

        StringBuffer parameter = new StringBuffer();
        parameter.append("a=1");
        for (Entry<String, String> entry : parameterMap.entrySet()) {
            parameter.append("&" + entry.getKey() + "=" + entry.getValue());
        }
        OutputStream os = connection.getOutputStream();
        os.write(parameter.toString().getBytes("UTF-8"));
        os.close();
        // System.out.println("parameter: " + parameter.toString());

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) { // 读取数据
            sb.append(line + "\n");
        }
        br.close();
        // System.out.println(sb.toString());
        return sb.toString();
    }
}