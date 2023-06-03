package com.study.bean.io;

public enum PayTypeEnum {

    WX_PAY(1, "微信支付"),;

    private Integer key;
    private String desc;

    PayTypeEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDesc(Integer key) {
        for (PayTypeEnum c : PayTypeEnum.values()) {
            if (c.key.equals(key)) {
                return c.getDesc();
            }
        }
        return null;
    }
}
