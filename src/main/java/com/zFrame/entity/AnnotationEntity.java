package com.zFrame.entity;

import com.zFrame.control.aop.AnnotationInter;

import io.swagger.annotations.ApiModelProperty;

public class AnnotationEntity {

    @AnnotationInter(id = "aeId", name = "主键")
    @ApiModelProperty(name = "主键")
    private String aeId;

    @ApiModelProperty(name = "姓名")
    @AnnotationInter(id = "userName", name = "姓名")
    private String userName;

}
