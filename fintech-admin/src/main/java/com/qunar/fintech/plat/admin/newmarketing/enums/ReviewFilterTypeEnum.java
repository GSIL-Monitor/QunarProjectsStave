package com.qunar.fintech.plat.admin.newmarketing.enums;

/**
 * @author qun.shi
 * @since 2019-02-02 5:19 PM
 */
public enum ReviewFilterTypeEnum {
    QUERY_PROCESSING_BY_ME(1,"查询待我处理的审核记录"),
    QUERY_PROCESSED_BY_ME(2,"查询经我处理的审核记录"),
    QUERY_COMMITED_BY_ME(3,"查询由我提交的审核记录");

    private Integer filterId;
    private String filterName;

    ReviewFilterTypeEnum(Integer filterId, String filterName) {
        this.filterId = filterId;
        this.filterName = filterName;
    }

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
}
