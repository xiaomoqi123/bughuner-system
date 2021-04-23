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

 Date: 24/08/2020 18:47:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_bug_info
-- ----------------------------
DROP TABLE IF EXISTS `app_bug_info`;
CREATE TABLE `app_bug_info`  (
  `app_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bug_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `edge_id` bigint(100) DEFAULT 0,
  `app_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bug_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `c_time` datetime(0) DEFAULT NULL,
  `current` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `m_time` datetime(0) DEFAULT NULL,
  `priority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `screenshot_adr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `info_flag` int(1) DEFAULT 0,
  PRIMARY KEY (`app_key`, `bug_id`) USING BTREE,
  INDEX `FK2ligj63trutj00wnf5mqlllv5`(`user_id`) USING BTREE,
  CONSTRAINT `FK2ligj63trutj00wnf5mqlllv5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 9216 kB; (`user_id`) REFER `yunyue/user`(`id`)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of app_bug_info
-- ----------------------------
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092915627412116', 0, '2.9.5', '页面刷新时出现异常', '2020-06-23 14:20:15', 'com.example.jingbin.cloudreader.view.webview.WebViewActivity', '2020-06-23 14:20:15', 'critical', 'no exist', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092916783753117', 0, '2.9.5', '点击“调整栏目顺序”没有响应', '2020-06-23 14:20:45', 'com.example.jingbin.cloudreader.view.webview.WebViewActivity', '2020-06-23 14:20:45', 'critical', 'no exist', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092917546378113', 0, '2.9.5', '一级分类太多，二级分类太少，分类没有意义', '2020-06-23 14:18:50', 'com.example.jingbin.cloudreader.ui.MainActivity', '2020-06-23 14:18:50', 'minor', 'no exoist', 'New', 'UI', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092918631331101', 3604, '2.9.5', '空白网页，无数据', '2020-06-23 13:23:51', 'com.example.jingbin.cloudreader.view.webview.WebViewActivity', '2020-06-23 13:23:51', 'critical', 'no exist', 'New', 'method', 2, 1);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921140689101', 3597, '2.9.5', '这个异常没有在这款手机上出现', '2020-06-23 14:05:40', 'com.example.jingbin.cloudreader.ui.MainActivity', '2020-06-23 14:05:40', 'minor', 'no exist', 'New', 'method', 2, 1);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921220123102', 3638, '2.9.5', '因为网络的原因可能会导致数据无法出现', '2020-06-23 14:07:00', 'com.example.jingbin.cloudreader.ui.film.child.FilmDetailActivity', '2020-06-23 14:07:00', 'critical', 'no exist', 'New', 'method', 2, 1);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921271394103', 3680, '2.9.5', '网络如果不稳定，会导致数据无法出现', '2020-06-23 14:07:51', 'com.example.jingbin.cloudreader.ui.film.child.FilmDetailActivity', '2020-06-23 14:07:51', 'critical', 'no exist', 'New', 'method', 2, 1);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921318463104', 3686, '2.9.5', '加载时间过长，该功能失效', '2020-06-23 14:08:38', 'com.example.jingbin.cloudreader.view.webview.WebViewActivity', '2020-06-23 14:08:38', 'critical', 'no exist', 'New', 'method', 2, 1);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921428562106', 0, '2.9.5', '先进入关于云阅，其中更新日志长时间加载不进来', '2020-06-23 14:10:28', 'com.example.jingbin.cloudreader.ui.menu.NavAboutActivity', '2020-06-23 14:10:28', 'critical', '202092921428562106.png', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921457102112', 0, '2.9.5', '同一页面字体大小不一致', '2020-06-23 14:17:20', 'com.example.jingbin.cloudreader.ui.MainActivity', '2020-06-23 14:17:20', 'minor', NULL, 'New', 'UI', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921470330107', 0, '2.9.5', '先进入关于云阅，其中玩安卓加载不出来', '2020-06-23 14:11:10', 'com.example.jingbin.cloudreader.ui.menu.NavAboutActivity', '2020-06-23 14:11:10', 'critical', '202092921470330107.png', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921601889108', 0, '2.9.5', '进入搜索界面，字体大小不一，影响观看', '2020-06-23 14:13:21', 'com.example.jingbin.cloudreader.ui.menu.SearchActivity', '2020-06-23 14:13:21', 'minor', '202092921601889108.png', 'New', 'UI', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921700454109', 0, '2.9.5', '主界面的每日推荐，长时间加载不进来', '2020-06-23 14:15:00', 'com.example.jingbin.cloudreader.ui.MainActivity', '2020-06-23 14:15:00', 'critical', '202092921700454109.png', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921724210110', 0, '2.9.5', '图片加载不进来', '2020-06-23 14:15:24', 'com.example.jingbin.cloudreader.ui.MainActivity', '2020-06-23 14:15:24', 'minor', '202092921724210110.png', 'New', 'method', 2, 0);
INSERT INTO `app_bug_info` VALUES ('yunyue', '202092921764709112', 0, '2.9.5', '在登陆页面输入特殊字符，点击注册，出现闪退\r\n在登陆页面输入特殊字符，点击注册，出现闪退', '2020-06-23 14:18:49', 'com.example.jingbin.cloudreader.ui.LoginActivity', '2020-06-23 14:18:49', 'critical', 'no exist', 'New', 'method', 2, 0);

SET FOREIGN_KEY_CHECKS = 1;
