package com.qunar.fintech.plat.admin.query.vo;

import com.google.common.base.Preconditions;

import java.io.Serializable;

/**
 * 分页查询参数类
 * 描述：
 * @author junfeng.zhou
 * @version V1.0
 */
public class PageQuery<T> extends Query<T> implements Serializable{

	private static final long serialVersionUID = 1L;

    /**
     * 从1开始
     */
	private int pageNo = 1;
	private int pageSize = 10;
	private String tableIndex;
	
	public PageQuery(){
	}
	
	public PageQuery(int pageNo, int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;
	}

	public int  getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}


    public void setPagesize(int pagesize) {
        setPageSize(pagesize);
    }

    public void setPage(int page) {
        setPageNo(page);
    }

    public int getOffset() {
        Preconditions.checkArgument(pageNo >= 1,"页号必须大于1");
        return (pageNo - 1) * pageSize;
    }

	@Override
	public String toString() {
		return "PageQuery{" +
				"pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", tableIndex='" + tableIndex + '\'' +
				'}';
	}
}
