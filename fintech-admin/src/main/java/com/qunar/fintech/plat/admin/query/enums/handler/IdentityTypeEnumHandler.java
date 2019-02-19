package com.qunar.fintech.plat.admin.query.enums.handler;

import com.qunar.fintech.plat.admin.query.enums.IdentityTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //--------------------- Change Logs----------------------
// <p>@author yth Initial Created at 2015年11月1日<p>
//-------------------------------------------------------
 */
public class IdentityTypeEnumHandler extends BaseTypeHandler<IdentityTypeEnum> {

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
			IdentityTypeEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.getCode());
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
	public IdentityTypeEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		String val = rs.getString(columnName);
		return IdentityTypeEnum.toEnum(val);
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
	public IdentityTypeEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		String val = rs.getString(columnIndex);
		return IdentityTypeEnum.toEnum(val);
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
	public IdentityTypeEnum getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		String val = cs.getString(columnIndex);
		return IdentityTypeEnum.toEnum(val);
	}

}
