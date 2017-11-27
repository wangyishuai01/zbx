/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-11-25 18:35:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `captcha`
-- ----------------------------
DROP TABLE IF EXISTS `captcha`;
CREATE TABLE `captcha` (
  `sid` int(20) NOT NULL AUTO_INCREMENT,
  `random` int(20) NOT NULL COMMENT '随机数',
  `big_img` varchar(20) DEFAULT NULL,
  `lit_img` varchar(20) DEFAULT NULL,
  `start_x` int(20) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of captcha
-- ----------------------------
INSERT INTO `captcha` VALUES ('1', '1', '/image/1_big.jpg', '/image/1_lit.png', '79');
INSERT INTO `captcha` VALUES ('2', '2', '/image/2_big.jpg', '/image/2_lit.png', '139');
INSERT INTO `captcha` VALUES ('3', '3', '/image/3_big.jpg', '/image/3_lit.png', '106');
INSERT INTO `captcha` VALUES ('4', '4', '/image/4_big.jpg', '/image/4_lit.png', '208');
INSERT INTO `captcha` VALUES ('5', '5', '/image/5_big.jpg', '/image/5_lit.png', '196');
INSERT INTO `captcha` VALUES ('6', '6', '/image/6_big.jpg', '/image/6_lit.png', '177');
INSERT INTO `captcha` VALUES ('7', '7', '/image/7_big.jpg', '/image/7_lit.png', '77');
INSERT INTO `captcha` VALUES ('8', '8', '/image/8_big.jpg', '/image/8_lit.png', '80');
INSERT INTO `captcha` VALUES ('9', '9', '/image/9_big.jpg', '/image/9_lit.png', '296');
INSERT INTO `captcha` VALUES ('10', '10', '/image/10_big.jpg', '/image/10_lit.png', '82');
INSERT INTO `captcha` VALUES ('11', '11', '/image/11_big.jpg', '/image/11_lit.png', '265');
INSERT INTO `captcha` VALUES ('12', '12', '/image/12_big.jpg', '/image/12_lit.png', '127');
INSERT INTO `captcha` VALUES ('13', '13', '/image/13_big.jpg', '/image/13_lit.png', '293');
INSERT INTO `captcha` VALUES ('14', '14', '/image/14_big.jpg', '/image/14_lit.png', '197');
INSERT INTO `captcha` VALUES ('15', '15', '/image/15_big.jpg', '/image/15_lit.png', '155');
INSERT INTO `captcha` VALUES ('16', '16', '/image/16_big.jpg', '/image/16_lit.png', '279');
INSERT INTO `captcha` VALUES ('17', '17', '/image/17_big.jpg', '/image/17_lit.png', '134');
INSERT INTO `captcha` VALUES ('18', '18', '/image/18_big.jpg', '/image/18_lit.png', '97');
INSERT INTO `captcha` VALUES ('19', '19', '/image/19_big.jpg', '/image/19_lit.png', '80');
INSERT INTO `captcha` VALUES ('20', '20', '/image/20_big.jpg', '/image/20_lit.png', '91');
INSERT INTO `captcha` VALUES ('21', '21', '/image/21_big.jpg', '/image/21_lit.png', '129');
INSERT INTO `captcha` VALUES ('22', '22', '/image/22_big.jpg', '/image/22_lit.png', '74');
INSERT INTO `captcha` VALUES ('23', '23', '/image/23_big.jpg', '/image/23_lit.png', '247');
INSERT INTO `captcha` VALUES ('24', '24', '/image/24_big.jpg', '/image/24_lit.png', '276');
INSERT INTO `captcha` VALUES ('25', '25', '/image/25_big.jpg', '/image/25_lit.png', '250');
INSERT INTO `captcha` VALUES ('26', '26', '/image/26_big.jpg', '/image/26_lit.png', '110');
INSERT INTO `captcha` VALUES ('27', '27', '/image/27_big.jpg', '/image/27_lit.png', '90');
INSERT INTO `captcha` VALUES ('28', '28', '/image/28_big.jpg', '/image/28_lit.png', '71');
INSERT INTO `captcha` VALUES ('29', '29', '/image/29_big.jpg', '/image/29_lit.png', '169');
INSERT INTO `captcha` VALUES ('30', '30', '/image/30_big.jpg', '/image/30_lit.png', '94');
INSERT INTO `captcha` VALUES ('31', '31', '/image/31_big.jpg', '/image/31_lit.png', '112');
INSERT INTO `captcha` VALUES ('32', '32', '/image/32_big.jpg', '/image/32_lit.png', '127');
INSERT INTO `captcha` VALUES ('33', '33', '/image/33_big.jpg', '/image/33_lit.png', '119');
INSERT INTO `captcha` VALUES ('34', '34', '/image/34_big.jpg', '/image/34_lit.png', '137');
INSERT INTO `captcha` VALUES ('35', '35', '/image/35_big.jpg', '/image/35_lit.png', '168');
INSERT INTO `captcha` VALUES ('36', '36', '/image/36_big.jpg', '/image/36_lit.png', '240');
INSERT INTO `captcha` VALUES ('37', '37', '/image/37_big.jpg', '/image/37_lit.png', '216');
INSERT INTO `captcha` VALUES ('38', '38', '/image/38_big.jpg', '/image/38_lit.png', '150');
INSERT INTO `captcha` VALUES ('39', '39', '/image/39_big.jpg', '/image/39_lit.png', '222');
INSERT INTO `captcha` VALUES ('40', '40', '/image/40_big.jpg', '/image/40_lit.png', '181');
