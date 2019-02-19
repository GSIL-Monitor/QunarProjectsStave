package com.qunar.fintech.plat.admin.query.utils.nemo;

import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author tongjie Initial Created at 21/07/2017<p>
//-------------------------------------------------------
public final class ParamChecker {

    private ParamChecker() {
    }

    public static void notBlank(String arg, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(StringUtils.isNotBlank(arg), errMsg);
    }

    public static void notNull(Object arg, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(arg != null, errMsg);
    }

    public static void isTrue(boolean flag, String errMsg) {
        Preconditions.checkArgument(flag, errMsg);
    }

    public static void notEmpty(Object object, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(object != null, errMsg);
    }

    public static void notEmpty(Collection<?> collection, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(collection), errMsg);
    }

    public static void notEmpty(Map<?, ?> map, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(MapUtils.isNotEmpty(map), errMsg);
    }

    public static void checkMaxLength(String arg, int maxLength, String errMsg) {
        if (arg == null) return;
        Preconditions.checkArgument(arg.length() <= maxLength, errMsg);
    }

    public static void checkMinLength(String arg, int minLength, String errMsg) {
        if (arg == null) return;
        Preconditions.checkArgument(arg.length() >= minLength, errMsg);
    }

    public static void notStringIdemp(String arg, String errMsg) throws IllegalArgumentException {
        Preconditions.checkArgument(StringUtils.isNotBlank(arg), errMsg);
    }
}
