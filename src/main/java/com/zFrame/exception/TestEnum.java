package com.zFrame.exception;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestEnum    
 * 类描述：枚举    
 * 创建人：Gz    
 * 创建时间：2019年7月17日 下午5:55:24    
 * 修改人：Gz    
 * 修改时间：2019年7月17日 下午5:55:24    
 * 修改备注：    
 * @version     
 *
 */
public enum TestEnum {
    TEST_ONE("1", "testOne", "test"), TEST_TWO("2", "testTwo", "test");

    private String code;

    private String res;

    private String test;

    // TEST_ONE参数对应构造方法参数！
    TestEnum(String code, String res, String test) {
        this.code = code;
        this.res = res;
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

}
