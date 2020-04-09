package com.laoji.cloud.feign;

import com.laoji.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * <p>
 * Description:
 * </p>
 *  @author: laoji
 *  @date:2020/4/8 18:02
 */
@FeignClient(value = "cloud-upload", path = "upload", configuration = FeignRequestConfiguration.class)
public interface UploadFeign {
    /**
     * 文件上传
     *
     * @param multipartFile {@code MultipartFile}
     * @return {@code String} 文件上传路径
     */
    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);
}
