package com.zFrame.util.factory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zFrame.util.factory.base.HandleInterface;

@Component
public class TestTwoFactory {
    @Autowired
    private Map<String, HandleInterface> handlerMap;

    public HandleInterface testTwoFactory(String name) {
        return handlerMap.get(name);
    }
}
