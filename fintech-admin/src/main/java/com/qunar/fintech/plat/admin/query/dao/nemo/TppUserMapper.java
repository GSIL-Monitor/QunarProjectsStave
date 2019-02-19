package com.qunar.fintech.plat.admin.query.dao.nemo;

import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TppUserMapper{

    /**
     * 通过cid 或 tid 查找数据
     * @param nemoQuery
     * @return
     */
    List<NemoQueryVo> selectByCidOrTid(@Param("nemoQuery") NemoQueryVo nemoQuery);
}