package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.dao.nemo.PlatUserMapper;
import com.qunar.fintech.plat.admin.query.dao.nemo.TppUserMapper;
import com.qunar.fintech.plat.admin.query.exception.FppException;
import com.qunar.fintech.plat.admin.query.service.NemoQueryService;
import com.qunar.fintech.plat.admin.query.utils.nemo.UserDbContext;
import com.qunar.fintech.plat.admin.query.utils.nemo.UserDbContextUtils;
import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import com.qunar.fintech.plat.admin.query.vo.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NemoQueryServiceImpl implements NemoQueryService{

    /**
     * 查询
     * @param nemoQueryVo
     * @return
     * @throws FppException
     */
    @Override
    public QueryResponse<NemoQueryVo> selectByPlatId(NemoQueryVo nemoQueryVo) throws FppException {
        QueryResponse<NemoQueryVo> result = new QueryResponse<>();
        if (nemoQueryVo.getExportType() == 3 || nemoQueryVo.getExportType() == 4) {
            result.setRows(tppUserMapper.selectByCidOrTid(nemoQueryVo));
        } else {
            if (nemoQueryVo.getExportType() == 1) {
                UserDbContextUtils.initCtxByPlatOpenId(nemoQueryVo.getPlatOpenId());
            } else if (nemoQueryVo.getExportType() == 2) {
                UserDbContextUtils.initCtxByUserId(nemoQueryVo.getOriginUserId());
            }
            List<NemoQueryVo> list = platUserMapper.selectTblByPlatId(nemoQueryVo, UserDbContext.getPlatUserTable());
            if (list == null || list.size() == 0) {
                list = platUserMapper.selectByPlatId(nemoQueryVo);
            }
            result.setRows(list);
        }
        return result;
    }

    @Autowired
    private PlatUserMapper platUserMapper;

    @Autowired
    private TppUserMapper tppUserMapper;
}
