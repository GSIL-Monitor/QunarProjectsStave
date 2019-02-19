package com.qunar.fintech.plat.admin.system.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExportFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExportFileExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andFileSeqIsNull() {
            addCriterion("file_seq is null");
            return (Criteria) this;
        }

        public Criteria andFileSeqIsNotNull() {
            addCriterion("file_seq is not null");
            return (Criteria) this;
        }

        public Criteria andFileSeqEqualTo(String value) {
            addCriterion("file_seq =", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqNotEqualTo(String value) {
            addCriterion("file_seq <>", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqGreaterThan(String value) {
            addCriterion("file_seq >", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqGreaterThanOrEqualTo(String value) {
            addCriterion("file_seq >=", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqLessThan(String value) {
            addCriterion("file_seq <", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqLessThanOrEqualTo(String value) {
            addCriterion("file_seq <=", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqLike(String value) {
            addCriterion("file_seq like", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqNotLike(String value) {
            addCriterion("file_seq not like", value, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqIn(List<String> values) {
            addCriterion("file_seq in", values, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqNotIn(List<String> values) {
            addCriterion("file_seq not in", values, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqBetween(String value1, String value2) {
            addCriterion("file_seq between", value1, value2, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andFileSeqNotBetween(String value1, String value2) {
            addCriterion("file_seq not between", value1, value2, "fileSeq");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeIsNull() {
            addCriterion("export_task_type is null");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeIsNotNull() {
            addCriterion("export_task_type is not null");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeEqualTo(Integer value) {
            addCriterion("export_task_type =", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeNotEqualTo(Integer value) {
            addCriterion("export_task_type <>", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeGreaterThan(Integer value) {
            addCriterion("export_task_type >", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("export_task_type >=", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeLessThan(Integer value) {
            addCriterion("export_task_type <", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeLessThanOrEqualTo(Integer value) {
            addCriterion("export_task_type <=", value, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeIn(List<Integer> values) {
            addCriterion("export_task_type in", values, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeNotIn(List<Integer> values) {
            addCriterion("export_task_type not in", values, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeBetween(Integer value1, Integer value2) {
            addCriterion("export_task_type between", value1, value2, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("export_task_type not between", value1, value2, "exportTaskType");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescIsNull() {
            addCriterion("export_task_desc is null");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescIsNotNull() {
            addCriterion("export_task_desc is not null");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescEqualTo(String value) {
            addCriterion("export_task_desc =", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescNotEqualTo(String value) {
            addCriterion("export_task_desc <>", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescGreaterThan(String value) {
            addCriterion("export_task_desc >", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescGreaterThanOrEqualTo(String value) {
            addCriterion("export_task_desc >=", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescLessThan(String value) {
            addCriterion("export_task_desc <", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescLessThanOrEqualTo(String value) {
            addCriterion("export_task_desc <=", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescLike(String value) {
            addCriterion("export_task_desc like", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescNotLike(String value) {
            addCriterion("export_task_desc not like", value, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescIn(List<String> values) {
            addCriterion("export_task_desc in", values, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescNotIn(List<String> values) {
            addCriterion("export_task_desc not in", values, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescBetween(String value1, String value2) {
            addCriterion("export_task_desc between", value1, value2, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andExportTaskDescNotBetween(String value1, String value2) {
            addCriterion("export_task_desc not between", value1, value2, "exportTaskDesc");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleIsNull() {
            addCriterion("attachment_title is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleIsNotNull() {
            addCriterion("attachment_title is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleEqualTo(String value) {
            addCriterion("attachment_title =", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleNotEqualTo(String value) {
            addCriterion("attachment_title <>", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleGreaterThan(String value) {
            addCriterion("attachment_title >", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_title >=", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleLessThan(String value) {
            addCriterion("attachment_title <", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleLessThanOrEqualTo(String value) {
            addCriterion("attachment_title <=", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleLike(String value) {
            addCriterion("attachment_title like", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleNotLike(String value) {
            addCriterion("attachment_title not like", value, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleIn(List<String> values) {
            addCriterion("attachment_title in", values, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleNotIn(List<String> values) {
            addCriterion("attachment_title not in", values, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleBetween(String value1, String value2) {
            addCriterion("attachment_title between", value1, value2, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andAttachmentTitleNotBetween(String value1, String value2) {
            addCriterion("attachment_title not between", value1, value2, "attachmentTitle");
            return (Criteria) this;
        }

        public Criteria andExtendIsNull() {
            addCriterion("extend is null");
            return (Criteria) this;
        }

        public Criteria andExtendIsNotNull() {
            addCriterion("extend is not null");
            return (Criteria) this;
        }

        public Criteria andExtendEqualTo(String value) {
            addCriterion("extend =", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotEqualTo(String value) {
            addCriterion("extend <>", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendGreaterThan(String value) {
            addCriterion("extend >", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendGreaterThanOrEqualTo(String value) {
            addCriterion("extend >=", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLessThan(String value) {
            addCriterion("extend <", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLessThanOrEqualTo(String value) {
            addCriterion("extend <=", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendLike(String value) {
            addCriterion("extend like", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotLike(String value) {
            addCriterion("extend not like", value, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendIn(List<String> values) {
            addCriterion("extend in", values, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotIn(List<String> values) {
            addCriterion("extend not in", values, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendBetween(String value1, String value2) {
            addCriterion("extend between", value1, value2, "extend");
            return (Criteria) this;
        }

        public Criteria andExtendNotBetween(String value1, String value2) {
            addCriterion("extend not between", value1, value2, "extend");
            return (Criteria) this;
        }

        public Criteria andExportStatusIsNull() {
            addCriterion("export_status is null");
            return (Criteria) this;
        }

        public Criteria andExportStatusIsNotNull() {
            addCriterion("export_status is not null");
            return (Criteria) this;
        }

        public Criteria andExportStatusEqualTo(Integer value) {
            addCriterion("export_status =", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusNotEqualTo(Integer value) {
            addCriterion("export_status <>", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusGreaterThan(Integer value) {
            addCriterion("export_status >", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("export_status >=", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusLessThan(Integer value) {
            addCriterion("export_status <", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusLessThanOrEqualTo(Integer value) {
            addCriterion("export_status <=", value, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusIn(List<Integer> values) {
            addCriterion("export_status in", values, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusNotIn(List<Integer> values) {
            addCriterion("export_status not in", values, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusBetween(Integer value1, Integer value2) {
            addCriterion("export_status between", value1, value2, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("export_status not between", value1, value2, "exportStatus");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescIsNull() {
            addCriterion("export_status_desc is null");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescIsNotNull() {
            addCriterion("export_status_desc is not null");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescEqualTo(String value) {
            addCriterion("export_status_desc =", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescNotEqualTo(String value) {
            addCriterion("export_status_desc <>", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescGreaterThan(String value) {
            addCriterion("export_status_desc >", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("export_status_desc >=", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescLessThan(String value) {
            addCriterion("export_status_desc <", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescLessThanOrEqualTo(String value) {
            addCriterion("export_status_desc <=", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescLike(String value) {
            addCriterion("export_status_desc like", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescNotLike(String value) {
            addCriterion("export_status_desc not like", value, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescIn(List<String> values) {
            addCriterion("export_status_desc in", values, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescNotIn(List<String> values) {
            addCriterion("export_status_desc not in", values, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescBetween(String value1, String value2) {
            addCriterion("export_status_desc between", value1, value2, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andExportStatusDescNotBetween(String value1, String value2) {
            addCriterion("export_status_desc not between", value1, value2, "exportStatusDesc");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}