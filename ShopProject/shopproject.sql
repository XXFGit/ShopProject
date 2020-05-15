/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : shopproject

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 15/05/2020 17:14:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_info
-- ----------------------------
DROP TABLE IF EXISTS `address_info`;
CREATE TABLE `address_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?名',
  `cellphone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机??',
  `adress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `is_default` tinyint(2) NULL DEFAULT NULL COMMENT '是否默?地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改??',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` smallint(2) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1223132313', 'admin2', '123456', 0, '2495183322@qq.com', '2020-04-10', '1', '超级管理员', 1, '2020-04-10 22:34:55', '2020-04-21 09:57:34');
INSERT INTO `admin_info` VALUES ('123132', 'admin', '123456', 1, '2495183322@qq.com', '2020-04-10', '1', '超级管理员', 1, '2020-04-10 22:34:55', '2020-04-21 09:57:34');
INSERT INTO `admin_info` VALUES ('1231323132', 'xxf', '123456', 0, '2495183322@qq.com', '2020-04-10', '1', '超级管理员', 1, '2020-04-10 22:34:55', '2020-04-21 09:57:34');

-- ----------------------------
-- Table structure for admin_resources
-- ----------------------------
DROP TABLE IF EXISTS `admin_resources`;
CREATE TABLE `admin_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_resources
-- ----------------------------
INSERT INTO `admin_resources` VALUES (1, '首页', '1', '/index.do', '', 0, 1, '', '2020-04-10 22:57:20', '2020-04-10 22:57:23');
INSERT INTO `admin_resources` VALUES (2, '权限管理', '1', '', '', 0, 2, '', '2020-04-10 22:57:20', '2020-04-10 22:57:23');
INSERT INTO `admin_resources` VALUES (3, '管理员管理', '1', '/admin/index.do', '', 2, 1, '', '2020-04-10 22:57:20', '2020-04-10 22:57:23');
INSERT INTO `admin_resources` VALUES (4, '角色管理', '1', '/role/index.do', 'role:list', 2, 2, '', '2020-04-10 22:57:20', '2020-04-16 17:27:31');
INSERT INTO `admin_resources` VALUES (5, '菜单管理', '1', '/menu/index.do', '', 2, 3, '', '2020-04-10 22:57:20', '2020-04-10 22:57:23');
INSERT INTO `admin_resources` VALUES (6, '用户管理', '1', '/user/index.do', NULL, 0, 4, NULL, '2020-04-13 22:39:05', '2020-04-13 22:39:05');
INSERT INTO `admin_resources` VALUES (11, '用户列表', '1', '/user/index.do', 'user:list', 6, 1, NULL, '2020-04-13 22:50:30', '2020-05-08 14:59:21');
INSERT INTO `admin_resources` VALUES (12, '新增', '2', '', 'sys:user:add', 11, 1, NULL, '2020-04-13 22:51:46', '2020-04-13 22:52:00');
INSERT INTO `admin_resources` VALUES (13, '新增', '2', '', 'role:save', 4, NULL, NULL, '2020-04-16 17:26:36', '2020-04-16 17:26:36');
INSERT INTO `admin_resources` VALUES (14, '修改', '2', '', 'role:save', 4, NULL, NULL, '2020-04-16 17:26:55', '2020-04-16 17:26:55');
INSERT INTO `admin_resources` VALUES (15, '删除', '2', '', 'role:del', 4, NULL, NULL, '2020-04-16 17:27:11', '2020-04-16 17:27:11');
INSERT INTO `admin_resources` VALUES (16, '商品管理', '1', '分类管理', '', 0, 3, NULL, '2020-05-08 14:40:37', '2020-05-08 14:41:03');
INSERT INTO `admin_resources` VALUES (17, '分类管理', '1', '/type/index.do', 'type:list', 16, NULL, NULL, '2020-05-08 14:42:04', '2020-05-08 14:43:36');
INSERT INTO `admin_resources` VALUES (18, '商品管理', '1', '/item/index.do', 'item:list', 16, NULL, NULL, '2020-05-08 14:42:56', '2020-05-08 14:42:56');
INSERT INTO `admin_resources` VALUES (19, '订单管理', '1', '', '', 0, NULL, NULL, '2020-05-08 14:46:09', '2020-05-08 14:46:09');
INSERT INTO `admin_resources` VALUES (20, '订单列表', '1', '/order/index.do', 'order:list', 19, NULL, NULL, '2020-05-08 14:46:42', '2020-05-08 14:47:23');
INSERT INTO `admin_resources` VALUES (21, '评论管理', '1', '', '', 0, NULL, NULL, '2020-05-08 14:48:22', '2020-05-08 14:48:22');
INSERT INTO `admin_resources` VALUES (22, '评论列表', '1', '/comment/index.do', 'comment:list', 21, NULL, NULL, '2020-05-08 14:49:06', '2020-05-08 14:49:06');
INSERT INTO `admin_resources` VALUES (23, '用户收货地址列表', '1', '/address/index.do', 'address:list', 6, NULL, NULL, '2020-05-08 14:50:30', '2020-05-08 14:50:30');
INSERT INTO `admin_resources` VALUES (24, '系统管理', '1', '', '', 0, NULL, NULL, '2020-05-08 14:52:51', '2020-05-08 14:52:51');
INSERT INTO `admin_resources` VALUES (25, '基本设置', '1', '/basic/index.do', 'basic:list', 24, NULL, NULL, '2020-05-08 14:54:12', '2020-05-08 14:54:12');
INSERT INTO `admin_resources` VALUES (26, '首页轮播图管理', '1', '/indeximg/index.do', 'indeximg:list', 24, NULL, NULL, '2020-05-08 14:53:33', '2020-05-08 14:53:33');
INSERT INTO `admin_resources` VALUES (27, '系统日志', '1', '/syslog/index.do', 'syslog:list', 24, NULL, NULL, '2020-05-08 14:54:53', '2020-05-08 14:54:53');

-- ----------------------------
-- Table structure for admin_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_resources`;
CREATE TABLE `admin_role_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `resources_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 295 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_resources
-- ----------------------------
INSERT INTO `admin_role_resources` VALUES (272, 1, 1, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (273, 1, 2, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (274, 1, 3, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (275, 1, 4, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (276, 1, 13, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (277, 1, 14, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (278, 1, 15, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (279, 1, 5, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (280, 1, 6, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (281, 1, 11, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (282, 1, 12, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (283, 1, 23, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (284, 1, 16, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (285, 1, 17, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (286, 1, 18, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (287, 1, 19, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (288, 1, 20, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (289, 1, 21, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (290, 1, 22, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (291, 1, 24, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (292, 1, 25, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (293, 1, 26, '2020-05-08 14:59:35', '2020-05-08 14:59:35');
INSERT INTO `admin_role_resources` VALUES (294, 1, 27, '2020-05-08 14:59:35', '2020-05-08 14:59:35');

-- ----------------------------
-- Table structure for cart_info
-- ----------------------------
DROP TABLE IF EXISTS `cart_info`;
CREATE TABLE `cart_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主?id',
  `item_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名?',
  `price` float NULL DEFAULT NULL COMMENT '商品价格',
  `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品?介概述',
  `item_count` int(11) NULL DEFAULT NULL COMMENT '商品?量',
  `total_amount` float NULL DEFAULT NULL COMMENT '?金?',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '??（1-已?算，2-??算）',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?iD',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?名?',
  `user_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?手机??',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `spare` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段1',
  `spare2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段2'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?名',
  `item_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名?',
  `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品?介概述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for index_img_info
-- ----------------------------
DROP TABLE IF EXISTS `index_img_info`;
CREATE TABLE `index_img_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '??',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `img_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?片地址',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '??',
  `sort_order` int(11) NULL DEFAULT NULL COMMENT '?序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改??',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_info
-- ----------------------------
DROP TABLE IF EXISTS `item_info`;
CREATE TABLE `item_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主?id',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名?',
  `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?介概述',
  `item_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品??ID',
  `item_type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品??名?',
  `price` float NULL DEFAULT NULL COMMENT '商品价格',
  `stock` int(11) NULL DEFAULT NULL COMMENT '?存?量',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品?情（html存?）',
  `is_remm` tinyint(2) NULL DEFAULT NULL COMMENT '是否上架',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新??',
  `spare_1` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段1',
  `spare_2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for item_type
-- ----------------------------
DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分?名?',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父ID',
  `sort_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `stopped` tinyint(2) NULL DEFAULT NULL COMMENT '是否?用',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改??',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主?id',
  `order_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '???',
  `item_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品id',
  `item_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名?',
  `price` float NULL DEFAULT NULL COMMENT '商品价格',
  `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品?介概述',
  `item_count` int(11) NULL DEFAULT NULL COMMENT '商品?量',
  `total_amount` float NULL DEFAULT NULL COMMENT '?金?',
  `freight` float NULL DEFAULT NULL COMMENT '??',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '??（1-待支付，2-已支付，3-取消，4-超?）',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?iD',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?名?',
  `user_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?手机??',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收?地址',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付??',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `spare` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段1',
  `spare2` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?用字段2'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理001666', '2020-04-10 22:36:10', '2020-04-21 15:47:33');
INSERT INTO `role` VALUES (2, '平台管理员', '发顺丰', '2020-04-13 22:47:00', '2020-04-13 22:47:00');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主?id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用?名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密?',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '性?',
  `age` int(11) NULL DEFAULT NULL COMMENT '年?',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?箱',
  `headimg` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '?像',
  `openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方登?openid',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '??',
  `create_time` datetime NULL DEFAULT NULL COMMENT '?建??',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新??',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'xxf', '123456', 1, 20, '17620937694', '2495183323@qq.com', NULL, NULL, 1, '2020-05-08 15:08:28', '2020-05-08 15:08:30');

SET FOREIGN_KEY_CHECKS = 1;
