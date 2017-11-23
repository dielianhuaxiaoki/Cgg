package com.cgg.pub.common.util;

/**
 * Created by IntelliJ IDEA.
 * User: ????
 * Date: 2010-10-11
 * Time: 13:53:57
 */
public class PubConfig {
    public static final String SECURITY_FILTER_PASS_KEY = "SECURITY_FILTER_PASS_KEY";

    /**
     * Session超时时间,以秒为单位
     */
    public static final int SESSION_TIMEOUT = 30 * 60;

    public final static class SESSION_KEY {
        public final static String VALIDATECODE_KEY = "VALIDATECODE_KEY";
        public final static String OPERATOR_KEY = "OPERATOR_SEESSION_INFO";
        public final static String PROVIDER_KEY = "PROVIDER_SESSION_INFO";
    }

    public final static class OPERATOR_SEESSION_INFO_KEY {
        public static final String OPERATOR_ID = "operatorId";
        public static final String CLIENT_IP_ADDRESS = "CLIENT_IP_ADDRESS";
        public static final String CLIENT_LOCALE = "CLIENT_LOCALE";
        public static final String OPERATORNAME = "operatorName";
        public static final String EMAIL = "email";
    }

    public final static class PROVIDER_SEESSION_INFO_KEY {
        public static final String PROVIDER_ID = "providerId";
        public static final String CLIENT_IP_ADDRESS = "CLIENT_IP_ADDRESS";
        public static final String CLIENT_LOCALE = "CLIENT_LOCALE";
        public static final String PROVIDERNAME = "providerName";
        public static final String EMAIL = "email";
    }

    public class ECOURSE_USER_SEESSION_INFO_KEY {
        public static final String USER_EMAIL = "USER_EMAIL";
        public static final String USER_NAME = "USER_NAME";
        public static final String CLIENT_IP_ADDRESS = "CLIENT_IP_ADDRESS";
        public static final String CLIENT_LOCALE = "CLIENT_LOCALE";
        public static final String USER_ID = "USER_ID";
    }
}
