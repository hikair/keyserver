/*
Navicat MySQL Data Transfer

Source Server         : zpq
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-12-20 16:30:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `key_user`
-- ----------------------------
DROP TABLE IF EXISTS `key_user`;
CREATE TABLE `key_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门名',
  `host_address` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `listen_key` varchar(255) DEFAULT NULL COMMENT '监听键名多个用逗号隔开',
  PRIMARY KEY (`id`),
  UNIQUE KEY `host_address` (`host_address`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of key_user
-- ----------------------------
INSERT INTO `key_user` VALUES ('1', '4', '销售部', '192.168.43.120', 'zpq', '回车,退格,删除,alt+回车');
INSERT INTO `key_user` VALUES ('3', '3', '测试部', '127.0.0.1', 'localhost', '回车');
INSERT INTO `key_user` VALUES ('4', '2', '研发部', '192.168.56.1', 'gj', '回车,间隔符,删除,g');
INSERT INTO `key_user` VALUES ('5', '1', '开发部', '192.168.2.6', 'home', '回车,退格,删除,alt+回车');
