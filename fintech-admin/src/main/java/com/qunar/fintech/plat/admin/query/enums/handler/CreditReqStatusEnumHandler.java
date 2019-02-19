package com.qunar.fintech.plat.admin.query.enums.handler;

import com.qunar.fintech.plat.admin.query.enums.CreditReqStatusEnum;
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
public class CreditReqStatusEnumHandler extends BaseTypeHandler<CreditReqStatusEnum> {

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
			CreditReqStatusEnum parameter, JdbcType jdbcType) throws SQLException {
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
	public CreditReqStatusEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int val = rs.getInt(columnName);
		return CreditReqStatusEnum.toEnum(val);
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
	public CreditReqStatusEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int val = rs.getInt(columnIndex);
		return CreditReqStatusEnum.toEnum(val);
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
	public CreditReqStatusEnum getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		int val = cs.getInt(columnIndex);
		return CreditReqStatusEnum.toEnum(val);
	}
}
