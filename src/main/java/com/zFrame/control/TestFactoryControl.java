package com.zFrame.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.util.factory.TestFactory;
import com.zFrame.util.factory.TestTwoFactory;
import com.zFrame.util.factory.base.BusinessInterface;

@RestController
public class TestFactoryControl {

    /**
     * 
     * handleBusiness(调用工厂类方法)    
     * @param   name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    @GetMapping("/test/factoryGet/{type}")
    public String handleBusiness(@PathVariable("type") String type) {
        // 大坑！！！此时无法在工厂实现类中调用注入的方法。
        /**
         *  在SMSImpl里要调用receiptService的方法,首先SMSImpl这个类就要从容器中拿,而不能直接new出来
         *  ,因为交给Spring管理的类一般默认是单例的,它会为这个对象注入属性,但自己new出来的类,
         *  就不会注入这个属性了,而我拿到的SMSImpl实现类其实就是我自己new出来的.
         */
        BusinessInterface bi = TestFactory.handleBusiness(type);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", type);
        Map<String, Object> result = bi.businessHandle(param);
        return JSONObject.toJSONString(result);
    }

    @Autowired
    private TestTwoFactory testTwoFactory;

    /**
     * 
     * handleBusiness(调用工厂类方法:工厂方法可以注入)    
     * @param   name    
     * @param  @return    设定文件    
     * @return String    DOM对象    
     * @Exception 异常对象
     */
    @GetMapping("/test/factoryGet2/{type}")
    public String handleBusiness2(@PathVariable("type") String type) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", type);
        String result = testTwoFactory.testTwoFactory(type).handle(param);
        System.out.println("test-------------" + testTwoFactory.testTwoFactory(type).getClass().getName());
        return result;
    }

}
