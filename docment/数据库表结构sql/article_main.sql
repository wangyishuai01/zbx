/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:35:15
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
  `desc` varchar(200) DEFAULT NULL,
  `isfree` int(1) DEFAULT '1' COMMENT '0：否  1：是',
  `nocomment` int(1) DEFAULT '0' COMMENT '0：否  1：是',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_main
-- ----------------------------
INSERT INTO `article_main` VALUES ('1', 'rwqweq', '', 'werwqerw', '1', '', '1', '0', '2017-10-03 17:26:43', '2017-10-03 17:26:48');
INSERT INTO `article_main` VALUES ('2', 'w4242', null, '32432423', '1', null, '1', '0', '2017-10-03 17:29:55', '2017-10-03 17:30:00');
INSERT INTO `article_main` VALUES ('3', 'dfsdxcgd', null, 'dfgerq232', '1', null, '1', '0', '2017-10-03 17:30:34', '2017-10-03 17:30:38');
INSERT INTO `article_main` VALUES ('4', 'weqw', null, 'wrew', '2', null, '1', '0', '2017-10-03 17:31:15', '2017-10-03 17:31:26');
INSERT INTO `article_main` VALUES ('5', 'srds', null, 'wer', '2', null, '1', '0', '2017-10-03 17:31:20', '2017-10-03 17:31:31');
INSERT INTO `article_main` VALUES ('6', 'sads', null, 'werw', '3', null, '1', '0', '2017-10-03 17:31:23', '2017-10-03 17:31:33');
