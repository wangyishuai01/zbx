/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:36:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cus_buy_history`
-- ----------------------------
DROP TABLE IF EXISTS `cus_buy_history`;
CREATE TABLE `cus_buy_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `customer_name` varchar(10) DEFAULT NULL,
  `buy_type` int(1) DEFAULT NULL COMMENT '购买类型：1：文章 2：视屏',
  `product_id` int(11) DEFAULT NULL COMMENT '文章id或视屏id',
  `pay_type` int(1) DEFAULT NULL COMMENT '付费类型 1：支付宝 2：微信',
  `pay_money` int(11) DEFAULT NULL COMMENT '付费金额',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cus_buy_history
-- ----------------------------
