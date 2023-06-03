package com.study.service;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.study.bean.User;
import com.study.bean.form.AfterPayVO;
import com.study.bean.form.WxPayForm;
import com.study.bean.io.OrderStatusEnum;
import com.study.bean.specific.Order;
import com.study.common.bean.CommonResult;
import com.study.dao.SpecificMapper;
import com.study.utils.IpUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PayService {

    @Autowired
    private  AuthenticationService authenticationService;

    @Value("${study.wx.notifyUrl}")
    private String notifyUrl;

    @Value("${study.wx.appId:}")
    public String appId;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpecificMapper mapper;

    //支付接口
    public CommonResult pay(Order order, String wxId) throws WxPayException {
        //Order order
        // 查询根据微信id（存微信用户表的id）查询MySQL数据库获取当前用户的微信数据
        User byId = authenticationService.searchDataByOpenid(wxId);
        if (Objects.isNull(byId)){
            return CommonResult.failed("为查询到微信用户信息支付失败");
        }
        WxPayService wxPayService = this.getWxPayService(appId);

        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("商品付款");
        orderRequest.setOutTradeNo(order.getOrderPayNo());
        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(order.getTotalmoney().toString()));//元转成分
        orderRequest.setOpenid(byId.getOpenid()); // 支付微信用户 微信openId
        orderRequest.setSpbillCreateIp(IpUtils.getAddrIp()); // 本地ip设置
        orderRequest.setSpbillCreateIp("114.55.114.66");
        orderRequest.setNotifyUrl(String.format(notifyUrl, "payNotify", byId.getOpenid())); // 支付回调路径与参数拼接设置
        // 支付方式
        orderRequest.setTradeType("JSAPI");

        orderRequest.setAppid(appId);
        Object result = wxPayService.createOrder(orderRequest);
        AfterPayVO afterPayVO = (AfterPayVO) result;
        afterPayVO.setAppId(appId);
        return CommonResult.success(afterPayVO);
    }


    //支付回调
    public void payNotify(String appid, String xmlData, HttpServletResponse response) throws Exception {

        WxPayService wxPayService = this.getWxPayService(appid);
        final WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

        // 小程序支付成功后回调这个接口 后的订单业务逻辑 （请根据项目来编写）
        //根据支付订单号查询订单信息并进行状态修改
        Order order = mapper.getOrderByOutTradeNo(notifyResult.getOutTradeNo());
        if(ObjectUtils.isNotEmpty(order)){
            //根据订单状态修改业务订单状态
            mapper.updateOrderStatus(order.getId(),"订单完成");
        }
        //回复微信防止重复回调
        StringBuilder resXml = new StringBuilder();
        resXml.append("<xml>");
        resXml.append("<return_code><![CDATA[SUCCESS]]></return_code>");
        resXml.append("<return_msg><![CDATA[OK]]></return_msg>");
        resXml.append("</xml>");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.toString().getBytes());
        out.flush();
        out.close();
    }

    /** 获取商户号配置并生成 WxPayService
     * getWxPayService
     * @param appid
     * @return
     */
    private WxPayService getWxPayService(String appid){
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appid);
        payConfig.setMchId(StringUtils.trimToNull("1644030577"));
        payConfig.setMchKey(StringUtils.trimToNull("81f773463aee4f2e5e869e442e2e2ce0"));
        payConfig.setKeyPath(StringUtils.trimToNull("G:\\WXCertUtil\\cert\\apiclient_cert.p12"));
        payConfig.setPrivateCertPath("G:\\WXCertUtil\\cert\\apiclient_cert.pem");
        payConfig.setPrivateKeyPath("G:\\WXCertUtil\\cert\\apiclient_key.pem");

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
