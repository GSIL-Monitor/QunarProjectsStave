package com.qunar.fintech.plat.admin.query.dao.nemo;

import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatUserMapper{
    /**
     * 查询plat_user中数据
     * @param nemoQuery
     * @return
     */
    List<NemoQueryVo> selectByPlatId(@Param("nemoQuery") NemoQueryVo nemoQuery);

    /**
     * 查询分表数据
     *
     * @param nemoQuery
     * @param tableName
     * @return
     */
    List<NemoQueryVo> selectTblByPlatId(@Param("nemoQuery") NemoQueryVo nemoQuery, @Param("tableName") String tableName);

}