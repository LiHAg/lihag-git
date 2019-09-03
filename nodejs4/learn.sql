/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : learn

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-03 16:30:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `aboutus_table`
-- ----------------------------
DROP TABLE IF EXISTS `aboutus_table`;
CREATE TABLE `aboutus_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` text,
  `pic_src` varchar(300) DEFAULT NULL,
  `href` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of aboutus_table
-- ----------------------------

-- ----------------------------
-- Table structure for `admin_table`
-- ----------------------------
DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_table
-- ----------------------------
INSERT INTO `admin_table` VALUES ('1', 'yellow', '10f38eac74359214334540452852cfb6');

-- ----------------------------
-- Table structure for `banner_table`
-- ----------------------------
DROP TABLE IF EXISTS `banner_table`;
CREATE TABLE `banner_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) DEFAULT NULL COMMENT '大标题',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `href` varchar(300) DEFAULT NULL COMMENT '点击链接',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of banner_table
-- ----------------------------
INSERT INTO `banner_table` VALUES ('5', '阿达空间环境卡和快接啊吧vjadjk', 'asdsad', 'gsdsdfsfs');

-- ----------------------------
-- Table structure for `blog_table`
-- ----------------------------
DROP TABLE IF EXISTS `blog_table`;
CREATE TABLE `blog_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `pic_src` varchar(300) DEFAULT NULL,
  `pic_big_src` varchar(300) DEFAULT NULL,
  `summary` varchar(500) DEFAULT NULL,
  `content` text,
  `post_time` int(11) DEFAULT NULL,
  `author` varchar(32) DEFAULT NULL,
  `n_view` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_table
-- ----------------------------

-- ----------------------------
-- Table structure for `contact_table`
-- ----------------------------
DROP TABLE IF EXISTS `contact_table`;
CREATE TABLE `contact_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `weibo` varchar(40) DEFAULT NULL,
  `wx` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contact_table
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_evalution_table`
-- ----------------------------
DROP TABLE IF EXISTS `custom_evalution_table`;
CREATE TABLE `custom_evalution_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `description` varchar(200) DEFAULT NULL COMMENT '评价的详情',
  `src` varchar(300) DEFAULT NULL COMMENT '用户的头像',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_evalution_table
-- ----------------------------
INSERT INTO `custom_evalution_table` VALUES ('8', '阿达空间环境卡和快接啊吧vjadjk', 'sd', '6397eac46d697ee8c06148e58c716ec1.jpg');

-- ----------------------------
-- Table structure for `intro_table`
-- ----------------------------
DROP TABLE IF EXISTS `intro_table`;
CREATE TABLE `intro_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(32) DEFAULT NULL COMMENT '大标题',
  `description` varchar(200) DEFAULT NULL COMMENT '描述文字',
  `href` varchar(300) DEFAULT NULL COMMENT '点击链接',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of intro_table
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_table`
-- ----------------------------
DROP TABLE IF EXISTS `msg_table`;
CREATE TABLE `msg_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `subject` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_table
-- ----------------------------

-- ----------------------------
-- Table structure for `new_table`
-- ----------------------------
DROP TABLE IF EXISTS `new_table`;
CREATE TABLE `new_table` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `summary` varchar(500) DEFAULT NULL COMMENT '简介',
  `ico_src` varchar(300) DEFAULT NULL COMMENT '产品小图标',
  `big_pic_src` varchar(300) DEFAULT NULL COMMENT '详情大图',
  `content` text COMMENT '内容',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_table
-- ----------------------------
