package com.zFrame.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改 
     *value–字段说明 
     *name–重写属性名字 
     *dataType–重写属性类型 
     *required–是否必填 
     *example–举例说明 
     *hidden–隐藏
     */
    @ApiModelProperty(value = "页码", dataType = "String", required = true)
    private String no;

    @ApiModelProperty(value = "名称", dataType = "String", required = true)
    private String name;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
