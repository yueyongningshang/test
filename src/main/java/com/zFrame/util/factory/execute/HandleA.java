package com.zFrame.util.factory.execute;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.zFrame.util.factory.base.HandleInterface;

@Component("handleA")
public class HandleA implements HandleInterface {

    @Override
    public String handle(Map<String, Object> param) {
        return "****************************************handleA*****************";
    }

}
