package com.cgg.pub.common.util;

import java.io.Serializable;

/**
 * 认证中心返回的用户信息
 */
public class RemoteUserInfo implements Serializable {

    /**
     * 整个系统惟一标识
     */
    private String remoteUserAccount;

    /**
     * 姓名
     */
    private String remoteUserName;

    /**
     * 电子邮件
     */
    private String remoteEmial;

    /**
     * 用户类型，详见枚举  RemoteUserInfo.RemoteUserType
     */
    private RemoteUserType remoteUserType;

    /**
     * 用途不详
     */
    private String se;

    /**
     *
     */
    private String remoteUserId;

    /**
     * 用户类型枚举
     */
    public static enum RemoteUserType {
        /**
         * 操作人员（后台管理员）
         */
        OPERATOR,
        /**
         * 开发者
         */
        DEVELOPER,

        /**
         * ECOURSE平台用户(教师或学生)
         */
        ECOURSE_USER
    }

    public static final String REQUEST_ATTRIBUTE_KEY = "com.zbkc.sso.RemoteUserInfo";

    public String getRemoteUserAccount() {
        return remoteUserAccount;
    }

    public void setRemoteUserAccount(String remoteUserAccount) {
        this.remoteUserAccount = remoteUserAccount;
    }

    public String getRemoteUserName() {
        return remoteUserName;
    }

    public void setRemoteUserName(String remoteUserName) {
        this.remoteUserName = remoteUserName;
    }

    public String getRemoteEmial() {
        return remoteEmial;
    }

    public void setRemoteEmial(String remoteEmial) {
        this.remoteEmial = remoteEmial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteUserInfo that = (RemoteUserInfo) o;

        if (!remoteUserAccount.equals(that.remoteUserAccount)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return remoteUserAccount.hashCode();
    }

    public RemoteUserType getRemoteUserType() {
        return remoteUserType;
    }

    public void setRemoteUserType(RemoteUserType remoteUserType) {
        this.remoteUserType = remoteUserType;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getRemoteUserId() {
        return remoteUserId;
    }

    public void setRemoteUserId(String remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    @Override
    public String toString() {
        return "RemoteUserInfo{" +
                "remoteUserAccount='" + remoteUserAccount + '\'' +
                ", remoteUserName='" + remoteUserName + '\'' +
                ", remoteEmial='" + remoteEmial + '\'' +
                ", remoteUserType='" + remoteUserType + '\'' +
                '}';
    }
}
