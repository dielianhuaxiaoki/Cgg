package com.cgg.pub.common.util;

import java.io.Serializable;

/**
 * @author chgang
 */
public class JsonResult implements Serializable {

    public final static int ERROR_CODE_NO_OPERATOR_SECURITY = -2000;

    public final static JsonResult SUCCESS;

    private boolean result = true;

    private String message = "";

    private Object data = "";

    private int errorCode = 0;

    static {
        SUCCESS = new JsonResult();
    }

    private JsonResult() {

    }

    public JsonResult(Object parData) {

        this.data = parData;
    }

    public static JsonResult failure(String message) {
        JsonResult rtn = new JsonResult();
        rtn.result = false;
        rtn.message = message;
        return rtn;
    }

    public static JsonResult failure(int errorCode, String message) {
        JsonResult rtn = new JsonResult();
        rtn.result = false;
        rtn.message = message;
        rtn.errorCode = errorCode;
        return rtn;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
