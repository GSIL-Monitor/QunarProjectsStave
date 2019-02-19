package com.qunar.fintech.plat.admin.query.utils.nemo;

/**
 * Author: tongjie
 * Date: 06/11/2017
 */
public final class UserDbContextUtils {

    public static final int HASH = 256;

    public static final int PLAT_USER_HASH = 10;

    public static final int TPP_USER_HASH = 128;

    public static final int OPEN_ID_LEN = 10;

    /**
     * 采用hash方式分表：tbl_plat_user 分成10张表；tbl_tpp_user 分成128张表
     */
    private static final String PLAT_USER_BASE_NAME = "tbl_plat_user_";

    private static final String TPP_USER_BASE_NAME = "tbl_tpp_user_";

    /**
     * 通过userId查询表的信息
     *
     * @param userId
     * @return
     */
    public static void initCtxByUserId(String userId) {
        ParamChecker.notBlank(userId, "userId must not be blank.");

        int hashCode = Math.abs(stringHash(userId)) % HASH;

        //将userIdHashCode转换成16进制
        String userIdHashCode = Integer.toHexString(hashCode);
        if (userIdHashCode.length() == 1) {
            userIdHashCode = "0" + userIdHashCode;
        }

        String platUserTableCode = PLAT_USER_BASE_NAME + String.format("%01d", hashCode % PLAT_USER_HASH);

        String tppUserTableCode = TPP_USER_BASE_NAME + String.format("%03d", hashCode % TPP_USER_HASH);

        UserDbContext.set(userIdHashCode, platUserTableCode, tppUserTableCode);
    }

    /**
     * 通过platUserId解析表信息
     *
     * @param platUserId platUserId
     * @return
     */
    public static void initCtxByPlatOpenId(String platUserId) {
        int len = platUserId.length();

        //取platUserId的最后两位
        String sf = platUserId.substring(len - 2, len);
        //将sf转换成10进制
        int hashCode = Integer.parseInt(sf, 16);

        String platUserTableCode = PLAT_USER_BASE_NAME + String.format("%01d", hashCode % PLAT_USER_HASH);

        String tppUserTableCode = TPP_USER_BASE_NAME + String.format("%03d", hashCode % TPP_USER_HASH);

        UserDbContext.set(sf, platUserTableCode, tppUserTableCode);
    }

    /**
     * 通过tppUserId解析表信息
     *
     * @param tppUserId tppUserId
     * @return
     */
    public static void initCtxByTppOpenId(String tppUserId) {
        int len = tppUserId.length();

        //取tppUserId的最后两位
        String sf = tppUserId.substring(len - 2, len);
        //将sf转换成10进制
        int hashCode = Integer.parseInt(sf, 16);

        String platUserTableCode = PLAT_USER_BASE_NAME + String.format("%01d", hashCode % PLAT_USER_HASH);

        String tppUserTableCode = TPP_USER_BASE_NAME + String.format("%03d", hashCode % TPP_USER_HASH);

        UserDbContext.set(sf, platUserTableCode, tppUserTableCode);
    }


    /**
     * string.hashCode
     *
     * @param str String
     * @return hashCode
     * @see String
     */
    private static int stringHash(String str) {
        int h = 0;
        char value[] = str.toCharArray();
        if (value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
        }

        if (Integer.MIN_VALUE == h) {
            h += 1;
        }

        return h;
    }
}
