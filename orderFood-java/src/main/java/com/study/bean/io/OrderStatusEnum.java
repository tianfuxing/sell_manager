package com.study.bean.io;

public enum OrderStatusEnum {
    CANCEL(0,"已取消"),
    NOT_YET_SHIPPED(1,"待支付"),
    PART_OF_THE_SHIPMENT(2,"待发货"),
    WAIT_RECEIVING(3,"待收货"),
    ACHIEVE(4,"已完成"),
    ;
    private Integer key;
    private String desc;

    OrderStatusEnum(Integer key, String desc){
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}