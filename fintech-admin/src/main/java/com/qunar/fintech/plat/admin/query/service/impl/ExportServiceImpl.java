package com.qunar.fintech.plat.admin.query.service.impl;

import com.qunar.fintech.plat.admin.query.enums.nemo.ExportStatusEnum;
import com.qunar.fintech.plat.admin.query.enums.nemo.ExportTaskTypeEnum;
import com.qunar.fintech.plat.admin.query.export.intf.ExportService;
import com.qunar.fintech.plat.admin.query.vo.NemoQueryVo;
import com.qunar.fintech.plat.admin.query.vo.Page;
import com.qunar.fintech.plat.admin.system.dao.entity.ExportFile;
import com.qunar.fintech.plat.admin.system.dao.mapper.ExportFileMapper;
import com.qunar.fintech.util.simple.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExportServiceImpl implements ExportService<ExportFile, NemoQueryVo> {
    /**
     * 通过文件序列号查询任务
     */
    @Override
    public ExportFile selectByFileSeq(String fileSeq) {
        ExportFile exportFileInfo = exportFileMapper.selectByFileSeq(fileSeq) ;
        return exportFileInfo;
    }
    /**
     * 更新文件任务状态
     */
    @Override
    public void updateExportFileByPrimaryKey(ExportFile entity, ExportStatusEnum ese) {
        entity.setExportStatus(ese.getKey());
        entity.setExportStatusDesc(ese.getValue());
        exportFileMapper.updateByPrimaryKey(entity);
    }
    /**
     * 新建文件任务
     */
    @Override
    public void insertExportFile(String fileSeq, Long userId, int exportTask) {
        String extend = ".zip";
        ExportFile exportFile = new ExportFile();
        exportFile.setFileSeq(fileSeq);
        exportFile.setExportStatus(ExportStatusEnum.CREATE.getKey());
        exportFile.setExportStatusDesc(ExportStatusEnum.CREATE.getValue());
        exportFile.setUserId(userId);
        exportFile.setUrl(fileSeq + extend);
        exportFile.setExtend(extend);
        exportFile.setStartTime(new Date());
        Date endTime = DateUtils.addDay(new Date(), VALID_TIME);
        exportFile.setEndTime(endTime);
        exportFile.setAttachmentTitle(fileSeq);
        ExportTaskTypeEnum type = ExportTaskTypeEnum.toEnum(exportTask);
        exportFile.setExportTaskType(type.getKey());
        exportFile.setExportTaskDesc(type.getValue());
        exportFileMapper.insert(exportFile);
    }
    /**
     * 查询任务列表
     */
    @Override
    public List<ExportFile> selectExportList(ExportFile ef, Page page) {
        List<ExportFile> exportFileList  = null;
        if(ef != null){
            exportFileList = exportFileMapper.selectExportFileByPage(ef.getUserId(), page);
        }
        return exportFileList;
    }
    /**
     * 查询任务总数量
     */
    @Override
    public Integer selectExportFileTotal(Long userId){
        return exportFileMapper.selectExportFileTotal(userId);
    }

    @Autowired
    private ExportFileMapper exportFileMapper;

    //有效期限 (单位：天)
    private Integer VALID_TIME = 14;
}
