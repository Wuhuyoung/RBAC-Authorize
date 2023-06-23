/*
 Navicat Premium Data Transfer

 Source Server         : mysql_localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : 127.0.0.1:3306
 Source Schema         : authorize

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 23/06/2023 18:22:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NULL DEFAULT 0,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_menu` tinyint NULL DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 0,
  `is_deleted` tinyint NULL DEFAULT 0,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modifeld` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 132 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, NULL, NULL, 'fa fa-align-justify', '业务管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (2, 1, 'system:main', 'page/welcome.html', 'fa fa-home', '主页', 0, '_self', 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (3, 1, '', '', 'fa fa-user', '用户管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (4, 3, '', 'page/user-table.html', 'fa fa-user-o', '用户', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (32, 0, '', '', 'fa fa-gears', '系统设置', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (61, 32, '', '', 'fa fa-gears', '系统管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (62, 61, 'system:get', 'page/setting.html', 'fa fa-gear', '系统设置', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (69, 62, 'system:update', '', 'fa fa-gear', '修改系统设置', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (72, 62, 'system:clearCache', '', 'fa fa-trash', '清理缓存', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (73, 32, '', '', 'fa fa-shield', '权限管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (74, 73, 'permission:list', 'page/permission.html', 'fa fa-shield', '权限', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (75, 74, 'permission:add', '', 'fa fa-save', '添加权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (76, 74, 'permission:update', '', 'fa fa-gear', '修改权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (77, 74, 'permission:delete', '', 'fa fa-remove', '删除权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (78, 74, 'permission:treeSelect', '', 'fa fa-list', '树形下拉选择框', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (79, 74, 'permission:treeList', '', 'fa fa-tree', '树形', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (80, 73, 'role:list', 'page/role.html', 'fa fa-user-circle', '角色管理', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (81, 80, 'role:add', '', 'fa fa-save', '添加角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (82, 80, 'role:update', '', 'fa fa-gear', '修改角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (84, 80, 'role:delete', '', 'fa fa-remove', '删除角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (85, 80, 'role:authority', '', 'fa fa-user-secret', '分配权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (86, 80, 'role:getPermission', '', 'fa fa-info', '获取角色权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (87, 89, 'role:getRole', '', 'fa fa-info', '获取用户角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (88, 89, 'role:initRole', '', 'fa fa-user-o', '获取角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (89, 73, 'user:list', 'page/user-role.html', 'fa fa-user-o', '分配角色', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (90, 4, 'user:add', '', 'fa fa-save', '添加用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (91, 89, 'user:assignRole', '', 'fa fa-info', '分配用户角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (92, 4, 'user:update', '', 'fa fa-gear', '编辑用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (93, 4, 'user:delete', '', 'fa fa-remove', '删除用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (104, 2, 'system:echarts', '', 'fa fa-info', 'echarts图', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (107, 4, 'user:list', '', 'fa fa-list', '用户列表', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (126, 3, '', '', 'fa fa-adn', '用户权限管理', -1, NULL, 0, 1, '2023-01-05 10:01:01', NULL);
INSERT INTO `permission` VALUES (127, 1, '', '', 'fa ', '123', -1, NULL, 0, 1, '2023-01-05 10:23:46', NULL);
INSERT INTO `permission` VALUES (128, 2, '', '', 'fa fa-address-book', '12345', 0, NULL, 0, 1, '2023-01-05 10:24:38', NULL);
INSERT INTO `permission` VALUES (129, 128, '', '', 'fa fa-500px', '123456', -1, NULL, 0, 1, '2023-01-05 10:26:32', NULL);
INSERT INTO `permission` VALUES (130, 3, '', '', 'fa fa-address-book', '用户分配角色', -1, NULL, 0, 1, '2023-01-05 12:46:18', NULL);
INSERT INTO `permission` VALUES (131, 2, '', '', 'fa ', '调整', -1, NULL, 0, 0, '2023-01-05 13:10:36', NULL);
INSERT INTO `permission` VALUES (132, 3, '', '', 'fa ', '用户权限修改', -1, NULL, 0, 1, '2023-01-06 10:44:03', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (6, '超级管理员', '老大');
INSERT INTO `role` VALUES (9, '工具人', 'QAQ');
INSERT INTO `role` VALUES (11, '普通管理员', '仅管理业务');
INSERT INTO `role` VALUES (12, '123', '无法管理权限');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NULL DEFAULT NULL,
  `r_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1447 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1338, 1, 11);
INSERT INTO `role_permission` VALUES (1339, 3, 11);
INSERT INTO `role_permission` VALUES (1340, 4, 11);
INSERT INTO `role_permission` VALUES (1341, 90, 11);
INSERT INTO `role_permission` VALUES (1342, 92, 11);
INSERT INTO `role_permission` VALUES (1343, 93, 11);
INSERT INTO `role_permission` VALUES (1344, 107, 11);
INSERT INTO `role_permission` VALUES (1376, 1, 6);
INSERT INTO `role_permission` VALUES (1377, 2, 6);
INSERT INTO `role_permission` VALUES (1378, 104, 6);
INSERT INTO `role_permission` VALUES (1379, 131, 6);
INSERT INTO `role_permission` VALUES (1380, 32, 6);
INSERT INTO `role_permission` VALUES (1381, 61, 6);
INSERT INTO `role_permission` VALUES (1382, 62, 6);
INSERT INTO `role_permission` VALUES (1383, 69, 6);
INSERT INTO `role_permission` VALUES (1384, 72, 6);
INSERT INTO `role_permission` VALUES (1385, 73, 6);
INSERT INTO `role_permission` VALUES (1386, 74, 6);
INSERT INTO `role_permission` VALUES (1387, 75, 6);
INSERT INTO `role_permission` VALUES (1388, 76, 6);
INSERT INTO `role_permission` VALUES (1389, 77, 6);
INSERT INTO `role_permission` VALUES (1390, 78, 6);
INSERT INTO `role_permission` VALUES (1391, 79, 6);
INSERT INTO `role_permission` VALUES (1392, 80, 6);
INSERT INTO `role_permission` VALUES (1393, 81, 6);
INSERT INTO `role_permission` VALUES (1394, 82, 6);
INSERT INTO `role_permission` VALUES (1395, 84, 6);
INSERT INTO `role_permission` VALUES (1396, 85, 6);
INSERT INTO `role_permission` VALUES (1397, 86, 6);
INSERT INTO `role_permission` VALUES (1398, 89, 6);
INSERT INTO `role_permission` VALUES (1399, 87, 6);
INSERT INTO `role_permission` VALUES (1400, 88, 6);
INSERT INTO `role_permission` VALUES (1401, 91, 6);
INSERT INTO `role_permission` VALUES (1412, 32, 12);
INSERT INTO `role_permission` VALUES (1413, 73, 12);
INSERT INTO `role_permission` VALUES (1414, 74, 12);
INSERT INTO `role_permission` VALUES (1415, 75, 12);
INSERT INTO `role_permission` VALUES (1416, 76, 12);
INSERT INTO `role_permission` VALUES (1417, 77, 12);
INSERT INTO `role_permission` VALUES (1418, 78, 12);
INSERT INTO `role_permission` VALUES (1419, 79, 12);
INSERT INTO `role_permission` VALUES (1420, 80, 12);
INSERT INTO `role_permission` VALUES (1421, 81, 12);
INSERT INTO `role_permission` VALUES (1422, 82, 12);
INSERT INTO `role_permission` VALUES (1423, 84, 12);
INSERT INTO `role_permission` VALUES (1424, 85, 12);
INSERT INTO `role_permission` VALUES (1425, 86, 12);
INSERT INTO `role_permission` VALUES (1426, 89, 12);
INSERT INTO `role_permission` VALUES (1427, 87, 12);
INSERT INTO `role_permission` VALUES (1428, 88, 12);
INSERT INTO `role_permission` VALUES (1429, 91, 12);
INSERT INTO `role_permission` VALUES (1430, 32, 9);
INSERT INTO `role_permission` VALUES (1431, 73, 9);
INSERT INTO `role_permission` VALUES (1432, 74, 9);
INSERT INTO `role_permission` VALUES (1433, 75, 9);
INSERT INTO `role_permission` VALUES (1434, 76, 9);
INSERT INTO `role_permission` VALUES (1435, 77, 9);
INSERT INTO `role_permission` VALUES (1436, 78, 9);
INSERT INTO `role_permission` VALUES (1437, 79, 9);
INSERT INTO `role_permission` VALUES (1438, 80, 9);
INSERT INTO `role_permission` VALUES (1439, 81, 9);
INSERT INTO `role_permission` VALUES (1440, 82, 9);
INSERT INTO `role_permission` VALUES (1441, 84, 9);
INSERT INTO `role_permission` VALUES (1442, 85, 9);
INSERT INTO `role_permission` VALUES (1443, 86, 9);
INSERT INTO `role_permission` VALUES (1444, 89, 9);
INSERT INTO `role_permission` VALUES (1445, 87, 9);
INSERT INTO `role_permission` VALUES (1446, 88, 9);
INSERT INTO `role_permission` VALUES (1447, 91, 9);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'han', '123');
INSERT INTO `user` VALUES (5, '张三', '123');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `r_id` int NULL DEFAULT NULL,
  `u_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (20, 6, 1);
INSERT INTO `user_role` VALUES (27, 12, 5);
INSERT INTO `user_role` VALUES (28, 9, 5);

SET FOREIGN_KEY_CHECKS = 1;
