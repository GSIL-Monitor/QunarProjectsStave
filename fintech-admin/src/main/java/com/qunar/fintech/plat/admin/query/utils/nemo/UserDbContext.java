package com.qunar.fintech.plat.admin.query.utils.nemo;

/**
 * Author: tongjie
 * Date: 06/11/2017
 */
public class UserDbContext {

    private static ThreadLocal<UserDB> dbLocal = new ThreadLocal<UserDB>();

    private UserDbContext() {
    }

    public static void set(String userIdHashCode, String platUserTable, String tppUserTable) {
        UserDB userDB = new UserDB(userIdHashCode, platUserTable, tppUserTable);
        dbLocal.set(userDB);
    }

    public static String getUserIdHashCode() {
        return dbLocal.get().getUserIdHashCode();
    }

    public static String getPlatUserTable() {
        return dbLocal.get().getPlatUserTable();
    }

    public static String getTppUserTable() {
        return dbLocal.get().getTppUserTable();
    }

    private static class UserDB {

        private String userIdHashCode;

        private String platUserTable;

        private String tppUserTable;

        UserDB(String userIdHashCode, String platUserTable, String tppUserTable) {
            this.userIdHashCode = userIdHashCode;
            this.platUserTable = platUserTable;
            this.tppUserTable = tppUserTable;
        }

        String getUserIdHashCode() {
            return userIdHashCode;
        }

        String getPlatUserTable() {
            return platUserTable;
        }

        String getTppUserTable() {
            return tppUserTable;
        }
    }
}
