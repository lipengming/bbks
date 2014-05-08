# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.14
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2014-05-03 14:34:25
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for bbks3
DROP DATABASE IF EXISTS `bbks3`;
CREATE DATABASE IF NOT EXISTS `bbks3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bbks3`;


# Dumping structure for table bbks3.sys_user_role
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK660C51788F4D3BB7` (`role_id`),
  KEY `FK660C51787514FBA4` (`user_id`),
  CONSTRAINT `FK660C51787514FBA4` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK660C51788F4D3BB7` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_book
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE IF NOT EXISTS `tb_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(100) DEFAULT NULL,
  `book_name` varchar(250) NOT NULL,
  `book_src` varchar(255) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `e_flag` varchar(255) DEFAULT NULL,
  `has_read` int(11) DEFAULT NULL,
  `is_free` tinyint(1) NOT NULL DEFAULT '0',
  `is_reading` int(11) DEFAULT NULL,
  `isbn` varchar(16) NOT NULL,
  `like_count` int(11) DEFAULT NULL,
  `outline` text,
  `press` varchar(255) DEFAULT NULL,
  `price` double unsigned DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `want_read` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `all_price` varchar(255) DEFAULT NULL,
  `authorintro` text,
  `directory` text,
  `islock` int(11) DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA4F6BA1A2EF495C4` (`category_id`),
  KEY `del_flag` (`del_flag`),
  KEY `e_flag` (`e_flag`),
  KEY `has_read` (`has_read`),
  KEY `is_free` (`is_free`),
  KEY `is_reading` (`is_reading`),
  KEY `want_read` (`want_read`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_bookcontent
DROP TABLE IF EXISTS `tb_bookcontent`;
CREATE TABLE IF NOT EXISTS `tb_bookcontent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `page_num` int(11) DEFAULT NULL,
  `bookid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5A79C91F2B5C8E77` (`bookid`),
  KEY `del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_category
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE IF NOT EXISTS `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `del_flag` char(1) DEFAULT '0',
  `intro` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4263BAF4EB29A98` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_comment
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE IF NOT EXISTS `tb_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audit_date` datetime DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `content_id` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `audit_user_id` bigint(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK38AF558E6FF23340` (`audit_user_id`),
  KEY `module` (`module`),
  KEY `content_id` (`content_id`),
  KEY `audit_date` (`audit_date`),
  KEY `create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_dynamic
DROP TABLE IF EXISTS `tb_dynamic`;
CREATE TABLE IF NOT EXISTS `tb_dynamic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) DEFAULT NULL,
  `creat_by` int(11) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_message
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE IF NOT EXISTS `tb_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) DEFAULT NULL,
  `creat_at` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `fromu` bigint(20) DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL,
  `tou` bigint(20) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_relationship
DROP TABLE IF EXISTS `tb_relationship`;
CREATE TABLE IF NOT EXISTS `tb_relationship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `del_flag` varchar(255) DEFAULT NULL,
  `flow_id` varchar(255) DEFAULT NULL,
  `flowed_id` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_resource
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE IF NOT EXISTS `tb_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `del_flag` varchar(255) DEFAULT NULL,
  `org_size` tinyblob,
  `res_id` varchar(255) DEFAULT NULL,
  `res_src` varchar(255) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `pics` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKECCF42BF989791FE` (`pics`),
  CONSTRAINT `FKECCF42BF989791FE` FOREIGN KEY (`pics`) REFERENCES `tb_dynamic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_role
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE IF NOT EXISTS `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `del_flag` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.tb_user
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE IF NOT EXISTS `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `del_flag` char(1) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `has_read` varchar(5) DEFAULT NULL,
  `is_company` varchar(2) DEFAULT NULL,
  `liking` varchar(5) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `reading` varchar(5) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `want_read` varchar(5) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.t_bookamazon
DROP TABLE IF EXISTS `t_bookamazon`;
CREATE TABLE IF NOT EXISTS `t_bookamazon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `catelog` varchar(255) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `directory` varchar(255) DEFAULT NULL,
  `isbn` text,
  `out_line` text,
  `press` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorintro` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.t_bookdb
DROP TABLE IF EXISTS `t_bookdb`;
CREATE TABLE IF NOT EXISTS `t_bookdb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `catelog` varchar(255) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `directory` text,
  `isbn` varchar(255) DEFAULT NULL,
  `out_line` text,
  `press` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorintro` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `translator` (`translator`),
  KEY `press` (`press`),
  KEY `isbn` (`isbn`),
  KEY `author` (`author`),
  KEY `bookname` (`bookname`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.t_bookdd
DROP TABLE IF EXISTS `t_bookdd`;
CREATE TABLE IF NOT EXISTS `t_bookdd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `catelog` varchar(255) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `directory` text,
  `isbn` varchar(255) DEFAULT NULL,
  `out_line` text,
  `press` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorintro` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  KEY `isbn` (`isbn`),
  KEY `bookname` (`bookname`),
  KEY `press` (`press`),
  KEY `translator` (`translator`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.t_bookjd
DROP TABLE IF EXISTS `t_bookjd`;
CREATE TABLE IF NOT EXISTS `t_bookjd` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `catelog` varchar(255) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `directory` text,
  `isbn` varchar(255) DEFAULT NULL,
  `out_line` text,
  `press` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorintro` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  KEY `isbn` (`isbn`),
  KEY `press` (`press`),
  KEY `translator` (`translator`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

# Data exporting was unselected.


# Dumping structure for table bbks3.t_bookpub
DROP TABLE IF EXISTS `t_bookpub`;
CREATE TABLE IF NOT EXISTS `t_bookpub` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `catelog` varchar(255) DEFAULT NULL,
  `cover_pic` varchar(255) DEFAULT NULL,
  `directory` text,
  `isbn` varchar(255) DEFAULT NULL,
  `out_line` text,
  `press` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `pub_price` double DEFAULT NULL,
  `translator` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `authorintro` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `press` (`press`),
  KEY `translator` (`translator`),
  KEY `bookname` (`bookname`),
  KEY `catelog` (`catelog`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

# Data exporting was unselected.
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
