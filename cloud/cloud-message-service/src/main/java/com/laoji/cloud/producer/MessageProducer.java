package com.laoji.cloud.producer;

import com.laoji.cloud.api.MessageService;
import com.laoji.cloud.dto.UmsAdminLoginLogDTO;
import com.laoji.cloud.message.MessageSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
*
*  @author: laoji
*  @date:2020/4/11 11:36
*/
@Component
@Service
public class MessageProducer implements MessageService {
    @Resource
    private MessageSource source;
    /**
     * 管理登录日志
     *
     * @param dto {@link UmsAdminLoginLogDTO}
     * @return {@code boolean}
     */
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }
}
