/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-30 22:38:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `commenttype` char(1) DEFAULT '1' COMMENT '评论类型：1：文章 2：视频',
  `customer_id` int(11) DEFAULT NULL,
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  `commenttoid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', 'java知识', '还不错！大师傅沃尔沃儿童问题热舞特温柔玩儿玩儿玩儿完而温柔玩儿we温柔玩儿玩儿玩儿完温柔玩儿玩儿玩儿玩儿完钱温柔玩儿温特温柔图为玩儿玩儿玩儿沃尔沃儿童温柔', '1', '1', '2017-10-04 01:38:34', '2017-10-04 01:38:37', '1');
INSERT INTO `comment` VALUES ('3', 'java知识', '还不错！大师傅沃尔沃儿童问题热舞特温柔玩儿玩儿玩儿完而', '1', '1', '2017-10-04 01:38:34', '2017-10-04 01:38:37', '1');
INSERT INTO `comment` VALUES ('6', 'java知识', '还不错！6', '1', '1', '2017-10-04 01:38:34', '2017-10-04 01:38:37', '1');
INSERT INTO `comment` VALUES ('7', 'java知识', '还不错！7', '1', '1', '2017-10-04 01:38:34', '2017-10-04 01:38:37', '1');
INSERT INTO `comment` VALUES ('2', 'wrwqeqw沃尔沃去', '124321312342134我去而且维权', '1', '1', '2017-10-24 23:24:51', '2017-10-24 23:24:54', '1');
INSERT INTO `comment` VALUES ('4', '为热吻企鹅', '委屈委屈', '1', '1', '2017-10-24 23:25:21', '2017-10-24 23:25:23', '1');
