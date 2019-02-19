package com.qunar.fintech.plat.admin.query.enums.handler;

import com.qunar.pay.finance.repaykernel.api.userrepay.enums.RecordTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 开关Handler
 * @author sujie.liu
 *
 */
public class RecordTypeEnumHandler extends BaseTypeHandler implements TypeHandler {

    @Override
    public Object getNullableResult(ResultSet rs, String paramString)
            throws SQLException {
        Integer code = rs.getInt(paramString);
        return code == null ? null : RecordTypeEnum.toEnum(code);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int paramString)
            throws SQLException {
        Integer code = cs.getInt(paramString);
        return code == null ? null : RecordTypeEnum.toEnum(code);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Integer code = resultSet.getInt(i);
        return code == null ? null: RecordTypeEnum.toEnum(code);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Object parameter, JdbcType jdbcType) throws SQLException {
        RecordTypeEnum obj = (RecordTypeEnum) parameter;
        ps.setInt(i, obj.getCode());
    }
}
