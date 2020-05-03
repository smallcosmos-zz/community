package com.hmily.community.privoder;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.hmily.community.exception.CustomizeErrorCode;
import com.hmily.community.exception.CustomizeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Date 2020/4/14 下午3:50
 * @Created by zhaoli
 */
@Component
public class AliyunProvider {
    @Value("${aliyun.endpoint}")
    String endpoint;
    @Value("${aliyun.AccessKey.ID}")
    String accessKeyId;
    @Value("${aliyun.AccessKey.Secret}")
    String accessKeySecret;
    @Value("${aliyun.bucketName}")
    String bucketName;


    //文件存储目录
    private String filedir = "hmily/communnity/";

    /**
     *
     * 上传图片
     * @param file
     * @return
     */
    public String uploadImg2Oss(MultipartFile file) {
        if (file.getSize() > 1024 * 1024 *20) {
            return "图片太大";
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

        String name = UUID.randomUUID().toString().replace("-","") + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            return "上传失败";
        }
    }

    /**
     * 上传图片获取fileUrl
     * @param inputStream
     * @param fileName
     * @return
     */
    private String uploadFile2OSS(InputStream inputStream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            throw  new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILE);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw  new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILE);
            }
        }
        return ret;
    }

    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获取图片路径
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            String url =  this.getUrl(this.filedir + split[split.length - 1]);
            return url;
        }
        else {
            throw  new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILE);
        }
    }

    public String getUrl(String key) {
        //设置URL过期时间
        Date expiration = new Date(System.currentTimeMillis() + 60*60*24*365*10);
        // 生成URL
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }else {
            throw  new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAILE);
        }
    }

    /**
     * 多图片上传
     * @param fileList
     * @return
     */
    public String checkList(List<MultipartFile> fileList) {
        String  fileUrl = "";
        String  str = "";
        String  photoUrl = "";
        for(int i = 0;i< fileList.size();i++){
            fileUrl = uploadImg2Oss(fileList.get(i));
            str = getImgUrl(fileUrl);
            if(i == 0){
                photoUrl = str;
            }else {
                photoUrl += "," + str;
            }
        }
        return photoUrl.trim();
    }

    /**
     * 单个图片上传
     * @param file
     * @return
     */
    public String checkImage(MultipartFile file){
        String fileUrl = uploadImg2Oss(file);
        String str = getImgUrl(fileUrl);
        return str.trim();
    }


}
