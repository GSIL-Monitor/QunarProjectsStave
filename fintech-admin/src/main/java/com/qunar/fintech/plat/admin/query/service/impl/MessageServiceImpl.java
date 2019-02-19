package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.service.MessageService;
import com.qunar.pay.g2.api.message.GwMessageFacade;
import com.qunar.pay.g2.api.message.dto.SendMessageRequestDto;
import com.qunar.pay.g2.api.message.dto.SendMessageResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dw.wang
 * @since 2017-03-10
 */
@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    /* 短信发送成功响应码 */
    private static final String SUCCESS_RESP_CODE = "000000";

    @Resource
    private GwMessageFacade gwMessageFacade;

    @Override
    public SendMessageResponseDto sendMsg(SendMessageRequestDto reqDto){
        logger.info("sendMsg# start,msgReq:{}", reqDto);
        SendMessageResponseDto respDto = new SendMessageResponseDto();
        try{
            logger.info("sendMsg# request, SendMessageRequestDto:{}", reqDto);
            respDto = gwMessageFacade.sendMessage(reqDto);
            logger.info("sendMsg# gwMessageFacade#sendMessage# respDto:{}", respDto);
//            if(respDto != null && SUCCESS_RESP_CODE.equals(respDto.getResponseCode())){
//				/* 响应成功 */
//                msgResp.setErrorCode(respDto.getResultCode());
//                msgResp.setErrorMsg(respDto.getResultInfo());
//                msgResp.setSendStatus(tranferGwSendStatus(respDto.getSendStatus()));
//            } else {
//				/* 响应失败，记录响应错误 */
//                msgResp.setSendStatus(MsgSendStatus.PROCESSING);
//                msgResp.setErrorCode(respDto == null ? "respnull" : respDto.getResponseCode());
//                msgResp.setErrorMsg(respDto == null ? "响应结果为null" : respDto.getResponseInfo());
//            }
        } catch (Exception e){
            logger.error("sendMsg# Exception,e:", e);
			/* 网络异常 */
//            msgResp.setSendStatus(MsgSendStatus.PROCESSING);
//            msgResp.setErrorCode(OperatorExceptionEnum.NETWORK_ERROR.getErrorCode());
//            msgResp.setErrorMsg("调用短信网关网络异常");
        }
        logger.info("sendMsg# end,mobile:{},msgResp:{}");
        return respDto;
    }

}
