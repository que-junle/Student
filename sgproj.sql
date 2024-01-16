/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : sgproj

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2021-05-24 13:28:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `f_teacher` varchar(30) CHARACTER SET utf8 NOT NULL,
  `f_time` datetime NOT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES ('1', 'GT10', '张飞', '2021-05-01 00:00:00');
INSERT INTO `t_class` VALUES ('2', 'GT11', '赵云', '2021-05-01 00:00:00');
INSERT INTO `t_class` VALUES ('3', 'GT12', '关羽', '2021-05-01 00:00:00');
INSERT INTO `t_class` VALUES ('4', 'GT13', '马超', '2021-05-01 00:00:00');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_time` datetime NOT NULL,
  `f_info` text CHARACTER SET utf8,
  `f_isok` tinyint(4) NOT NULL,
  `fk_roomid` int(11) NOT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `f_room` (`fk_roomid`),
  CONSTRAINT `f_room` FOREIGN KEY (`fk_roomid`) REFERENCES `t_room` (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `pk_id` int(11) NOT NULL,
  `f_address` varchar(25) CHARACTER SET utf8 NOT NULL,
  `f_maxNum` int(11) NOT NULL,
  `f_price` int(6) NOT NULL,
  `f_master` varchar(10) CHARACTER SET utf8 NOT NULL,
  `f_state` tinyint(4) NOT NULL,
  `f_type` varchar(25) CHARACTER SET utf8 NOT NULL,
  `f_sextype` varchar(12) CHARACTER SET utf8 NOT NULL,
  `f_pay` varchar(15) CHARACTER SET utf8 NOT NULL,
  `f_time` datetime NOT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_room
-- ----------------------------

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(10) CHARACTER SET utf8 NOT NULL,
  `f_sex` tinyint(4) NOT NULL,
  `f_photo` varchar(30) CHARACTER SET utf8 NOT NULL,
  `f_phone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `f_time` datetime NOT NULL,
  `fk_classid` int(11) NOT NULL,
  `fk_roomid` int(11) NOT NULL,
  PRIMARY KEY (`pk_id`),
  KEY `fk_class` (`fk_classid`),
  KEY `fk_room` (`fk_roomid`),
  CONSTRAINT `fk_class` FOREIGN KEY (`fk_classid`) REFERENCES `t_class` (`pk_id`),
  CONSTRAINT `fk_room` FOREIGN KEY (`fk_roomid`) REFERENCES `t_room` (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `pk_userid` int(11) NOT NULL AUTO_INCREMENT,
  `f_username` varchar(21) CHARACTER SET utf8 NOT NULL,
  `f_password` varchar(21) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`pk_userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '1');
