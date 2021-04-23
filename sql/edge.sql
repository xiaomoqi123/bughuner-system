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

 Date: 24/08/2020 18:51:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edge
-- ----------------------------
DROP TABLE IF EXISTS `edge`;
CREATE TABLE `edge`  (
  `id` bigint(100) UNSIGNED NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type_exception` int(11) DEFAULT NULL,
  `source_node` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `target_node` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `event_handlers` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `event_type` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `app_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `data_type` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT 0,
  `weight` int(11) DEFAULT 1,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `assist_time` bigint(255) DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3841 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 9216 kB' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of edge
-- ----------------------------
INSERT INTO `edge` VALUES (3589, 'System.error', 5, 'MainActivity', 'ResolverActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460244944.png', 'Click Confirm button for AndroidAlert', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:04:05', NULL, NULL);
INSERT INTO `edge` VALUES (3590, '', 0, 'MainActivity', 'ResolverActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460244944.png', 'Click Confirm button for AndroidAlert', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:05', NULL, NULL);
INSERT INTO `edge` VALUES (3591, 'System.error', 5, 'ResolverActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460255193.png', 'Click Confirm button for AndroidAlert', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:04:15', NULL, NULL);
INSERT INTO `edge` VALUES (3592, '', 0, 'ResolverActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460255193.png', 'Click Confirm button for AndroidAlert', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:15', NULL, NULL);
INSERT INTO `edge` VALUES (3593, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460274790.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:35', NULL, NULL);
INSERT INTO `edge` VALUES (3594, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460283685.png', 'Click Return button because handled father components', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:44', NULL, NULL);
INSERT INTO `edge` VALUES (3595, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460287119.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_one, bounds: \'[314,55][465,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:50', NULL, NULL);
INSERT INTO `edge` VALUES (3596, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460297836.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_two, bounds: \'[465,55][616,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:04:59', NULL, NULL);
INSERT INTO `edge` VALUES (3597, 'System.error', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460321255.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_three, bounds: \'[616,55][767,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:05:21', NULL, NULL);
INSERT INTO `edge` VALUES (3598, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460321255.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_three, bounds: \'[616,55][767,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:05:21', NULL, NULL);
INSERT INTO `edge` VALUES (3599, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460328080.png', 'Click widget com.example.jingbin.cloudreader:id/action_search, bounds: \'[948,66][1080,198]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:05:29', NULL, NULL);
INSERT INTO `edge` VALUES (3603, '', 0, 'FilmDetailActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460408272.png', 'Click widget //android.widget.ImageView[contains(@content-desc,\'更多选项\')], bounds: \'[969,66][1080,198]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:06:48', NULL, NULL);
INSERT INTO `edge` VALUES (3604, 'System.error', 5, 'FilmDetailActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460428591.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'0\')], bounds: \'[530,66][1069,198]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:07:08', NULL, NULL);
INSERT INTO `edge` VALUES (3605, '', 0, 'FilmDetailActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460428591.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'0\')], bounds: \'[530,66][1069,198]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:07:08', NULL, NULL);
INSERT INTO `edge` VALUES (3606, '', 0, 'WebViewActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460466284.png', 'Click widget //android.widget.ImageButton[contains(@content-desc,\'返回\')], bounds: \'[0,55][154,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:07:46', NULL, NULL);
INSERT INTO `edge` VALUES (3636, '', 0, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460776642.png', 'Click widget //android.widget.LinearLayout[contains(@bounds,\'[0,546][1080,938]\')]', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:13:01', NULL, NULL);
INSERT INTO `edge` VALUES (3637, '', 0, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460795122.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:13:15', NULL, NULL);
INSERT INTO `edge` VALUES (3638, 'System.error', 5, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460803943.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'3\')], bounds: \'[0,938][1080,1330]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:13:24', NULL, NULL);
INSERT INTO `edge` VALUES (3639, '', 0, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460803943.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'3\')], bounds: \'[0,938][1080,1330]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:13:24', NULL, NULL);
INSERT INTO `edge` VALUES (3640, '', 0, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460817573.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:13:38', NULL, NULL);
INSERT INTO `edge` VALUES (3641, '', 0, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460821668.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'4\')], bounds: \'[0,1330][1080,1722]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:13:43', NULL, NULL);
INSERT INTO `edge` VALUES (3642, '', 0, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460844687.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:14:05', NULL, NULL);
INSERT INTO `edge` VALUES (3643, 'System.error', 5, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460849191.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'5\')], bounds: \'[0,1722][1080,1920]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:14:13', NULL, NULL);
INSERT INTO `edge` VALUES (3644, '', 0, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460849191.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'5\')], bounds: \'[0,1722][1080,1920]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:14:13', NULL, NULL);
INSERT INTO `edge` VALUES (3645, '', 0, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460866769.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:14:31', NULL, NULL);
INSERT INTO `edge` VALUES (3646, 'System.error', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460902954.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:15:08', NULL, NULL);
INSERT INTO `edge` VALUES (3647, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460902954.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:15:08', NULL, NULL);
INSERT INTO `edge` VALUES (3648, '', 0, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460935456.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:15:36', NULL, NULL);
INSERT INTO `edge` VALUES (3649, '', 0, 'MainActivity', 'Launcher', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460953709.png', 'Click widget com.example.jingbin.cloudreader:id/iv_avatar, bounds: \'[44,160][209,325]\'', 'CLICK', 'YunYue', 1, 0, 1, '2019-01-14 18:15:53', NULL, NULL);
INSERT INTO `edge` VALUES (3652, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460274790.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:04:35', NULL, NULL);
INSERT INTO `edge` VALUES (3653, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460283685.png', 'Click Return button because handled father components', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:04:44', NULL, NULL);
INSERT INTO `edge` VALUES (3654, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460287119.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_one, bounds: \'[314,55][465,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:04:50', NULL, NULL);
INSERT INTO `edge` VALUES (3655, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460297836.png', 'Click widget com.example.jingbin.cloudreader:id/iv_title_two, bounds: \'[465,55][616,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:04:59', NULL, NULL);
INSERT INTO `edge` VALUES (3656, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460328080.png', 'Click widget com.example.jingbin.cloudreader:id/action_search, bounds: \'[948,66][1080,198]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:05:29', NULL, NULL);
INSERT INTO `edge` VALUES (3658, 'android.system.ErrorException', 5, 'FilmDetailActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460408272.png', 'Click widget //android.widget.ImageView[contains(@content-desc,\'更多选项\')], bounds: \'[969,66][1080,198]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:06:48', NULL, NULL);
INSERT INTO `edge` VALUES (3659, 'android.system.ErrorException', 5, 'WebViewActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460466284.png', 'Click widget //android.widget.ImageButton[contains(@content-desc,\'返回\')], bounds: \'[0,55][154,209]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:07:46', NULL, NULL);
INSERT INTO `edge` VALUES (3679, 'android.system.ErrorException', 5, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460776642.png', 'Click widget //android.widget.LinearLayout[contains(@bounds,\'[0,546][1080,938]\')]', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:13:01', NULL, NULL);
INSERT INTO `edge` VALUES (3680, 'android.system.ErrorException', 5, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460795122.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:13:15', NULL, NULL);
INSERT INTO `edge` VALUES (3681, 'android.system.ErrorException', 5, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460817573.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:13:38', NULL, NULL);
INSERT INTO `edge` VALUES (3682, 'android.system.ErrorException', 5, 'MainActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460821668.png', 'Click widget //android.widget.LinearLayout[contains(@index,\'4\')], bounds: \'[0,1330][1080,1722]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:13:43', NULL, NULL);
INSERT INTO `edge` VALUES (3683, 'android.system.ErrorException', 5, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460844687.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:14:05', NULL, NULL);
INSERT INTO `edge` VALUES (3684, 'android.system.ErrorException', 5, 'FilmDetailActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460866769.png', 'Click Return button because this page has done', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:14:31', NULL, NULL);
INSERT INTO `edge` VALUES (3685, 'android.system.ErrorException', 5, 'MainActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460935456.png', 'Click widget com.example.jingbin.cloudreader:id/ll_title_menu, bounds: \'[0,55][145,209]\'', 'CLICK', 'YunYue', 2, 1, 1, '2019-01-14 18:15:36', NULL, NULL);
INSERT INTO `edge` VALUES (3686, 'android.system.ErrorException', 5, 'MainActivity', 'Launcher', 'http://121.41.17.111:8080/yunyue2/pic/8df430a8_1547460953709.png', 'Click widget com.example.jingbin.cloudreader:id/iv_avatar, bounds: \'[44,160][209,325]\'', 'CLICK', 'YunYue', 2, 0, 1, '2019-01-14 18:15:53', NULL, NULL);
INSERT INTO `edge` VALUES (3750, '', 0, 'WebViewActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_172138.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3755, '', 0, 'MainActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_155311.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3761, '', 0, 'LoginActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_171414.jpg', 'implicit_back_event', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3762, '', 0, 'NavAboutActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_173246.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3775, '', 0, 'FilmDetailActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_174031.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3780, '', 0, 'FilmDetailActivity', 'FilmDetailActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_174037.jpg', 'implicit_back_event', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3785, '', 0, 'WebViewActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_193531.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3789, '', 0, 'WebViewActivity', 'LoginActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_171414.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3807, '', 0, 'SearchActivity', 'MainActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_170625.jpg', 'implicit_back_event', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3814, '', 0, 'MainActivity', 'SearchActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_170617.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3823, '', 0, 'ViewBigImageActivity', 'ViewBigImageActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_175514.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3834, '', 0, 'WebViewActivity', 'WebViewActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_172059.jpg', 'implicit_back_event', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);
INSERT INTO `edge` VALUES (3840, '', 0, 'LoginActivity', ' LoginActivity', 'http://121.41.17.111:8080/yunyue2/pic/Screenshot_20191027_165826.jpg', 'click', '', 'YunYue', 0, 0, 1, '2019-10-10 17:20:05', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
