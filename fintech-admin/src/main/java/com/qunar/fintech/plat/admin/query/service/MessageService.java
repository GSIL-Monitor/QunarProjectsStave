package com.qunar.fintech.plat.admin.query.service;

import com.qunar.pay.g2.api.message.dto.SendMessageRequestDto;
import com.qunar.pay.g2.api.message.dto.SendMessageResponseDto;

/**
 * 短信发送服务
 * @author dw.wang
 * @since 2017-03-10
 */
public interface MessageService {

    /**
     * 发送短信
     */
    SendMessageResponseDto sendMsg(SendMessageRequestDto msgReq);
}
