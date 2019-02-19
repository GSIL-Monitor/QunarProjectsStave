//package com.qunar.fintech.plat.admin.query.enums.handler;
//
//import com.qunar.fintech.plat.admin.query.enums.AuditStatusEnum;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Created by bob.li on 2015/9/18.
// */
//public class AuditStatusEnumHandler extends BaseTypeHandler implements TypeHandler {
//    @Override
//    public Object getNullableResult(ResultSet rs, String paramString) throws SQLException {
//        Integer code = rs.getInt(paramString);
//        return code == null ? null: AuditStatusEnum.toEnum(code);
//    }
//
//    @Override
//    public Object getNullableResult(CallableStatement cs, int paramString) throws SQLException {
//        Integer code = cs.getInt(paramString);
//        return code == null ? null: AuditStatusEnum.toEnum(code);
//    }
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
//        AuditStatusEnum obj = (AuditStatusEnum)parameter;
//        ps.setInt(i, obj.getCode());
//    }
//}
