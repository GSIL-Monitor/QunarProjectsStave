package com.qunar.fintech.plat.admin.query.utils;

import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static final String DOMAIN = "qunar.com";

    /**
     * 获取cookie中的用户登陆名
     *
     * @param request
     * @return
     */
    public static String getUserNameByQName(HttpServletRequest request) {
        String userName = getCookieValue(request, "_q");
        if (StringUtils.hasLength(userName) && userName.length() > 2) {
            userName = new String(userName.substring(2));
        }
        return userName;
    }

    /**
     * 获取cookie中的值
     *
     * @param request
     * @param keyName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String keyName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (keyName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeCookie(final HttpServletResponse response, String name, String value) {

        Cookie cookie = null;
        try {
            cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("url转码出现异常", e);
            // e.printStackTrace();
            return;
        }
        cookie.setPath("/");
        cookie.setDomain(DOMAIN);
        cookie.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            try {
                return URLDecoder.decode(cookie.getValue(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("url转码出现异常", e);
            }
            return null;
        } else {
            return null;
        }
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie userCookie = new Cookie(name, null);
        userCookie.setPath("/");
        userCookie.setDomain(DOMAIN);
        userCookie.setMaxAge(0);
        response.addCookie(userCookie);
    }

    public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CookieUtil.class);
}
