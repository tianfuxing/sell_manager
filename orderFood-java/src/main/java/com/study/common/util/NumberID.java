package com.study.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberID {

    public synchronized static String  getNumberId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }
}
