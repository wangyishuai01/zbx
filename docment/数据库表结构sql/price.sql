/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-20 22:15:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `price`
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(1) DEFAULT NULL COMMENT '价格类型：1：文章 2：视屏',
  `product_id` int(11) DEFAULT NULL COMMENT '文章id或视屏id',
  `price` double(11,2) DEFAULT '0.00' COMMENT '单位：元',
  `play_number` int(11) DEFAULT '0',
  `sum_price` double(11,2) DEFAULT '0.00' COMMENT '总钱数：单位：元',
  `again_pay_days` int(11) DEFAULT '0' COMMENT '单位：天',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES ('1', '1', '1', '2.00', '0', '0.00', '10', '2017-11-20 21:57:28', '2017-11-20 21:57:28');
INSERT INTO `price` VALUES ('2', '1', '3', '4.00', '0', '0.00', '10', '2017-11-20 22:08:13', '2017-11-20 22:08:13');
INSERT INTO `price` VALUES ('3', '2', '1', '5.00', '0', '0.00', '10', '2017-11-20 22:10:41', '2017-11-20 22:10:41');
INSERT INTO `price` VALUES ('4', '2', '2', '5.00', '0', '0.00', '10', '2017-11-20 22:11:58', '2017-11-20 22:11:58');
