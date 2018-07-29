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

/*Table structure for table `depts` */

DROP TABLE IF EXISTS `depts`;

CREATE TABLE `depts` (
  `dept_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `depts` */

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `employee_id` int(10) NOT NULL AUTO_INCREMENT,
  `realname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dept_id` int(10) DEFAULT NULL,
  `resigned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`employee_id`),
  KEY `dept_id` (`dept_id`),
  KEY `resigned` (`resigned`),
  KEY `email` (`email`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `depts` (`dept_id`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`email`) REFERENCES `visitors` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employees` */

/*Table structure for table `interviews` */

DROP TABLE IF EXISTS `interviews`;

CREATE TABLE `interviews` (
  `interview_id` int(15) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `interviewer` varchar(20) NOT NULL,
  `result` varchar(10) NOT NULL DEFAULT '无' COMMENT '无 未通过 通过',
  PRIMARY KEY (`interview_id`),
  KEY `visitor_id` (`visitor_id`),
  KEY `time` (`time`),
  CONSTRAINT `interviews_ibfk_1` FOREIGN KEY (`visitor_id`) REFERENCES `visitors` (`visitor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `interviews` */

insert  into `interviews`(`interview_id`,`visitor_id`,`time`,`interviewer`,`result`) values (1,1,'2018-07-26 00:11:21','sb','未通过'),(2,1,'2018-07-27 00:12:14','sb','通过'),(3,1,'2018-07-31 00:10:54','sb','无');

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
  `dept_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`position_id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `positions_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `depts` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `positions` */

insert  into `positions`(`position_id`,`name`,`dept_id`) values (1,'idol',NULL),(2,'substitude',NULL),(3,'nerd',NULL),(4,'whore',NULL),(5,'bitch',NULL),(6,'bustard',NULL),(7,'java master',NULL);

/*Table structure for table `recruitments` */

DROP TABLE IF EXISTS `recruitments`;

CREATE TABLE `recruitments` (
  `recruit_id` int(10) NOT NULL AUTO_INCREMENT,
  `position_id` int(10) NOT NULL,
  `demand` varchar(500) NOT NULL,
  `number` int(5) NOT NULL DEFAULT '1',
  `available` tinyint(1) NOT NULL DEFAULT '1',
  `pbdate` date NOT NULL COMMENT '发布日期',
  PRIMARY KEY (`recruit_id`),
  KEY `position_id` (`position_id`),
  CONSTRAINT `recruitments_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `recruitments` */

insert  into `recruitments`(`recruit_id`,`position_id`,`demand`,`number`,`available`,`pbdate`) values (1,1,'java,c',10,1,'2018-07-27'),(2,3,'c,c++',10,1,'2018-07-27'),(3,6,'java',5,0,'2018-07-27'),(4,7,'master',1,1,'2018-07-28'),(5,7,'java',1,1,'2018-07-28'),(6,7,'java',1,1,'2018-07-28'),(7,7,'asdfa',1,1,'2018-07-28'),(8,1,'java',1,1,'2018-07-28'),(9,7,'sdfgs',1,1,'2018-07-28'),(10,7,'sgsd',1,1,'2018-07-28'),(11,7,'xxx',5,1,'2018-07-28'),(12,7,'xxx',5,1,'2018-07-28'),(13,7,'xxx',5,1,'2018-07-28'),(14,7,'xxx',5,1,'2018-07-28'),(15,7,'xxx',5,1,'2018-07-28'),(16,7,'xxx',5,1,'2018-07-28'),(17,7,'xxx',5,1,'2018-07-28'),(18,7,'xxx',5,1,'2018-07-28'),(19,7,'xxx',5,1,'2018-07-28'),(20,7,'xxx',5,1,'2018-07-28'),(21,7,'xxx',5,1,'2018-07-28'),(22,7,'xxx',5,1,'2018-07-28'),(23,7,'xxx',5,1,'2018-07-28'),(24,7,'xxx',5,1,'2018-07-28'),(25,7,'xxx',5,1,'2018-07-28'),(26,7,'xxx',5,1,'2018-07-28'),(27,7,'xxx',5,1,'2018-07-28'),(28,7,'xxx',5,1,'2018-07-28'),(29,7,'xxx',5,1,'2018-07-28'),(30,7,'xxx',5,1,'2018-07-28'),(31,7,'xxx',5,1,'2018-07-28'),(32,7,'xxx',5,1,'2018-07-28'),(33,7,'xxx',5,1,'2018-07-28'),(34,7,'xxx',5,1,'2018-07-28'),(35,7,'xxx',5,1,'2018-07-28'),(36,7,'xxx',5,1,'2018-07-28'),(37,7,'xxx',5,1,'2018-07-28'),(38,7,'xxx',5,1,'2018-07-28'),(39,7,'xxx',5,1,'2018-07-28'),(40,7,'xxx',5,1,'2018-07-28'),(41,7,'xxx',5,1,'2018-07-28'),(42,7,'xxx',5,1,'2018-07-28'),(43,7,'xxx',5,1,'2018-07-28'),(44,7,'xxx',5,1,'2018-07-28'),(45,7,'xxx',5,1,'2018-07-28'),(46,7,'xxx',5,1,'2018-07-28'),(47,7,'xxx',5,1,'2018-07-28'),(48,7,'xxx',5,1,'2018-07-28'),(49,7,'xxx',5,1,'2018-07-28'),(50,7,'xxx',5,1,'2018-07-28'),(51,7,'xxx',5,1,'2018-07-28'),(52,7,'xxx',5,1,'2018-07-28'),(53,7,'xxx',5,1,'2018-07-28'),(54,7,'xxx',5,1,'2018-07-28'),(55,7,'xxx',5,1,'2018-07-28'),(56,7,'xxx',5,1,'2018-07-28'),(57,7,'xxx',5,1,'2018-07-28'),(58,7,'xxx',5,1,'2018-07-28'),(59,7,'xxx',5,1,'2018-07-28'),(60,7,'xxx',5,1,'2018-07-28'),(61,7,'xxx',5,1,'2018-07-28'),(62,7,'xxx',5,1,'2018-07-28');

/*Table structure for table `resumes` */

DROP TABLE IF EXISTS `resumes`;

CREATE TABLE `resumes` (
  `resume_id` int(20) NOT NULL AUTO_INCREMENT,
  `visitor_id` int(10) NOT NULL,
  `position` varchar(20) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`resume_id`),
  KEY `visitor_id` (`visitor_id`),
  KEY `position` (`position`),
  CONSTRAINT `resumes_ibfk_1` FOREIGN KEY (`visitor_id`) REFERENCES `visitors` (`visitor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `resumes` */

insert  into `resumes`(`resume_id`,`visitor_id`,`position`,`content`) values (41,1,'神奇宝贝大师','我是来自马萨拉镇的萨托西，骑过洛奇亚，超梦，裂空座。。。'),(44,1,'巡山大师','safaaasasdfasfzsafafasdasdgasdgasgassdvas');

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
  `basic_salary` decimal(10,0) NOT NULL,
  `bonus` decimal(10,0) NOT NULL DEFAULT '0',
  `rwd_pnt` decimal(10,0) NOT NULL DEFAULT '0',
  `date` date NOT NULL COMMENT '发放日期',
  `haspaid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未发放1已发放',
  PRIMARY KEY (`salary_id`),
  KEY `employee_id` (`employee_id`),
  KEY `salary` (`basic_salary`),
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
  `age` int(5) DEFAULT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `role` varchar(100) NOT NULL DEFAULT 'visitor' COMMENT 'admin employee visitor',
  `permission` varchar(100) NOT NULL DEFAULT 'visitor' COMMENT 'admin employee visitor',
  `position_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`visitor_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `phone` (`phone`),
  KEY `age` (`age`),
  KEY `position_id` (`position_id`),
  CONSTRAINT `visitors_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `visitors` */

insert  into `visitors`(`visitor_id`,`name`,`password`,`age`,`gender`,`email`,`phone`,`address`,`role`,`permission`,`position_id`) values (1,'aa','aa',10,'man','xx','123456','xx','visitor','visitor',NULL),(11,'bb','aa',10,'man','xx1','1234561','xx','employee','employee',NULL),(12,'cc','aa',10,'man','xx2','1234562','xx','admin','admin',NULL),(13,'dd','aa',10,'man','xx3','1234563','xx','visitor','visitor',NULL),(14,'ee','aa',10,'man','xx4','1234564','xx','visitor','visitor',NULL),(15,'ff','aa',10,'man','xx5','1234565','xx','visitor','visitor',NULL),(16,'gg','aa',10,'man','xx6','1234566','xx','visitor','visitor',NULL),(17,'hh','aa',10,'man','xx7','1234567','xx','visitor','visitor',NULL),(18,'ii','aa',10,'man','xx8','1234568','xx','visitor','visitor',NULL),(19,'jj','aa',10,'man','xx9','1234569','xx','visitor','visitor',NULL),(35,'gzx','123',15,'男','zhengxingao@live.cn','13612341234','11','visitor','visitor',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
