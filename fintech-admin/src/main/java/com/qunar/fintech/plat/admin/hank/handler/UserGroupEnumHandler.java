package com.qunar.fintech.plat.admin.hank.handler;

import com.qunar.pay.finance.qf.commons.api.enums.UserGroupEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangzhiguo on 2017/9/27.
 */
public class UserGroupEnumHandler extends BaseTypeHandler<UserGroupEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserGroupEnum userGroupEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, userGroupEnum.getDbValue());
    }

    @Override
    public UserGroupEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Integer code = rs.getInt(columnName);
        return UserGroupEnum.toEnum(code);
    }

    @Override
    public UserGroupEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer code = rs.getInt(columnIndex);
        return UserGroupEnum.toEnum(code);
    }

    @Override
    public UserGroupEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer code = cs.getInt(columnIndex);
        return UserGroupEnum.toEnum(code);
    }
}
