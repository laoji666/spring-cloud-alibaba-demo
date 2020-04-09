package com.laoji.cloud.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.laoji.cloud.dto.FileInfo;
import com.laoji.commons.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传服务
 * <p>
 * Description:
 * </p>
*
*  @author: laoji
*  @date:2020/4/8 18:05
*/
@RestController
@RequestMapping(value = "upload")
public class UploadController {
    @Value("${oss.ENDPOINT}")
    private String ENDPOINT;
    @Value("${oss.ACCESS_KEY_ID}")
    private String ACCESS_KEY_ID;
    @Value("${oss.ACCESS_KEY_SECRET}")
    private String ACCESS_KEY_SECRET;
    @Value("${oss.BUCKET_NAME}")
    private String BUCKET_NAME;
    /**
     * 文件上传
     *
     * @param multipartFile @{code MultipartFile}
     * @return {@link ResponseResult<FileInfo>} 文件上传路径
     */
    @PostMapping(value = "")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;
        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, newName, new ByteArrayInputStream(multipartFile.getBytes())));
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(ResponseResult.OK, "文件上传成功", new FileInfo("http://" + BUCKET_NAME + "." + ENDPOINT + "/" + newName));
        } catch (IOException e) {
            return new ResponseResult<FileInfo>(ResponseResult.FAIL, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }
}
