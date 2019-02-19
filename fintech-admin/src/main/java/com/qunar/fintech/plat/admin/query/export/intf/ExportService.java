package com.qunar.fintech.plat.admin.query.export.intf;

import com.qunar.fintech.plat.admin.query.enums.nemo.ExportStatusEnum;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.system.dao.entity.ExportFile;

import java.util.List;

/**
 * 导出接口
 * @param <T>导出文件的实体类
 * @param <Q>导出的查询参数对象
 */
public interface ExportService<T extends ExportFile,Q> {
    /**
     * 新建文件任务
     */
    void insertExportFile(String fileSeq, Long userId, int exportTaskEnum);
    /**
     * 通过文件序列号查询任务
     */
    T selectByFileSeq(String fileSeq);
    /**
     * 更新文件任务状态
     */
    void updateExportFileByPrimaryKey(T entity,ExportStatusEnum ese);
    /**
     * 查询任务列表
     */
    List<ExportFile> selectExportList(ExportFile ef, Page page);
    /**
    * 查询任务总数量
    */
    Integer selectExportFileTotal(Long userId);

}
