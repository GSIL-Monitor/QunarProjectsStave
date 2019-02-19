package com.qunar.fintech.plat.admin.system.dao.mapper;

import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.system.dao.entity.ExportFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportFileMapper {

    /**
     * 新建导出任务
     * @param record
     * @return
     */
    int insert(ExportFile record);
    /**
     * 查询导出任务
     * @param
     * @return
     */
    ExportFile selectByFileSeq(@Param("fileSeq")String fileSeq);
    /**
     * 更新文件任务状态
     * @param record
     * @return
     */
    int updateByPrimaryKey(ExportFile record);
    /**
     * 按页查询导出文件任务信息
     * @param userId
     * @param page
     * @return
     */
    List<ExportFile> selectExportFileByPage(@Param("userId")Long userId, @Param("page") Page page);
    /**
     * 查询导出文件任务总数
     * @param userId
     * @return
     */
    Integer selectExportFileTotal(@Param("userId")Long userId);
}