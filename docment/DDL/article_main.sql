/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-25 18:36:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article_main`
-- ----------------------------
DROP TABLE IF EXISTS `article_main`;
CREATE TABLE `article_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` blob,
  `imgurl` varchar(50) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `excerpt` varchar(200) DEFAULT NULL,
  `isfree` int(1) DEFAULT '1' COMMENT '0：否  1：是',
  `nocomment` int(1) DEFAULT '0' COMMENT '0：否  1：是',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  `state` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_main
-- ----------------------------
