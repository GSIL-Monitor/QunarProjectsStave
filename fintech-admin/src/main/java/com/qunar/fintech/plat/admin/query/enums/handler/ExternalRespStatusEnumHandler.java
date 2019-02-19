package com.qunar.fintech.plat.admin.query.enums.handler;

import com.qunar.fintech.plat.admin.query.enums.ExternalRespStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jinyancao on 2016/04/11/16/28
 */
public class ExternalRespStatusEnumHandler extends BaseTypeHandler<ExternalRespStatusEnum> {
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
			ExternalRespStatusEnum parameter, JdbcType jdbcType) throws SQLException {
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
	public ExternalRespStatusEnum getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int val = rs.getInt(columnName);
		return ExternalRespStatusEnum.toEnum(val);
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
	public ExternalRespStatusEnum getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int val = rs.getInt(columnIndex);
		return ExternalRespStatusEnum.toEnum(val);
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
	public ExternalRespStatusEnum getNullableResult(CallableStatement cs,
			int columnIndex) throws SQLException {
		int val = cs.getInt(columnIndex);
		return ExternalRespStatusEnum.toEnum(val);
	}

}
