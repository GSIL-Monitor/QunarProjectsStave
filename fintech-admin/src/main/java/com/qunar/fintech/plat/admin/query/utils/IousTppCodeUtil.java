package com.qunar.fintech.plat.admin.query.utils;

/**
 * Created by shining.cui on 2015/8/28.
 */
public class IousTppCodeUtil {
    public static String tppCodeToTppName(String tppCode) {
        String str = "";
        if ("CMBCLOAN".equals(tppCode)) {
            str="民生称心贷";
        }else if ("GYLOAN".equals(tppCode)) {
            str="高阳闪白条";
        }else if ("QUNARLOAN".equals(tppCode)) {
            str="QUNAR消费贷";
        } else if("BAIDULOAN".equals(tppCode)){
            str="百度金融";
        } else if("EGLOAN".equals(tppCode)){
            str = "恒丰银行";
        } else if("JIMULOAN".equals(tppCode)){
            str = "积木盒子";
        }
        return str;
    }
}
