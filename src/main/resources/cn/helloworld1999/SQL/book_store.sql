/*
 Navicat Premium Dump SQL

 Source Server         : safe
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : localhost:3306
 Source Schema         : book_store

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 13/07/2024 09:30:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '医学伦理学', 20, 20);
INSERT INTO `book` VALUES (2, '道家文化与中医学', 15.5, 66);
INSERT INTO `book` VALUES (3, '钢铁是怎样炼成的', 15, 484);
INSERT INTO `book` VALUES (4, '消费社会', 20, 16);
INSERT INTO `book` VALUES (5, '永久记录', 30, 20);
INSERT INTO `book` VALUES (6, '真名实姓', 25, 45);
INSERT INTO `book` VALUES (7, '模式识别', 29, 47);
INSERT INTO `book` VALUES (8, '机器学习', 66, 42);
INSERT INTO `book` VALUES (9, '区块链', 25, 100);
INSERT INTO `book` VALUES (10, '计算机图形学', 56, 29);
INSERT INTO `book` VALUES (11, '概率论', 33, 50);
INSERT INTO `book` VALUES (12, '线性代数', 65, 54);
INSERT INTO `book` VALUES (13, '计算机视觉', 55.12, 54);
INSERT INTO `book` VALUES (14, '卷积神经网络', 12.23, 49);
INSERT INTO `book` VALUES (15, 'TensFlow', 45.5, 60);
INSERT INTO `book` VALUES (16, '计算机组成原理', 65.5, 49);
INSERT INTO `book` VALUES (17, '计算机网络', 44.85, 48);
INSERT INTO `book` VALUES (18, '自顶向下的IO', 65.75, 50);
INSERT INTO `book` VALUES (19, '数据结构与算法', 15.5, 64);
INSERT INTO `book` VALUES (20, '信号与系统', 45.45, 66);
INSERT INTO `book` VALUES (21, '模糊控制', 18.8, 50);
INSERT INTO `book` VALUES (22, 'python', 14.5, 22);
INSERT INTO `book` VALUES (23, 'C++', 25.4, 12);
INSERT INTO `book` VALUES (24, 'CUDA', 14.5, 20);
INSERT INTO `book` VALUES (25, 'pytorch', 41.14, 25);
INSERT INTO `book` VALUES (26, 'pandas', 14.51, 60);
INSERT INTO `book` VALUES (27, 'MySQL', 14.6, 15);
INSERT INTO `book` VALUES (28, '人工智能导论', 12, 66);
INSERT INTO `book` VALUES (29, '机器学习进阶', 41.55, 45);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `order_date` date NULL DEFAULT NULL,
  `order_sum_price` double NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_order_user`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 172 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (153, 2, '2024-07-11', 0, '测试信息', '已完成');
INSERT INTO `order` VALUES (154, 2, '2024-07-11', 0, NULL, '已完成');
INSERT INTO `order` VALUES (155, 2, '2024-07-11', 55.12, NULL, '已完成');
INSERT INTO `order` VALUES (156, 2, '2024-07-11', 0, NULL, '已完成');
INSERT INTO `order` VALUES (157, 2, '2024-07-11', 29, NULL, '已完成');
INSERT INTO `order` VALUES (158, 2, '2024-07-11', 29, NULL, '已完成');
INSERT INTO `order` VALUES (159, 2, '2024-07-11', 0, NULL, '已完成');
INSERT INTO `order` VALUES (160, 2, '2024-07-11', 0, NULL, '已完成');
INSERT INTO `order` VALUES (161, 31, '2024-07-12', 15.5, NULL, '已完成');
INSERT INTO `order` VALUES (162, 2, '2024-07-12', 132, NULL, '已完成');
INSERT INTO `order` VALUES (163, 2, '2024-07-12', 0, NULL, '已完成');
INSERT INTO `order` VALUES (164, 2, '2024-07-12', 15, NULL, '已完成');
INSERT INTO `order` VALUES (165, 2, '2024-07-12', 30, NULL, '已完成');
INSERT INTO `order` VALUES (166, 2, '2024-07-12', 55.12, NULL, '已完成');
INSERT INTO `order` VALUES (167, 2, '2024-07-12', 15.5, NULL, '已完成');
INSERT INTO `order` VALUES (168, 2, '2024-07-12', 15, NULL, '已完成');
INSERT INTO `order` VALUES (169, 2, '2024-07-12', 59, NULL, '已完成');
INSERT INTO `order` VALUES (170, NULL, '2024-07-12', 0, NULL, '待付款');
INSERT INTO `order` VALUES (171, NULL, '2024-07-12', 0, NULL, '待付款');

-- ----------------------------
-- Table structure for order_subpage
-- ----------------------------
DROP TABLE IF EXISTS `order_subpage`;
CREATE TABLE `order_subpage`  (
  `order_subpage_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price_one` double NULL DEFAULT NULL,
  `price_sum` double NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_id` int NOT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`order_subpage_id`) USING BTREE,
  INDEX `fk_os_or`(`order_id` ASC) USING BTREE,
  INDEX `fk_os_bk`(`book_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_subpage
-- ----------------------------
INSERT INTO `order_subpage` VALUES (45, 82, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (46, 82, '永久记录', 1, '已生成订单', 30, 30, NULL, 5, 31);
INSERT INTO `order_subpage` VALUES (47, 83, '消费社会', 1, '已生成订单', 20, 20, NULL, 4, 31);
INSERT INTO `order_subpage` VALUES (48, 84, '真名实姓', 1, '已生成订单', 25, 25, NULL, 6, 31);
INSERT INTO `order_subpage` VALUES (49, 84, '模式识别', 1, '已生成订单', 29, 29, NULL, 7, 31);
INSERT INTO `order_subpage` VALUES (50, 85, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (51, 86, '永久记录', 1, '已生成订单', 30, 30, NULL, 5, 31);
INSERT INTO `order_subpage` VALUES (52, 87, '卷积神经网络', 1, '已生成订单', 12.23, 12.23, NULL, 14, 31);
INSERT INTO `order_subpage` VALUES (53, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (54, 88, '永久记录', 1, '已生成订单', 30, 30, NULL, 5, 31);
INSERT INTO `order_subpage` VALUES (55, NULL, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (56, 89, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (57, NULL, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (58, NULL, '道家文化与中医学', 1, '已生成订单', 15.5, 15.5, NULL, 2, 31);
INSERT INTO `order_subpage` VALUES (59, NULL, '道家文化与中医学', 1, '已生成订单', 15.5, 15.5, NULL, 2, 31);
INSERT INTO `order_subpage` VALUES (60, NULL, '区块链', 1, '已生成订单', 25, 25, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (61, NULL, '区块链', 1, '已生成订单', 25, 25, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (62, 91, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (63, 92, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (64, 93, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (65, 94, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (66, 95, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (67, 95, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (68, NULL, '医学伦理学', 1, '加入购物车', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (69, 96, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (70, 97, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (71, 99, '区块链', 0, '已生成订单', 25, 0, NULL, 9, 31);
INSERT INTO `order_subpage` VALUES (72, 99, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (73, 100, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (74, 101, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (75, 102, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (76, 103, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (77, 104, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (78, 105, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (79, 106, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (80, 107, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (81, 108, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (82, 109, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (83, 110, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (84, 111, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (85, 112, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (86, 113, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (87, 114, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (88, 115, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (89, 116, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (90, 117, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (91, 118, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (92, 119, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 31);
INSERT INTO `order_subpage` VALUES (93, NULL, '计算机图形学', 10, '加入购物车', 56, 560, NULL, 10, 31);
INSERT INTO `order_subpage` VALUES (94, 120, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (95, 121, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (96, 122, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (97, 123, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (98, 124, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 31);
INSERT INTO `order_subpage` VALUES (99, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (100, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (101, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (102, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (103, NULL, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (104, 128, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (105, 130, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (106, 130, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (107, 130, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (108, 129, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (109, 131, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (110, 132, '道家文化与中医学', -1, '已生成订单', 15.5, 15.5, NULL, 2, 2);
INSERT INTO `order_subpage` VALUES (111, 133, '道家文化与中医学', -49, '已生成订单', 15.5, 15.5, NULL, 2, 2);
INSERT INTO `order_subpage` VALUES (112, 147, '永久记录', 0, '已生成订单', 30, 0, NULL, 5, 2);
INSERT INTO `order_subpage` VALUES (113, 145, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (114, 145, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (115, 145, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (116, 145, '计算机视觉', 1, '已生成订单', 55.12, 55.12, NULL, 13, 2);
INSERT INTO `order_subpage` VALUES (117, 145, '计算机组成原理', 1, '已生成订单', 65.5, 65.5, NULL, 16, 2);
INSERT INTO `order_subpage` VALUES (118, 145, '计算机网络', 1, '已生成订单', 44.85, 44.85, NULL, 17, 2);
INSERT INTO `order_subpage` VALUES (119, 148, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (120, 148, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (121, 146, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (122, 147, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (123, 148, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (124, 148, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (125, 148, '计算机图形学', 1, '已生成订单', 56, 56, NULL, 10, 2);
INSERT INTO `order_subpage` VALUES (126, 148, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (127, 151, '计算机网络', 1, '已生成订单', 44.85, 44.85, NULL, 17, 2);
INSERT INTO `order_subpage` VALUES (128, 149, '医学伦理学', 1, '已生成订单', 20, 20, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (129, 150, '道家文化与中医学', 1, '已生成订单', 15.5, 15.5, NULL, 2, 2);
INSERT INTO `order_subpage` VALUES (130, 151, '真名实姓', 1, '已生成订单', 25, 25, NULL, 6, 2);
INSERT INTO `order_subpage` VALUES (131, 152, '线性代数', 1, '已生成订单', 65, 65, NULL, 12, 2);
INSERT INTO `order_subpage` VALUES (132, 155, '计算机视觉', 1, '已生成订单', 55.12, 55.12, NULL, 13, 2);
INSERT INTO `order_subpage` VALUES (133, 156, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (134, 159, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (135, 155, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (136, 157, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (137, 157, '模式识别', 1, '已生成订单', 29, 29, NULL, 7, 2);
INSERT INTO `order_subpage` VALUES (138, 158, '模式识别', 1, '已生成订单', 29, 29, NULL, 7, 2);
INSERT INTO `order_subpage` VALUES (139, 160, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (140, 162, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (141, 164, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (142, 164, '医学伦理学', 0, '已生成订单', 20, 0, NULL, 1, 2);
INSERT INTO `order_subpage` VALUES (143, 161, '道家文化与中医学', 1, '已生成订单', 15.5, 15.5, NULL, 2, 31);
INSERT INTO `order_subpage` VALUES (144, 162, '机器学习', 1, '已生成订单', 66, 66, NULL, 8, 2);
INSERT INTO `order_subpage` VALUES (145, 162, '机器学习', 1, '已生成订单', 66, 66, NULL, 8, 2);
INSERT INTO `order_subpage` VALUES (146, 164, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (147, 165, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (148, 165, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (149, 166, '计算机视觉', 1, '已生成订单', 55.12, 55.12, NULL, 13, 2);
INSERT INTO `order_subpage` VALUES (150, 169, '模式识别', 1, '已生成订单', 29, 29, NULL, 7, 2);
INSERT INTO `order_subpage` VALUES (151, 167, '道家文化与中医学', 1, '已生成订单', 15.5, 15.5, NULL, 2, 2);
INSERT INTO `order_subpage` VALUES (152, 168, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (153, 169, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (154, 169, '钢铁是怎样炼成的', 1, '已生成订单', 15, 15, NULL, 3, 2);
INSERT INTO `order_subpage` VALUES (155, NULL, '医学伦理学', 1, '加入购物车', 20, 20, NULL, 1, NULL);
INSERT INTO `order_subpage` VALUES (156, NULL, '医学伦理学', 1, '待生成订单', 20, 20, NULL, 1, NULL);
INSERT INTO `order_subpage` VALUES (157, NULL, '医学伦理学', 1, '待生成订单', 20, 20, NULL, 1, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 2, '客户');
INSERT INTO `role` VALUES (2, 2, '管理员');
INSERT INTO `role` VALUES (3, 1, '商家');
INSERT INTO `role` VALUES (5, 4, '客户');
INSERT INTO `role` VALUES (6, 31, '客户');
INSERT INTO `role` VALUES (7, 2, '商家');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '这个user_id是给系统内表间查找用的；\r\n因为据说是可以实现B+树，效率会吊打UUID遍历',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` double NULL DEFAULT NULL,
  `role` int NULL DEFAULT 0 COMMENT '这个是他拥有的角色号码，这个号码和role的关系是一对多',
  `user_phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `role`(`role` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'yokoyrena', 'ydZaQ6SaMI', 59, 1, '13938033070');
INSERT INTO `user` VALUES (2, 'admin', 'admin', 1446.38, 1, '17057036843');
INSERT INTO `user` VALUES (3, 'iairi6', 'kOEWlDXJu6', 71.89, 1, '18179961864');
INSERT INTO `user` VALUES (4, 'lux', 'tHWWFVuaVI', 767.5, 2, '15600886765');
INSERT INTO `user` VALUES (5, 'mo19', 'qusFt6QhOe', 507.72, 2, '17703942667');
INSERT INTO `user` VALUES (6, 'kwokwing511', 'SjHXdwFkoe', 649.4, 1, '18928499163');
INSERT INTO `user` VALUES (7, 'qiu318', 'DsZx6oO4dP', 870.55, 2, '15315946510');
INSERT INTO `user` VALUES (8, 'miutakeu2', 'M6Uj9s7kER', 388.39, 0, '111222');
INSERT INTO `user` VALUES (9, 'vasqfranc', '1vh0k67GXr', 844.82, 0, '17080013504');
INSERT INTO `user` VALUES (10, 'xiuli', 'KGOYjRdWDP', 220.89, 1, '18867515888');
INSERT INTO `user` VALUES (31, 'hahaha2333', '123456', 7111.72, 0, '19961412333');
INSERT INTO `user` VALUES (39, '123', '123', 0, 0, '123');

SET FOREIGN_KEY_CHECKS = 1;
