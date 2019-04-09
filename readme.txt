- 项目简介：
    这是一个单机版的DNF网站，因为一般的登录器注册账号没有邮件激活功能，故而练手做出这个小网站。
    
- 功能介绍：
    网站主要功能有 ：
      1.客户端下载
      2.账号注册(需要邮箱激活，并与游戏账号同步)
      3.账号登录
      4.密码找回/修改(需要邮箱接收安全密码验证)
      
- 使用技术/软件：
    1.maven 
    2.java web (使用html和java，前后端分离，没有使用框架)
    3.mysql
    
- 数据库测试文件：
--------------------------------------------------------------------------------------------------------------
    1.accounts.sql文件
--------------------------------------------------------------------------------------------------------------
    
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accounts
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts`  (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `accountname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UID`) USING BTREE,
  UNIQUE INDEX `AK_nq_accountname`(`accountname`) USING BTREE,
  UNIQUE INDEX `AK_nq_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of accounts
-- ----------------------------
INSERT INTO `accounts` VALUES (24, 'wz1024', '849685fd8236dae9185cb3abf2e9950e', '305940660@qq.com', '1', 'c0d3c09b9c7e49b00eb4bf3bd9f9c4');
INSERT INTO `accounts` VALUES (10000, 'xx123123', '4297f44b13955235245b2497399d7a93', '3059406@qq.com', '1', 'c0d3c09b9c7e49a9b00b4bf3bd9f9c4');
INSERT INTO `accounts` VALUES (10001, 'tangxuan', 'e10adc3949ba59abbe56e057f20f883e', '305940@qq.com', '1', 'c0d3c09b9c749a9b00eb4bf3bd9f9c4');
INSERT INTO `accounts` VALUES (10002, 'wz10242', '849685fd8236dae9185cb3abf2e9950e', '35940660@qq.com', '1', 'c0d3c0b9c7e49a9b00e4bf3bd9f9c4');
INSERT INTO `accounts` VALUES (10003, 'wz102488', '849685fd8236dae9185cb3abf2e9950e', '305960@qq.com', '1', 'c0d3c09b9c7e49a9b00eb4bf39f9c4');
INSERT INTO `accounts` VALUES (10004, 'wz123', 'e10adc3949ba59abbe56e057f20f883e', '30590@qq.com', '1', 'c0d3c09b9c7e49a9b00eb43bd9f9c4');
SET FOREIGN_KEY_CHECKS = 1;

------------------------------------------------------------------------------------------------------------------------------------
--********************************************************************************************************************************--
------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------
    2.charac_info.sql
-----------------------------------------------------------------------------------------------------------------------------------


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for charac_info
-- ----------------------------
DROP TABLE IF EXISTS `charac_info`;
CREATE TABLE `charac_info`  (
  `m_id` int(11) NOT NULL DEFAULT 0,
  `charac_no` int(11) NOT NULL AUTO_INCREMENT,
  `charac_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `village` tinyint(4) NOT NULL DEFAULT 1,
  `job` tinyint(4) NOT NULL DEFAULT 0,
  `lev` tinyint(4) NOT NULL DEFAULT 1,
  `exp` int(11) NOT NULL DEFAULT 0,
  `grow_type` tinyint(4) NOT NULL DEFAULT 0,
  `HP` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `maxHP` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `maxMP` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `phy_attack` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `phy_defense` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `mag_attack` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `mag_defense` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `element_resist` tinyblob NOT NULL,
  `spec_property` tinyblob NOT NULL,
  `inven_weight` int(6) NOT NULL DEFAULT 0,
  `hp_regen` smallint(6) NOT NULL DEFAULT 0,
  `mp_regen` smallint(6) NOT NULL DEFAULT 0,
  `move_speed` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `attack_speed` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `cast_speed` smallint(6) UNSIGNED NOT NULL DEFAULT 0,
  `hit_recovery` smallint(6) NOT NULL DEFAULT 0,
  `jump` smallint(6) NOT NULL DEFAULT 0,
  `charac_weight` int(11) NOT NULL DEFAULT 0,
  `fatigue` smallint(6) NOT NULL DEFAULT 0,
  `max_fatigue` smallint(6) NOT NULL DEFAULT 70,
  `premium_fatigue` smallint(6) NOT NULL DEFAULT 0,
  `max_premium_fatigue` smallint(6) NOT NULL DEFAULT 0,
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_play_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dungeon_clear_point` int(11) NOT NULL DEFAULT 0,
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `delete_flag` tinyint(4) NOT NULL DEFAULT 0,
  `guild_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `guild_right` tinyint(4) NOT NULL DEFAULT 0,
  `member_flag` tinyint(4) NOT NULL DEFAULT 0,
  `sex` tinyint(4) NOT NULL DEFAULT 1,
  `expert_job` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `skill_tree_index` tinyint(4) NOT NULL DEFAULT -1,
  `link_charac_no` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `event_charac_level` tinyint(4) NOT NULL DEFAULT 0,
  `guild_secede` tinyint(2) NOT NULL DEFAULT 0,
  `start_time` int(11) NOT NULL DEFAULT 0,
  `finish_time` int(11) NOT NULL DEFAULT 0,
  `competition_area` tinyint(2) NOT NULL DEFAULT -1,
  `competition_period` tinyint(2) NOT NULL DEFAULT -1,
  `mercenary_start_time` int(11) NOT NULL DEFAULT 0,
  `mercenary_finish_time` int(11) NOT NULL DEFAULT 0,
  `mercenary_area` tinyint(4) NOT NULL DEFAULT -1,
  `mercenary_period` tinyint(4) NOT NULL DEFAULT -1,
  PRIMARY KEY (`charac_no`) USING BTREE,
  UNIQUE INDEX `charac_name`(`charac_name`) USING BTREE,
  INDEX `charac_info_idx1`(`m_id`) USING BTREE,
  INDEX `charac_info_idx2`(`exp`) USING BTREE,
  INDEX `idx_guild_id`(`guild_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of charac_info
-- ----------------------------
INSERT INTO `charac_info` VALUES (10001, 1, 'sven', 1, 0, 85, 0, 0, 0, 39600, 22400, 4107, 4107, 3573, 3573, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 732000, 0, 2600, 8500, 8500, 7000, 7260, 4300, 680000, 0, 156, 0, 0, '2015-12-04 21:21:17', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 1, 1, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10001, 2, 'Lanaya', 1, 5, 2, 0, 0, 0, 1875, 2025, 110, 110, 100, 100, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 403000, 0, 525, 8500, 9500, 8000, 5510, 4400, 480000, 0, 156, 0, 0, '2015-12-10 20:51:40', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10002, 3, 'å‰‘ç¥ž', 1, 0, 90, 0, 17, 0, 45600, 19900, 4497, 4497, 3633, 3633, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 747000, 0, 2725, 8500, 8500, 7000, 7710, 4300, 680000, 0, 156, 0, 0, '2019-03-14 13:33:36', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10002, 4, 'çº¢ç‹—', 1, 0, 90, 0, 19, 0, 49350, 16150, 4872, 4872, 3258, 3258, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 747000, 0, 2725, 8500, 8500, 7000, 7710, 4300, 680000, 0, 156, 0, 0, '2019-03-14 13:53:40', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10002, 5, 'é¬¼æ³£', 1, 0, 90, 0, 18, 0, 38100, 27400, 3372, 3372, 4758, 4758, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 747000, 0, 4600, 8500, 8500, 7000, 6960, 4300, 680000, 0, 156, 0, 0, '2019-03-14 13:57:34', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10003, 6, 'å¤§çº¢ç‹—å•Š', 1, 0, 43, 0, 19, 0, 23500, 9100, 2287, 2287, 1613, 1613, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 606000, 0, 1550, 8500, 8500, 7000, 6770, 4300, 680000, 0, 156, 0, 1560, '2019-03-14 16:43:39', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10004, 7, 'æœˆå½±æ˜ŸåŠ«', 1, 6, 90, 0, 17, 0, 43275, 22225, 4750, 3990, 3915, 3605, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 670300, 0, 3100, 9600, 10000, 10000, 7710, 4650, 450000, 0, 156, 0, 0, '2019-03-14 17:27:39', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10003, 8, 'é¬¼æ³£å•Š', 1, 0, 1, 0, 18, 0, 1800, 1400, 75, 75, 45, 45, 0x00000000C80038FF, 0x00000000000000000000000000000000000000000000000000000000000000000000, 480000, 0, 500, 8500, 8500, 7000, 6000, 4300, 680000, 0, 156, 0, 1560, '2019-03-14 18:24:25', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10003, 13, 'æˆ˜åœºç»Ÿæ²»è€…', 1, 2, 24, 0, 20, 0, 10800, 8500, 1144, 1056, 1134, 1046, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 469000, 0, 1185, 8200, 9500, 8000, 6450, 4900, 600000, 0, 156, 0, 1560, '2019-03-29 12:44:17', '0000-00-00 00:00:00', 0, '2019-04-08 09:29:55', 1, 0, 0, 0, 1, 2, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10004, 9, 'æ‰“æš´å¯¹æ–¹ç‹—å¤´', 1, 1, 90, 0, 17, 0, 39000, 26500, 3400, 3400, 4730, 4730, 0x0000000038FFC800, 0x00000000000000000000000000000000000000000000000000000000000000000000, 670300, 0, 4600, 9100, 9500, 10000, 6960, 4700, 500000, 0, 156, 0, 0, '2019-03-15 10:34:18', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10002, 10, 'ququ', 1, 4, 90, 0, 19, 0, 39200, 26300, 4473, 3737, 4355, 3695, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 747000, 0, 4600, 7800, 9500, 10000, 5890, 5000, 780000, 0, 156, 0, 0, '2019-03-15 11:03:56', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10004, 11, 'askjdhkjsad', 1, 2, 90, 0, 18, 0, 41650, 23850, 4960, 4960, 3170, 3170, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 667000, 0, 2725, 8200, 9500, 8000, 7780, 4900, 600000, 0, 156, 0, 0, '2019-03-15 11:04:13', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10003, 12, 'å¤§æžªå•Š', 1, 2, 90, 0, 18, 0, 40950, 24550, 4820, 4820, 3310, 3310, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 667000, 0, 2725, 8200, 9500, 8000, 7640, 4900, 600000, 0, 156, 0, 1560, '2019-03-25 14:23:15', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);
INSERT INTO `charac_info` VALUES (10003, 14, 'å®‡å®™ç»Ÿæ²»è€…', 1, 2, 30, 0, 20, 0, 13200, 10300, 1400, 1340, 1390, 1330, 0x0000000000000000, 0x00000000000000000000000000000000000000000000000000000000000000000000, 100008999, 0, 1300, 8200, 9500, 8000, 6440, 4900, 600000, 0, 156, 0, 1560, '2019-04-08 09:32:01', '0000-00-00 00:00:00', 0, '0000-00-00 00:00:00', 0, 0, 0, 0, 1, 4, -1, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1);

SET FOREIGN_KEY_CHECKS = 1;
