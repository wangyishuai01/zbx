/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-22 17:45:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `keyrelation`
-- ----------------------------
DROP TABLE IF EXISTS `keyrelation`;
CREATE TABLE `keyrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywordsid` int(11) DEFAULT NULL,
  `relationtype` int(1) DEFAULT NULL COMMENT '1：文章 2：视屏',
  `productid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyrelation
-- ----------------------------
