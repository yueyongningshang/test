package com.zFrame.control.lock;

public class TryAgainException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    // 异常编码
    private String errorCode;

    // 异常信息
    private String errorInfo;

    public TryAgainException(String errorCode, String errorInfo) {
        // 继承父类的有参构造方法
        super(errorInfo);
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
