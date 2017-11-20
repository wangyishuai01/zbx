/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-20 15:26:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tclassify`
-- ----------------------------
DROP TABLE IF EXISTS `tclassify`;
CREATE TABLE `tclassify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pid` int(11) DEFAULT '0',
  `torder` int(11) DEFAULT NULL,
  `level` int(2) DEFAULT '1',
  `desc` varchar(50) DEFAULT NULL,
  `isdisplay` int(1) DEFAULT '1',
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tclassify
-- ----------------------------
INSERT INTO `tclassify` VALUES ('1', '001', 'java', '0', '1', '1', null, '1', '2017-11-20 15:15:58', '2017-11-20 15:15:58');
INSERT INTO `tclassify` VALUES ('2', '001', 'java 初级', '1', '1', '2', null, '1', '2017-11-20 15:17:49', '2017-11-20 15:17:49');
INSERT INTO `tclassify` VALUES ('3', '001', 'java 中级', '1', '1', '1', null, '1', '2017-11-20 15:23:11', '2017-11-20 15:23:43');
INSERT INTO `tclassify` VALUES ('4', '001', 'java 高级', '1', '1', '2', null, '1', '2017-11-20 15:23:31', '2017-11-20 15:23:31');
INSERT INTO `tclassify` VALUES ('5', '001', 'web', '0', '1', '1', null, '1', '2017-11-20 15:24:12', '2017-11-20 15:24:12');
INSERT INTO `tclassify` VALUES ('6', '001', 'web 初级', '5', '1', '2', null, '1', '2017-11-20 15:24:28', '2017-11-20 15:24:28');
INSERT INTO `tclassify` VALUES ('7', '001', 'web 中级', '5', '1', '2', null, '1', '2017-11-20 15:24:42', '2017-11-20 15:24:42');
INSERT INTO `tclassify` VALUES ('8', '001', 'web 高级', '5', '1', '2', null, '1', '2017-11-20 15:24:58', '2017-11-20 15:24:58');
