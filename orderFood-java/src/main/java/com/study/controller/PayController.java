package com.study.controller;

import com.study.bean.form.WxPayForm;
import com.study.bean.specific.Order;
import com.study.common.bean.CommonResult;
import com.study.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Value("${study.wx.appId:}")
    public String appId;

    //支付
    @PostMapping("/pay")
    public CommonResult pay(@PathVariable String openId, @RequestBody Order form) throws Exception {
        return payService.pay(form,openId);
    }

    //支付回调
    @PostMapping("/payNotify")
    public CommonResult payNotify(@RequestBody String xmlData, HttpServletResponse response) throws Exception {
        payService.payNotify(appId, xmlData, response);
        return CommonResult.success();
    }
}

