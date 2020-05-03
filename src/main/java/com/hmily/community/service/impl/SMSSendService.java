package com.hmily.community.service.impl;

/**
 * @Date 2020/5/2 上午10:34
 * @Created by zhaoli
 */
public interface SMSSendService {
    boolean smsSend(String phone,String TemplateCode,String code);
}
