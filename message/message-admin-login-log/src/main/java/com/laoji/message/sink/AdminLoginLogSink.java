package com.laoji.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 管理员登录日志消息消费者
 * @author laoji
 */
public interface AdminLoginLogSink {
    /**
     * 消费 admin-login-log-topic 消息
     * @return SubscribableChannel {@link SubscribableChannel}
     */
    @Input("admin-login-log-topic")
    SubscribableChannel adminLoginLog();
}
