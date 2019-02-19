//package com.qunar.fintech.plat.admin.newmarketing;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.qunar.fintech.plat.admin.base.BaseAdminDBTest;
//import com.qunar.fintech.plat.admin.newmarketing.controller.MarketingReviewController;
//import com.qunar.fintech.plat.admin.newmarketing.dto.CouponDetailDto;
//import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewUpdateReq;
//import com.qunar.fintech.plat.admin.newmarketing.enums.ReviewNodeStatusEnum;
//import com.qunar.fintech.plat.admin.query.utils.JSONUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.annotation.Resource;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.nio.charset.StandardCharsets;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
///**
// * @author qun.shi
// * @since 2019-02-13 5:32 PM
// */
//@WebAppConfiguration()
//public class ReviewControllerTest extends BaseAdminDBTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(CouponControllerTest.class);
//
//    protected MockMvc mockMvc;
//
//    private MockHttpSession session;
//
//    @Autowired
//    protected WebApplicationContext wac;
//
//    /**
//     * 用户信息文件：创建券的时候，审核用
//     */
//    private static MockMultipartFile userInfoFile=null;
//
//    private static MockMultipartFile req = null;
//
//    /**
//     * 审核管理
//     */
//    @Resource
//    private MarketingReviewController marketingReviewController;
//
//    private static ReviewUpdateReq reviewUpdateReq = null;
//
//    /**
//     * 准备数据
//     */
//    static {
//        // mock 创建券时上传的用户信息文件
//        String filePath = "/Users/shiqun/test.txt";
//        File file = new File(filePath);
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//            userInfoFile = new MockMultipartFile("userInfoFile","test.txt",
//                    MediaType.TEXT_PLAIN_VALUE,fileInputStream);
//        } catch (FileNotFoundException e) {
//            logger.error("文件不存在，filePath = {}",filePath);
//        } catch (IOException e) {
//            logger.error("读取文件异常，filePath = {}",filePath);
//        }
//
//        // 活动信息
//        CouponDetailDto couponDetailDto = new CouponDetailDto();
//        couponDetailDto.setActivityCode("1666666666");
//        couponDetailDto.setActivityName("拿去花双旦-- 修");
//        couponDetailDto.setActivityDesc("拿去花双旦活动");
//        couponDetailDto.setUserDesc("新用户");
//        couponDetailDto.setUserFeature("Q、C平台未授信新用户");
//
//        // 优惠券预算信息
//        couponDetailDto.setActivityTotalAmt(new BigDecimal(10000));
//        couponDetailDto.setCouponTotalAmt(new BigDecimal(40000));
//        couponDetailDto.setCouponTotalNum(4000);
//        couponDetailDto.setCouponExpectedUsedNum(400);
//        couponDetailDto.setActivityAccountName("CIOUS2018Sale99");
//        couponDetailDto.setCouponStartTime("2018-11-11 00:00:00");
//        couponDetailDto.setCouponEndTime("2019-11-11 00:00:00");
//
//        // 优惠券配置信息 - 基本信息
//        couponDetailDto.setCouponCode("1777777777777");
//        couponDetailDto.setCouponName("测试优惠券");
//        couponDetailDto.setCouponDesc("测试优惠券");
//        couponDetailDto.setInstructions("['退款不可使用']");
//        couponDetailDto.setRuleTips("酒店可用");
//        couponDetailDto.setCouponUseUrl("http://wwww.google.com");
//
//        // 优惠券配置信息 - 券规则配置 10元免息券
//        couponDetailDto.setSupportProductNoList("IOUS");
//        couponDetailDto.setRepayIndexList("ALL"); // -1：尾期，1：首期，All：全期
//        couponDetailDto.setCouponDimon("PLAT"); // 平台维度：PLAT，自然人维度：CUSTOMER
//        couponDetailDto.setSupportOrgChannelList("IOUS"); // 携程：CTRIP，去哪儿：QUNAR，金融APP：FINANICE
//        couponDetailDto.setCouponType(0); // 券类型 0:还款N元免息券 1:还款N折免息券 2:还款N天免息券 3:贷款随机立减券 4:贷款固额立减券 5:还款固额立减券
//        couponDetailDto.setCouponAmount(new BigDecimal(10)); // 券面额
//        couponDetailDto.setDiscountRate(null); // 券折扣
//        couponDetailDto.setFreeIntDays(0); // 免息天数，按天
//        couponDetailDto.setMaxFreeAmt(new BigDecimal(10));
//
//        couponDetailDto.setMaxReduceAmount(null); // 立减最大优惠金额
//        couponDetailDto.setMinReduceAmount(null); // 立减最小优惠金额
//        couponDetailDto.setReduceRate(null); // 立减精度
//        couponDetailDto.setAvgReduceAmount(null); // 立减平均优惠金额
//
//        couponDetailDto.setDelayDays(0); // 延迟生效天数
//        couponDetailDto.setAvailableDays(30); // 有效天数
//        couponDetailDto.setUserGrantNumLimit(null); // 单用户最多发放张数 null 不限制，不为null，限制熟练
//        couponDetailDto.setDayGrantNumLimit(null); // 某券当天最多发放张数，null 不限制，不为null，限制数量
//        couponDetailDto.setUserUseNumLimit(null); // 单用户最多使用张数，null不限制，不为null，限制数量
//
//        // 优惠券配置信息 - 通知内容配置
//        couponDetailDto.setNoticeUserByMsg(1); // 0：发短信，1：不发短信
//        couponDetailDto.setMsgUrl(null); // 短信里面的地址
//        couponDetailDto.setMsgContent(null); // 短信里面的内容
//        couponDetailDto.setNoticeQunarPublic(1); // 0：通知公共，1：不通知
//
//        // 账务报警配置
//        couponDetailDto.setAccountAlarmReceiver("qun.shi"); // talkid, 用逗号隔开
//
//        /**
//         * 报警通知方式
//         * 1：qtalk通知 2：短信 4：QMQ通知 8：邮件通知 3：qtalk和短信 5：qtalk和qmq 6：短息和qmq
//         * 7：qtalk、短信和qmq 9：qtalk和email 10：短信和email 11：qtalk、短信和邮件 12：qmq和email
//         * 13：qtalk、qmq和邮件 14：短信、qmq和邮件 15：全部
//         */
//        couponDetailDto.setAccountAlarmNoticeMethod(1);
//        couponDetailDto.setAccountAlarmExtEmailReceiver("1229587131@qq.com"); // 外部邮箱
//        couponDetailDto.setAccountAlarmExtMobileReceiver("18896994232"); //外部手机号
//        couponDetailDto.setAccountAlarmNotifyInterval(1); // 分钟
//        couponDetailDto.setAccountAlarmStartTime("00:00"); // 时分
//        couponDetailDto.setAccountAlarmEndTime("23:59"); // 时分
//        couponDetailDto.setAccountAlarmModel("MIN"); // MIN：小于某值就报警，MAX：大于某值就报警
//        couponDetailDto.setAccountAlarmAmount(new BigDecimal(100)); // 配合model使用，大于小于配置的金额报警
//        couponDetailDto.setAccountAlarmRemark("使用说明");
//        couponDetailDto.setAccountAlarmUseStatus("USED"); // USED：开启报警，UNUSED： 关闭报警
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        reviewUpdateReq = new ReviewUpdateReq();
//        reviewUpdateReq.setNewReviewNo("RM1888888888");
//        reviewUpdateReq.setOldReviewNo("RM1534535345345932");
//        reviewUpdateReq.setReviewContentKey("1777777777777");
//        reviewUpdateReq.setReviewContentValue(JSONUtils.getGson().toJson(couponDetailDto));
//
//        try {
//            req = new MockMultipartFile(
//                    "req",
//                    "req",
//                    MediaType.APPLICATION_JSON_VALUE,
//                    objectMapper.writeValueAsString(reviewUpdateReq).getBytes(StandardCharsets.UTF_8));
//        } catch (JsonProcessingException e) {
//            logger.error("生成json数据异常，couponDetailDto = {}",filePath);
//        }
//    }
//
//    @Before()
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(marketingReviewController).build();
//        this.session = new MockHttpSession();
//        // 设置登录用户
//        session.putValue("userId","qun.shi");
//    }
//
//    @Test
//    public void submit(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/submit")
//                    .param("reviewNos", "RM1534535345345932")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("提交失败，e={}",e.getMessage());
//        }
//        logger.info("提交券返回结果，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void comment(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/comment")
//                    .param("reviewNo", "RM1534535345345932")
//                    .param("status", ReviewNodeStatusEnum.PASS.getCode())
//                    .param("comment", "通过")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("提交失败，e={}",e.getMessage());
//        }
//        logger.info("提交券返回结果，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void update(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(fileUpload("/newmarketing/review/update")
//                    .file(userInfoFile)
//                    .file(req))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("更新审核信息失败，e={}",e.getMessage());
//        }
//        logger.info("更新审核信息成功，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void publish(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/publish")
//                    .param("reviewNos", "RM1534535345345932")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("发布失败，e={}",e.getMessage());
//        }
//        logger.info("发布返回结果，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void getReviewList(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/list/0")
//                    .param("couponCode", "1777777777777")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("查询审核记录失败失败，e={}",e.getMessage());
//        }
//        logger.info("查询审核记录结果，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void getReviewDetail(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/getReviewDetail")
//                    .param("reviewNo", "RM1534535345345932")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("查询审核详情失败，e={}",e.getMessage());
//        }
//        logger.info("查询审核详情结果，responseJson = {}",responseJson);
//    }
//
//    @Test
//    public void getReviewNodeList(){
//        String responseJson = "";
//        try {
//            responseJson = mockMvc.perform(post("/newmarketing/review/getReviewNodeList")
//                    .param("reviewNo", "RM1534535345345932")
//                    .session(session))
//                    .andDo(print())
//                    .andReturn().getResponse().getContentAsString();
//        } catch (Exception e) {
//            logger.error("查询审核详情失败，e={}",e.getMessage());
//        }
//        logger.info("查询审核详情结果，responseJson = {}",responseJson);
//    }
//}
