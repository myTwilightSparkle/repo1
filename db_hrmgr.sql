/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.1.49-community : Database - db_hrmgr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_hrmgr` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_hrmgr`;

/*Table structure for table `admins` */

DROP TABLE IF EXISTS `admins`;

CREATE TABLE `admins` (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admins` */

/*Table structure for table `attendence` */

DROP TABLE IF EXISTS `attendence`;

CREATE TABLE `attendence` (
  `employee_id` int(10) NOT NULL,
  `start` datetime DEFAULT NULL COMMENT '出勤开始时间',
  `end` datetime DEFAULT NULL COMMENT '出勤结束时间',
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `attendence_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attendence` */

/*Table structure for table `depts` */

DROP TABLE IF EXISTS `depts`;

CREATE TABLE `depts` (
  `dept_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `depts` */

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `employee_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(5) NOT NULL,
  `gender` varchar(2) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `dept_id` int(10) DEFAULT NULL,
  `resigned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  KEY `dept_id` (`dept_id`),
  KEY `age` (`age`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `depts` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employees` */

/*Table structure for table `leave` */

DROP TABLE IF EXISTS `leave`;

CREATE TABLE `leave` (
  `leave_id` int(20) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `start` date NOT NULL COMMENT '申请开始时间',
  `end` date DEFAULT NULL COMMENT '申请结束时间',
  PRIMARY KEY (`leave_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `leave_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leave` */

/*Table structure for table `overtime` */

DROP TABLE IF EXISTS `overtime`;

CREATE TABLE `overtime` (
  `overtime_id` int(20) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`overtime_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `overtime_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `overtime` */

/*Table structure for table `positions` */

DROP TABLE IF EXISTS `positions`;

CREATE TABLE `positions` (
  `position_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `name` varchar(50) NOT NULL,
  `dept_id` int(10) NOT NULL,
  `employee_id` int(10) DEFAULT NULL COMMENT '当前担任者',
  PRIMARY KEY (`position_id`),
  KEY `name` (`name`),
  KEY `dept_id` (`dept_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `positions_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `depts` (`dept_id`),
  CONSTRAINT `positions_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `positions` */

/*Table structure for table `recruitment` */

DROP TABLE IF EXISTS `recruitment`;

CREATE TABLE `recruitment` (
  `recruit_id` int(10) NOT NULL AUTO_INCREMENT,
  `position_id` int(10) NOT NULL,
  `number` int(5) NOT NULL DEFAULT '1',
  `available` tinyint(1) NOT NULL DEFAULT '1',
  `pbdate` date NOT NULL COMMENT '发布日期',
  PRIMARY KEY (`recruit_id`),
  KEY `position_id` (`position_id`),
  CONSTRAINT `recruitment_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `recruitment` */

/*Table structure for table `resumes` */

DROP TABLE IF EXISTS `resumes`;

CREATE TABLE `resumes` (
  `resume_id` int(30) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(10) NOT NULL,
  `objective_id` int(10) DEFAULT NULL COMMENT '意向职位',
  `content` varchar(800) DEFAULT NULL COMMENT '简历正文',
  PRIMARY KEY (`resume_id`),
  KEY `visitor_id` (`visitor_id`),
  KEY `objective_id` (`objective_id`),
  CONSTRAINT `resumes_ibfk_2` FOREIGN KEY (`objective_id`) REFERENCES `positions` (`position_id`),
  CONSTRAINT `resumes_ibfk_1` FOREIGN KEY (`visitor_id`) REFERENCES `visitors` (`visitor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `resumes` */

/*Table structure for table `rwd_pnt` */

DROP TABLE IF EXISTS `rwd_pnt`;

CREATE TABLE `rwd_pnt` (
  `rp_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '奖惩记录id',
  `employee_id` int(10) NOT NULL,
  `date` date NOT NULL,
  `cause` varchar(20) NOT NULL,
  `fine` decimal(10,0) unsigned zerofill NOT NULL COMMENT '正罚负奖',
  PRIMARY KEY (`rp_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `rwd_pnt_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rwd_pnt` */

/*Table structure for table `salaries` */

DROP TABLE IF EXISTS `salaries`;

CREATE TABLE `salaries` (
  `salary_id` int(20) NOT NULL AUTO_INCREMENT,
  `employee_id` int(10) NOT NULL,
  `salary` decimal(10,0) NOT NULL,
  `date` date NOT NULL COMMENT '发放日期',
  `haspaid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未发放1已发放',
  PRIMARY KEY (`salary_id`),
  KEY `employee_id` (`employee_id`),
  KEY `salary` (`salary`),
  CONSTRAINT `salaries_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salaries` */

/*Table structure for table `training` */

DROP TABLE IF EXISTS `training`;

CREATE TABLE `training` (
  `training_id` int(5) NOT NULL AUTO_INCREMENT,
  `teacher` varchar(20) NOT NULL,
  `issue` varchar(30) NOT NULL,
  `begin_date` date NOT NULL,
  `finish_date` date NOT NULL,
  PRIMARY KEY (`training_id`),
  KEY `teacher` (`teacher`),
  KEY `issue` (`issue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training` */

/*Table structure for table `visitors` */

DROP TABLE IF EXISTS `visitors`;

CREATE TABLE `visitors` (
  `visitor_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(5) NOT NULL,
  `gender` varchar(2) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`visitor_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`),
  KEY `age` (`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `visitors` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
