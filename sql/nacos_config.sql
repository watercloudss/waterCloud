/*
 Navicat Premium Data Transfer

 Source Server         : dock-mysql8
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 127.0.0.1:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 25/12/2021 00:00:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 123456\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n# 默认数据库表下划线命名\n      table-underline: true \n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \n# 返回类型为Map,显示null对应的字段    \n    call-setters-on-nulls: true \n# 指定需要扫描的entity包     \n  type-aliases-package: com.watercloud.webmagic.entity  \n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:6379', '509a0eb46c402debf0388c80ee6bb623', '2021-12-24 15:30:16', '2021-12-24 15:36:46', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'cloud-webmagic的配置文件', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (9, 'cloud-flash-dev.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 9001\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n# 默认数据库表下划线命名\r\n      table-underline: true \r\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \r\n# 返回类型为Map,显示null对应的字段    \r\n    call-setters-on-nulls: true \r\n# 指定需要扫描的entity包     \r\n  type-aliases-package: com.watercloud.webmagic.entity  \r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '7c1d9d943a6362743cd1ccc70714e498', '2021-12-24 15:58:33', '2021-12-24 15:58:33', NULL, '0:0:0:0:0:0:0:1', '', '', 'cloud-flash服务的配置文件', NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint UNSIGNED NOT NULL,
  `nid` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9000\r\nspring:\r\n  application:\r\n    name: cloud-webmagic\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n      config:\r\n        server-addr: 127.0.0.1:8848\r\n        file-extension: yaml\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n      table-underline: true # 默认数据库表下划线命名\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\r\n    call-setters-on-nulls: true  # 返回类型为Map,显示null对应的字段\r\n  type-aliases-package: com.watercloud.webmagic.entity  # 指定需要扫描的entity包\r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '8038319c4bd54b1904871947606049a8', '2021-12-24 15:30:15', '2021-12-24 15:30:16', NULL, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (1, 2, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9000\r\nspring:\r\n  application:\r\n    name: cloud-webmagic\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n      config:\r\n        server-addr: 127.0.0.1:8848\r\n        file-extension: yaml\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n      table-underline: true # 默认数据库表下划线命名\r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\r\n    call-setters-on-nulls: true  # 返回类型为Map,显示null对应的字段\r\n  type-aliases-package: com.watercloud.webmagic.entity  # 指定需要扫描的entity包\r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '8038319c4bd54b1904871947606049a8', '2021-12-24 15:31:50', '2021-12-24 15:31:51', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 3, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 123456\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n      table-underline: true # 默认数据库表下划线命名\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\n    call-setters-on-nulls: true  # 返回类型为Map,显示null对应的字段\n  type-aliases-package: com.watercloud.webmagic.entity  # 指定需要扫描的entity包\n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:6379', '47ccacb9a8a1d54b74880bcd62c4d5fc', '2021-12-24 15:33:13', '2021-12-24 15:33:14', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 4, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 12345\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n      table-underline: true # 默认数据库表下划线命名\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\n    call-setters-on-nulls: true  # 返回类型为Map,显示null对应的字段\n  type-aliases-package: com.watercloud.webmagic.entity  # 指定需要扫描的entity包\n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:6379', '0531246a1981d53fcab0787ff9327e7f', '2021-12-24 15:34:25', '2021-12-24 15:34:26', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 5, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 123456\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n      table-underline: true # 默认数据库表下划线命名\n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # 这个配置会将执行的sql打印出来，在开发或测试的时候可以用\n    call-setters-on-nulls: true  # 返回类型为Map,显示null对应的字段\n  type-aliases-package: com.watercloud.webmagic.entity  # 指定需要扫描的entity包\n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:6379', '47ccacb9a8a1d54b74880bcd62c4d5fc', '2021-12-24 15:35:40', '2021-12-24 15:35:41', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 6, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 123456\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n# 默认数据库表下划线命名\n      table-underline: true \n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \n# 返回类型为Map,显示null对应的字段    \n    call-setters-on-nulls: true \n# 指定需要扫描的entity包     \n  type-aliases-package: com.watercloud.webmagic.entity  \n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:6379', '509a0eb46c402debf0388c80ee6bb623', '2021-12-24 15:36:25', '2021-12-24 15:36:26', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (1, 7, 'cloud-webmagic-dev.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 9000\nspring:\n  datasource:\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\n    username: root\n    password: 123456\n    type: com.alibaba.druid.pool.DruidDataSource\n    druid:\n      # 连接池的配置信息\n      # 初始化大小，最小，最大\n      initial-size: 5\n      min-idle: 5\n      maxActive: 20\n      # 配置获取连接等待超时的时间\n      maxWait: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      timeBetweenEvictionRunsMillis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM sys_user\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      # 打开PSCache，并且指定每个连接上PSCache的大小\n      poolPreparedStatements: true\n      maxPoolPreparedStatementPerConnectionSize: 20\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,slf4j\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        login-username: admin\n        login-password: 123456\n  redis:\n    host: 127.0.0.1\n    port: 6379\n    password:\n    lettuce:\n      pool:\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\n        max-active: 8\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\n        max-wait: -1ms\n        # 连接池中的最大空闲连接 默认为8\n        max-idle: 8\n        # 连接池中的最小空闲连接 默认为 0\n        min-idle: 0\n        statViewServlet:\n          enabled: true\n  kafka:\n    #kafka地址，可以指定多个\n    bootstrap-servers: 192.168.0.107:9092\n    consumer:\n      #指定group_id\n      group-id: group_id\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\n      auto-offset-reset: earliest\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n    producer:\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n#mybatis plus 设置\nmybatis-plus:\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\n  global-config:\n    banner: true # MP3.0自带的banner\n    db-config:\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\n#      id-type: ASSIGN_ID\n# 默认数据库表下划线命名\n      table-underline: true \n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \n  configuration:\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \n# 返回类型为Map,显示null对应的字段    \n    call-setters-on-nulls: true \n# 指定需要扫描的entity包     \n  type-aliases-package: com.watercloud.webmagic.entity  \n#以下为自定义配置\nJWTConfig:\n  ISSUER: cloud-webmagic\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\n  EXPIRE_TIME: 1800000\nShiroConfig:\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\nredission:\n  address: redis://127.0.0.1:63791', 'b335283b53e12864bedd3b3607089b99', '2021-12-24 15:36:45', '2021-12-24 15:36:46', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 8, 'cloud-flash-dev.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9001\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n# 默认数据库表下划线命名\r\n      table-underline: true \r\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \r\n# 返回类型为Map,显示null对应的字段    \r\n    call-setters-on-nulls: true \r\n# 指定需要扫描的entity包     \r\n  type-aliases-package: com.watercloud.webmagic.entity  \r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '7c1d9d943a6362743cd1ccc70714e498', '2021-12-24 15:54:58', '2021-12-24 15:54:59', NULL, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES (8, 9, 'cloud-flash-dev.yml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9001\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n# 默认数据库表下划线命名\r\n      table-underline: true \r\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \r\n# 返回类型为Map,显示null对应的字段    \r\n    call-setters-on-nulls: true \r\n# 指定需要扫描的entity包     \r\n  type-aliases-package: com.watercloud.webmagic.entity  \r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '7c1d9d943a6362743cd1ccc70714e498', '2021-12-24 15:57:53', '2021-12-24 15:57:55', NULL, '0:0:0:0:0:0:0:1', 'D', '');
INSERT INTO `his_config_info` VALUES (0, 10, 'cloud-flash-dev.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9001\r\nspring:\r\n  datasource:\r\n    url: jdbc:mysql://localhost:3306/cloud-webmagic?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai\r\n    username: root\r\n    password: 123456\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    druid:\r\n      # 连接池的配置信息\r\n      # 初始化大小，最小，最大\r\n      initial-size: 5\r\n      min-idle: 5\r\n      maxActive: 20\r\n      # 配置获取连接等待超时的时间\r\n      maxWait: 60000\r\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\r\n      timeBetweenEvictionRunsMillis: 60000\r\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\r\n      minEvictableIdleTimeMillis: 300000\r\n      validationQuery: SELECT 1 FROM sys_user\r\n      testWhileIdle: true\r\n      testOnBorrow: false\r\n      testOnReturn: false\r\n      # 打开PSCache，并且指定每个连接上PSCache的大小\r\n      poolPreparedStatements: true\r\n      maxPoolPreparedStatementPerConnectionSize: 20\r\n      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\r\n      filters: stat,wall,slf4j\r\n      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录\r\n      connectionProperties: druid.stat.mergeSql\\=true;druid.stat.slowSqlMillis\\=5000\r\n      web-stat-filter:\r\n        enabled: true\r\n      stat-view-servlet:\r\n        enabled: true\r\n        login-username: admin\r\n        login-password: 123456\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    password:\r\n    lettuce:\r\n      pool:\r\n        # 连接池最大连接数(使用负值表示没有限制) 默认为8\r\n        max-active: 8\r\n        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1\r\n        max-wait: -1ms\r\n        # 连接池中的最大空闲连接 默认为8\r\n        max-idle: 8\r\n        # 连接池中的最小空闲连接 默认为 0\r\n        min-idle: 0\r\n        statViewServlet:\r\n          enabled: true\r\n  kafka:\r\n    #kafka地址，可以指定多个\r\n    bootstrap-servers: 192.168.0.107:9092\r\n    consumer:\r\n      #指定group_id\r\n      group-id: group_id\r\n      #earliest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset时，从头开始消费；\r\n      #latest：当各分区下有已提交的 offset 时，从提交的 offset 开始消费；无提交的 offset 时，消费新产生的该分区下的数据；\r\n      auto-offset-reset: earliest\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n    producer:\r\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\r\n#mybatis plus 设置\r\nmybatis-plus:\r\n  mapper-locations: classpath:com/watercloud/webmagic/mapper/*.xml\r\n  global-config:\r\n    banner: true # MP3.0自带的banner\r\n    db-config:\r\n      #主键类型  0:\"数据库ID自增\",1:\"该类型为未设置主键类型\", 2:\"用户输入ID\",3:\"全局唯一ID (数字类型唯一ID)\", 4:\"全局唯一ID UUID\",5:\"字符串全局唯一ID (idWorker 的字符串表示)\";\r\n#      id-type: ASSIGN_ID\r\n# 默认数据库表下划线命名\r\n      table-underline: true \r\n# 这个配置会将执行的sql打印出来，在开发或测试的时候可以用      \r\n  configuration:\r\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl \r\n# 返回类型为Map,显示null对应的字段    \r\n    call-setters-on-nulls: true \r\n# 指定需要扫描的entity包     \r\n  type-aliases-package: com.watercloud.webmagic.entity  \r\n#以下为自定义配置\r\nJWTConfig:\r\n  ISSUER: cloud-webmagic\r\n  SECRET: 01ea098224c7d0d2077c14b9a3a1ed16\r\n  EXPIRE_TIME: 1800000\r\nShiroConfig:\r\n  AnonUrls: /druid/**,/**/*.js,/**/*.css,/**/*.html,/**/*.svg,/**/*.pdf,/**/*.jpg,/**/*.png,/**/*.ico\r\nredission:\r\n  address: redis://127.0.0.1:6379', '7c1d9d943a6362743cd1ccc70714e498', '2021-12-24 15:58:32', '2021-12-24 15:58:33', NULL, '0:0:0:0:0:0:0:1', 'I', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
