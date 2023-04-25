package com.ruoyi.web.controller.system;


import com.alibaba.fastjson2.JSONObject;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.sign.HttpUtil;
import com.ruoyi.common.utils.sign.JSONUtil;
import com.ruoyi.common.utils.sign.UUIDUtil;
import com.ruoyi.common.utils.wx.WxUtil;
import com.ruoyi.system.domain.base.ResponseData;
import com.ruoyi.system.service.ISysConfigService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/wxUser")
public class WxUserController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(WxUserController.class);

    @Resource
    private WxUtil wxUtil;

//    @Resource
//    private IUserinfoService iUserinfoService;
//
//    @Autowired
//    private LogininfoService logininfoService;

    @Autowired
    private ISysConfigService iSysConfigService;

    @Value("${pubprogram.appid}")
    private String  APPID;

    @Value("${pubprogram.appSecret}")
    private String APPSECRET;

    @Value("${pubprogram.backUrl}")
    private String backUrl;
    @RequestMapping(value = "/wxLogin",produces="application/json;charset=UTF-8")
    public ResponseData wxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //回调地址,要跟下面的地址能调通(getWechatGZAccessToken.do)
        JSONObject jsonObject = new JSONObject();
        /**
         *这儿一定要注意！！首尾不能有多的空格（因为直接复制往往会多出空格），其次就是参数的顺序不能变动
         **/
        jsonObject.put("appid",APPID);
        jsonObject.put("backUrl",URLEncoder.encode(backUrl,"UTF-8"));
        return ResponseData.successWithData(jsonObject);
    }

    // 获取微信JS-SDK签名
    @RequestMapping(value = "/getJSSDKSignature",produces="application/json;charset=UTF-8")
    public ResponseData getJSSDKSignature(@RequestParam(value = "url") String url)  {
        if(ObjectUtils.isEmpty(url)){
            return ResponseData.errorWithMsg("url未指定，无法获取签名");
        }
        String access_token = iSysConfigService.selectConfigByKey("access_token");
        log.info("==========wxUserController,access_token:"+access_token+"=======");
        String ticketJson = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi", null);
        log.info("==========wxUserController,ticketJson:"+ticketJson+"=======");
        String ticket = JSONUtil.getString(ticketJson, "ticket");  // ticket
        log.info("==========wxUserController,ticket:"+ticket+"=======");
        String noncestr = UUIDUtil.randomUUID8();  // 随机字符串
        long timestamp = new Date().getTime()/1000;// 时间戳
        String str = "jsapi_ticket=" + ticket;
        str += "&noncestr=" + noncestr;
        str += "&timestamp=" + timestamp;
        str += "&url=" + url;
        String signature = DigestUtils.sha1Hex(str);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("access_token", access_token);
        jsonObject.put("APPID", APPID);
        jsonObject.put("APPSECRET", APPSECRET);
        jsonObject.put("ticket", ticket);
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("nonceStr", noncestr);
        jsonObject.put("signature", signature);
        jsonObject.put("appid",APPID);
        return ResponseData.successWithData(jsonObject);
    }


    @RequestMapping(value = "/login" ,produces="application/json;charset=UTF-8")
    public ResponseData login(@RequestParam(value = "code",required = false) String code){
        JSONObject res = wxUtil.login(code);
        JSONObject jsonObject = new JSONObject();
        if (Objects.isNull(res)) {
            return ResponseData.errorWithMsg("登录失败");
        }
        String openid = res.getString("openid");
        String session_key = res.getString("session_key");
//        String openid = "ouZ5E1cZD4EEJe2ASZxJygFuGEL8";
        //

        //判断用户是不是第一次登录
//        Userinfo userinfo = iUserinfoService.selectUserinfoById(openid);
//        if(ObjectUtils.isEmpty(userinfo) || ObjectUtils.isEmpty(userinfo.getComtyCode()) || userinfo.getComtyCode().equals("")){
//            jsonObject.put("status","0");
//        }else{
//            jsonObject.put("status","1");
//        }
//        String token = iUserinfoService.login(openid);
        String token = null;
        if (StringUtils.isEmpty(token)) {
            return ResponseData.errorWithMsg("code错误，登录失败");
        }


//        Userinfo wxUser = iUserinfoService.selectUserinfoById(openid);
//        JSONObject userinfo1 = JSONObject.parseObject(res.getString("userInfo"));
//        if(jsonObject.get("status").equals("0")){
//            wxUser.setNickName(userinfo1.getString("nickname"));
//            wxUser.setSex(userinfo1.getInteger("sex"));
//            wxUser.setHeadimgurl(userinfo1.getString("headimgurl"));
//            int flag = iUserinfoService.updateUserinfo(wxUser);
//        }
//        //登录日志记录
//        logininfoService.insertLoginlog(wxUser);
        //登录日志结束

        //修改用户信息
        jsonObject.put("token", token);
        jsonObject.put("WxUser", null);
        return ResponseData.successWithData(jsonObject);
    }
}
