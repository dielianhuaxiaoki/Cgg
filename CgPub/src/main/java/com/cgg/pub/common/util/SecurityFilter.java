package com.cgg.pub.common.util;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 */
//@Component
public class SecurityFilter implements Filter {

    public static final ThreadLocal threadLocal = new ThreadLocal();

    public final static String SessiondIdKey = "ZBKC_SESSION_ID";

    /**
     * 排除不校验权限的URL
     */
    private Map<String, String> excludeUrls = new ConcurrentHashMap<String, String>();

//    @Autowired
    private AppMemcachedClient appMemcachedClient;

    public void init(FilterConfig filterConfig) throws ServletException {
//        logger.info("=================SecurityFilter init==================");
        String excludedUrls = filterConfig.getInitParameter("ExcludedUrl");

        //load /WEB-INF/excludeUrl.properties
        InputStream in = null;
        try {
            File file = new File(filterConfig.getServletContext().getRealPath("/") + excludedUrls);
            if (file.exists() && file.canRead()) {
                in = new FileInputStream(file);
                Properties prop = new Properties();
                prop.load(in);
                if (prop != null) {
                    for (Object excludeUrl : prop.keySet()) {
                        String sUrl = excludeUrl.toString().trim();
//                        logger.info("excludeUrl:" + sUrl);
                        excludeUrls.put(sUrl, "");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("load " + excludedUrls + " error");
            System.err.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        logger.info("=================SecurityFilter doFilter==================");
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        String requestUri = request.getRequestURI();
//        logger.info("Request URI:" + requestUri);

        try {
            //Cookie中添加ZBKC_SESSION_ID
            String sessionId = getFormCookie(request, SessiondIdKey);
            if (sessionId == null) {
                sessionId = UUID.randomUUID().toString();
                Cookie cookie = new Cookie(SessiondIdKey, sessionId);
                cookie.setPath("/");
                response.addCookie(cookie);
                request.setAttribute(SessiondIdKey, sessionId);
            }


            //排除不需要过滤器处理的URL
            if (excludeUrls.containsKey(requestUri)) {
                request.setAttribute(PubConfig.SECURITY_FILTER_PASS_KEY, Boolean.TRUE);
                chain.doFilter(request, response);
                return;
            }

//            logger.debug("sessionId:" + sessionId);
            RemoteUserInfo remoteUserInfo = (RemoteUserInfo) appMemcachedClient.get(sessionId);

//            logger.info("remoteUserInfo:" + remoteUserInfo);

            if (remoteUserInfo != null) {
                //更新session超时时间
                appMemcachedClient.replace(sessionId, PubConfig.SESSION_TIMEOUT, remoteUserInfo);
            }

            if (remoteUserInfo == null) {
                //未登录，没有权限继续访问
                writeSecurityFilter(response, requestUri);
                return;
            }

            //如果是操作人员，验证其权限
            if (RemoteUserInfo.RemoteUserType.OPERATOR.equals(remoteUserInfo.getRemoteUserType())) {
                Map operatorSessionInfo = new HashMap();
                operatorSessionInfo.put(PubConfig.OPERATOR_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS, AppIpUtils.getRealIp(request));
                operatorSessionInfo.put(PubConfig.OPERATOR_SEESSION_INFO_KEY.CLIENT_LOCALE, request.getLocale());
                operatorSessionInfo.put(PubConfig.OPERATOR_SEESSION_INFO_KEY.OPERATOR_ID, remoteUserInfo.getRemoteUserId());
                operatorSessionInfo.put(PubConfig.OPERATOR_SEESSION_INFO_KEY.OPERATORNAME, remoteUserInfo.getRemoteUserName());
                operatorSessionInfo.put(PubConfig.OPERATOR_SEESSION_INFO_KEY.EMAIL, remoteUserInfo.getRemoteEmial());

//                logger.debug("set ThreadLocal value:" + operatorSessionInfo + ",threadId:" + Thread.currentThread().getId());
                threadLocal.set(operatorSessionInfo);
                try {
                    request.setAttribute(PubConfig.SECURITY_FILTER_PASS_KEY, Boolean.TRUE);
                    chain.doFilter(req, resp);
                    return;
                } catch (Exception e) {
//                    logger.error(request.getRemoteAddr() + " io error", e);
                } finally {
//                    logger.debug("remove ThreadLocal value");
                    threadLocal.remove();
                }
            }

            //如果是开发者，验证其权限
            else if (RemoteUserInfo.RemoteUserType.DEVELOPER.equals(remoteUserInfo.getRemoteUserType())) {
                Map providerInfoSessionInfo = new HashMap();
                providerInfoSessionInfo.put(PubConfig.PROVIDER_SEESSION_INFO_KEY.PROVIDER_ID, remoteUserInfo.getRemoteUserId());
                providerInfoSessionInfo.put(PubConfig.PROVIDER_SEESSION_INFO_KEY.PROVIDERNAME, remoteUserInfo.getRemoteUserName());
                providerInfoSessionInfo.put(PubConfig.PROVIDER_SEESSION_INFO_KEY.EMAIL, remoteUserInfo.getRemoteEmial());
                providerInfoSessionInfo.put(PubConfig.PROVIDER_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS, AppIpUtils.getRealIp(request));
                providerInfoSessionInfo.put(PubConfig.PROVIDER_SEESSION_INFO_KEY.CLIENT_LOCALE, request.getLocale());
//                logger.debug("set ThreadLocal value:" + providerInfoSessionInfo + ",threadId:" + Thread.currentThread().getId());
                threadLocal.set(providerInfoSessionInfo);
                try {
                    request.setAttribute(PubConfig.SECURITY_FILTER_PASS_KEY, Boolean.TRUE);
                    chain.doFilter(request, response);
                    return;
                } catch (Exception e) {
//                    logger.error(request.getRemoteAddr() + " io error", e);
                } finally {
//                    logger.debug("remove ThreadLocal value");
                    threadLocal.remove();
                }
            }

            //如果是ECOURSE平台用户，验证其权限
            else if (RemoteUserInfo.RemoteUserType.ECOURSE_USER.equals(remoteUserInfo.getRemoteUserType())) {
                Map userSessionInfo = new HashMap();
                userSessionInfo.put(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_ID, remoteUserInfo.getRemoteUserId());
                userSessionInfo.put(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_NAME, remoteUserInfo.getRemoteUserName());
                userSessionInfo.put(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.USER_EMAIL, remoteUserInfo.getRemoteEmial());
                userSessionInfo.put(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.CLIENT_IP_ADDRESS, AppIpUtils.getRealIp(request));
                userSessionInfo.put(PubConfig.ECOURSE_USER_SEESSION_INFO_KEY.CLIENT_LOCALE, request.getLocale());
//                logger.debug("set ThreadLocal value:" + userSessionInfo + ",threadId:" + Thread.currentThread().getId());
                threadLocal.set(userSessionInfo);
                try {
                    request.setAttribute(PubConfig.SECURITY_FILTER_PASS_KEY, Boolean.TRUE);
                    chain.doFilter(request, response);
                    return;
                } catch (Exception e) {
//                    logger.error(request.getRemoteAddr() + " io error", e);
                } finally {
//                    logger.debug("remove ThreadLocal value");
                    threadLocal.remove();
                }
            }

            //没有权限
            writeSecurityFilter(response, requestUri);
        } catch (Throwable t) {
            //权限过滤器出现异常，没有权限继续访问
            writeSecurityFilter(response, requestUri);
            return;
        }
    }

    private void writeSecurityFilter(HttpServletResponse response, String requestURI) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonResult jsonResult = JsonResult.failure(JsonResult.ERROR_CODE_NO_OPERATOR_SECURITY, "您没有权限访问URI[" + requestURI + "]");
            String jsonString = mapper.writeValueAsString(jsonResult);
            writer = response.getWriter();
            writer.print(jsonString);
            return;
        } catch (IOException e) {
//            logger.error("response write error:");
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    private String getFormCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String val = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    val = cookie.getValue();
                    break;
                }
            }
        }

        return val;
    }

    public void destroy() {
//        logger.info("=================SecurityFilter destroy==================");
    }
}

