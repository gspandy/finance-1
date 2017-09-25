/*
 Navicat Premium Data Transfer

 Source Server         : db_dev
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : 172.16.11.120
 Source Database       : xlh_member

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : utf-8

 Date: 09/25/2017 13:38:45 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `member_user`
-- ----------------------------
DROP TABLE IF EXISTS `finance_user`;
CREATE TABLE `finance_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` bigint(20) NOT NULL COMMENT '手机号码',
  `account` varchar(250) NOT NULL COMMENT '账户名称',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `type` tinyint(5) NOT NULL COMMENT '账户类型 1:金融办 2:金融机构',
  `avatar` varchar(250) DEFAULT NULL COMMENT '用户头像',
  `reg_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最近登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_member_user_mobile` (`mobile`) USING BTREE,
  KEY `idx_member_user_user_account` (`account`) USING BTREE,
  KEY `idx_member_user_reg_time` (`reg_time`),
  KEY `idx_member_user_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78379 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

SET FOREIGN_KEY_CHECKS = 1;
