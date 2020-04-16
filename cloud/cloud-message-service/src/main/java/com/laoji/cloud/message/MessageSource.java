package com.laoji.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
*  消息生产者 接口
*  @author: laoji
*  @date:2020/4/11 11:36
*/
public interface MessageSource {
    /**
     * 管理员日志消息生产者
     * @return MessageChannel  {@link MessageChannel}
     */
    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();
}
