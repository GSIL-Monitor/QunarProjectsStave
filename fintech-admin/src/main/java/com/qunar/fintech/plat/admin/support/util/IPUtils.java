package com.qunar.fintech.plat.admin.support.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IPUtils {

    private static String ip;
    private static String wholeIp;  //完整IP
    private static final Logger Log = LoggerFactory.getLogger(IPUtils.class);
    private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    static {
        try {
            @SuppressWarnings("static-access")
            String _ip = getAddress().getLocalHost().getHostAddress();
            Integer temp = new Integer(_ip.substring(_ip.lastIndexOf(".") + 1));
            ip = String.format("%03d", temp);
            wholeIp = _ip;
        } catch (UnknownHostException e) {
            Log.error("获取本地IP异常", e);
        }
    }

    private static InetAddress getAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
            logger.error("");
        }
        return null;
    }

    public static String getLocalIp() {
        return ip;
    }

    /**
     * 获得完整IP
     * @return
     */
    public static String getWholeIp() {
        return wholeIp;
    }
}
