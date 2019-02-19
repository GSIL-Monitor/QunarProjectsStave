package com.qunar.fintech.plat.admin.query.enums;


import org.apache.commons.lang.StringUtils;

/**
 * Created by wenqiang.kong on 2015/5/5.
 */
public enum FundTypeEnum {

    REGULAR_FUND("REGULAR_FUND", "定期基金"),
    MONETARY_FUND("MONETARY_FUND", "活期基金"),
    INSURANCE_MONEY("INSURANCE_MONEY", "保险理财"),
    TRAVELCARD("TRAVELCARD", "旅行投资"),
    ;

    //基金类型编号
    private String fundTypeCode;
    //基金类型名称
    private String fundTypeName;

    private FundTypeEnum(String fundTypeCode, String fundTypeName){
        this.fundTypeCode = fundTypeCode;
        this.fundTypeName = fundTypeName;
    }

    public String getFundTypeCode() {
        return fundTypeCode;
    }

    public static FundTypeEnum getFundTypeEnum(String fundTypeCode){
        if (StringUtils.isEmpty(fundTypeCode)) {
            throw  new IllegalArgumentException("param fundTypeCode can not be null or empty");
        }
        for (FundTypeEnum fundTypeEnum : FundTypeEnum.values()){
            if (fundTypeEnum.getFundTypeCode().equals(fundTypeCode)){
                return fundTypeEnum;
            }
        }
        return null;
    }

    public void setFundTypeCode(String fundTypeCode) {
        this.fundTypeCode = fundTypeCode;
    }

    public String getFundTypeName() {
        return fundTypeName;
    }

    public void setFundTypeName(String fundTypeName) {
        this.fundTypeName = fundTypeName;
    }
}
