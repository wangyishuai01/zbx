/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-20 22:14:50
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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_main
-- ----------------------------
INSERT INTO `article_main` VALUES ('1', 'java 基本数据类型', 0x3C703EC7EBCECACED2C8A5B6F1C8A4CEB6C6F3B6ECC8ABCEC4A1A4313C696D67207372633D22687474703A2F2F6C6F63616C686F73743A383038302F7A62782F75656469746F722F6A73702F75706C6F61642F696D6167652F32303137313132302F313531313138363234353734343039313430332E706E6722207469746C653D22313531313138363234353734343039313430332E706E672220616C743D22696E646578496D675F30322E706E67222F3E3C2F703E, null, '2', '介绍java中基本数据类型', '0', '1', '2017-11-20 21:57:28', '2017-11-20 22:08:50', '1');
INSERT INTO `article_main` VALUES ('2', 'web 入门', 0x3C703ECDF5C6F3B6ECB5C4C8ABCEC4A1A43C62722F3E3C2F703E, null, '6', null, '1', '1', '2017-11-20 21:59:29', '2017-11-20 22:09:32', '1');
INSERT INTO `article_main` VALUES ('3', 'java 反射机制原理', 0x3C703EB0B0C9BDB5D8C7F8A1A43C62722F3E3C2F703E, null, '4', null, '0', '1', '2017-11-20 22:08:04', '2017-11-20 22:08:04', '1');
