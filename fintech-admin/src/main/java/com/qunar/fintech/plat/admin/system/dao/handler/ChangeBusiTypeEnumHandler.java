package com.qunar.fintech.plat.admin.system.dao.handler;

import com.qunar.fintech.plat.admin.system.dao.enums.ChangeBusiTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 23:57
 */
public class ChangeBusiTypeEnumHandler extends BaseTypeHandler<ChangeBusiTypeEnum> {

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
    public void setNonNullParameter(PreparedStatement ps, int i, ChangeBusiTypeEnum parameter, JdbcType jdbcType) throws SQLException {
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
    public ChangeBusiTypeEnum getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int val = rs.getInt(columnName);
        return ChangeBusiTypeEnum.toEnum(val);
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
    public ChangeBusiTypeEnum getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int val = rs.getInt(columnIndex);
        return ChangeBusiTypeEnum.toEnum(val);
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
    public ChangeBusiTypeEnum getNullableResult(CallableStatement cs,
                                             int columnIndex) throws SQLException {
        int val = cs.getInt(columnIndex);
        return ChangeBusiTypeEnum.toEnum(val);
    }
}
