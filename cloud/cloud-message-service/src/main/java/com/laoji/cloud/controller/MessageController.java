package com.laoji.cloud.controller;

import com.laoji.cloud.dto.UmsAdminLoginLogDTO;
import com.laoji.cloud.producer.MessageProducer;
import com.laoji.commons.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
*
*  @author: laoji
*  @date:2020/4/16 11:05
*/
@RestController
@RequestMapping(value = "message")
public class MessageController {
    @Resource
    private MessageProducer messageProducer;

    @PostMapping(value = "admin/login/log")
    public ResponseResult<Void> sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto) {
        boolean flag = messageProducer.sendAdminLoginLog(dto);
        // 发送成功
        if (flag) {
            return new ResponseResult<Void>(ResponseResult.OK, "管理员登录日志发送成功");
        }
        // 发送失败
        else {
            return new ResponseResult<Void>(ResponseResult.FAIL, "管理员登录日志发送失败");
        }
    }
}
