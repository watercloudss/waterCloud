/*
 Navicat Premium Data Transfer

 Source Server         : dock-mysql8
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 127.0.0.1:3306
 Source Schema         : cloud-flash

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 30/12/2021 16:30:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flash_activity
-- ----------------------------
DROP TABLE IF EXISTS `flash_activity`;
CREATE TABLE `flash_activity`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `activity_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ç§’æ€æ´»åŠ¨åç§°',
  `activity_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'ç§’æ€æ´»åŠ¨æè¿°',
  `start_time` datetime NOT NULL COMMENT 'ç§’æ€æ´»åŠ¨å¼€å§‹æ—¶é—´',
  `end_time` datetime NOT NULL COMMENT 'ç§’æ€æ´»åŠ¨ç»“æŸæ—¶é—´',
  `status` int NOT NULL DEFAULT 0 COMMENT 'ç§’æ€æ´»åŠ¨çŠ¶æ€',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'æ›´æ–°æ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `flash_activity_end_time_idx`(`end_time`) USING BTREE,
  INDEX `flash_activity_start_time_idx`(`start_time`) USING BTREE,
  INDEX `flash_activity_status_idx`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'ç§’æ€æ´»åŠ¨è¡¨' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flash_activity
-- ----------------------------
INSERT INTO `flash_activity` VALUES (1, 'jd-元旦活动', 'jd1.1秒杀全面开启活动', '2021-12-13 20:00:00', '2022-01-03 23:59:59', 1, '2021-12-15 21:59:18', '2021-12-14 21:32:48');
INSERT INTO `flash_activity` VALUES (2, '元旦活动-11214213256', 'jd1.1秒杀全面开启活动', '2021-12-30 00:00:00', '2022-01-05 23:59:59', 0, '2021-12-14 21:32:55', '2021-12-14 21:32:55');
INSERT INTO `flash_activity` VALUES (3, '元旦活动-21214213259', 'jd1.1秒杀全面开启活动', '2021-12-30 00:00:00', '2022-01-05 23:59:59', 0, '2021-12-14 21:32:58', '2021-12-14 21:32:58');
INSERT INTO `flash_activity` VALUES (4, '元旦活动-31214213302', 'jd1.1秒杀全面开启活动', '2021-12-30 00:00:00', '2022-01-05 23:59:59', 0, '2021-12-14 21:33:01', '2021-12-14 21:33:01');

-- ----------------------------
-- Table structure for flash_item
-- ----------------------------
DROP TABLE IF EXISTS `flash_item`;
CREATE TABLE `flash_item`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `item_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ç§’æ€å“åç§°æ ‡é¢˜',
  `item_sub_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ç§’æ€å“å‰¯æ ‡é¢˜',
  `item_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'ç§’æ€å“ä»‹ç»å¯Œæ–‡æœ¬æ–‡æ¡ˆ',
  `initial_stock` int NOT NULL DEFAULT 0 COMMENT 'ç§’æ€å“åˆå§‹åº“å­˜',
  `available_stock` int NOT NULL DEFAULT 0 COMMENT 'ç§’æ€å“å¯ç”¨åº“å­˜',
  `stock_warm_up` int NOT NULL DEFAULT 0 COMMENT 'ç§’æ€å“åº“å­˜æ˜¯å¦å·²ç»é¢„çƒ­',
  `original_price` bigint NOT NULL COMMENT 'ç§’æ€å“åŽŸä»·',
  `flash_price` bigint NOT NULL COMMENT 'ç§’æ€ä»·',
  `start_time` datetime NOT NULL COMMENT 'ç§’æ€å¼€å§‹æ—¶é—´',
  `end_time` datetime NOT NULL COMMENT 'ç§’æ€ç»“æŸæ—¶é—´',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'ç§’æ€å¯é…è§„åˆ™ï¼ŒJSONæ ¼å¼',
  `status` int NOT NULL DEFAULT 0 COMMENT 'ç§’æ€å“çŠ¶æ€',
  `activity_id` bigint NOT NULL COMMENT 'æ‰€å±žæ´»åŠ¨id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'æ›´æ–°æ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `flash_item_end_time_idx`(`end_time`) USING BTREE,
  INDEX `flash_item_start_time_idx`(`start_time`) USING BTREE,
  INDEX `flash_item_status_idx`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'ç§’æ€å“' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flash_item
-- ----------------------------
INSERT INTO `flash_item` VALUES (1, 'iPhone11', 'iPhone超级秒杀', '元旦购物节，iPhone秒杀活动', 23, 13, 1, 120000, 6900, '2021-12-13 00:00:00', '2022-01-03 23:59:59', NULL, 1, 1, '2021-12-19 02:01:15', '2021-12-14 22:02:52');
INSERT INTO `flash_item` VALUES (2, 'iPhone12', 'iPhone超级秒杀', '元旦购物节，iPhone秒杀活动', 23, 23, 1, 120000, 6900, '2021-12-30 00:00:00', '2022-01-03 23:59:59', NULL, 1, 1, '2021-12-14 22:38:04', '2021-12-14 22:02:57');
INSERT INTO `flash_item` VALUES (3, 'iPhone13', 'iPhone超级秒杀', '元旦购物节，iPhone秒杀活动', 23, 23, 1, 120000, 6900, '2021-12-30 00:00:00', '2022-01-03 23:59:59', NULL, 1, 1, '2021-12-14 22:38:17', '2021-12-14 22:02:59');

-- ----------------------------
-- Table structure for flash_order
-- ----------------------------
DROP TABLE IF EXISTS `flash_order`;
CREATE TABLE `flash_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®',
  `item_id` bigint NOT NULL COMMENT 'ç§’æ€å“ID',
  `activity_id` bigint NOT NULL COMMENT 'ç§’æ€æ´»åŠ¨ID',
  `item_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ç§’æ€å“åç§°æ ‡é¢˜',
  `flash_price` bigint NOT NULL COMMENT 'ç§’æ€ä»·',
  `quantity` int NOT NULL COMMENT 'æ•°é‡',
  `total_amount` bigint NOT NULL COMMENT 'æ€»ä»·æ ¼',
  `status` int NOT NULL DEFAULT 0 COMMENT 'è®¢å•çŠ¶æ€',
  `user_id` bigint NOT NULL COMMENT 'ç”¨æˆ·ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'æ›´æ–°æ—¶é—´',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `flash_order_id_uk`(`id`) USING BTREE,
  INDEX `flash_order_user_id_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'ç§’æ€è®¢å•è¡¨' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flash_order
-- ----------------------------

-- ----------------------------
-- Table structure for shedlock
-- ----------------------------
DROP TABLE IF EXISTS `shedlock`;
CREATE TABLE `shedlock`  (
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lock_until` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `locked_at` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `locked_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'åˆ†å¸ƒå¼è°ƒåº¦é”' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shedlock
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
