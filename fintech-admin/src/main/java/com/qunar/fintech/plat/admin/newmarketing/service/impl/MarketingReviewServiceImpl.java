package com.qunar.fintech.plat.admin.newmarketing.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewUpdateReq;
import com.qunar.fintech.plat.admin.newmarketing.enums.MenuTypeEnum;
import com.qunar.fintech.plat.admin.newmarketing.service.FileUploadService;
import com.qunar.fintech.plat.admin.newmarketing.service.FlowConfigService;
import com.qunar.fintech.plat.admin.query.utils.nemo.ParamChecker;
import com.qunar.fintech.plat.admin.system.dao.entity.MenuDO;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewFlowConfig;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewInfo;
import com.qunar.fintech.plat.admin.system.dao.entity.ReviewNode;
import com.qunar.fintech.plat.admin.system.dao.entity.RoleDO;
import com.qunar.fintech.plat.admin.system.dao.mapper.ReviewFlowConfigMapper;
import com.qunar.fintech.plat.admin.system.dao.mapper.ReviewInfoMapper;
import com.qunar.fintech.plat.admin.system.dao.mapper.ReviewNodeMapper;
import com.qunar.fintech.plat.admin.newmarketing.dto.CouponQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewNodeDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewQueryReq;
import com.qunar.fintech.plat.admin.newmarketing.dto.ReviewInfoDetailDto;
import com.qunar.fintech.plat.admin.newmarketing.enums.ReviewFilterTypeEnum;
import com.qunar.fintech.plat.admin.newmarketing.enums.ReviewInfoStatusEnum;
import com.qunar.fintech.plat.admin.newmarketing.enums.ReviewNodeStatusEnum;
import com.qunar.fintech.plat.admin.newmarketing.service.MarketingReviewService;
import com.qunar.fintech.plat.admin.system.dao.entity.UserDO;
import com.qunar.fintech.plat.admin.system.service.MenuService;
import com.qunar.fintech.plat.admin.system.service.RoleService;
import com.qunar.fintech.plat.admin.system.service.UserService;
import com.qunar.pay.finance.qf.commons.api.exception.BusiException;
import com.qunar.pay.finance.qf.commons.api.exception.CommonApiErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.qunar.fintech.plat.admin.newmarketing.constants.ReviewConstants.FIRST_REVIEW_ORDER_VALUE;
import static com.qunar.fintech.plat.admin.newmarketing.exception.MarketingExceptionCode.*;

/**
 * @author qun.shi
 * @since 2019-01-31 7:49 PM
 */
@Service
public class MarketingReviewServiceImpl implements MarketingReviewService {

    /**
     * 查询审核节点详情
     */
    @Override
    public List<ReviewNodeDetailDto> queryNodesByReviewNo(String reviewNo){
        List<ReviewNode> reviewNodes
                = reviewNodeMapper.selectByReviewNo(Lists.<String>newArrayList(reviewNo));

        // 按照审核顺序排序
        Collections.sort(reviewNodes, new Comparator<ReviewNode>() {
            @Override
            public int compare(ReviewNode o1, ReviewNode o2) {
                return o1.getReviewOrder().compareTo(o2.getReviewOrder());
            }
        });

        List<ReviewNodeDetailDto> reviewNodeDetailDtos = Lists.newArrayList();
        for (ReviewNode node : reviewNodes) {
            ReviewNodeDetailDto reviewNodeDetailDto = node.buildReviewNodeResp();
            reviewNodeDetailDtos.add(reviewNodeDetailDto);
        }
        return reviewNodeDetailDtos;
    }



    /**
     * 更新审核信息
     */
    @Override
    public void updateReviewInfo(final MultipartFile userInfoFile, final ReviewUpdateReq req) {
        // 查询审核信息
        List<ReviewInfo> oldReviewInfos = reviewInfoMapper.selectByReviewNo(Lists.<String>newArrayList(req.getOldReviewNo()));
        ReviewInfo oldReviewInfo = oldReviewInfos.get(0);

        // 只有初始状态和拒绝状态的审核信息才能更新
        if (!(ReviewInfoStatusEnum.REJECT.getCode().equals(oldReviewInfo.getStatus())
                || ReviewInfoStatusEnum.INIT.getCode().equals(oldReviewInfo.getStatus()))) {
            logger.error("审核内容更新失败，reviewUpdateReq={}", req);
            throw new BusiException(REVIEW_INFO_CONTENT_UPDATE_FAIL);
        }

        // 如果是初始状态的审核信息，直接更新
        if (ReviewInfoStatusEnum.INIT.getCode().equals(oldReviewInfo.getStatus())) {
            updateOldReviewInfo(userInfoFile,req);
        } else if (ReviewInfoStatusEnum.REJECT.getCode().equals(oldReviewInfo.getStatus())) {
            ParamChecker.notNull(userInfoFile,"上传文件不能为空！");
            // 重新生成一份待提交的审核数据, 以及初始的审核流节点信息
            final ReviewInfo newReviewInfo = ReviewInfo.buildReviewInfo(req, oldReviewInfo);
            add(userInfoFile, newReviewInfo);
        }
    }

    /**
     * 更新db里面的审核信息
     */
    private void updateOldReviewInfo(final MultipartFile userInfoFile,final ReviewUpdateReq req){
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 保存文件，以reviewNo作为文件名
                fileUploadService.saveUserInfoFile(userInfoFile, req.getOldReviewNo());

                if (reviewInfoMapper.updateContentByStatus(req.getOldReviewNo(),
                        req.getReviewContentValue(), ReviewInfoStatusEnum.INIT.getCode()) < 1) {
                    logger.error("审核内容更新失败，reviewUpdateReq={}", req);
                    throw new BusiException(REVIEW_INFO_CONTENT_UPDATE_FAIL);
                }
            }
        });
    }

    /**
     * 发布
     */
    @Override
    public void pubish(ReviewQueryReq req) {
        ParamChecker.notNull(req.getReviewNos(), "审核流水不能为空！");
        ParamChecker.notNull(req.getReviewNos().size() > 0, "审核流水不能为空！");

        // 查询审核记录
        for (String reviewNo : req.getReviewNos()) {
            List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByReviewNo(Lists.<String>newArrayList(reviewNo));
            ReviewInfo reviewInfo = reviewInfos.get(0);
            String reviewMenuId = reviewInfo.getReviewMenuId();

            MenuTypeEnum menuTypeEnum = MenuTypeEnum.toEnum(Integer.valueOf(reviewMenuId));
            ParamChecker.notNull(menuTypeEnum, "不支持的菜单类型！");

            eventExecuteFactory.execute(menuTypeEnum, reviewInfo.getContentValue());
        }
    }

    /**
     * 节点批复：通过或者拒绝
     */
    @Override
    public void comment(ReviewNodeQueryReq req) {
        ParamChecker.notBlank(req.getReviewNo(), "审核流水不能为空！");
        ParamChecker.notBlank(req.getReviewer(), "审核人不能为空！");
        ParamChecker.notBlank(req.getStatus(), "操作类型不能为空！");
        ParamChecker.notBlank(req.getComment(), "操作理由不能为空！");

        // 查询审核状态
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByReviewNo(Lists.<String>newArrayList(req.getReviewNo()));
        ReviewInfo reviewInfo = reviewInfos.get(0);
        if (!ReviewInfoStatusEnum.REVIEWING.getCode().equals(reviewInfo.getStatus())) {
            logger.error("审核未提交或已完成，不能再次批复，reviewInfo={}", reviewInfo);
            throw new BusiException(NOT_NEED_TO_REVIEW);
        }

        // 查询用户的角色
        UserDO userDOWithRoles = queryUserRoles(req.getReviewer());

        // 查询所有流节点信息
        List<ReviewNode> dbReviewNodes
                = reviewNodeMapper.selectByReviewNo(Lists.<String>newArrayList(req.getReviewNo()));

        // 找到用户能够审核的节点
        ReviewNode dbReviewNode = findNodeUserCanReview(userDOWithRoles, dbReviewNodes);

        // 判断该节点是否是最后一个
        boolean isLastReviewNode = false;
        if (dbReviewNode.getReviewOrder().equals(dbReviewNodes.size() - 1)) {
            isLastReviewNode = true;
        }

        // 更新审核信息和审核节点信息
        execute(req, dbReviewNode, isLastReviewNode);
    }

    /**
     * 事务执行审核操作
     */
    private void execute(final ReviewNodeQueryReq req, final ReviewNode dbReviewNode, final boolean isLastReviewNode) {
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 修改当前节点状态：通过或者拒绝
                if (reviewNodeMapper.updateInfoByStatusAndId(req.getReviewer(), req.getStatus(), req.getComment(),
                        dbReviewNode.getId(), ReviewNodeStatusEnum.REVIEWING.getCode()) < 1) {
                    logger.error("审核节点更新失败，reviewNodeQueryReq={}", req);
                    throw new BusiException(REVIEW_NODE_STATUS_AND_COMMENT_AND_REVIEWER_UPDATE_FAIL);
                }

                // 如果当前节点拒绝
                if (ReviewNodeStatusEnum.REJECT.getCode().equals(req.getStatus())) {
                    // 整个审核流程不通过
                    if (reviewInfoMapper.updateStatus(req.getReviewNo(),
                            ReviewInfoStatusEnum.REJECT.getCode(), ReviewInfoStatusEnum.REVIEWING.getCode()) < 1) {
                        logger.error("审核状态更新拒绝失败，reviewNodeQueryReq={}", req);
                        throw new BusiException(REVIEW_INFO_STATUS_UPDATE_FAIL);
                    }
                } else {
                    // 如果当前节点通过，判断是否是最后一个节点
                    if (isLastReviewNode) {
                        // 是最后节点，审核流通过
                        if (reviewInfoMapper.updateStatus(req.getReviewNo(),
                                ReviewInfoStatusEnum.PASS.getCode(), ReviewInfoStatusEnum.REVIEWING.getCode()) < 1) {
                            logger.error("审核状态更新通过失败，reviewNodeQueryReq={}", req);
                            throw new BusiException(REVIEW_INFO_STATUS_UPDATE_FAIL);
                        }
                    } else {
                        // 不是最后的节点，更新审核流当前审核角色
                        if (reviewInfoMapper.updateCurRoleId(req.getReviewNo(), dbReviewNode.getNextRoleId()) < 1) {
                            logger.error("当前的审核角色更新失败，reviewNodeQueryReq={}", req);
                            throw new BusiException(REVIEW_INFO_CUR_ROLE_ID_UPDATE_FAIL);
                        }

                        // 更新下一个审核流节点信息
                        if (reviewNodeMapper.updateStatus(req.getReviewNo(), ReviewNodeStatusEnum.REVIEWING.getCode(),
                                ReviewNodeStatusEnum.INIT.getCode(), dbReviewNode.getReviewOrder() + 1) < 1) {
                            logger.error("下一个审核流节点状态更新失败，reviewNodeQueryReq={}", req);
                            throw new BusiException(REVIEW_INFO_CUR_ROLE_ID_UPDATE_FAIL);
                        }
                    }
                }
            }
        });
    }

    /**
     * 找到用户能够审核的节点
     */
    private ReviewNode findNodeUserCanReview(UserDO userDO, List<ReviewNode> reviewNodes) {
        // 按照审核顺序排序
        Collections.sort(reviewNodes, new Comparator<ReviewNode>() {
            @Override
            public int compare(ReviewNode o1, ReviewNode o2) {
                return o1.getReviewOrder().compareTo(o2.getReviewOrder());
            }
        });

        boolean noRight = false;
        for (ReviewNode node : reviewNodes) {
            // 找到当前审核中的节点
            if (!ReviewNodeStatusEnum.REVIEWING.getCode().equals(node.getStatus())) {
                continue;
            }

            // 判断用户在此节点时候具有权限
            if (userDO.getroleIds().contains(Long.valueOf(node.getCurRoleId()))) {
                return node;
            }else{
                noRight = true;
            }
        }

        if (noRight) {
            // 没有权限审核，中断流程
            throw new BusiException(NO_RIGHT_TO_REVIEW);
        } else {
            // 没有必要审核
            throw new BusiException(NOT_NEED_TO_REVIEW);
        }
    }

    /**
     * 提交待审核，不可编辑
     */
    @Override
    public void submit(final ReviewQueryReq queryReq) {
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                for (String reviewNo : queryReq.getReviewNos()) {
                    // 查询第一个节点
                    ReviewNode reviewNode = reviewNodeMapper.selectByReviewNoAndOrder(reviewNo, FIRST_REVIEW_ORDER_VALUE);

                    // 修改审核状态：初始改为审核中
                    if (reviewInfoMapper.updateStatusAndCurRoleIdAndSubmitter(reviewNo,
                            reviewNode.getCurRoleId(), ReviewInfoStatusEnum.REVIEWING.getCode(), queryReq.getCurLoginUserId(), ReviewInfoStatusEnum.INIT.getCode()) < 1) {
                        logger.error("更新初始的审核状态为待审核失败，reviewNo={}", reviewNo);
                        throw new BusiException(REVIEW_INFO_STATUS_UPDATE_FAIL);
                    }

                    // 第一个流节点：初始改为待审核
                    if (reviewNodeMapper.updateStatus(reviewNo, ReviewNodeStatusEnum.REVIEWING.getCode(),
                            ReviewNodeStatusEnum.INIT.getCode(), FIRST_REVIEW_ORDER_VALUE) < 1) {
                        logger.error("将第一个初始状态的审核节点更改为待审核失败，reviewNo={}", reviewNo);
                        throw new BusiException(REVIEW_NODE_STATUS_UPDATE_FAIL);
                    }
                }
            }
        });
    }

    public void comment() {

    }

    /**
     * 增加一条审核记录
     */
    @Override
    public void add(final MultipartFile userInfoFile, final ReviewInfo reviewInfo) {

        // 根据reviewMenuId查询审核节点配置
        List<ReviewFlowConfig> reviewFlowConfigs = reviewFlowConfigMapper
                .selectByReviewMenuId(reviewInfo.getReviewMenuId());

        ReviewFlowConfig reviewFlowConfig = marketingFlowConfigService.getCanUseFlowConfig(reviewFlowConfigs, reviewInfo);
        reviewInfo.setQueryMenuId(reviewFlowConfig.getQueryMenuId());
        reviewInfo.setFlowNo(reviewFlowConfig.getFlowNo());

        // 查询菜单名称
        MenuDO menuDO = menuService.get(Long.valueOf(reviewInfo.getReviewMenuId()));
        ParamChecker.notNull(menuDO, "菜单信息不能为空！");
        reviewInfo.setReviewMenuName(menuDO.getName());

        final List<ReviewNode> reviewNodes = buildReviewNodeList(reviewFlowConfig, reviewInfo);

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // todo 保存文件，以reviewNo作为文件名
                //fileUploadService.saveUserInfoFile(userInfoFile, reviewInfo.getReviewNo());

                if (reviewInfoMapper.insert(reviewInfo) < 1) {
                    logger.error("新增审核记录失败，reviewInfo={}", reviewInfo);
                    throw new BusiException(REVIEW_INFO_ADD_FAIL);
                }

                for (ReviewNode reviewNode : reviewNodes) {
                    if (reviewNodeMapper.insert(reviewNode) < 1) {
                        logger.error("新增审核节点失败，reviewNode={}", reviewNode);
                        throw new BusiException(REVIEW_NODE_ADD_FAIL);
                    }
                }
            }
        });
    }

    public List<ReviewNode> buildReviewNodeList(ReviewFlowConfig reviewFlowConfig, ReviewInfo reviewInfo) {
        ParamChecker.notBlank(reviewFlowConfig.getFlowNodeRole(), "审核流节点不能为空！");

        String[] roles = reviewFlowConfig.getFlowNodeRole().split(",");
        ParamChecker.isTrue(roles.length > 0, "审核流节点不能为空！");

        List<ReviewNode> reviewNodes = Lists.newArrayList();
        for (int i = 0; i < roles.length; i++) {
            ReviewNode reviewNode = new ReviewNode();
            reviewNode.setReviewNo(reviewInfo.getReviewNo());
            reviewNode.setReviewMenuId(reviewInfo.getReviewMenuId());
            reviewNode.setCurRoleId(roles[i]);

            // 下一个审核节点
            if (i < roles.length - 1) {
                reviewNode.setNextRoleId(roles[i + 1]);
            }

            reviewNode.setReviewOrder(i);
            RoleDO roleDO = roleService.get(Long.valueOf(roles[i]));
            reviewNode.setRoleName(roleDO.getRoleName());
            reviewNode.setStatus(ReviewNodeStatusEnum.INIT.getCode());
            reviewNode.setFlowNo(reviewFlowConfig.getFlowNo());
            reviewNodes.add(reviewNode);
        }
        return reviewNodes;
    }

    /**
     * 按条件查询审核记录
     */
    @Override
    public List<ReviewInfoDetailDto> getReviewInfoListByFilterType(ReviewQueryReq req) {

        // 查询待我审核的记录
        if (ReviewFilterTypeEnum.QUERY_PROCESSING_BY_ME.getFilterId().equals(req.getFilterType())) {
            return queryProcessingByMe(req);
        }

        // 查询经我审核的记录
        if (ReviewFilterTypeEnum.QUERY_PROCESSED_BY_ME.getFilterId().equals(req.getFilterType())) {
            return queryProcessedByMe(req);
        }

        // 查询由我提交的记录
        if (ReviewFilterTypeEnum.QUERY_COMMITED_BY_ME.getFilterId().equals(req.getFilterType())) {
            return queryCommitedByMe(req);
        }

        // 默认查询所有
        return getAllReviewList(req);
    }

    /**
     * 查询所有的审核记录
     */
    private List<ReviewInfoDetailDto> getAllReviewList(ReviewQueryReq req) {
        // 查询所有的审核记录
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectAll();

        // all reviwNo
        List<String> reviewNos = Lists.newArrayList();
        for (ReviewInfo reviewInfo : reviewInfos) {
            reviewNos.add(reviewInfo.getReviewNo());
        }

        // 查询批复详情
        List<ReviewNode> reviewNodes = reviewNodeMapper.selectByReviewNo(reviewNos);

        // 审核信息
        List<ReviewInfoDetailDto> reviewInfoDetailDtoList = buildReviewQueryReq(reviewInfos, reviewNodes);

        // 按条件过滤信息
        List<ReviewInfoDetailDto> filteredReviewInfoDetailDtos = filterCoupon(reviewInfoDetailDtoList, req.getCouponQueryReq());

        return filteredReviewInfoDetailDtos;
    }

    /**
     * 查询经我处理的审核记录
     */
    private List<ReviewInfoDetailDto> queryProcessedByMe(ReviewQueryReq req) {
        // 查询我审核的历史节点信息
        List<ReviewNode> reviewNodes = reviewNodeMapper.selectByReviewerAndStatus(req.getCurLoginUserId(),
                Lists.newArrayList(ReviewNodeStatusEnum.PASS.getCode(),ReviewNodeStatusEnum.REJECT.getCode()));

        // all reviwNo
        List<String> reviewNos = Lists.newArrayList();
        for (ReviewNode reviewNode : reviewNodes) {
            reviewNos.add(reviewNode.getReviewNo());
        }

        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectByReviewNo(reviewNos);

        // 审核信息
        List<ReviewInfoDetailDto> reviewInfoDetailDtoList = buildReviewQueryReq(reviewInfos, reviewNodes);

        // 按条件过滤信息
        List<ReviewInfoDetailDto> filteredReviewInfoDetailDtos = filterCoupon(reviewInfoDetailDtoList, req.getCouponQueryReq());
        return filteredReviewInfoDetailDtos;
    }

    /**
     * 查询经我提交的审核记录
     */
    private List<ReviewInfoDetailDto> queryCommitedByMe(ReviewQueryReq req) {
        // 查询我提交的记录
        List<ReviewInfo> reviewInfos = reviewInfoMapper.selectBySubmitter(req.getCurLoginUserId());

        // all reviwNo
        List<String> reviewNos = Lists.newArrayList();
        for (ReviewInfo reviewInfo : reviewInfos) {
            reviewNos.add(reviewInfo.getReviewNo());
        }

        // 查询批复详情
        List<ReviewNode> reviewNodes = reviewNodeMapper.selectByReviewNo(reviewNos);

        // 审核信息
        List<ReviewInfoDetailDto> reviewInfoDetailDtoList = buildReviewQueryReq(reviewInfos, reviewNodes);

        // 按条件过滤信息
        List<ReviewInfoDetailDto> filteredReviewInfoDetailDtos = filterCoupon(reviewInfoDetailDtoList, req.getCouponQueryReq());

        return filteredReviewInfoDetailDtos;
    }

    /**
     * 查询待我处理的审核记录
     */
    private List<ReviewInfoDetailDto> queryProcessingByMe(ReviewQueryReq req) {
        //待我审核的记录
        List<ReviewInfoDetailDto> filteredReviewList = Lists.newArrayList();

        // 用户包含的权限
        UserDO userDOWithRoles = queryUserRoles(req.getCurLoginUserId());

        // 查询待我审核的记录
        List<ReviewInfo> reviewInfos
                = reviewInfoMapper.selectByCurRoleIdAndStatus(userDOWithRoles.getroleIds(),
                ReviewInfoStatusEnum.REVIEWING.getCode());

        // all reviwNo
        List<String> reviewNos = Lists.newArrayList();
        for (ReviewInfo reviewInfo : reviewInfos) {
            reviewNos.add(reviewInfo.getReviewNo());
        }

        // 查询批复详情
        List<ReviewNode> reviewNodes = reviewNodeMapper.selectByReviewNo(reviewNos);

        // 审核信息
        List<ReviewInfoDetailDto> reviewInfoDetailDtoList = buildReviewQueryReq(reviewInfos, reviewNodes);

        // 按条件过滤信息
        List<ReviewInfoDetailDto> filteredReviewInfoDetailDtos = filterCoupon(reviewInfoDetailDtoList, req.getCouponQueryReq());


        // 待我审核的记录
        for (ReviewInfoDetailDto reviewInfoDetailDto : filteredReviewInfoDetailDtos) {
            Long curRoleId = Long.valueOf(reviewInfoDetailDto.getCurRoleId());
            if (userDOWithRoles.getroleIds().contains(curRoleId)) {
                filteredReviewList.add(reviewInfoDetailDto);
            }
        }

        return filteredReviewList;
    }

    /**
     * 根据用户ID查询用户的权限
     */
    private UserDO queryUserRoles(String userId) {
        // 根据userid中查询用户的信息
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", userId);
        List<UserDO> users = userService.list(map);

        // 获取当前用户
        UserDO userDOWithoutRoles = users.get(0);

        // 查询此用户的权限
        return userService.get(userDOWithoutRoles.getUserId());
    }

    /**
     * 按条件过滤审核信息
     */
    private List<ReviewInfoDetailDto> filterCoupon(List<ReviewInfoDetailDto> reviewInfoDetailDtoList,
                                                   CouponQueryReq couponQueryReq) {
        List<ReviewInfoDetailDto> filteredReviewInfoDetailDtos = Lists.newArrayList();
        for (ReviewInfoDetailDto reviewInfoDetailDto : reviewInfoDetailDtoList) {
            if (couponQueryReq.match(reviewInfoDetailDto)) {
                filteredReviewInfoDetailDtos.add(reviewInfoDetailDto);
            }
        }
        return filteredReviewInfoDetailDtos;
    }

    /**
     * 构造审核记录查询结果信息
     */
    private List<ReviewInfoDetailDto> buildReviewQueryReq(List<ReviewInfo> reviewInfos, List<ReviewNode> reviewNodes) {
        //map key : reviewNo, value : List<ReviewNode>
        Map<String, List<ReviewNode>> reviewNoReviewNodeListMap = Maps.newHashMap();
        for (ReviewNode reviewNode : reviewNodes) {
            if (reviewNoReviewNodeListMap.containsKey(reviewNode.getReviewNo())) {
                reviewNoReviewNodeListMap.get(reviewNode.getReviewNo()).add(reviewNode);
            } else {
                List<ReviewNode> nodes = Lists.newArrayList();
                nodes.add(reviewNode);
                reviewNoReviewNodeListMap.put(reviewNode.getReviewNo(), nodes);
            }
        }

        List<ReviewInfoDetailDto> reviewInfoDetailDtoList = Lists.newArrayList();
        for (ReviewInfo reviewInfo : reviewInfos) {
            ReviewInfoDetailDto reviewInfoDetailDto = reviewInfo.buildReviewQueryResp();

            // 按照审核顺序排序
            List<ReviewNode> nodes = reviewNoReviewNodeListMap.get(reviewInfo.getReviewNo());
            Collections.sort(nodes, new Comparator<ReviewNode>() {
                @Override
                public int compare(ReviewNode o1, ReviewNode o2) {
                    return o1.getReviewOrder().compareTo(o2.getReviewOrder());
                }
            });

            // 审核状态
            String curStatus = reviewInfo.getStatus();
            if (ReviewInfoStatusEnum.REVIEWING.getCode().equals(curStatus)) {
                for (ReviewNode node : nodes) {
                    if (ReviewNodeStatusEnum.REVIEWING.getCode().equals(node.getStatus())) {
                        reviewInfoDetailDto.setCurRoleId(node.getCurRoleId());
                        reviewInfoDetailDto.setCurNodeName(node.getRoleName());
                        break;
                    }
                }
            }

            reviewInfoDetailDtoList.add(reviewInfoDetailDto);
        }
        return reviewInfoDetailDtoList;
    }

    /**
     * 查询最新已通过的一条审核记录
     */
    @Override
    public ReviewInfo queryLastPassedReviewInfoByKey(String contentKey) {
        // 查询当前key，对应的所有审核历史记录
        List<ReviewInfo> dbReviewInfos = reviewInfoMapper.queryReviewInfoByKey(contentKey);

        // check
        if (dbReviewInfos == null || dbReviewInfos.size() == 0) {
            logger.error("审核记录为空！，contentKey={}",contentKey);
            return null;
        }

        // 过滤已通过的审核记录
        List<ReviewInfo> passedReviewInfos = Lists.newArrayList();
        for (ReviewInfo reviewInfo : dbReviewInfos) {
            if (ReviewInfoStatusEnum.PASS.getCode().equals(reviewInfo.getStatus())) {
                passedReviewInfos.add(reviewInfo);
            }
        }

        // 按照最新的审核时间排序
        Collections.sort(dbReviewInfos, new Comparator<ReviewInfo>() {
            @Override
            public int compare(ReviewInfo o1, ReviewInfo o2) {
                return o2.getLastReviewTime().compareTo(o1.getLastReviewTime());
            }
        });

        // 取最新已通过的一条记录
        return dbReviewInfos.get(0);
    }

    private static final Logger logger = LoggerFactory.getLogger(MarketingReviewServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionTemplate txTemplate;

    @Resource
    private ReviewInfoMapper reviewInfoMapper;

    @Resource
    private ReviewFlowConfigMapper reviewFlowConfigMapper;

    @Resource
    private ReviewNodeMapper reviewNodeMapper;

    @Resource
    private FlowConfigService marketingFlowConfigService;

    @Resource
    private EventExecuteFactory eventExecuteFactory;

    @Resource
    private RoleService roleService;

    @Resource
    FileUploadService fileUploadService;

    @Resource
    MenuService menuService;
}
