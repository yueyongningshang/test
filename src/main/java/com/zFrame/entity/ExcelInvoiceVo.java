package com.zFrame.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "开票管理导出")
@Data
public class ExcelInvoiceVo {

    // 客户名称
    private String companyName;

    // 费用大类名称
    private String costName;

    // 主提单号
    private String mainBillNo;

    // 发票号码
    private String invoiceNo;

    // 船名/航次/航班/车
    private String info;

    // 启运港
    private String beginPort;

    // 卸货港区
    private String unloadPort;

    // 目的港
    private String reachPort;

    // 申报的国际货代免税收入 默认为空
    private String noTaxMoney;
}
