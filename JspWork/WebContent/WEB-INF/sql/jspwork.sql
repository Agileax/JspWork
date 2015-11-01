/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : jspwork

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-11-01 22:27:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` char(36) NOT NULL,
  `name` char(12) NOT NULL,
  `phone` char(11) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` char(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `contents` text,
  `start` char(10) DEFAULT NULL,
  `end` char(10) DEFAULT NULL,
  `staff` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cs
-- ----------------------------
DROP TABLE IF EXISTS `cs`;
CREATE TABLE `cs` (
  `id` char(36) NOT NULL,
  `clientName` char(12) DEFAULT NULL,
  `clientOpinion` varchar(100) DEFAULT NULL,
  `staff` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` char(36) NOT NULL,
  `name` char(120) DEFAULT NULL,
  `modelNum` char(120) DEFAULT NULL,
  `number` char(120) DEFAULT NULL,
  `price` char(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` char(36) NOT NULL,
  `name` char(120) DEFAULT NULL,
  `sex` char(20) DEFAULT NULL,
  `age` char(110) DEFAULT NULL,
  `education` char(120) DEFAULT NULL,
  `department` char(100) DEFAULT NULL,
  `date` char(200) DEFAULT NULL,
  `duty` char(100) DEFAULT NULL,
  `wage` char(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
