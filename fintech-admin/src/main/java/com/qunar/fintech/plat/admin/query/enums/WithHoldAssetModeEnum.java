package com.qunar.fintech.plat.admin.query.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * User: yupei.wang
 * Date: 2016/1/20
 */
public enum WithHoldAssetModeEnum {
    QUNARCARD("QUNARCARD", "GWCARD", "骆驼卡余额"),
    QUNARPAY("QUNARPAY", "GWCARD", "余额"),
    CARD("CARD", "CARD", "常用卡"),
    QCARD("QCARD", "QCARD", "信用卡"),//常用卡信用卡
    QCARDD("QCARDD", "QCARDD", "借记卡"),//常用卡借记卡
    NOCARD("NOCARD", "NOCARD", "信用卡"),
    NOCARDD("NOCARDD", "NOCARDD", "借记卡"),
    QBAO("QBAO", "QBAO", "趣游宝"),

    DAIKOUPAY("DAIKOUPAY","DAIKOU","商户代扣"),
    DAIKOUSUB("DAIKOUSUB","DAIKOU","商户代扣折扣"),
    FPPDAIKOUSUB("FPPDAIKOUSUB","DAIKOU","fpp赔付补扣并还款"),
    USERREPAYDAIKOUSUB("USERREPAYDAIKOUSUB","DAIKOU","用户层还款补扣并还款"),
    REFUNDDAIKOUSUB("REFUNDDAIKOUSUB","DAIKOU","退款补扣并还款"),
    FPPDAIKOUTOUSERBALANCE("FPPDAIKOUTOUSERBALANCE","DAIKOU","FPP赔付剩余金额退到用户余额"),
    ;

    private String bankCode;
    private String pmCode;
    private String desc;

    // 超退逻辑原路退回不支持的资产模式
    public static final List<WithHoldAssetModeEnum> BACK_REFUND_MODE_NOT_SUPPORT = Lists.newArrayList(REFUNDDAIKOUSUB, REFUNDDAIKOUSUB);
    // 超退逻辑原路退回支持的优先级靠后的资产模式
    public static final List<WithHoldAssetModeEnum> BACK_REFUND_MODE_AFTER = Lists.newArrayList(DAIKOUPAY,DAIKOUSUB,FPPDAIKOUSUB,USERREPAYDAIKOUSUB);

    /**
     * 是否为折扣
     * @return
     */
    public static boolean isSubWithHold(WithHoldAssetModeEnum modelEnum){
        return DAIKOUSUB.equals(modelEnum);
    }
    /**
     * 是否为补扣
     * @return
     */
    public static boolean isRepairWithHold(WithHoldAssetModeEnum modelEnum){
        return DAIKOUSUB.equals(modelEnum) || FPPDAIKOUSUB.equals(modelEnum) || USERREPAYDAIKOUSUB.equals(modelEnum);
    }

    public static boolean isQuanrAccount(WithHoldAssetModeEnum modelEnum){
        return QUNARCARD.equals(modelEnum) || QUNARPAY.equals(modelEnum) || QBAO.equals(modelEnum);
    }

    /**
     * 是否常用卡
     * @return
     */
    public static boolean isCommCard(WithHoldAssetModeEnum modelEnum){
        return WithHoldAssetModeEnum.QCARD.equals(modelEnum) || WithHoldAssetModeEnum.QCARDD.equals(modelEnum);
    }
    /**
     * 是否常用卡
     */
    public static boolean isCard(WithHoldAssetModeEnum modelEnum){
        return WithHoldAssetModeEnum.CARD.equals(modelEnum);
    }

    WithHoldAssetModeEnum(String bankCode, String pmCode, String desc) {
        this.bankCode = bankCode;
        this.pmCode = pmCode;
        this.desc = desc;
    }

    public String getPmCode() {
        return pmCode;
    }

    public void setPmCode(String pmCode) {
        this.pmCode = pmCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static Map<String, WithHoldAssetModeEnum> typeMap = Maps.newHashMap();

    static {
        for (WithHoldAssetModeEnum typeEnum : WithHoldAssetModeEnum.values()) {
            typeMap.put(typeEnum.bankCode, typeEnum);
        }
    }

    public static WithHoldAssetModeEnum toEnum(String bankCode) {
        if (typeMap.containsKey(bankCode)) {
            return typeMap.get(bankCode);
        }
        return null;
    }
}
