package com.cgg.pub.common.util;


import java.util.Locale;
import java.util.Map;

/**
 * web上下文访问工具类
 *
 * @author Robin
 */
public class SessionContext {

    @SuppressWarnings("unused")
    private SessionContext() {

    }

    /**
     * 操作人员信息
     */
    public static class OPERATOR {
        /**
         * 获取客户端的区域
         *
         * @return
         */
        public static Locale getClientLocale() {
            Map operatorInfo = (Map) SecurityFilter.threadLocal.get();
            return (Locale) operatorInfo.get(PubConfig.OPERATOR_SEESSION_INFO_KEY.CLIENT_LOCALE);
        }

        /**
         * 获取客户端IP地址
         *
         * @return
         */
        public static String getClientIpAddress() {
            Map operatorInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) operatorInfo.get(PubConfig.OPERATOR_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS);
        }

        /**
         * 获取当前操作人员用户名
         *
         * @return
         */
        public static String getOperatorName() {
            Map operatorInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) operatorInfo.get(PubConfig.OPERATOR_SEESSION_INFO_KEY.OPERATORNAME);
        }

        /**
         * 获取当前操作人员邮箱
         *
         * @return
         */
        public static String getOperatorEmail() {
            Map operatorInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) operatorInfo.get(PubConfig.OPERATOR_SEESSION_INFO_KEY.EMAIL);
        }

        /**
         * 获取当前操作人员ID
         *
         * @return
         */
        public static String getOperatorId() {
            Map operatorInfo = (Map) SecurityFilter.threadLocal.get();

            if (operatorInfo == null) {
                return null;
            }

            return (String) operatorInfo.get(PubConfig.OPERATOR_SEESSION_INFO_KEY.OPERATOR_ID);
        }
    }

    /**
     * 开发者信息
     */
    public static class AppProvider {
        /**
         * 获取开发者ID
         *
         * @return
         */
        public static String getProviderId() {
            Map providerInfo = (Map) SecurityFilter.threadLocal.get();
            if (providerInfo == null) {
                return null;
            }
            return (String) providerInfo.get(PubConfig.PROVIDER_SEESSION_INFO_KEY.PROVIDER_ID);
        }

        /**
         * 获取开发者客户端的区域
         *
         * @return
         */
        public static Locale getProviderClientLocale() {
            Map providerInfo = (Map) SecurityFilter.threadLocal.get();
            return (Locale) providerInfo.get(PubConfig.PROVIDER_SEESSION_INFO_KEY.CLIENT_LOCALE);
        }

        /**
         * 获取开发者客户端IP地址
         *
         * @return
         */
        public static String getProviderClientIpAddress() {
            Map providerInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) providerInfo.get(PubConfig.PROVIDER_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS);
        }

        /**
         * 获取开发者名称
         *
         * @return
         */
        public static String getProviderName() {
            Map providerInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) providerInfo.get(PubConfig.PROVIDER_SEESSION_INFO_KEY.PROVIDERNAME);
        }

        /**
         * 获取开发者Email
         *
         * @return
         */
        public static String getProviderEmail() {
            Map providerInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) providerInfo.get(PubConfig.PROVIDER_SEESSION_INFO_KEY.EMAIL);
        }
    }

    /**
     * ECOURSE平台用户
     */
    public static class ECOURSE_USER {
        /**
         * 获取用户ID
         *
         * @return
         */
        public static String getUserId() {
            Map userInfo = (Map) SecurityFilter.threadLocal.get();
            if (userInfo == null) {
                return null;
            }
            return (String) userInfo.get(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_ID);
        }

        /**
         * 获取用户客户端的区域
         *
         * @return
         */
        public static Locale getUserClientLocale() {
            Map userInfo = (Map) SecurityFilter.threadLocal.get();
            return (Locale) userInfo.get(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.CLIENT_LOCALE);
        }

        /**
         * 获取用户客户端IP地址
         *
         * @return
         */
        public static String getUserClientIpAddress() {
            Map userInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) userInfo.get(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS);
        }

        /**
         * 获取用户名称
         *
         * @return
         */
        public static String getUserName() {
            Map userInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) userInfo.get(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_NAME);
        }

        /**
         * 获取用户Email
         *
         * @return
         */
        public static String getUserEmail() {
            Map userInfo = (Map) SecurityFilter.threadLocal.get();
            return (String) userInfo.get(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_EMAIL);
        }
    }

}
