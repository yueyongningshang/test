package com.zFrame.util;

/**
 * 
 *     
 * 项目名称：ff-banktrans    
 * 类名称：BillInfo    
 * 类描述：结算常量维护    
 * 创建人：Gz    
 * 创建时间：2019年9月18日 下午8:37:00    
 * 修改人：Gz    
 * 修改时间：2019年9月18日 下午8:37:00    
 * 修改备注：    
 * @version     
 *
 */
public class BillInfo {

    /**结算人员：所属结算组*/
    public static final String SETTLEMENT_G_NO = "000501";

    /** 账单数据来源  0 手动新增*/
    public static final String BILL_DATA_SOURCE_ADD = "0";

    /** 账单数据来源  1 系统生成*/
    public static final String BILL_DATA_SOURCE_SYS = "1";

    /** 账单编号前缀 ZD*/
    public static final String BILL_INDEX = "ZD";

    /** 账单编号截取位数*/
    public static final int BILL_SUB_NUM = 4;

    /** 是否生成账单 1 是*/
    public static final String BILL_STATUS_YES = "1";

    /** 是否生成账单 0 否*/
    public static final String BILL_STATUS_NO = "0";

    /** 账单 输入框长度   30*/
    public static final int BILL_INPUT_LENGTH = 30;

    /** 账单 文本框长度   300*/
    public static final int BILL_TEXT_LENGTH = 300;

    /** 开票导出 文件名 */
    public static final String EXCEL_INVOICE_FILE_NAME = "国际货物运输代理免征增值税收入明细表 ";

    /** 开票导出 标题 */
    public static final String[] EXCEL_INVOICE_HEADERS = { "序号", "客户", "费用大类", "提（运）单号", "发票号码", "船名/航次/航班/车", "启运港",
            "卸货港区", "目的港", "申报的国际货代免税收入" };

    public static final String EXCEL_INVOICE_TITLE_NAME = "国际货物运输代理免征增值税收入明细表（参考样表）";
}
