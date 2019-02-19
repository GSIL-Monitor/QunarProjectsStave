package com.qunar.fintech.plat.admin.query.vo;

import java.io.Serializable;

/**
 * 查询参数类
 * 描述：
 * @author junfeng.zhou
 * @version V1.0
 */
public class Query<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private T query;

	public T getQuery() {
		return query;
	}

	public void setQuery(T query) {
		this.query = query;
	}
}
