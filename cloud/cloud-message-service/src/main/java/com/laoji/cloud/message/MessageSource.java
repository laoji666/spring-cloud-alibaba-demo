package com.laoji.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
*
*  @author: laoji
*  @date:2020/4/11 11:36
*/
public interface MessageSource {
    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();
}
