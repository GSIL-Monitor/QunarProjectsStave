package com.qunar.fintech.plat.admin.newmarketing.service;

import com.qunar.fintech.plat.admin.newmarketing.dto.GrantCouponReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qun.shi
 * @since 2019-02-12 7:33 PM
 */
public interface GrantCouponService {

    /**
     * 后台发券
     */
    void grant(final GrantCouponReq req, MultipartFile[] userInfoFiles);
}
