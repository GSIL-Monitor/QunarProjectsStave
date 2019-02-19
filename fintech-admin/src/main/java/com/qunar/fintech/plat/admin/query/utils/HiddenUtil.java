package com.qunar.fintech.plat.admin.query.utils;

/**
 * Created by baron.jiang on 2015/2/5.
 */
public class HiddenUtil {

    private static final char MOBILE_FIRST_CHAR = '1';

    private static final int LENGTH_3 = 3;

    private static final int LENGTH_7 = 7;

    private static final int OLD_ID_LENGTH_15 = 15;

    private static final int BANKCARD_NO_LENGTH_16 = 16;



    private static final int LENGTH_6 = 6;

    private static final int LENGTH_12 = 12;

    private static final int ID_LENGTH_18 = 18;

    private static final int LENGTH_18 = 14;


    public static String hiddenMobile(String mobile) {
        if (null == mobile || mobile.trim().length() == 0) {
            return null;
        }
        // 手机号 1开头，剔除86相关
        int idx = mobile.indexOf(MOBILE_FIRST_CHAR);
        if (idx >= 0) {
            mobile = mobile.substring(idx);
            // 手机号，长度需要大于等于11位
            int len = mobile.length();
            if (len >= 11) {
                return mobile.substring(0, LENGTH_3) + "****" + mobile.substring(LENGTH_7);
            }
        }
        return null;
    }

    public static String hiddenIdentityCardNo(String identityCardNo) {
        if (null == identityCardNo || identityCardNo.trim().length() == 0) {
            return null;
        }
        int len = identityCardNo.length();
        if (len == OLD_ID_LENGTH_15) {
            return identityCardNo.substring(0, LENGTH_6) + "*****" + identityCardNo.substring(LENGTH_12);
        }
        if (len == ID_LENGTH_18) {
            return identityCardNo.substring(0, LENGTH_6) + "********" + identityCardNo.substring(LENGTH_18);
        }

        return null;
    }

    public static String hiddenBankCardNo(String bankCardNo) {
        if (null == bankCardNo || bankCardNo.trim().length() == 0) {
            return null;
        }
        int len = bankCardNo.length();
        if (len == BANKCARD_NO_LENGTH_16) {
            return bankCardNo.substring(0, LENGTH_6) + "******" + bankCardNo.substring(LENGTH_12);
        }

        return null;
    }
}
