package com.qunar.fintech.plat.admin.system.dao.entity;

import com.qunar.fintech.plat.admin.system.dao.enums.ChangeBusiTypeEnum;
import com.qunar.fintech.plat.admin.system.dao.enums.ProcStatusEnum;
import com.qunar.pay.finance.qf.commons.api.model.ToString;

import java.util.Date;

/**
 * Description: 合同变更请求记录
 * User: rengang.pei
 * Date: 2018-11-03
 * Time: 23:23
 */
public class ContractChangeReq extends ToString {

    private Long id;

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
     */
    private ChangeBusiTypeEnum busiType;

    /**
     * 状态
     */
    private ProcStatusEnum procStatus;

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ChangeBusiTypeEnum getBusiType() {
        return busiType;
    }

    public void setBusiType(ChangeBusiTypeEnum busiType) {
        this.busiType = busiType;
    }

    public ProcStatusEnum getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(ProcStatusEnum procStatus) {
        this.procStatus = procStatus;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
