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

 Date: 10/17/2017 09:24:25 AM
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
  KEY `idx_finance_user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78381 DEFAULT CHARSET=utf8 COMMENT='API授权信息表';

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
) ENGINE=InnoDB AUTO_INCREMENT=78381 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
--  Records of `finance_user`
-- ----------------------------
BEGIN;
INSERT INTO `finance_user` VALUES ('78379', '0', '13311112222', 'xlh123456', '0', null, null, null, '2017-10-13 22:52:52', '2017-10-13 22:52:52');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
