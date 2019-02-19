package com.qunar.fintech.plat.admin.marketing.service.impl;

import com.google.common.base.Splitter;
import com.google.common.io.CharStreams;
import com.qunar.fintech.marketing.api.dto.GrantCouponReqDto;
import com.qunar.fintech.marketing.api.dto.GrantCouponRespDto;
import com.qunar.fintech.marketing.api.facade.MarketingFacade;
import com.qunar.fintech.marketing.api.model.CouponGrantReqInfo;
import com.qunar.fintech.marketing.api.model.UserInfo;
import com.qunar.fintech.plat.admin.marketing.dto.InterestFreeCouponDto;
import com.qunar.fintech.plat.admin.marketing.service.CouponService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.resp.QResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * weilai
 * 2018年9月3日12点18分
 */
@Service
public class CouponServiceimpl implements CouponService {
    private static final Logger logger = LoggerFactory.getLogger(CouponServiceimpl.class);
    @Override
    public int addInterestFreeCoupon(InterestFreeCouponDto interestFreeCouponDto) {
        CommonsMultipartFile source = interestFreeCouponDto.getFile();
        logger.info("addInterestFreeCoupon - begin: {}", interestFreeCouponDto);
        int count = 0;
        try {
            if(interestFreeCouponDto.getFile()!=null) {
                //读取文件存进集合中
                List<String> stringList = CharStreams.readLines(new InputStreamReader(source.getInputStream()));
                logger.debug("addInterestFreeCoupon strlist: {}", stringList);
                //计数器，如果执行过程失败记录下已经执行了多少条数据
                for (int i=1;i<stringList.size();i++){
                    //便利集合的每一个元素，把它用“，”分割成两部分
                    List<String> uidMobile = Splitter.on(",").splitToList(stringList.get(i));
                    String uid = uidMobile.get(0);
                    String mobile = uidMobile.get(1);
                    //封装实体
                    logger.debug("addInterestFreeCoupon - grant param: {}, mobile: {}", uid, mobile);
                    GrantCouponReqDto gcr = buildGrantCouponReqDto(interestFreeCouponDto,uid,mobile);
                    // 调用发券服务
                    QResponse<GrantCouponRespDto> qResponse = marketingFacade.grant(gcr);

                    //异常处理
                    if (!qResponse.isSuccess()) {
                        logger.error("发券失败:{},uid:{},mobile:{}",qResponse.getReturnMsg(),uid,mobile);
                        continue;
                    }
                    count ++;
                }
                logger.info("addInterestFreeCoupon - end: {}", count);
            }
        } catch (IOException e) {
            logger.error("updateRepaymentFile#update repayment schedule with file exception", e);
        } catch (IllegalStateException e) {
            logger.error("addInterestFreeCoupon - err", e);
            throw e;
        } catch (Exception e) {
            logger.error("addInterestFreeCoupon - err", e);
        }
        return count;
    }

    /**
     * 组装GrantCouponReqDto
     * @param interestFreeCouponDto
     * @param uid
     * @param mobile
     * @return
     */
    private static GrantCouponReqDto buildGrantCouponReqDto(InterestFreeCouponDto interestFreeCouponDto,String uid,String mobile){
        GrantCouponReqDto reqDto = new GrantCouponReqDto();
        reqDto.setOrgChannel(interestFreeCouponDto.getOrgChannel());
        reqDto.setProductNo(interestFreeCouponDto.getProductNo());
        List<CouponGrantReqInfo> list = new ArrayList<>();
        CouponGrantReqInfo cgr = new CouponGrantReqInfo();
        cgr.setActivityCode(interestFreeCouponDto.getActivityCode());
        cgr.setCouponCode(interestFreeCouponDto.getCouponCode());
        cgr.setCouponAmount(interestFreeCouponDto.getCouponAmount());
        list.add(cgr);
        reqDto.setCouponGrantReqInfos(list);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setMobile(mobile);
        reqDto.setUserInfo(userInfo);
        return reqDto;
    }

    @Resource
    private MarketingFacade marketingFacade;
}
