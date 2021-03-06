package com.redmount.template.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class RequestUtil {
    public static String getHeaderStringFromRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration enu = request.getHeaderNames();
        String headerName;
        while (enu.hasMoreElements()) {//以此取出头信息
            headerName = enu.nextElement().toString();
            sb.append(headerName);
            sb.append(":");
            sb.append(request.getHeader(headerName));
            sb.append(",");
        }
        return sb.toString();
    }
    /**
     * 取的当前访问来源IP
     *
     * @param request 请求实体
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip;
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}
