/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-30 22:19:03
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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_main
-- ----------------------------
INSERT INTO `article_main` VALUES ('1', 'rwqweq', 0x3C703E3C696D67207372633D22687474703A2F2F696D672E62616964752E636F6D2F68692F6C64772F775F303030322E676966222F3ECED8CED8CED8CED8CED8CED8CED8CED83C2F703E, 'werwqerw', '1', '', '1', '0', '2017-10-03 17:26:43', '2017-10-03 17:26:48', '1');
INSERT INTO `article_main` VALUES ('2', 'w4242', null, '32432423', '1', null, '1', '0', '2017-10-03 17:29:55', '2017-10-03 17:30:00', null);
INSERT INTO `article_main` VALUES ('3', 'dfsdxcgd', null, 'dfgerq232', '1', null, '1', '0', '2017-10-03 17:30:34', '2017-10-03 17:30:38', null);
INSERT INTO `article_main` VALUES ('4', 'weqw', null, 'wrew', '2', null, '1', '0', '2017-10-03 17:31:15', '2017-10-03 17:31:26', null);
INSERT INTO `article_main` VALUES ('5', 'srds', null, 'wer', '2', null, '1', '0', '2017-10-03 17:31:20', '2017-10-03 17:31:31', null);
