
package com.zFrame.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zFrame.ZFrameApplication;

@RunWith(SpringRunner.class)
// 类需要定义为启动类 ZFrameApplication 非当前类，不然无法扫包注入service
@SpringBootTest(classes = { ZFrameApplication.class })
public class TestJunitControl {

    @Test
    public void updateApplyToFreshport() { //
    }
}
