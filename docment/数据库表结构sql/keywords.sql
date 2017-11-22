/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-22 17:46:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `keywords`
-- ----------------------------
DROP TABLE IF EXISTS `keywords`;
CREATE TABLE `keywords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `excerpt` varchar(50) DEFAULT NULL,
  `state` int(1) DEFAULT '1' COMMENT '0：否  1：是',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keywords
-- ----------------------------
