/*
Navicat MySQL Data Transfer

Source Server         : zpq
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-12-20 23:00:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `key_template`
-- ----------------------------
DROP TABLE IF EXISTS `key_template`;
CREATE TABLE `key_template` (
  `template_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `template_name` varchar(255) DEFAULT NULL COMMENT '模板名',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  `dept_name` varchar(255) DEFAULT NULL COMMENT '部门名',
  `key_names` varchar(255) DEFAULT NULL COMMENT '键名',
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of key_template
-- ----------------------------
INSERT INTO `key_template` VALUES ('1', '模板一', '1', '开发部', '回车,空格,退格');
INSERT INTO `key_template` VALUES ('2', '模板二', '1', '开发部', '回车,删除');
