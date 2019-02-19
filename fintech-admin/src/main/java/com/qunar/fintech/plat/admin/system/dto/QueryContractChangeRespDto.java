package com.qunar.fintech.plat.admin.system.dto;

import com.qunar.fintech.plat.admin.system.dao.enums.ChangeBusiTypeEnum;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * Description:
 * User: rengang.pei
 * Date: 2018-11-04
 * Time: 12:51
 */
public class QueryContractChangeRespDto extends ToString {

    /**
     * 流水号
     */
    private String reqNo;

    /**
     * 客户id
     */
    private String customId;

    /**
     * 金融platId
     */
    private String platId;

    /**
     * 产品
     *
     * @see com.qunar.pay.finance.qf.commons.api.enums.ProductEnum
     */
    private String productNo;

    /**
     * 产品渠道
     * @see com.qunar.pay.finance.qf.commons.api.enums.OrgChannelEnum
     */
    private String orgChannel;

    /**
     * 通道
     */
    private String tppCode;

    /**
     * 申请人邮箱
     */
    private String email;

    /**
     * 业务类型
     * @see ChangeBusiTypeEnum
     */
    private Integer busiType;

    /**
     * 状态
     * @see ProcStatusEnum
     */
    private Integer procStatus;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getPlatId() {
        return platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBusiType() {
        return busiType;
    }

    public void setBusiType(Integer busiType) {
        this.busiType = busiType;
    }

    public Integer getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
