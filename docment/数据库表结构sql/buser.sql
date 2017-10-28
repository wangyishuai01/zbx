/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:35:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `buser`
-- ----------------------------
DROP TABLE IF EXISTS `buser`;
CREATE TABLE `buser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `power` char(1) DEFAULT 'A',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buser
-- ----------------------------
INSERT INTO `buser` VALUES ('1', 'admin', 'admin', 'admin', '18311301201', '1061650793@qq.com', 'A', '2017-10-02 14:03:57', '2017-10-02 14:04:03');
