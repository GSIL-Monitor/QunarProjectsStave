package com.qunar.fintech.plat.admin.newmarketing.exception;

import com.qunar.pay.finance.qf.commons.api.exception.ApiErrorCode;

/**
 * @author qun.shi
 * @since 2019-01-31 8:48 PM
 */
public class MarketingExceptionCode {

    /**
     * 活动相关
     */
    public static final ApiErrorCode MARKETING_ACTIVITY_QUERY_FAIL
            = new ApiErrorCode("MARKETING_ACTIVITY_QUERY", "营销活动查询失败");

    public static final ApiErrorCode MARKETING_ACTIVITY_ADD_FAIL
            = new ApiErrorCode("MARKETING_ACTIVITY_ADD", "营销活动新增失败");

    public static final ApiErrorCode MARKETING_ACTIVITY_UPDATE_FAIL
            = new ApiErrorCode("MARKETING_ACTIVITY_UPDATE", "营销活动更新失败");

    /**
     * 券相关
     */
    public static final ApiErrorCode MARKETING_COUPON_ADD_FAIL
            = new ApiErrorCode("MARKETING_COUPON_ADD", "营销优惠券新增失败");

    public static final ApiErrorCode MARKETING_COUPON_QUERY_FAIL
            = new ApiErrorCode("MARKETING_COUPON_QUERY", "营销优惠券查询失败");

    public static final ApiErrorCode MARKETING_COUPON_UPDATE_FAIL
            = new ApiErrorCode("MARKETING_COUPON_UPDATE", "营销优惠券更新失败");

    public static final ApiErrorCode MARKETING_COUPON_TYPE_ERROR
            = new ApiErrorCode("MARKETING_COUPON_TYPE_ERROR", "营销券类型出错");

    /**
     * 审核通过之后，事件执行
     */
    public static final ApiErrorCode NO_EVENT_EXECUTE_CONTENT
            = new ApiErrorCode("NO_EVENT_EXECUTE_ENGINE", "没有对应的事件处理内容");

    public static final ApiErrorCode NO_EVENT_EXECUTE_ENGINE
            = new ApiErrorCode("NO_EVENT_EXECUTE_ENGINE", "没有对应的事件处理引擎");

    public static final ApiErrorCode EVENT_EXECUTE_FAIL
            = new ApiErrorCode("EVENT_EXECUTE_FAIL", "审核事件执行失败");

    /**
     * 审核相关
     */
    public static final ApiErrorCode REVIEW_INFO_ADD_FAIL
            = new ApiErrorCode("REVIEW_INFO_ADD_FAIL", "审核内容新增失败");

    public static final ApiErrorCode REVIEW_INFO_ADD_AFTER_REJECT_FAIL
            = new ApiErrorCode("REVIEW_INFO_ADD_AFTER_REJECT_FAIL", "拒绝之后，重新生成审核记录失败");

    public static final ApiErrorCode REVIEW_INFO_STATUS_UPDATE_FAIL
            = new ApiErrorCode("REVIEW_INFO_STATUS_UPDATE_FAIL", "审核状态更新失败");

    public static final ApiErrorCode REVIEW_INFO_CONTENT_UPDATE_FAIL
            = new ApiErrorCode("REVIEW_INFO_CONTENT_UPDATE_FAIL", "审核内容更新失败");

    public static final ApiErrorCode REVIEW_INFO_CUR_ROLE_ID_UPDATE_FAIL
            = new ApiErrorCode("REVIEW_INFO_CUR_ROLE_ID_UPDATE_FAIL", "当前的审核角色更新失败");

    public static final ApiErrorCode NOT_NEED_TO_REVIEW
            = new ApiErrorCode("NOT_NEED_TO_REVIEW", "审核未提交或已完成，无需批复");

    public static final ApiErrorCode NO_RIGHT_TO_REVIEW
            = new ApiErrorCode("NO_RIGHT_TO_REVIEW", "没有权限审核");

    /**
     * 审核节点相关
     */
    public static final ApiErrorCode REVIEW_NODE_ADD_FAIL
            = new ApiErrorCode("REVIEW_NODE_ADD_FAIL", "审核节点新增失败");

    public static final ApiErrorCode REVIEW_NODE_STATUS_UPDATE_FAIL
            = new ApiErrorCode("REVIEW_NODE_UPDATE_FAIL", "审核节点状态更新失败");

    public static final ApiErrorCode REVIEW_NODE_STATUS_AND_COMMENT_AND_REVIEWER_UPDATE_FAIL
            = new ApiErrorCode("REVIEW_NODE_STATUS_AND_COMMENT_UPDATE_FAIL", "审核节点状态和评论更新失败");

    /**
     * 文件上传相关
     */
    public static final ApiErrorCode UPLOAD_FILE_FAIL
            = new ApiErrorCode("UPLOAD_FILE_FAIL", "上传文件失败");

    public static final ApiErrorCode DOWN_LOAD_FILE_FAIL
            = new ApiErrorCode("DOWN_LOAD_FILE_FAIL", "文件文件下载失败");

    public static final ApiErrorCode NO_MATCH_REVIEW_FLOW_CONFIG =
            new ApiErrorCode("NO_MATCH_REVIEW_FLOW_CONFIG", "没有匹配的审核流配置");
}
