/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-25 18:38:10
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_main
-- ----------------------------
