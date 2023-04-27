package com.study.common.util;

import redis.clients.jedis.Jedis;

public class InitData {

    public static boolean init(){
        try{
            Jedis jedis = new Jedis("127.0.0.1",6379);
            if (jedis.ping().equals("PONG")){
                return true;
            }
            return false;
        }catch(Exception ex){
        }
        return false;
    }
}
