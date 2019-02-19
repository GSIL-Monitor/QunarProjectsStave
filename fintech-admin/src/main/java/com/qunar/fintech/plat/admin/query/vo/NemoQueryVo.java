package com.qunar.fintech.plat.admin.query.vo;

import com.qunar.fintech.plat.admin.query.export.annotation.Excel;

import java.util.List;

public class NemoQueryVo{

    @Excel(exportName="平台ID")
    private String platOpenId;

    @Excel(exportName="UID")
    private String originUserId;

    @Excel(exportName="渠道")
    private String orgChannel;

    @Excel(exportName="会员ID")
    private String customerId;

    @Excel(exportName="tppID")
    private String tppOpenId;

    @Excel(exportName="tppCode")
    private String tppCode;

    private int exportType;

    public String getPlatOpenId() {
        return platOpenId;
    }

    public void setPlatOpenId(String platOpenId) {
        this.platOpenId = platOpenId;
    }

    public String getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(String originUserId) {
        this.originUserId = originUserId;
    }

    public String getOrgChannel() {
        return orgChannel;
    }

    public void setOrgChannel(String orgChannel) {
        this.orgChannel = orgChannel;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTppOpenId() {
        return tppOpenId;
    }

    public void setTppOpenId(String tppOpenId) {
        this.tppOpenId = tppOpenId;
    }

    public String getTppCode() {
        return tppCode;
    }

    public void setTppCode(String tppCode) {
        this.tppCode = tppCode;
    }

    public int getExportType() {
        return exportType;
    }

    public void setExportType(int exportType) {
        this.exportType = exportType;
    }

}
