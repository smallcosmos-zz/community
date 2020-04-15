package com.hmily.community.controller;

import com.hmily.community.dto.FileDTO;
import com.hmily.community.privoder.AliyunProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Date 2020/4/14 下午12:15
 * @Created by zhaoli
 */
@Controller
public class FileController {
    @Autowired
    private AliyunProvider aliyunProvider;
    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        String imgUrl = aliyunProvider.checkImage(file);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl(imgUrl);
        return fileDTO;
    }
}
