package com.ruoyi.system.domain.base;


import java.io.Serializable;

/**
 * @Author tianfuxing
 * @Date 2022/2/17
 * @Description http响应实体
 */

public class ResponseData implements Serializable {
    public ResponseData() {
    }

    /**
     * 错误码
     */
    private Integer code = Integer.MIN_VALUE;
    /**
     * 说明信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 接口调用标记
     */
    private Boolean success = Boolean.FALSE;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private ResponseData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseData(Object data, Boolean success) {
        this.data = data;
        this.success = success;
        if (this.success) {
            this.code = 0;
        }
    }

    private ResponseData(String msg, Boolean success) {
        this.msg = msg;
        this.success = success;
        if (this.success) {
            this.code = 0;
        }
    }

    private ResponseData(ResponseCode code) {
        this.msg = code.getName();
        this.code = code.getCode();
    }

    private ResponseData(Boolean success) {
        this.success = success;
    }


    public static ResponseData errorWithCodeAndMsg(ResponseCode code) {
        return errorWithCodeAndMsg(code.getCode(), code.getName());
    }

    public static ResponseData errorWithCodeAndMsg(Integer code, String msg) {
        return new ResponseData(code, msg);
    }

    public static ResponseData successWithData(Object data) {
        return new ResponseData(data, Boolean.TRUE);
    }

    public static ResponseData errorWithMsg(String msg) {
        return new ResponseData(msg, Boolean.FALSE);
    }

    public static ResponseData success() {
        ResponseData responseData = new ResponseData(Boolean.TRUE);
        responseData.setCode(0);
        return responseData;
    }

}
