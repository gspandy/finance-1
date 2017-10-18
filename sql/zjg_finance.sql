/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : zjg_finance

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 10/18/2017 09:58:13 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `api_user`
-- ----------------------------
DROP TABLE IF EXISTS `api_user`;
CREATE TABLE `api_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(250) NOT NULL COMMENT '账户名称',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_api_user_name` (`name`) USING BTREE,
  KEY `idx_api_user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='API用户信息表';

-- ----------------------------
--  Records of `api_user`
-- ----------------------------
BEGIN;
INSERT INTO `api_user` VALUES ('1', 'admin', 'admin', '2017-10-17 09:51:30', '2017-10-17 09:51:32'), ('2', 'roc', 'roc', '2017-10-17 13:18:21', '2017-10-17 13:18:23');
COMMIT;

-- ----------------------------
--  Table structure for `api_role`
-- ----------------------------
DROP TABLE IF EXISTS `api_role`;
CREATE TABLE `api_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(250) NOT NULL COMMENT '角色名称',
  `value` varchar(50) NOT NULL COMMENT '角色值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_api_role_name` (`name`) USING BTREE,
  KEY `idx_api_role_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='API角色信息表';

-- ----------------------------
--  Table structure for `api_authority`
-- ----------------------------
DROP TABLE IF EXISTS `api_authority`;
CREATE TABLE `api_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(250) NOT NULL COMMENT '角色名称',
  `value` varchar(50) NOT NULL COMMENT '角色值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_api_authority_name` (`name`) USING BTREE,
  KEY `idx_api_authority_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='API权限信息表';


-- ----------------------------
--  Table structure for `finance_user`
-- ----------------------------
DROP TABLE IF EXISTS `finance_user`;
CREATE TABLE `finance_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` bigint(20) NOT NULL COMMENT '手机号码',
  `name` varchar(250) NOT NULL COMMENT '账户名称',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `type` tinyint(5) NOT NULL COMMENT '账户类型 1:金融办 2:金融机构',
  `duty` varchar(250) DEFAULT NULL COMMENT '用户职务',
  `reg_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_finance_user_mobile` (`mobile`) USING BTREE,
  KEY `idx_finance_user_reg_time` (`reg_time`),
  KEY `idx_finance_user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
--  Records of `finance_user`
-- ----------------------------
BEGIN;
INSERT INTO `finance_user` VALUES ('78379', '0', '13311112222', 'xlh123456', '0', null, null, null, '2017-10-13 22:52:52', '2017-10-13 22:52:52');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
