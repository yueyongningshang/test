package com.zFrame.util.factory.execute;

import java.util.HashMap;
import java.util.Map;

import com.zFrame.util.factory.base.BusinessInterface;

public class BplanBusiness implements BusinessInterface {

    @Override
    public Map<String, Object> businessHandle(Map<String, Object> param) {
        Map<String, Object> x = new HashMap<String, Object>();
        x.put("B", "B任务处理完成");
        return x;
    }

}
