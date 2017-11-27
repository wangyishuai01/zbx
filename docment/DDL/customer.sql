/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-25 18:36:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `realname` varchar(11) DEFAULT NULL,
  `sex` char(1) DEFAULT '1' COMMENT '1：男  2：女',
  `age` int(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `makedate` datetime DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  `isdisable` int(1) DEFAULT '1' COMMENT '是否禁用 1-启用 0-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '13112344321', '111222', '13112344321', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-11-18 18:40:42', '0');
INSERT INTO `customer` VALUES ('2', '13112344322', '123', '13112344322', '1232@qq.com', null, '1', '30', '1999-01-26', '2017-10-28 21:36:38', '2017-10-28 21:36:41', '1');
INSERT INTO `customer` VALUES ('3', '13112344323', '123', '13112344323', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('4', '13112344324', '123', '13112344324', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('5', '13112344325', '123', '13112344325', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('6', '13112344326', '123', '13112344326', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('7', '13112344327', '123', '13112344327', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('8', '13112344328', '123', '13112344328', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('9', '13112344329', '123', '13112344329', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '0');
INSERT INTO `customer` VALUES ('10', '13112344330', '123', '13112344330', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('11', '13112344331', '123', '13112344331', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('12', '13112344332', '123', '13112344332', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('13', '13112344333', '123', '13112344333', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('14', '13112344334', '123', '13112344334', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('15', '13112344335', '123', '13112344335', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('16', '13112344336', '123', '13112344336', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
INSERT INTO `customer` VALUES ('17', '13112344337', '123456', '13112344337', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-11-17 22:54:43', '0');
INSERT INTO `customer` VALUES ('18', '13112344338', '123', '13112344338', '123@qq.com', '张三', '1', '30', '1999-01-26', '2017-10-04 01:40:02', '2017-10-04 01:40:05', '1');
