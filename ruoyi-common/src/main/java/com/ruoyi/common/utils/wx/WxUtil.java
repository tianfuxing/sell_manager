package com.ruoyi.common.utils.wx;

import com.alibaba.fastjson2.JSONObject;

import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author liqian
 * @Date 2018/8/8
 * @Description 微信相关服务
 */
@Component
public class WxUtil {

    @Value("${pubprogram.appid}")
    private String  APPID;

    @Value("${pubprogram.appSecret}")
    private String APPSECRET;

    /**
     * 获取凭证校检接口
     * 返回
     *  eg: {"session_key":"dyNs8qiYu2V4+4ogz1X+OA==","openid":"opPQu5ebCVi30R8hOFoYyBgNE8cs"}
     */
    public JSONObject login(String code) {
        System.out.println("code:==============="+code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID+
                "&secret=" +APPSECRET+
                "&code=" +code+
                "&grant_type=authorization_code";
        String accessUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String pram = "appid=" + APPID+ "&secret=" +APPSECRET+ "&code=" +code+ "&grant_type=authorization_code";
        String responseEntity = HttpUtils.sendGet(accessUrl,pram);
        if (responseEntity != null) {
            String sessionData = responseEntity;
            System.out.println("sessionData====================="+sessionData);
            JSONObject json = JSONObject.parseObject(sessionData);
            if(ObjectUtils.isNotEmpty(json.getString("errcode"))){
                return null;
            }
            String openid=json.getString("openid").toString();

            String token=json.getString("access_token").toString();
            //获取信息
            String infoUrl="https://api.weixin.qq.com/sns/userinfo";
            String parm = "access_token=" +token+ "&openid=" +openid+ "&lang=zh_CN";
            String responseEntity1 = HttpUtils.sendGet(infoUrl,parm);
            if (responseEntity1 != null) {
                System.out.println("userinfo====================="+responseEntity1);
                JSONObject jsonJUser = JSONObject.parseObject(responseEntity1);
                json.put("userInfo",jsonJUser);
            }
            return json;
        }
        return null;
    }


}
