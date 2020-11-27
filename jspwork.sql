/*
 Navicat Premium Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : jspwork

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 27/11/2020 16:15:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for active
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动名称',
  `save_time` datetime(0) NULL DEFAULT NULL,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `Aname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES (1, '踢足球', '秋你太美!', '2020-11-25 15:13:33', '足球社');

-- ----------------------------
-- Table structure for association
-- ----------------------------
DROP TABLE IF EXISTS `association`;
CREATE TABLE `association`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Apinfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`, `Aname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of association
-- ----------------------------
INSERT INTO `association` VALUES (1, '足球社', '我用双脚成就你的梦想');

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `saveTime` datetime(0) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`, `Aname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES (1, '足球之王', '足球社', '2020-11-25 15:07:17', '带你踢球带你飞！老铁没毛病哈！');

-- ----------------------------
-- Table structure for introduce
-- ----------------------------
DROP TABLE IF EXISTS `introduce`;
CREATE TABLE `introduce`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `introduce` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `Aname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of introduce
-- ----------------------------
INSERT INTO `introduce` VALUES (1, '足球社', '城院C罗等你来踢!');

-- ----------------------------
-- Table structure for join_status
-- ----------------------------
DROP TABLE IF EXISTS `join_status`;
CREATE TABLE `join_status`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` int(11) NOT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_status
-- ----------------------------
INSERT INTO `join_status` VALUES (1, '蔡徐坤', 1, '通过');

-- ----------------------------
-- Table structure for leaving_word
-- ----------------------------
DROP TABLE IF EXISTS `leaving_word`;
CREATE TABLE `leaving_word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `word` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `Aname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leaving_word
-- ----------------------------
INSERT INTO `leaving_word` VALUES (1, '足球社', '这个足球社是真的香!既是年轻人又讲武德!');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Aname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'caixukun', 'lanqiu', '蔡徐坤', '足球社');

SET FOREIGN_KEY_CHECKS = 1;
