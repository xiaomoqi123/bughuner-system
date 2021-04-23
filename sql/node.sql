/*
 Navicat Premium Data Transfer

 Source Server         : 118.178.18.181
 Source Server Type    : MariaDB
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : yunyue

 Target Server Type    : MariaDB
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 24/08/2020 18:48:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node`  (
  `id` bigint(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `window` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `app_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `known` int(1) DEFAULT 0,
  `adju_dist` bigint(255) DEFAULT 1000,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `neighbors` tinyblob,
  PRIMARY KEY (`id`, `window`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 9216 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of node
-- ----------------------------
INSERT INTO `node` VALUES (1, 'SlideScrollViewActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (2, 'MovieDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (3, 'LoadingActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (4, 'ViewBigImageActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (5, 'SlideShadeViewActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (6, 'MovieDetailActivity', 'YunYue', 'OptionsMenu', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (7, 'NavDeedBackActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (8, 'BookDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (9, 'OneMovieDetailActivity', 'YunYue', 'ACT', 1, 0, NULL, NULL);
INSERT INTO `node` VALUES (10, 'NavHomePageActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (11, 'LoginActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (12, 'CategoryDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (13, 'MainActivity', 'YunYue', 'ACT', 1, 0, NULL, NULL);
INSERT INTO `node` VALUES (14, 'WebViewActivity', 'YunYue', 'ACT', 1, 1, 'LoginActivity', NULL);
INSERT INTO `node` VALUES (15, 'MyCollectActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (16, 'NavDownloadActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (17, 'SearchActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (18, 'BookDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (19, 'OneMovieDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (20, 'SlideShadeViewActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (21, 'Launcher', 'YunYue', 'LAUNCHER', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (22, 'DoubanTopActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (23, 'FilmDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (24, 'NavAboutActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (25, 'MainActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (26, 'FilmDetailActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (27, 'WebViewActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (28, 'ArticleListActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (29, 'BookTypeActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (30, 'ResolverActivity', 'YunYue', 'ACT', 1, 1, NULL, NULL);
INSERT INTO `node` VALUES (31, 'LoginActivity', 'YunYue', 'ACT', 1, 1000, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
