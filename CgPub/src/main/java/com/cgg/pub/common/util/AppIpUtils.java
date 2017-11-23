package com.cgg.pub.common.util;

import javax.servlet.http.HttpServletRequest;

public class AppIpUtils {
    private AppIpUtils() {

    }

    public static String getRealIp(HttpServletRequest request) {
        String result = request.getHeader("x-forwarded-for");
        if (result != null && result.trim().length() > 0) {
            //可能有代理
            if (result.indexOf(".") == -1) {
                //没有“.”肯定是非IPv4格式
                result = null;
            } else {
                if (result.indexOf(",") != -1) {
                    //有“,”，估计多个代理。取第一个不是内网的IP。
                    result = result.trim().replace("'", "");
                    String[] temparyip = result.split(",");
                    for (int i = 0; i < temparyip.length; i++) {
                        if (isIPAddress(temparyip[i])
                                && temparyip[i].substring(0, 3) != "10."
                                && temparyip[i].substring(0, 7) != "192.168"
                                && temparyip[i].substring(0, 7) != "172.16.") {
                            return temparyip[i]; //找到不是内网的地址
                        }
                    }
                } else if (isIPAddress(result)) {//代理即是IP格式
                    return result;
                } else {
                    result = null; //代理中的内容 非IP，取IP
                }
            }
        }

        if (result == null || result.trim().length() == 0) {
            result = request.getHeader("Proxy-Client-IP");
        }
        if (result == null || result.trim().length() == 0) {
            result = request.getRemoteAddr();
        }
        return result;
    }

    /**
     * 判断是否是IP地址格式
     *
     * @param str1
     * @return
     */
    private static boolean isIPAddress(String str1) {
        if (str1 == null || str1.trim().length() < 7 || str1.trim().length() > 15) {
            return false;
        }
        return true;
    }
}
