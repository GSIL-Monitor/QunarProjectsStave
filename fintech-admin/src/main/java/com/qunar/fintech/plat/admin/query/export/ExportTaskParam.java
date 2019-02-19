package com.qunar.fintech.plat.admin.query.export;

import com.qunar.fintech.plat.admin.query.export.intf.ExportService;
import com.qunar.fintech.plat.admin.query.service.NemoQueryService;

/**
 * 异步导出参数
 * @param <Q>
 */
public class ExportTaskParam<Q> {

    private Long userId;

    private ExportQuery<Q> query;

    private Class<?> pojoClass;

    private ExportService exportService;

    private NemoQueryService nemoQueryService;

    private UploadFileParam uploadFileParam;

    public ExportQuery<Q> getQuery() {
        return query;
    }

    public void setQuery(ExportQuery<Q> query) {
        this.query = query;
    }

    public Class<?> getPojoClass() {
        return pojoClass;
    }

    public void setPojoClass(Class<?> pojoClass) {
        this.pojoClass = pojoClass;
    }

    public ExportService getExportService() {
        return exportService;
    }

    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }

    public NemoQueryService getNemoQueryService() {
        return nemoQueryService;
    }

    public void setNemoQueryService(NemoQueryService nemoQueryService) {
        this.nemoQueryService = nemoQueryService;
    }

    public UploadFileParam getUploadFileParam() {
        return uploadFileParam;
    }

    public void setUploadFileParam(UploadFileParam uploadFileParam) {
        this.uploadFileParam = uploadFileParam;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
