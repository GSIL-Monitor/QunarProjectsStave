package com.qunar.fintech.plat.admin.contract.dao.handler;

import com.qunar.fintech.plat.admin.contract.dao.enums.BindStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 枚举处理
 *
 * @author yupei.wang
 * @version V1.0
 * @date 2017年11月20日 下午5:09:51
 */
public class BindStatusEnumHandler extends BaseTypeHandler<BindStatusEnum> {

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
                                    BindStatusEnum parameter, JdbcType jdbcType) throws SQLException {
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
    public BindStatusEnum getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        int val = rs.getInt(columnName);
        return BindStatusEnum.toEnum(val);
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
    public BindStatusEnum getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        int val = rs.getInt(columnIndex);
        return BindStatusEnum.toEnum(val);
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
    public BindStatusEnum getNullableResult(CallableStatement cs,
                                            int columnIndex) throws SQLException {
        int val = cs.getInt(columnIndex);
        return BindStatusEnum.toEnum(val);
    }
}
