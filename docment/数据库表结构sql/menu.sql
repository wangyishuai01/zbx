/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50139
Source Host           : localhost:3306
Source Database       : zbx

Target Server Type    : MYSQL
Target Server Version : 50139
File Encoding         : 65001

Date: 2017-10-28 22:36:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `pid` int(11) DEFAULT '0',
  `order` int(11) DEFAULT NULL,
  `level` int(2) DEFAULT '1',
  `desc` varchar(50) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `isdisplay` int(1) DEFAULT '1' COMMENT '是否显示：1：是  0：否',
  `makedate` datetime NOT NULL,
  `modifydate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', '0', '1', '1', null, '/jsp/user/userMain.jsp', '1', '2017-10-02 15:44:15', '2017-10-02 15:44:19');
INSERT INTO `menu` VALUES ('2', '文章管理', '0', '1', '1', null, '/jsp/article/articleMain.jsp', '1', '2017-10-02 15:44:15', '2017-10-02 15:44:19');
INSERT INTO `menu` VALUES ('3', '视频管理', '0', '1', '1', null, '/NewFile.jsp', '1', '2017-10-02 15:44:15', '2017-10-02 15:44:19');
INSERT INTO `menu` VALUES ('4', '类别管理', '0', '1', '1', null, '/jsp/tclassify/classifyMain.jsp', '1', '2017-10-02 15:44:15', '2017-10-02 15:44:19');
INSERT INTO `menu` VALUES ('5', '评论管理', '0', '1', '1', null, '/jsp/comment/commentMain.jsp', '1', '2017-10-02 15:44:15', '2017-10-02 15:44:19');
