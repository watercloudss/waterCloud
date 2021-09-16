/*
Navicat MySQL Data Transfer

Source Server         : MySQL8
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : cloud-webmagic

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-09-17 00:30:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_type` int DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '日志内容',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP',
  `method_class` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求java方法',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
  `response_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求类型',
  `cost_time` bigint DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `request_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_logt_ype` (`log_type`) USING BTREE,
  KEY `index_log_type` (`log_type`) USING BTREE,
  KEY `idx_sl_log_type` (`log_type`) USING BTREE,
  KEY `idx_sl_create_time` (`create_time`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1437756571945046017', '1', 'CW0001', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', 'Result(success=true, message=操作成功！, code=200, result=Usertest(id=22, name=小明, age=12, version=34, createTime=2021-09-13T23:14:13, updateTime=2021-09-14T20:34:13.601), timestamp=1631622853636, onlTable=null)', '82', null, '2021-09-14 20:34:14', null, '2021-09-14 20:34:14', null);
INSERT INTO `sys_log` VALUES ('1437756076572577793', '1', 'CW0001', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '12312', '263', null, '2021-09-14 20:32:16', null, '2021-09-14 20:32:16', null);
INSERT INTO `sys_log` VALUES ('1437757533812228098', '1', 'CW0001', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"age\":12,\"createTime\":\"2021-09-13 23:14:13\",\"id\":22,\"name\":\"小明\",\"updateTime\":\"2021-09-14T20:38:02.650\",\"version\":35},\"success\":true,\"timestamp\":1631623082687}', '194', null, '2021-09-14 20:38:03', null, '2021-09-14 20:38:03', null);
INSERT INTO `sys_log` VALUES ('1437758737359405058', '1', '', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.common.exception.CloudWebmagicExceptionHandler.handleException()', '  e: java.lang.ArithmeticException: / by zero', '{\"code\":500,\"message\":\"操作失败，/ by zero\",\"success\":false,\"timestamp\":1631623369774}', '17', null, '2021-09-14 20:42:50', null, '2021-09-14 20:42:50', null);
INSERT INTO `sys_log` VALUES ('1437777922131079170', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '/ by zero', '0', null, '2021-09-14 21:59:04', null, '2021-09-14 21:59:04', null);
INSERT INTO `sys_log` VALUES ('1437779590046388225', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '/ by zero', '0', null, '2021-09-14 22:05:42', null, '2021-09-14 22:05:42', null);
INSERT INTO `sys_log` VALUES ('1437780298380435457', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '/ by zero', '-1', null, '2021-09-14 22:08:30', null, '2021-09-14 22:08:30', '500');
INSERT INTO `sys_log` VALUES ('1437780803924090881', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"age\":12,\"createTime\":\"2021-09-13 23:14:13\",\"id\":22,\"name\":\"小明\",\"updateTime\":\"2021-09-14T22:10:30.814\",\"version\":50},\"success\":true,\"timestamp\":1631628630850}', '75', null, '2021-09-14 22:10:31', null, '2021-09-14 22:10:31', '200');
INSERT INTO `sys_log` VALUES ('1437782406815031297', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', 'ohhhhhhhhhhhhhhhhhhhhhhhhhhh!', '-1', null, '2021-09-14 22:16:53', null, '2021-09-14 22:16:53', '500');
INSERT INTO `sys_log` VALUES ('1437792972233363457', '1', 'get', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.TestController.get()', '  age: 12', 'ohhhhhhhhhhhhhhhhhhhhhhhhhhh!', '-1', null, '2021-09-14 22:58:52', null, '2021-09-14 22:58:52', '500');
INSERT INTO `sys_log` VALUES ('1437802537750474754', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"123456\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631633812564}', '181', null, '2021-09-14 23:36:53', null, '2021-09-14 23:36:53', '500');
INSERT INTO `sys_log` VALUES ('1437803239147155458', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"success\":true,\"timestamp\":1631633979920}', '58', null, '2021-09-14 23:39:40', null, '2021-09-14 23:39:40', '200');
INSERT INTO `sys_log` VALUES ('1437803472648253442', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzU4MzUsInVzZXJuYW1lIjoiYWRtaW4ifQ.6TaKUBAqEhionZnPUhLDOaoWoYMjL74faDv0oPlwGuY\"},\"success\":true,\"timestamp\":1631634035632}', '65', null, '2021-09-14 23:40:36', null, '2021-09-14 23:40:36', '200');
INSERT INTO `sys_log` VALUES ('1437804320895905794', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzYwMzcsInVzZXJuYW1lIjoiYWRtaW4ifQ.ioxeMf7-83hfQwpJLlfyM-e33m-VnkNaYdyJdHxjasg\"},\"success\":true,\"timestamp\":1631634237875}', '16', null, '2021-09-14 23:43:58', null, '2021-09-14 23:43:58', '200');
INSERT INTO `sys_log` VALUES ('1437804405625040897', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzYwNTgsInVzZXJuYW1lIjoiYWRtaW4ifQ.93Q2YCNzGF0Nc2WEcq8M2JVKrKswljEyKXONf9Xo2MQ\"},\"success\":true,\"timestamp\":1631634258079}', '14', null, '2021-09-14 23:44:18', null, '2021-09-14 23:44:18', '200');
INSERT INTO `sys_log` VALUES ('1437804513364127746', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzYwODMsInVzZXJuYW1lIjoiYWRtaW4ifQ.kCMB4pdRSwmsOCX-cZHWJXLREIZd8Zv5-E4g0-EQHFQ\"},\"success\":true,\"timestamp\":1631634283760}', '13', null, '2021-09-14 23:44:44', null, '2021-09-14 23:44:44', '200');
INSERT INTO `sys_log` VALUES ('1437807330908827649', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzY3NTUsInVzZXJuYW1lIjoiYWRtaW4ifQ.J7JthnJPmKQhFuQwyCfWwgexSnAQLhrZw6W6qk3ccVs\"},\"success\":true,\"timestamp\":1631634955324}', '245', null, '2021-09-14 23:55:56', null, '2021-09-14 23:55:56', '200');
INSERT INTO `sys_log` VALUES ('1437807864332009474', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE2MzY4ODIsInVzZXJuYW1lIjoiYWRtaW4ifQ.qhh3-9OEJ6iDY4JiD3ALj7lizgENFROSVOhXE7JG1qw\"},\"success\":true,\"timestamp\":1631635082521}', '221', null, '2021-09-14 23:58:03', null, '2021-09-14 23:58:03', '200');
INSERT INTO `sys_log` VALUES ('1438167918080004098', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', 'Empty key', '-1', null, '2021-09-15 23:48:46', null, '2021-09-15 23:48:46', '500');
INSERT INTO `sys_log` VALUES ('1438168151442702337', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', 'Empty key', '-1', null, '2021-09-15 23:49:42', null, '2021-09-15 23:49:42', '500');
INSERT INTO `sys_log` VALUES ('1438170591894237185', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTcyMzM2MywiaWF0IjoxNjMxNzIxNTYzLCJ1c2VybmFtZSI6ImFkbWluIn0.ycTbINDMZrXsC9022ejJCqlEQgg1QT5xZLzHU18BMiE\"},\"success\":true,\"timestamp\":1631721563493}', '211', null, '2021-09-15 23:59:24', null, '2021-09-15 23:59:24', '200');
INSERT INTO `sys_log` VALUES ('1438172727361261570', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTcyMzg3MiwiaWF0IjoxNjMxNzIyMDcyLCJ1c2VybmFtZSI6ImFkbWluIn0.zpnvH1-NOSEdvZowO3m6GQ-FX6cfTCj1J44g3IzSS4s\"},\"success\":true,\"timestamp\":1631722072647}', '228', null, '2021-09-16 00:07:53', null, '2021-09-16 00:07:53', '200');
INSERT INTO `sys_log` VALUES ('1438173606080532481', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTcyNDA4MiwiaWF0IjoxNjMxNzIyMjgyLCJ1c2VybmFtZSI6ImFkbWluIn0.RPRCX_BsumXsgiFeeJno2Cj1KlExDB1Fdcaa53zUrWM\"},\"success\":true,\"timestamp\":1631722282098}', '220', null, '2021-09-16 00:11:22', null, '2021-09-16 00:11:22', '200');
INSERT INTO `sys_log` VALUES ('1438325979616698370', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTc2MDQxMCwiaWF0IjoxNjMxNzU4NjEwLCJ1c2VybmFtZSI6ImFkbWluIn0.awQaHFkhHMuBvdZGdNVxHgT6cT8HwB7rYEV5zkjyO_w\"},\"success\":true,\"timestamp\":1631758610796}', '263', null, '2021-09-16 10:16:51', null, '2021-09-16 10:16:51', '200');
INSERT INTO `sys_log` VALUES ('1438332451436175362', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTc2MTk1MywiaWF0IjoxNjMxNzYwMTUzLCJ1c2VybmFtZSI6ImFkbWluIn0.eWsh6MfX4u12jNDXn1yIfuAOcb8ev4L6JVxU4_1dTjM\"},\"success\":true,\"timestamp\":1631760153735}', '348', null, '2021-09-16 10:42:34', null, '2021-09-16 10:42:34', '200');
INSERT INTO `sys_log` VALUES ('1438334626228936706', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTc2MjQ3MiwiaWF0IjoxNjMxNzYwNjcyLCJ1c2VybmFtZSI6ImFkbWluIn0.wBJTzpAQu4m7jCwBQ-edl1cJzi7aRhfy2s46B5xOwLA\"},\"success\":true,\"timestamp\":1631760672389}', '223', null, '2021-09-16 10:51:13', null, '2021-09-16 10:51:13', '200');
INSERT INTO `sys_log` VALUES ('1438341357310910465', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d1\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631762277320}', '5', null, '2021-09-16 11:17:57', null, '2021-09-16 11:17:57', '500');
INSERT INTO `sys_log` VALUES ('1438512758437302274', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631803142368}', '184', null, '2021-09-16 22:39:03', null, '2021-09-16 22:39:03', '500');
INSERT INTO `sys_log` VALUES ('1438513846775304193', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631803402035}', '34', null, '2021-09-16 22:43:22', null, '2021-09-16 22:43:22', '500');
INSERT INTO `sys_log` VALUES ('1438513869839781889', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d1\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631803407526}', '11', null, '2021-09-16 22:43:28', null, '2021-09-16 22:43:28', '500');
INSERT INTO `sys_log` VALUES ('1438513893126557697', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTgwNTIxMywiaWF0IjoxNjMxODAzNDEzLCJ1c2VybmFtZSI6ImFkbWluIn0.MI5oAmwr3h5KqGhK45OKfGR5ZujeZT0CEDLpc-XEuXo\"},\"success\":true,\"timestamp\":1631803413039}', '54', null, '2021-09-16 22:43:33', null, '2021-09-16 22:43:33', '200');
INSERT INTO `sys_log` VALUES ('1438518104216047618', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"\",\"username\":\"admin\"}]', '{\"code\":510,\"message\":\"用户名或密码错误！！！\",\"success\":true,\"timestamp\":1631804416949}', '156', null, '2021-09-16 23:00:17', null, '2021-09-16 23:00:17', '500');
INSERT INTO `sys_log` VALUES ('1438521889575460866', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTgwNzExOSwiaWF0IjoxNjMxODA1MzE5LCJ1c2VybmFtZSI6ImFkbWluIn0.e9a3ucJHGfFmpVkYDNgo__5l16XPaw8xgkrX3W_DBxI\"},\"success\":true,\"timestamp\":1631805319442}', '319', null, '2021-09-16 23:15:20', null, '2021-09-16 23:15:20', '200');
INSERT INTO `sys_log` VALUES ('1438527605073793025', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"gender\":\"M\",\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTgwODQ4MiwiaWF0IjoxNjMxODA2NjgyLCJ1c2VybmFtZSI6ImFkbWluIn0.5hRtl0AMQHxmiumy8L_ybstQMWXDjNY-qLjwEvVGcEA\"},\"success\":true,\"timestamp\":1631806682100}', '232', null, '2021-09-16 23:38:02', null, '2021-09-16 23:38:02', '200');
INSERT INTO `sys_log` VALUES ('1438530890908372993', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTgwOTI2NSwiaWF0IjoxNjMxODA3NDY1LCJ1c2VybmFtZSI6ImFkbWluIn0.DXTAEIrhJQH-c9NPljDrlQrTFfpi_HsxBpB9VwSTCrE\"},\"success\":true,\"timestamp\":1631807465569}', '218', null, '2021-09-16 23:51:06', null, '2021-09-16 23:51:06', '200');
INSERT INTO `sys_log` VALUES ('1438530916237774849', '2', 'login', '0:0:0:0:0:0:0:1', 'com.watercloud.webmagic.controller.SysUserController.login()', '[{\"password\":\"cb362cfeefbf3d8d\",\"username\":\"admin\"}]', '{\"code\":200,\"message\":\"操作成功！\",\"result\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJjbG91ZC13ZWJtYWdpYyIsImV4cCI6MTYzMTgwOTI3MSwiaWF0IjoxNjMxODA3NDcxLCJ1c2VybmFtZSI6ImFkbWluIn0._S_6SLtMlSqEPy6BntOy1zBWz47xB1ekVY1qgLVUHoM\"},\"success\":true,\"timestamp\":1631807471699}', '7', null, '2021-09-16 23:51:12', null, '2021-09-16 23:51:12', '200');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构编码',
  `status` tinyint(1) DEFAULT NULL COMMENT '性别(1-正常,2-冻结)',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除状态(0-正常,1-已删除)',
  `third_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方登录的唯一标识',
  `third_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '第三方类型',
  `activiti_sync` tinyint(1) DEFAULT NULL COMMENT '同步工作流引擎(1-同步,0-不同步)',
  `work_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工号，唯一键',
  `post` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '职务，关联职务表',
  `telephone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '座机号',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `user_identity` tinyint(1) DEFAULT NULL COMMENT '身份（1普通成员 2上级）',
  `depart_ids` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '负责部门',
  `rel_tenant_ids` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '多租户标识',
  `client_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_user_name` (`username`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_work_no` (`work_no`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_username` (`username`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_phone` (`phone`) USING BTREE,
  UNIQUE KEY `uniq_sys_user_email` (`email`) USING BTREE,
  KEY `index_user_status` (`status`) USING BTREE,
  KEY `index_user_del_flag` (`del_flag`) USING BTREE,
  KEY `idx_su_username` (`username`) USING BTREE,
  KEY `idx_su_status` (`status`) USING BTREE,
  KEY `idx_su_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3d464b4ea0d2491aab8a7bde74c57e95', 'zhangsan', '张三', '02ea098224c7d0d2077c14b9a3a1ed16', 'x5xRdeKB', 'https://static.jeecg.com/temp/jmlogo_1606575041993.png', null, null, null, null, '财务部', '1', '0', null, null, '1', '0005', '总经理', null, 'admin', '2020-05-14 21:26:24', 'admin', '2020-09-09 14:42:51', '1', '', '', null);
INSERT INTO `sys_user` VALUES ('a75d45a015c44384a04449ee80dc3503', 'jeecg', 'jeecg', '58a714412072f0b9', 'mIgiYJow', 'https://static.jeecg.com/temp/国炬软件logo_1606575029126.png', null, '1', null, null, 'A02A01', '1', '0', null, null, '1', '00002', 'devleader', null, 'admin', '2019-02-13 16:02:36', 'admin', '2020-11-26 15:16:05', '1', '', null, null);
INSERT INTO `sys_user` VALUES ('e9ca23d68d884d4ebb19d07889727dae', 'admin', '管理员', 'cb362cfeefbf3d8d', 'RCGTeGiH', 'https://static.jeecg.com/temp/国炬软件logo_1606575029126.png', '2018-12-05 00:00:00', '1', 'jeecg@163.com', '18611111111', 'A01', '1', '0', null, null, '1', '00001', '总经理', null, null, '2019-06-21 17:54:10', 'admin', '2020-07-10 15:27:10', '2', 'c6d7cb4deeac411cb3384b1b31278596', '', null);
INSERT INTO `sys_user` VALUES ('f0019fdebedb443c98dcb17d88222c38', 'zhagnxiao', '张小红', 'f898134e5e52ae11a2ffb2c3b57a4e90', 'go3jJ4zX', 'https://static.jeecg.com/temp/jmlogo_1606575041993.png', '2019-04-01 00:00:00', null, null, null, '研发部,财务部', '2', '0', null, null, '1', '00003', '', null, 'admin', '2020-10-01 19:34:10', 'admin', '2020-11-26 15:24:59', '1', '', '', null);

-- ----------------------------
-- Table structure for usertest
-- ----------------------------
DROP TABLE IF EXISTS `usertest`;
CREATE TABLE `usertest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `version` int DEFAULT '0',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of usertest
-- ----------------------------
INSERT INTO `usertest` VALUES ('21', '小明3', '3', '0', '2021-09-13 21:31:31', '2021-09-13 21:31:31');
INSERT INTO `usertest` VALUES ('22', '小明', '12', '52', '2021-09-13 23:14:13', '2021-09-14 22:58:52');
INSERT INTO `usertest` VALUES ('23', '小明', '876', '0', '2021-09-13 23:22:00', '2021-09-13 23:22:01');

-- ----------------------------
-- Table structure for zh_spiderinfo
-- ----------------------------
DROP TABLE IF EXISTS `zh_spiderinfo`;
CREATE TABLE `zh_spiderinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '0',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of zh_spiderinfo
-- ----------------------------
INSERT INTO `zh_spiderinfo` VALUES ('2', '拒绝「干饭一时爽，洗碗忙一场」——洗碗机挑选攻略', '/special/1419627503204323329', 'https://pic4.zhimg.com/100/v2-7db42c51f4e996ddf658c61a5c25548f_hd.png', '不论是大家庭亦或是小两口，都曾「干饭一时爽，洗碗忙一场」，洗碗这件事可大可小，处理不好可能会引发家庭矛盾。「洗碗机」，这款能提升家居幸福感的电器，能够维系家庭合睦，加码精致家居生活，让我们一起走进拥有洗碗机的家居生活看看吧～', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('3', '抗衰做的好，状态差不了！', '/special/1419620371297931264', 'https://pic2.zhimg.com/100/v2-3c7a002551adb56edc89119042ea20ed_hd.png', '抗衰是护肤界永恒热门话题，抗衰的方法千千万，到底哪一种适合你？到底几岁开始抗衰？抗衰到底指的是什么？@知乎美妆 制作的本期专题给你详解。', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('4', '与新生活来电', '/special/1419617316958654464', 'https://pic3.zhimg.com/100/v2-b558fd32464408823f8ebd4f195c613e_hd.png', '京东电器，焕新你的生活！智能舒适的宅家生活；硬核潮酷的数码黑科技；新品集结一应俱全，体验全面升级。在京东小魔方，探索一切 #与新生活来电# 的可能性。', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('5', '叹为观「知」：走进 2021 世界机器人大会', '/special/1419391243478941696', 'https://pic1.zhimg.com/100/v2-d2bb09e95c4658ed697ea0dc2c3e1c34_hd.png', '这是一场机器人领域规模最大、规格最高、国际元素最丰富的顶级盛会，是机器人的王国，是机器人爱好者的乐园，全面展示机器人领域新技术、新产品、新模式、新业态。2021 世界机器人大会举办之际，工信微报联合知乎发起叹为观「知」行动，邀请知乎答主现场探访，分享知识、经验和见解，欢迎关注～', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('6', '「茶」觉七彩云南', '/special/1419334153960009728', 'https://pic3.zhimg.com/100/v2-8359611712e7e5b469d63dcc9a243a9e_hd.png', '从唐代煎茶、宋代点茶、明代泡茶，到当下茶饮的全民化，你对茶叶了解多少？怎样才能买到真的好茶？又有哪些茶是值得珍藏的？', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('7', '日常「真香」电器 解析指南', '/special/1419264522045116416', 'https://pic3.zhimg.com/100/v2-04d8154139a61c9c5fa4b8945507cdbe_hd.png', '电器与我们的日常生活密不可分，穿梭在大街小巷，难免会有五花八门的电器让我们「乱花渐欲迷人眼」，可也总会有一些「佼佼者」，让我们不禁感叹「嗯真香」。对于这些自带「香味」的电器，究竟有哪些特异功能呢？今日份，让我们走进真香电器系列，查收「闭眼入」硬核指南，下一个「无敌带货王」可能就是你！', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('8', '新品情报局 | 科沃斯全能新品', '/special/1419257321579573249', 'https://pic4.zhimg.com/100/v2-e10f5a119bda312396736fe1d2b009db_hd.png', '据爆料，科沃斯堪称「地表全能基站」的跨时代新品将于 9 月 15 日正式发布。极简主义设计、行动宛若无人驾驶、超前时代的人工智能操控……更多新品爆料、购机福利，新品情报局带你一览无余。', '0', '2021-09-14 16:12:14', '2021-09-14 16:12:14');
INSERT INTO `zh_spiderinfo` VALUES ('31', '笨巧果丨知乎城市周刊—九月刊 ', '/special/1421153668409880576', 'https://pic4.zhimg.com/100/v2-5391f055da224dcb9d5a9068404954d7_hd.png', '「笨巧果」，一份为天津城市生活定制的「轻松理性向」专题周刊。收录城市热议话题，邀请优秀答主进行讨论。「笨巧果」周刊，带你了解天津的那些事儿。 ', '0', '2021-09-14 22:12:04', '2021-09-14 22:12:04');
INSERT INTO `zh_spiderinfo` VALUES ('32', '从油开始，美味零负担', '/special/1420756456509960192', 'https://pic2.zhimg.com/100/v2-332094960d2e12835a687ec44b4a0f65_hd.png', '食用油在日常生活中扮演了重要角色，让食物色香味美，增添丰富营养，一日三餐煎炒烹炸样样都离不开它。但注重饮食健康的当代人少不了对「油脂摄入」的关注，因油脂带来的「反式脂肪酸」一直是影响人体健康的关键危害，所以更健康潮流的饮食方式，应该从选好一款食用油开始。这一次金龙鱼携「零反式脂肪食用油」在知乎带来了更多的健康解答，和大家一起，从油开始享受美味零负担。', '0', '2021-09-14 22:12:04', '2021-09-14 22:12:04');
INSERT INTO `zh_spiderinfo` VALUES ('33', '天黑请闭眼，周德东老师请上线', '/special/1420747897398382592', 'https://pic2.zhimg.com/100/v2-b39992563de70e18a218b349c29bb94d_hd.png', '周德东新专栏《周德东讲真：最不真实的真实事件》上线，记录了十二个最不真实的真实故事，你信了吗？ 你们有没有遇到过稀奇古怪的故事？有没有细思极恐的经历？有没有一瞬间感觉世界出了 BUG ? 参与活动，三句话讲出你的故事，周德东老师已经为你准备了一份「细思极恐」的礼物。', '0', '2021-09-14 22:12:04', '2021-09-14 22:12:04');
