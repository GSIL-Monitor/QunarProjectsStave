package com.qunar.fintech.plat.admin.query.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金额工具类
 *
 */
public class AmountUtil {
    private static final Logger logger= LoggerFactory.getLogger(AmountUtil.class);
    /* 一年的天数 */
    public static final BigDecimal ONE_YEAR_DAYS = new BigDecimal("360.00");
    /* 一百，做百分处理 */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100.00");
    /* 一万，用于计算万份收 */
    public static final BigDecimal TEN_THOUSAND = new BigDecimal("10000.00");
    /* 6位有效小数 */
    public static final int SIX_VALID_COUNT = 6;
    /* 2位有效小数 */
    public static final int TWO_VALID_COUNT = 2;
    /* 3位有效小数 */
    public static final int THREE_VALID_COUNT = 3;

    /**
     * 计算万份收益
     *
     * @param profitRate 利率
     * @param copyAmount 份数
     * @return 万份收益
     */
    public static BigDecimal calculateMillionProfit(BigDecimal profitRate,BigDecimal copyAmount) {
        if (profitRate == null) {
            profitRate = BigDecimal.ZERO;
        }
        BigDecimal numerator = profitRate.multiply(TEN_THOUSAND).multiply(copyAmount);
        BigDecimal denominator = ONE_YEAR_DAYS.multiply(ONE_HUNDRED);
        BigDecimal calcProfitAmount = numerator.divide(denominator, SIX_VALID_COUNT, RoundingMode.DOWN);
        return calcProfitAmount.setScale(TWO_VALID_COUNT, RoundingMode.DOWN);
    }

    /**
     * 计算活期基金的盈余收益，即小数点后面2位之后的部分。
     *
     * @param profitAmount 收益
     * @return 盈余收益
     */
    public static BigDecimal calculateSurplus(BigDecimal profitAmount) {
        if (profitAmount == null || profitAmount.compareTo(BigDecimal.ZERO) <= 0) {
            /* 为空时或者小于等于0时直接返回0 */
            return BigDecimal.ZERO;
        }
        BigDecimal profitAmountWithLoss = profitAmount.setScale(TWO_VALID_COUNT, RoundingMode.DOWN);

        return profitAmount.subtract(profitAmountWithLoss);
    }

    /**
     * decimalA 加 decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal add(BigDecimal decimalA, BigDecimal decimalB) {
        if (decimalA == null || decimalB == null)
            return null;
        try {
            decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
            decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
            return (decimalA.add(decimalB)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * decimalA 减 decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal sub(BigDecimal decimalA, BigDecimal decimalB) {
        if (decimalA == null || decimalB == null)
            return null;
        try {
            decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
            decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
            return (decimalA.subtract(decimalB)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * decimalA 减 decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal subRoundDown(BigDecimal decimalA, BigDecimal decimalB) {
        if (decimalA == null || decimalB == null)
            return null;
        try {
            // decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
            // decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
            return (decimalA.subtract(decimalB)).setScale(2, BigDecimal.ROUND_DOWN);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * decimalA 乘 decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal mul(BigDecimal decimalA, BigDecimal decimalB) {
        if (decimalA == null || decimalB == null)
            return null;
        try {
            decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
            decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
            return (decimalA.multiply(decimalB)).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * decimalA 除 decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static BigDecimal div(BigDecimal decimalA, BigDecimal decimalB) {
        if (decimalA == null || decimalB == null)
            return null;

        if (decimalB.compareTo(BigDecimal.ZERO) == 0)
            return null;
        try {
            decimalA = decimalA.setScale(2, BigDecimal.ROUND_HALF_UP);
            decimalB = decimalB.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal result = decimalA.divide(decimalB, RoundingMode.HALF_UP);
            return result.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 字符串转BigDecimal
     * 
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str) {
        if (str == null || "".equals(str.trim()))
            return null;
        try {
            return (new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * Double转BigDecimal
     * 
     * @param
     * @return
     */
    public static BigDecimal toBigDecimal(Double d) {
        if (d == null)
            return null;
        try {
            return (new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * decimalA近似等于decimalB
     * 
     * @param decimalA
     * @param decimalB
     * @return
     */
    public static boolean isApproxiEqual(BigDecimal decimalA, BigDecimal decimalB) {
        decimalA = checkBigDecimalNullAndSetScale(decimalA);
        decimalB = checkBigDecimalNullAndSetScale(decimalB);

        return decimalA.compareTo(decimalB) >= 0;
    }

    public static BigDecimal checkBigDecimalNullAndSetScale(BigDecimal totalBigDecimal) {
        if (totalBigDecimal == null) {
            totalBigDecimal = BigDecimal.ZERO;
        }
        totalBigDecimal = totalBigDecimal.setScale(2, RoundingMode.DOWN);//直接去掉小数部分，给用户显示小的，防止引起问题
        return totalBigDecimal;
    }
    public static BigDecimal bigDecimalSetScale(BigDecimal totalBigDecimal,Integer num) {
        if (totalBigDecimal == null) {
            totalBigDecimal = BigDecimal.ZERO;
        }
        totalBigDecimal = totalBigDecimal.setScale(num, RoundingMode.DOWN);//直接去掉小数部分，给用户显示小的，防止引起问题
        return totalBigDecimal;
    }
}
