package com.hmily.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.hmily.community.service.impl.SMSSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2020/5/2 上午10:33
 * @Created by zhaoli
 */
@RestController
@CrossOrigin
public class SMSSendController {
    @Autowired
    private SMSSendService smsSendService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/sms/{phoneNumers}")
    public String SmsSend(@PathVariable("phoneNumers") String phoneNumers){
        String code = redisTemplate.opsForValue().get(phoneNumers);
        if (code != null){
            return "验证码已经发送，请填写验证码";
        }
        Map<String,String> map = new HashMap<>();
        map.put("code", UUID.randomUUID().toString().substring(0,4));
        boolean b = smsSendService.smsSend(phoneNumers, "SMS_189610902", JSONObject.toJSONString(map));
        if(b){
            redisTemplate.opsForValue().set(phoneNumers,map.get("code"),5, TimeUnit.MINUTES);
            return "验证码发送成功";
        }else {
            return "验证码发送失败，请重新发送";
        }

    }

}
