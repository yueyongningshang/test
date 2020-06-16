package com.zFrame.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Jmeter--测试接口")
public class TestJmeterControl {
    /**
     * 
     * testJmeterPost(测试jmeter post)    
     * @param  name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    @ApiOperation(value = "测试jmeter post", notes = "测试jmeter post")
    @PostMapping(value = "/v1/test/testJmeterPost")
    public Map<String, Object> testJmeterPost(String x) {
        Map<String, Object> model = new HashMap<String, Object>();
        Integer a = 1;
        try {
            for (int i = 0; i < 100; i++) {
                a = a + i;
            }
            System.out.println("aaaaaa----------------" + a);
            model.put("success", "成功: a=" + a);
        } catch (Exception e) {
            // 自定义异常
            e.printStackTrace();
            model.put("error", "失败");
        }
        return model;
    }
}
