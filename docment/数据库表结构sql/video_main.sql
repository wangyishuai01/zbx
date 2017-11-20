/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-20 22:15:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `video_main`
-- ----------------------------
DROP TABLE IF EXISTS `video_main`;
CREATE TABLE `video_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `videopath` varchar(50) DEFAULT NULL,
  `imgurl` varchar(50) DEFAULT NULL,
  `articleid` int(11) DEFAULT NULL,
  `suffix` varchar(8) DEFAULT NULL,
  `size` int(11) DEFAULT NULL COMMENT '单位：M（兆）',
  `playtime` int(11) DEFAULT NULL COMMENT '单位：s（秒）',
  `isfree` int(1) DEFAULT '1' COMMENT '0：否  1：是',
  `nocomment` int(1) DEFAULT '0' COMMENT '0：否  1：是',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_main
-- ----------------------------
INSERT INTO `video_main` VALUES ('1', 'java 基本数据类型', 'D://upload/video/20171120/20171120221019.mp4', null, '1', '.mp4', '1', null, '0', '1', '2017-11-20 22:10:41', '2017-11-20 22:10:41');
INSERT INTO `video_main` VALUES ('2', 'java 反射机制原理', 'D://upload/video/20171120/20171120221144.mp4', null, '3', '.mp4', '1', null, '0', '1', '2017-11-20 22:11:58', '2017-11-20 22:12:12');
INSERT INTO `video_main` VALUES ('3', 'web 入门简介', 'D://upload/video/20171120/20171120221336.mp4', null, '2', '.mp4', '1', null, '1', '0', '2017-11-20 22:13:50', '2017-11-20 22:13:50');
