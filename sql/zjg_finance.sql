/*
 Navicat Premium Data Transfer

 Source Server         : db_dev
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : 172.16.11.120
 Source Database       : zjg_finance

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : utf-8

 Date: 10/20/2017 12:49:48 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `api_authority`
-- ----------------------------
DROP TABLE IF EXISTS `api_authority`;
CREATE TABLE `api_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(250) NOT NULL COMMENT '权限名称',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `value` varchar(50) NOT NULL COMMENT '权限值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7r9q0ughot4woggkv1aw99vlp` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `api_authority`
-- ----------------------------
BEGIN;
INSERT INTO `api_authority` VALUES ('1', '2017-10-18 13:24:11', '访问user微服务', '2017-10-18 13:24:14', 'user-service');
COMMIT;

-- ----------------------------
--  Table structure for `api_role`
-- ----------------------------
DROP TABLE IF EXISTS `api_role`;
CREATE TABLE `api_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(250) NOT NULL COMMENT '角色名称',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `value` varchar(50) NOT NULL COMMENT '角色值',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7hwvya4jjpx053h47s3qlsno9` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `api_role`
-- ----------------------------
BEGIN;
INSERT INTO `api_role` VALUES ('1', '2017-10-18 13:26:39', '管理员', '2017-10-18 13:26:41', 'ROLE_ADMIN'), ('2', '2017-10-18 13:27:36', '普通用户', '2017-10-18 13:27:38', 'ROLE_USER');
COMMIT;

-- ----------------------------
--  Table structure for `api_role_authoritys`
-- ----------------------------
DROP TABLE IF EXISTS `api_role_authoritys`;
CREATE TABLE `api_role_authoritys` (
  `api_role_entity_id` bigint(20) NOT NULL COMMENT '主键',
  `authoritys_id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`api_role_entity_id`,`authoritys_id`),
  KEY `FKqy4o5e6teyb1ti3oo395d1g0e` (`authoritys_id`),
  CONSTRAINT `FKbetiwbwwe1u95ap5n6cvl6x1g` FOREIGN KEY (`api_role_entity_id`) REFERENCES `api_role` (`id`),
  CONSTRAINT `FKqy4o5e6teyb1ti3oo395d1g0e` FOREIGN KEY (`authoritys_id`) REFERENCES `api_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `api_role_authoritys`
-- ----------------------------
BEGIN;
INSERT INTO `api_role_authoritys` VALUES ('1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `api_user`
-- ----------------------------
DROP TABLE IF EXISTS `api_user`;
CREATE TABLE `api_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(250) NOT NULL COMMENT '用户名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_scb81k0sobpewfaxhwyquccop` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `api_user`
-- ----------------------------
BEGIN;
INSERT INTO `api_user` VALUES ('1', '2017-10-18 13:16:52', 'admin', 'admin', '2017-10-18 13:16:55'), ('2', '2017-10-18 13:17:08', 'roc', 'roc', '2017-10-18 13:17:15');
COMMIT;

-- ----------------------------
--  Table structure for `api_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `api_user_roles`;
CREATE TABLE `api_user_roles` (
  `api_user_entity_id` bigint(20) NOT NULL COMMENT '主键',
  `roles_id` bigint(20) NOT NULL COMMENT '主键',
  PRIMARY KEY (`api_user_entity_id`,`roles_id`),
  KEY `FK81n4i83i1ochnbtnq0916uong` (`roles_id`),
  CONSTRAINT `FK4rk7yiya5b8lvrkt90e7s1f8q` FOREIGN KEY (`api_user_entity_id`) REFERENCES `api_user` (`id`),
  CONSTRAINT `FK81n4i83i1ochnbtnq0916uong` FOREIGN KEY (`roles_id`) REFERENCES `api_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `api_user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `api_user_roles` VALUES ('1', '1'), ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `finance_user`
-- ----------------------------
DROP TABLE IF EXISTS `finance_user`;
CREATE TABLE `finance_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `duty` varchar(250) DEFAULT NULL COMMENT '账户职务',
  `last_login_time` datetime NOT NULL COMMENT '最近登录时间',
  `mobile` bigint(20) NOT NULL COMMENT '电话号码',
  `name` varchar(250) NOT NULL COMMENT '账户名称',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `reg_time` datetime NOT NULL COMMENT '注册时间',
  `type` int(11) NOT NULL COMMENT '账户类型 1:金融办 2:金融机构',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q6ksevh3uivdx0ilaop0b4wj0` (`mobile`),
  UNIQUE KEY `UK_yydjqhonruvekv1vymusoda1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `finance_user`
-- ----------------------------
BEGIN;
INSERT INTO `finance_user` VALUES ('1', '2017-10-18 16:17:57', '经理', '2017-10-18 16:18:08', '13817058426', 'roc', 'xlh123456', '2017-10-18 16:18:39', '1', '2017-10-18 16:18:52');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
