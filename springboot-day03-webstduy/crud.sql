/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : crud

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-03 16:34:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departid` int(11) NOT NULL AUTO_INCREMENT,
  `departmentname` varchar(50) NOT NULL,
  PRIMARY KEY (`departid`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1004', 'aaa');
INSERT INTO `department` VALUES ('1005', 'bbb');
INSERT INTO `department` VALUES ('1006', 'ccc');
INSERT INTO `department` VALUES ('1007', 'ddd');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(20) DEFAULT NULL,
  `gender` int(2) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `departid` int(11) NOT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `departid` (`departid`),
  CONSTRAINT `departid` FOREIGN KEY (`departid`) REFERENCES `department` (`departid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('2001', '李清银', '1', '22@qq.com', '1007', '1994-09-09');
INSERT INTO `employee` VALUES ('2002', '黄小薇', '0', '11@qq.com', '1004', '1996-11-14');
