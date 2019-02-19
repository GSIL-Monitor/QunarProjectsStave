package com.qunar.fintech.plat.admin.query.utils;

/**
 * Created by bob.li on 2015/12/23.
 */
public class CommonConstant {
    /* 查询征信指标 start */
    public static final String INNER_MERCHANT_CODE = "QUNAR";           // 内部查询征信的 MERCHANT_CODE
    public static final String QCREDIT_TPP_QUNAR = "Qunar";             // 请求征信Qunar通道代码
    public static final String QC_CONTACTS_INFORMATION = "Q004002";     // 干系人相关信息code(json格式)

    public static final String QCREDIT_KEY_CID = "CID";                 // 干系人身份证 key
    public static final String QCREDIT_KEY_CTYPE = "CType";             // 干系人类别 key
    public static final String QCREDIT_KEY_CNAME = "CName";             // 干系人姓名（多个） key
    public static final String QCREDIT_KEY_CADDRESS = "Caddress";       // 干系人地址 key
    public static final String QCREDIT_KEY_CEMAIL = "Cemail";           // 干系人邮箱 key
    public static final String QCREDIT_KEY_CPHONE = "CPhone";           // 干系人手机号（多个） key
    public static final String QCREDIT_KEY_CPHONE_SPLIT = ",";          // 干系人手机号分隔符


    /* 查询征信指标 end */
}
