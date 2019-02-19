/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : fin_plat_admin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-04 13:33:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog_content`
-- ----------------------------
DROP TABLE IF EXISTS `blog_content`;
CREATE TABLE `blog_content` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `slug` varchar(255) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `modified` bigint(20) DEFAULT NULL COMMENT '最近修改人id',
  `content` text COMMENT '内容',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类',
  `hits` int(5) DEFAULT NULL,
  `comments_num` int(5) DEFAULT '0' COMMENT '评论数量',
  `allow_comment` int(1) DEFAULT '0' COMMENT '开启评论',
  `allow_ping` int(1) DEFAULT '0' COMMENT '允许ping',
  `allow_feed` int(1) DEFAULT '0' COMMENT '允许反馈',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `gtm_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gtm_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='文章内容';

-- ----------------------------
-- Records of blog_content
-- ----------------------------
INSERT INTO `blog_content` VALUES ('75', '基于 Springboot 和 Mybatis 的后台管理系统', null, null, null, '<h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">项目介绍</h3><ul style=\"margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 0px 30px; list-style-position: initial; list-style-image: initial; color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><li><p>面向学习型的开源框架，简洁高效，减少过渡封装，展现技术本质</p></li><li><p>Springboot作为基础框架，使用mybatis作为持久层框架</p></li><li><p>使用官方推荐的thymeleaf做为模板引擎，shiro作为安全框架,主流技术，“一网打尽”</p></li><li><p>基于注解的sql写法，零XML，极简配置，一键前后台代码生成</p></li></ul><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">演示地址</span>&nbsp;<a href=\"http://47.93.239.129/\" style=\"outline: 0px; color: rgb(68, 102, 187);\">http://47.93.239.129</a></p><h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">功能简介</h3><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\">1. 用户管理<br>2. 角色管理<br>3. 部门管理<br>4. 菜单管理<br>5. 系统日志<br>6. 代码生成<br>7. 内容管理</p><h3 style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif;\">所用框架</h3><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">前端</span><br>1. Bootstrap<br>2. jQuery<br>3. bootstrap-table<br>4. layer<br>5. jsTree&nbsp;<br>6. summernote<br>7. jquery-validate<br>8. jquery-treegrid</p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">后端</span><br>1. SpringBoot&nbsp;<br>2. MyBatis<br>3. Thymeleaf<br>4. Shiro<br>5. druid</p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><span style=\"font-weight: bolder;\">项目截图</span></p><p style=\"color: rgb(17, 17, 17); font-family: &quot;PingFang SC&quot;, &quot;Helvetica Neue&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, &quot;Noto Sans CJK SC&quot;, Sathu, EucrosiaUPC, sans-serif; font-size: 16px;\"><img height=\"400\" src=\"https://static.oschina.net/uploads/space/2017/0912/182421_5LaN_3244087.png\" width=\"650\" style=\"border-width: initial; border-style: none; outline: 0px; width: 882px; max-width: -webkit-fit-content; height: auto;\"></p>', '', null, null, null, '0', '0', '0', '1', '1', 'bootdo', '2017-09-22 14:44:44', '2017-09-22 14:44:44');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类\0\0', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2524 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
-- INSERT INTO `sys_menu` VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('3', '0', '系统管理', '', '', '0', 'fa fa-desktop', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('77', '0', '系统工具', '', '', '0', 'fa fa-gear', '4');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('49', '0', '博客管理', '', '', '0', 'fa fa-rss', '6');

INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('2', '3', '系统菜单', 'sys/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('6', '3', '用户管理', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('7', '3', '角色管理', 'sys/role', 'sys:role:role', '1', 'fa fa-paw', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('14', '6', '删除', '', 'sys:user:remove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('25', '6', '停用', '', 'sys:user:disable', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0');

INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('27', '3', '系统日志', 'sys/log', 'sys:log:log', '1', 'fa fa-warning', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('28', '27', '刷新', '', 'sys:log:list', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('29', '27', '删除', '', 'sys:log:remove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('30', '27', '清空', '', 'sys:log:clear', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('48', '77', '代码生成', 'common/generator', 'common:generator', '1', 'fa fa-code', '3');

INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('51', '50', '新增', '', 'blog:bContent:add', '2', '', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', '', '1');

INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('57', '3', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('58', '50', '编辑', '', 'blog:bContent:edit', '2', '', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('59', '50', '删除', '', 'blog:bContent:remove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('60', '50', '批量删除', '', 'blog:bContent:batchRemove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', '', '0');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('71', '3', '文件管理', '/sys/file', 'sys:file:file', '1', 'fa fa-folder-open', '2');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('72', '77', '计划任务', 'sys/job', 'sys:job', '1', 'fa fa-hourglass-1', '4');

INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('73', '3', '部门管理', '/sys/dept', 'sys:dept:dept', '1', 'fa fa-users', '3');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('74', '73', '增加', '/sys/dept/add', 'sys:dept:add', '2', '', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('75', '73', '刪除', 'sys/dept/remove', 'sys:dept:remove', '2', '', '2');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('76', '73', '编辑', '/sys/dept/edit', 'sys:dept:edit', '2', '', '3');


INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('78', '3', '数据字典', '/sys/dict', 'sys:dict:sysDict', '1', 'fa fa-book', '1');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('79', '78', '增加', '/sys/dict/add', 'sys:dict:add', '2', '', '2');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('80', '78', '编辑', '/sys/dict/edit', 'sys:dict:edit', '2', '', '2');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('81', '78', '删除', '/sys/dict/remove', 'sys:dict:remove', '2', '', '3');
INSERT INTO `sys_menu`(menu_id, parent_id, name, url, perms, type, icon, order_num) VALUES ('83', '78', '批量删除', '/sys/dict/batchRemove', 'sys:dict:batchRemove', '2', '', '4');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` (role_id,role_name) VALUES ('1', '管理员');
INSERT INTO `sys_role` (role_id,role_name) VALUES ('2', '游客');
-- INSERT INTO `sys_role` VALUES ('49', '白金会员', null, '消费5000以上', null, null, null);
-- INSERT INTO `sys_role` VALUES ('52', '白银会员', null, '消费两千以上', null, null, null);
-- INSERT INTO `sys_role` VALUES ('56', '普通用户', null, '普通用户', null, null, null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2091 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('367', '44', '1');
INSERT INTO `sys_role_menu` VALUES ('368', '44', '32');
INSERT INTO `sys_role_menu` VALUES ('369', '44', '33');
INSERT INTO `sys_role_menu` VALUES ('370', '44', '34');
INSERT INTO `sys_role_menu` VALUES ('371', '44', '35');
INSERT INTO `sys_role_menu` VALUES ('372', '44', '28');
INSERT INTO `sys_role_menu` VALUES ('373', '44', '29');
INSERT INTO `sys_role_menu` VALUES ('374', '44', '30');
INSERT INTO `sys_role_menu` VALUES ('375', '44', '38');
INSERT INTO `sys_role_menu` VALUES ('376', '44', '4');
INSERT INTO `sys_role_menu` VALUES ('377', '44', '27');
INSERT INTO `sys_role_menu` VALUES ('378', '45', '38');
INSERT INTO `sys_role_menu` VALUES ('379', '46', '3');
INSERT INTO `sys_role_menu` VALUES ('380', '46', '20');
INSERT INTO `sys_role_menu` VALUES ('381', '46', '21');
INSERT INTO `sys_role_menu` VALUES ('382', '46', '22');
INSERT INTO `sys_role_menu` VALUES ('383', '46', '23');
INSERT INTO `sys_role_menu` VALUES ('384', '46', '11');
INSERT INTO `sys_role_menu` VALUES ('385', '46', '12');
INSERT INTO `sys_role_menu` VALUES ('386', '46', '13');
INSERT INTO `sys_role_menu` VALUES ('387', '46', '14');
INSERT INTO `sys_role_menu` VALUES ('388', '46', '24');
INSERT INTO `sys_role_menu` VALUES ('389', '46', '25');
INSERT INTO `sys_role_menu` VALUES ('390', '46', '26');
INSERT INTO `sys_role_menu` VALUES ('391', '46', '15');
INSERT INTO `sys_role_menu` VALUES ('392', '46', '2');
INSERT INTO `sys_role_menu` VALUES ('393', '46', '6');
INSERT INTO `sys_role_menu` VALUES ('394', '46', '7');
INSERT INTO `sys_role_menu` VALUES ('598', '50', '38');
INSERT INTO `sys_role_menu` VALUES ('632', '38', '42');
INSERT INTO `sys_role_menu` VALUES ('737', '51', '38');
INSERT INTO `sys_role_menu` VALUES ('738', '51', '39');
INSERT INTO `sys_role_menu` VALUES ('739', '51', '40');
INSERT INTO `sys_role_menu` VALUES ('740', '51', '41');
INSERT INTO `sys_role_menu` VALUES ('741', '51', '4');
INSERT INTO `sys_role_menu` VALUES ('742', '51', '32');
INSERT INTO `sys_role_menu` VALUES ('743', '51', '33');
INSERT INTO `sys_role_menu` VALUES ('744', '51', '34');
INSERT INTO `sys_role_menu` VALUES ('745', '51', '35');
INSERT INTO `sys_role_menu` VALUES ('746', '51', '27');
INSERT INTO `sys_role_menu` VALUES ('747', '51', '28');
INSERT INTO `sys_role_menu` VALUES ('748', '51', '29');
INSERT INTO `sys_role_menu` VALUES ('749', '51', '30');
INSERT INTO `sys_role_menu` VALUES ('750', '51', '1');
INSERT INTO `sys_role_menu` VALUES ('1064', '54', '53');
INSERT INTO `sys_role_menu` VALUES ('1095', '55', '2');
INSERT INTO `sys_role_menu` VALUES ('1096', '55', '6');
INSERT INTO `sys_role_menu` VALUES ('1097', '55', '7');
INSERT INTO `sys_role_menu` VALUES ('1098', '55', '3');
INSERT INTO `sys_role_menu` VALUES ('1099', '55', '50');
INSERT INTO `sys_role_menu` VALUES ('1100', '55', '49');
INSERT INTO `sys_role_menu` VALUES ('1101', '55', '1');
INSERT INTO `sys_role_menu` VALUES ('1375', '59', '1');
INSERT INTO `sys_role_menu` VALUES ('1376', '59', '3');
INSERT INTO `sys_role_menu` VALUES ('1642', '62', '1');
INSERT INTO `sys_role_menu` VALUES ('1643', '62', '27');
INSERT INTO `sys_role_menu` VALUES ('1644', '62', '48');
INSERT INTO `sys_role_menu` VALUES ('1645', '62', '57');
INSERT INTO `sys_role_menu` VALUES ('1646', '62', '71');
INSERT INTO `sys_role_menu` VALUES ('1856', '53', '28');
INSERT INTO `sys_role_menu` VALUES ('1857', '53', '29');
INSERT INTO `sys_role_menu` VALUES ('1858', '53', '30');
INSERT INTO `sys_role_menu` VALUES ('1859', '53', '27');
INSERT INTO `sys_role_menu` VALUES ('1860', '53', '57');
INSERT INTO `sys_role_menu` VALUES ('1861', '53', '71');
INSERT INTO `sys_role_menu` VALUES ('1862', '53', '48');
INSERT INTO `sys_role_menu` VALUES ('1863', '53', '72');
INSERT INTO `sys_role_menu` VALUES ('1864', '53', '1');
INSERT INTO `sys_role_menu` VALUES ('1865', '53', '7');
INSERT INTO `sys_role_menu` VALUES ('1866', '53', '55');
INSERT INTO `sys_role_menu` VALUES ('1867', '53', '56');
INSERT INTO `sys_role_menu` VALUES ('1868', '53', '62');
INSERT INTO `sys_role_menu` VALUES ('1869', '53', '15');
INSERT INTO `sys_role_menu` VALUES ('1870', '53', '2');
INSERT INTO `sys_role_menu` VALUES ('1871', '53', '61');
INSERT INTO `sys_role_menu` VALUES ('1872', '53', '20');
INSERT INTO `sys_role_menu` VALUES ('1873', '53', '21');
INSERT INTO `sys_role_menu` VALUES ('1874', '53', '22');
INSERT INTO `sys_role_menu` VALUES ('1875', '49', '12');
INSERT INTO `sys_role_menu` VALUES ('1876', '49', '13');
INSERT INTO `sys_role_menu` VALUES ('1877', '49', '14');
INSERT INTO `sys_role_menu` VALUES ('1878', '49', '24');
INSERT INTO `sys_role_menu` VALUES ('1879', '49', '25');
INSERT INTO `sys_role_menu` VALUES ('1880', '49', '26');
INSERT INTO `sys_role_menu` VALUES ('1881', '49', '61');
INSERT INTO `sys_role_menu` VALUES ('1882', '49', '20');
INSERT INTO `sys_role_menu` VALUES ('1883', '49', '21');
INSERT INTO `sys_role_menu` VALUES ('1884', '49', '22');
INSERT INTO `sys_role_menu` VALUES ('1885', '49', '74');
INSERT INTO `sys_role_menu` VALUES ('1886', '49', '75');
INSERT INTO `sys_role_menu` VALUES ('1887', '49', '76');
INSERT INTO `sys_role_menu` VALUES ('1888', '49', '6');
INSERT INTO `sys_role_menu` VALUES ('1889', '49', '2');
INSERT INTO `sys_role_menu` VALUES ('1890', '49', '73');
INSERT INTO `sys_role_menu` VALUES ('1897', '48', '28');
INSERT INTO `sys_role_menu` VALUES ('1898', '48', '30');
INSERT INTO `sys_role_menu` VALUES ('1899', '48', '57');
INSERT INTO `sys_role_menu` VALUES ('1900', '48', '71');
INSERT INTO `sys_role_menu` VALUES ('1901', '48', '48');
INSERT INTO `sys_role_menu` VALUES ('1902', '48', '72');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '28');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '29');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '30');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '27');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '57');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '79');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '80');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '81');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '78');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '71');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '1');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '12');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '13');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '14');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '24');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '25');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '26');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '6');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '55');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '56');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '62');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '15');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '7');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '61');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '20');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '21');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '22');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '2');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '74');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '75');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '76');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '73');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '3');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '48');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '72');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '77');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '68');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '51');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '58');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '59');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '60');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '50');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '49');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '83');
INSERT INTO `sys_role_menu`(role_id,menu_id) VALUES ( '1', '82');
INSERT INTO `sys_role_menu` VALUES ('2072', '52', '77');
INSERT INTO `sys_role_menu` VALUES ('2073', '52', '49');
INSERT INTO `sys_role_menu` VALUES ('2074', '52', '3');
INSERT INTO `sys_role_menu` VALUES ('2075', '52', '72');
INSERT INTO `sys_role_menu` VALUES ('2076', '52', '48');
INSERT INTO `sys_role_menu` VALUES ('2084', '56', '68');
INSERT INTO `sys_role_menu` VALUES ('2085', '56', '60');
INSERT INTO `sys_role_menu` VALUES ('2086', '56', '59');
INSERT INTO `sys_role_menu` VALUES ('2087', '56', '58');
INSERT INTO `sys_role_menu` VALUES ('2088', '56', '51');
INSERT INTO `sys_role_menu` VALUES ('2089', '56', '50');
INSERT INTO `sys_role_menu` VALUES ('2090', '56', '49');

-- ----------------------------
-- Table structure for `sys_task`
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('2', '0/10 * * * * ?', 'run', '1', 'sdfsdfsdf', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.qunar.fintech.plat.admin.system.quartz.TaskTest', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'com.bootdo.common.task.TaskTest');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` int(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` (user_id, username, name, password, dept_id, email, mobile, status, user_id_create) VALUES ('1', 'wei.duan', '超级管理员', '27bd386e70f280e24c2f4f2a549b82cf', '6', 'admin@example.com', '123456', '1', '1');
INSERT INTO `sys_user` (user_id, username, name, password, dept_id, email, mobile, status, user_id_create) VALUES ('2', 'test', '临时用户', '6cf3bb3deba2aadbd41ec9a22511084e', '6', 'test@bootdo.com', '', '1', '1');
INSERT INTO `sys_user` VALUES ('36', 'ldh', '刘德华', 'bfd9394475754fbe45866eba97738c36', '6', 'ldh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('123', 'zxy', '张学友', '35174ba93f5fe7267f1fb3c1bf903781', '6', 'zxy@bootdo', null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('124', 'wyf', '吴亦凡', 'e179e6f687bbd57b9d7efc4746c8090a', '6', 'wyf@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('130', 'lh', '鹿晗', '7924710cd673f68967cde70e188bb097', '9', 'lh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('131', 'lhc', '令狐冲', 'd515538e17ecb570ba40344b5618f5d4', '6', 'lhc@bootdo.com', null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('132', 'lyf', '刘亦菲', '7fdb1d9008f45950c1620ba0864e5fbd', '13', 'lyf@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('133', 'my', '马云', '40545c12927485fc6ebf65a146246aa0', '9', 'my@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('134', 'lyh', '李彦宏', 'dc26092b3244d9d432863f2738180e19', '8', 'lyh@bootdo.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('135', 'wjl', '王健林', '3967697dfced162cf6a34080259b83aa', '6', 'wjl@bootod.com', null, '1', null, null, null);
INSERT INTO `sys_user` VALUES ('136', 'gdg', '郭德纲', '3bb1bda86bc02bf6478cd91e42135d2f', '9', 'gdg@bootdo.com', null, '1', null, null, null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('73', '30', '48');
INSERT INTO `sys_user_role` VALUES ('74', '30', '49');
INSERT INTO `sys_user_role` VALUES ('75', '30', '50');
INSERT INTO `sys_user_role` VALUES ('76', '31', '48');
INSERT INTO `sys_user_role` VALUES ('77', '31', '49');
INSERT INTO `sys_user_role` VALUES ('78', '31', '52');
INSERT INTO `sys_user_role` VALUES ('79', '32', '48');
INSERT INTO `sys_user_role` VALUES ('80', '32', '49');
INSERT INTO `sys_user_role` VALUES ('81', '32', '50');
INSERT INTO `sys_user_role` VALUES ('82', '32', '51');
INSERT INTO `sys_user_role` VALUES ('83', '32', '52');
INSERT INTO `sys_user_role` VALUES ('84', '33', '38');
INSERT INTO `sys_user_role` VALUES ('85', '33', '49');
INSERT INTO `sys_user_role` VALUES ('86', '33', '52');
INSERT INTO `sys_user_role` VALUES ('87', '34', '50');
INSERT INTO `sys_user_role` VALUES ('88', '34', '51');
INSERT INTO `sys_user_role` VALUES ('89', '34', '52');
INSERT INTO `sys_user_role` VALUES ('97', '36', '48');
INSERT INTO `sys_user_role` VALUES ('106', '124', '1');
INSERT INTO `sys_user_role`( user_id, role_id) VALUES ( '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '1');
INSERT INTO `sys_user_role` VALUES ('113', '131', '48');
INSERT INTO `sys_user_role` VALUES ('117', '135', '1');
INSERT INTO `sys_user_role` VALUES ('120', '134', '1');
INSERT INTO `sys_user_role` VALUES ('121', '134', '48');
INSERT INTO `sys_user_role` VALUES ('122', '133', '1');
INSERT INTO `sys_user_role` VALUES ('123', '130', '1');
INSERT INTO `sys_user_role` VALUES ('124', null, '48');
