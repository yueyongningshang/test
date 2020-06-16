package com.zFrame.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.Student;
import com.zFrame.entity.Test;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：JsonControl    
 * 类描述：fastJson,关于json的使用    
 * 创建人：Gz    
 * 创建时间：2019年2月13日 下午1:28:14    
 * 修改人：Gz    
 * 修改时间：2019年2月13日 下午1:28:14    
 * 修改备注：    
 * @version     
 *
 */
public class JsonControl {

    // list赋值测试
    public static void listValue(List<Test> testList) {
        for (int i = 0; i < 3; i++) {
            Test t = new Test();
            t.setName("test" + i);
            t.setNo("no" + i);
            testList.add(t);
        }
    }

    // map赋值测试
    public static void mapValue(Map<String, Object> maps) {
        maps.put("a", 1);
        maps.put("b", 2);
        maps.put("c", 3);
    }

    public static void main(String[] args) {
        // *******************前言***********************
        // jsonobject是一种特殊的map 都是键值对
        List<Test> testList = new ArrayList<>();
        JsonControl.listValue(testList);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("d", testList);
        JsonControl.mapValue(maps);
        String strMap = "{'name':'test0','no':'no0'}";
        String strList = "[{'name':'test0','no':'no0'},{'name':'test1','no':'no1'},{'name':'test2','no':'no2'}]";
        String strListOne = "[{'id':'a', 'time':'b','type':'c','crttime':'d','boxId':'e'}]";
        // 1.将Java对象序列化为JSON字符串，支持各种Java基本类型和JavaBean
        // object 转 json 字符串
        String listStr = JSONObject.toJSONString(testList);
        String mapStr = JSONObject.toJSONString(maps);
        System.out.println("listStr----" + listStr);
        System.out.println("mapStr-----" + mapStr);
        // 2.将JSON字符串反序列化为JavaBean
        // json字符串转object
        // ①：json-->entity
        Test test = JSONObject.parseObject(strMap, Test.class);
        System.out.println("test----" + JSONObject.toJSONString(test) + "  name--" + test.getName());
        // ②：json-->list
        List<Test> list = JSONObject.parseArray(strList, Test.class);
        System.out.println("list----" + JSONObject.toJSONString(list));
        // ③：json-->map
        Map<String, String> map = JSONObject.toJavaObject(JSONObject.parseObject(strMap), Map.class);

        Map<String, String> map2 = JSONObject.parseObject(strMap, Map.class);
        System.out.println("map---------" + JSONObject.toJSONString(map));
        System.out.println("map2---------" + JSONObject.toJSONString(map2));
        // ④：bean-->map
        JSONObject jsonObject = (JSONObject) JSON.toJSON(test);
        Set<Entry<String, Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> m = new HashMap<String, Object>();
        for (Entry<String, Object> entry : entrySet) {
            m.put(entry.getKey(), entry.getValue());
        }
        System.out.println("m---------" + m.toString());

        // 查询对象
        JSONObject dataObj = new JSONObject();
        Student stuA = new Student("15806631533", "name1", 24, 1);
        Student stuB = new Student("12222222222", "name2", 25, 0);
        Student stuC = new Student("13333333333", "name3", 26, 0);
        Student stuD = new Student("14444444444", "name4", 27, 1);
        List<Student> stulist = new ArrayList<Student>();
        stulist.add(stuA);
        stulist.add(stuB);
        stulist.add(stuC);
        stulist.add(stuD);
        dataObj.put("json", JSONArray.toJSONString(stulist));
        System.out.println("dataObj-------" + dataObj.toString());
        Map<String, Object> interMap = new HashMap<String, Object>();
        interMap.put("json", stulist);
        System.out.println("mapObj--------" + JSONObject.toJSONString(interMap));

        Map<String, String> parameterMap = new HashMap<String, String>();
        net.sf.json.JSONArray obj = net.sf.json.JSONArray.fromObject(stulist);
        parameterMap.put("data", obj.toString());
        System.out.println("json------" + JSON.toJSONString(parameterMap));

        Map<String, Object> params = new HashMap<String, Object>();
        params = JSON.parseObject(JSONObject.toJSONString(interMap), Map.class);
        params = JSON.parseObject(dataObj.toString(), new HashMap<String, Object>().getClass());
        params = JSON.parseObject(JSONObject.toJSONString(parameterMap), Map.class);
    }

    static class FastJson {
        public static void main(String[] args) {
            List<Test> testList = new ArrayList<Test>();
            JsonControl.listValue(testList);
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("d", testList);
            JsonControl.mapValue(maps);
            String strMap = "{'name':'test0','no':'no0'}";
            String strList = "[{'name':'test0','no':'no0'},{'name':'test1','no':'no1'},{'name':'test2','no':'no2'}]";
            String strListOne = "[{'id':'a', 'time':'b','type':'c','crttime':'d','boxId':'e'}]";
            // 序列化API
            // 1.将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
            String jsonStr = JSON.toJSONString(testList);
            System.out.println("jsonStr-----" + jsonStr);
            // 2.将Java对象序列化为JSON字符串，返回JSON字符串的utf-8 bytes
            // byte[] jsonBytes = JSON.toJSONBytes("a"); ??
            // JSON字符串反序列化API
            // 1.将JSON字符串反序列化为JavaBean
            JSONObject jsonObj = JSON.parseObject(strMap);
            System.out.println(jsonObj.toString());
            // 2.将JSON字符串反序列化为泛型类型的JavaBean
            Test test = JSON.parseObject(strMap, Test.class);
            System.out.println(JSON.toJSON(test));
            List<Test> test2 = JSON.parseArray(strList, Test.class);
            System.out.println(JSON.toJSONString(test2));

            String json1 = JSONArray.toJSONString(testList);
            String json2 = JSONObject.toJSONString(testList);
            System.out.println("json1----------" + json1);
            System.out.println("json2----------" + json2);
        }
    }

}
