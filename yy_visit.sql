/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : yy_visit

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 30/05/2022 23:03:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for delicacy
-- ----------------------------
DROP TABLE IF EXISTS `delicacy`;
CREATE TABLE `delicacy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店名',
  `t_id` int NOT NULL COMMENT '菜品主打类型id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `average` decimal(10, 1) NULL DEFAULT NULL COMMENT '人均',
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径（相对）',
  `rate` int UNSIGNED NULL DEFAULT 0 COMMENT '评分（1极差、2失望、3一般、4满意、5惊喜）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of delicacy
-- ----------------------------
INSERT INTO `delicacy` VALUES (11, 'KCOOK概念韩餐(世茂52+店)', 4, '澳门路小学', 5500.0, '/upload/img/11/073846cda4144894a44233fd6c272724.png', 1);
INSERT INTO `delicacy` VALUES (12, '一根葱社区火锅(大连路总店)', 4, '武汉理工大学余家头校区', 4500.0, '/upload/img/12/3f0e31a2ff574df3a3cb90c2a1378ad6.jpg', 2);
INSERT INTO `delicacy` VALUES (13, '夏氏砂锅(万松园店)', 2, '升升公寓', 4500.0, '/upload/img/13/9bd3bc8644df4f389dbd777507b71d4e.jpg', 3);
INSERT INTO `delicacy` VALUES (14, '阿卡AK·Lab洋风韩餐(街道口店)', 4, '青龙山地铁小镇', 4500.0, '/upload/img/14/eab48649a6a64f5ebdc792919cadd314.jpg', 4);

-- ----------------------------
-- Table structure for delicacy_user
-- ----------------------------
DROP TABLE IF EXISTS `delicacy_user`;
CREATE TABLE `delicacy_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `d_id` int NOT NULL COMMENT '美食小店主键id',
  `u_id` int NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delicacy_user
-- ----------------------------
INSERT INTO `delicacy_user` VALUES (3, 13, 2);
INSERT INTO `delicacy_user` VALUES (4, 12, 2);
INSERT INTO `delicacy_user` VALUES (10, 14, 1);
INSERT INTO `delicacy_user` VALUES (12, 13, 1);
INSERT INTO `delicacy_user` VALUES (14, 11, 1);

-- ----------------------------
-- Table structure for food_type
-- ----------------------------
DROP TABLE IF EXISTS `food_type`;
CREATE TABLE `food_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜品分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_type
-- ----------------------------
INSERT INTO `food_type` VALUES (0, '其他美食');
INSERT INTO `food_type` VALUES (1, '小吃快餐');
INSERT INTO `food_type` VALUES (2, '自助餐');
INSERT INTO `food_type` VALUES (3, '火锅');
INSERT INTO `food_type` VALUES (4, '湖北菜');
INSERT INTO `food_type` VALUES (5, '面包甜点');
INSERT INTO `food_type` VALUES (6, '烧烤烤串');
INSERT INTO `food_type` VALUES (7, '粉面馆');

-- ----------------------------
-- Table structure for recommendation
-- ----------------------------
DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '美食名',
  `price` decimal(10, 1) NULL DEFAULT NULL COMMENT '价格',
  `img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径（相对）',
  `d_id` int NOT NULL COMMENT '美食小店主键id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommendation
-- ----------------------------

-- ----------------------------
-- Table structure for yy_user
-- ----------------------------
DROP TABLE IF EXISTS `yy_user`;
CREATE TABLE `yy_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号，唯一',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yy_user
-- ----------------------------
INSERT INTO `yy_user` VALUES (1, 'yy', '123');

SET FOREIGN_KEY_CHECKS = 1;
