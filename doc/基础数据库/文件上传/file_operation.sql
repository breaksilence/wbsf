/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.28-log : Database - yuxineducation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yuxineducation` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `file_operation_t` */

DROP TABLE IF EXISTS `file_operation_t`;

CREATE TABLE `file_operation_t` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '附件主键',
  `file_name` varchar(50) NOT NULL COMMENT '文件名',
  `original_file_name` varchar(100) DEFAULT NULL COMMENT '原始文件名',
  `description` varchar(255) DEFAULT NULL COMMENT '文件说明',
  `md5` varchar(32) DEFAULT NULL COMMENT '文件的MD5值',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(10) DEFAULT NULL COMMENT '文件类型',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '文件状态(0游离状态,1被引用，-1已过期)',
  `expired _date` datetime DEFAULT NULL COMMENT '过期时间',
  `file_uri` varchar(1000) DEFAULT NULL COMMENT '文件存储路径',
  `download_times` bigint(20) unsigned DEFAULT '0' COMMENT '下载次数',
  `create_by` bigint(20) unsigned DEFAULT NULL COMMENT '创建人',
  `modify_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `modify_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  `delete_flag` int(1) NOT NULL DEFAULT '1' COMMENT '逻辑删除标识(0正常,1删除,-1不可删除，)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='附件表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
