/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/01/2021 18:44:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(0) NOT NULL,
  `auth_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `parent_id` int(0) NOT NULL COMMENT '父级id',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (101, '按键管理', 0, NULL);
INSERT INTO `menu` VALUES (102, '按键统计', 0, NULL);
INSERT INTO `menu` VALUES (201, '用户管理', 101, 'users');
INSERT INTO `menu` VALUES (202, '按键分配', 101, 'keys');
INSERT INTO `menu` VALUES (203, '模板管理', 101, 'templates');
INSERT INTO `menu` VALUES (204, '分析统计', 102, 'echarts');
INSERT INTO `menu` VALUES (205, '日志统计', 102, 'logs');

SET FOREIGN_KEY_CHECKS = 1;
