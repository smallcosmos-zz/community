package com.hmily.community.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/5/2 上午10:36
 * @Created by zhaoli
 */
@Service
public class SMSSendServiceImpl implements SMSSendService {

    @Value("${aliyun.AccessKey.ID}")
    String accessKeyId;
    @Value("${aliyun.AccessKey.Secret}")
    String accessKeySecret;
    @Value("${aliyun.regionID}")
    String regionId;
    @Value("${aliyun.SignName}")
    String SignName;

    @Override
    public boolean smsSend(String phone, String TemplateCode, String code) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", code);
        try {
            CommonResponse response = client.getCommonResponse(request);

            String data = response.getData();
            String[] strings = data.split(",");
            if(strings[0].split(":")[1].contains("OK")){
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
