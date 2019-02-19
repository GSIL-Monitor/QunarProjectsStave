package com.qunar.fintech.plat.admin.system.dao.handler;

import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: 枚举处理
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 23:23
 */
public class ProcStatusEnumHandler extends BaseTypeHandler<ProcStatusEnum> {

    /* (非 Javadoc)
       * <p>Title: setNonNullParameter</p>
       * <p>Description: </p>
       * @param ps
       * @param i
       * @param parameter
       * @param jdbcType
       * @throws SQLException
       * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
       */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    ProcStatusEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getCode());
    }

    /* (非 Javadoc)
     * <p>Title: getNullableResult</p>
     * <p>Description: </p>
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public ProcStatusEnum getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int val = rs.getInt(columnName);
        return ProcStatusEnum.toEnum(val);
    }

    /* (非 Javadoc)
     * <p>Title: getNullableResult</p>
     * <p>Description: </p>
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet, int)
     */
    @Override
    public ProcStatusEnum getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int val = rs.getInt(columnIndex);
        return ProcStatusEnum.toEnum(val);
    }

    /* (非 Javadoc)
     * <p>Title: getNullableResult</p>
     * <p>Description: </p>
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement, int)
     */
    @Override
    public ProcStatusEnum getNullableResult(CallableStatement cs,
                                          int columnIndex) throws SQLException {
        int val = cs.getInt(columnIndex);
        return ProcStatusEnum.toEnum(val);
    }
}
