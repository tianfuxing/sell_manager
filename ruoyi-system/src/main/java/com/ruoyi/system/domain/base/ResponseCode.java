package com.ruoyi.system.domain.base;

/**
 * @Author tyianfuxing
 * @Date 2022/2/17
 * @Description 错误响应代码含义
 */
public enum ResponseCode {

    /**
     * 没有登录
     */
    NO_LOGIN(-1, "用户没有登录"),
    /**
     * TOKEN过期
     */
    TOKEN_EXPIRE(-2, "TOKEN过期"),
    /**
     * TOKEN签名错误
     */
    TOKEN_SIGNATURE_FAIL(-3, "TOKEN签名错误"),
    /**
     * 服务端错误
     */
    SERVER_ERROR(-4, "服务端错误"),
    /**
     * 插入失败
     */
    INSERT_ERROR(-5, "插入失败"),
    /**
     * 更新失败
     */
    UPDATE_ERROR(-6, "更新失败"),
    /**
     * 删除失败
     */
    DELETE_ERROR(-7, "删除失败"),
    /**
     * 登录失败
     */
    LOGIN_ERROR(-8, "用户名或密码错误"),
    /**
     * 用户名重复
     */
    DUPLICATE_USERNAME(-9, "用户名重复");
    /**
     * 代码
     */
    private Integer code;
    /**
     * 对应含义
     */
    private String name;

    ResponseCode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
