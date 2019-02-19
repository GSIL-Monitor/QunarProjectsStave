package com.qunar.fintech.plat.admin.query.enums.handler;

import com.qunar.fintech.plat.admin.query.enums.CreditActivateStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by baron.jiang on 2015/11/3.
 */
public class CreditActivateStatusEnumHandler extends BaseTypeHandler<CreditActivateStatusEnum> {

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
			CreditActivateStatusEnum parameter, JdbcType jdbcType) throws SQLException {
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
	public CreditActivateStatusEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int val = rs.getInt(columnName);
		return CreditActivateStatusEnum.toEnum(val);
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
	public CreditActivateStatusEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int val = rs.getInt(columnIndex);
		return CreditActivateStatusEnum.toEnum(val);
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
	public CreditActivateStatusEnum getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		int val = cs.getInt(columnIndex);
		return CreditActivateStatusEnum.toEnum(val);
	}
}
