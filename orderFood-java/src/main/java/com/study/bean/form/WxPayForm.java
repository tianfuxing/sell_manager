package com.study.bean.form;

import java.math.BigDecimal;

/**
 * 微信支付form提交数据
 */
public class WxPayForm {

    private Integer orderId;

    //订单号
    private String orderNo;

    //支付订单号
    private String orderPayNo;

    //实际支付金额
    private BigDecimal payAmount;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderPayNo() {
        return orderPayNo;
    }

    public void setOrderPayNo(String orderPayNo) {
        this.orderPayNo = orderPayNo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}