/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:36:32
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
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tclassify
-- ----------------------------
INSERT INTO `tclassify` VALUES ('3', '002', 'web3', '0', '3', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('2', '002', 'web2', '0', '2', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('4', '002', 'web4', '0', '4', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('5', '002', 'web5', '0', '5', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('6', '002', 'web6', '0', '6', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('7', '002', 'web7', '0', '7', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('8', '002', 'web8', '0', '8', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('9', '002', 'web9', '0', '9', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('1', '002', 'web1', '0', '1', '1', null, '1', '2017-10-03 21:53:11', '2017-10-03 21:53:13');
INSERT INTO `tclassify` VALUES ('10', '001', 'wqerw', '0', '1', '1', null, '1', '2017-10-25 23:52:27', '2017-10-25 23:52:30');
INSERT INTO `tclassify` VALUES ('11', '001', 'eqweqeq1', '0', '1', '1', null, '1', '2017-10-26 00:03:56', '2017-10-26 00:03:59');
INSERT INTO `tclassify` VALUES ('12', '001', '未全额区委区·区区区区', '0', '1', '1', null, '0', '2017-10-26 00:02:58', '2017-10-26 00:02:58');
INSERT INTO `tclassify` VALUES ('13', '001', '认为让沃尔沃', '0', '1', '1', null, '1', '2017-10-28 01:58:38', '2017-10-28 01:58:38');
INSERT INTO `tclassify` VALUES ('14', '001', '色纺纱地方', '0', '1', '1', null, '0', '2017-10-28 02:07:05', '2017-10-28 02:07:05');
INSERT INTO `tclassify` VALUES ('15', '001', '是非得失', '0', '1', '1', null, '1', '2017-10-28 02:09:11', '2017-10-28 02:09:11');
