package com.zFrame.exception;

@SuppressWarnings("serial")
public class TestException extends RuntimeException {

    // 异常编码
    private String errorCode;

    // 异常信息
    private String errorInfo;

    public TestException(String errorCode, String errorInfo) {
        // 继承父类的有参构造方法
        super(errorInfo);
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public TestException(String errorCode, String errorInfo, Exception e) {
        // 继承父类的有参构造方法
        super(errorCode, e);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

}
