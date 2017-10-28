/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:35:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `buser_limit`
-- ----------------------------
DROP TABLE IF EXISTS `buser_limit`;
CREATE TABLE `buser_limit` (
  `id` int(11) NOT NULL,
  `buser_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buser_limit
-- ----------------------------
INSERT INTO `buser_limit` VALUES ('1', '1', '1');
INSERT INTO `buser_limit` VALUES ('2', '1', '2');
INSERT INTO `buser_limit` VALUES ('3', '1', '3');
INSERT INTO `buser_limit` VALUES ('4', '1', '4');
INSERT INTO `buser_limit` VALUES ('5', '1', '5');
