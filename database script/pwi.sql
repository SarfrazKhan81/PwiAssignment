/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.17 : Database - pwidb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `brands` */

DROP TABLE IF EXISTS `brands`;

CREATE TABLE `brands` (
  `BID` int(50) NOT NULL AUTO_INCREMENT,
  `B_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `brands` */

insert  into `brands`(`BID`,`B_NAME`) values (1,'Brand1');
insert  into `brands`(`BID`,`B_NAME`) values (2,'Brand2');

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `IID` int(50) NOT NULL AUTO_INCREMENT,
  `WID` int(50) DEFAULT NULL,
  `PID` int(50) DEFAULT NULL,
  `PSID` int(50) DEFAULT NULL,
  `INSTOCK` int(100) DEFAULT NULL,
  `AVLQTY` int(100) DEFAULT NULL,
  `INTRANSIT` int(100) DEFAULT NULL,
  `MOQ` int(100) DEFAULT NULL,
  `QPB` int(100) DEFAULT NULL,
  `ROP` int(100) DEFAULT NULL,
  PRIMARY KEY (`IID`),
  KEY `PSID` (`PSID`),
  KEY `WID` (`WID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `inventory` */

insert  into `inventory`(`IID`,`WID`,`PID`,`PSID`,`INSTOCK`,`AVLQTY`,`INTRANSIT`,`MOQ`,`QPB`,`ROP`) values (1,1,1,1,1,2222,2,2,2,2);
insert  into `inventory`(`IID`,`WID`,`PID`,`PSID`,`INSTOCK`,`AVLQTY`,`INTRANSIT`,`MOQ`,`QPB`,`ROP`) values (2,1,1,2,900,100,1000,1000,12,100);
insert  into `inventory`(`IID`,`WID`,`PID`,`PSID`,`INSTOCK`,`AVLQTY`,`INTRANSIT`,`MOQ`,`QPB`,`ROP`) values (12,2,1,1,1,2222,2,2,2,2);
insert  into `inventory`(`IID`,`WID`,`PID`,`PSID`,`INSTOCK`,`AVLQTY`,`INTRANSIT`,`MOQ`,`QPB`,`ROP`) values (13,11,1,1,1,1111,1,1,1,1);
insert  into `inventory`(`IID`,`WID`,`PID`,`PSID`,`INSTOCK`,`AVLQTY`,`INTRANSIT`,`MOQ`,`QPB`,`ROP`) values (14,0,1,1,1,2222,2,2,2,2);

/*Table structure for table `locations` */

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `LID` int(50) NOT NULL AUTO_INCREMENT,
  `L_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`LID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `locations` */

insert  into `locations`(`LID`,`L_NAME`) values (1,'USA');
insert  into `locations`(`LID`,`L_NAME`) values (2,'Ireland');
insert  into `locations`(`LID`,`L_NAME`) values (3,'Netherlands');
insert  into `locations`(`LID`,`L_NAME`) values (4,'Dubai');
insert  into `locations`(`LID`,`L_NAME`) values (5,'Australia');
insert  into `locations`(`LID`,`L_NAME`) values (6,'Italy');
insert  into `locations`(`LID`,`L_NAME`) values (7,'Pakistan');
insert  into `locations`(`LID`,`L_NAME`) values (8,'Mexico');

/*Table structure for table `product_sizes` */

DROP TABLE IF EXISTS `product_sizes`;

CREATE TABLE `product_sizes` (
  `PSID` int(50) NOT NULL AUTO_INCREMENT,
  `PID` int(50) DEFAULT NULL COMMENT 'FORGIN KEY OF PRODUCTS TABLE',
  `SIZE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PSID`),
  KEY `PID` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `product_sizes` */

insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (1,1,'10 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (2,1,'20 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (3,1,'30 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (10,4,'110 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (11,4,'120 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (12,4,'130 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (16,6,'110 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (17,6,'120 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (18,6,'130 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (22,8,'110 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (23,8,'120 ml');
insert  into `product_sizes`(`PSID`,`PID`,`SIZE`) values (24,8,'130 ml');

/*Table structure for table `product_types` */

DROP TABLE IF EXISTS `product_types`;

CREATE TABLE `product_types` (
  `PTID` int(50) NOT NULL AUTO_INCREMENT,
  `P_TYPE` varchar(50) DEFAULT NULL,
  `SELLABLE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PTID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `product_types` */

insert  into `product_types`(`PTID`,`P_TYPE`,`SELLABLE`) values (1,'Finished',1);
insert  into `product_types`(`PTID`,`P_TYPE`,`SELLABLE`) values (2,'Component',0);
insert  into `product_types`(`PTID`,`P_TYPE`,`SELLABLE`) values (3,'Packaging',0);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `PID` int(50) NOT NULL AUTO_INCREMENT,
  `P_NAME` varchar(100) DEFAULT NULL,
  `B_NAME` varchar(50) DEFAULT NULL COMMENT 'BRAND NAME',
  `P_TYPE` varchar(50) DEFAULT NULL COMMENT 'PRODUCT TYPE NAME',
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `products` */

insert  into `products`(`PID`,`P_NAME`,`B_NAME`,`P_TYPE`) values (1,'ProductA','Brand1','Finished');
insert  into `products`(`PID`,`P_NAME`,`B_NAME`,`P_TYPE`) values (4,'ProductB','Brand2','Finished');
insert  into `products`(`PID`,`P_NAME`,`B_NAME`,`P_TYPE`) values (6,'ProductD','Brand2','Finished');
insert  into `products`(`PID`,`P_NAME`,`B_NAME`,`P_TYPE`) values (8,'ProductF','Brand2','Finished');

/*Table structure for table `warehouses` */

DROP TABLE IF EXISTS `warehouses`;

CREATE TABLE `warehouses` (
  `WID` int(50) NOT NULL AUTO_INCREMENT,
  `W_NAME` varchar(50) DEFAULT NULL,
  `W_TYPE` char(10) DEFAULT NULL COMMENT 'W for warehouse & O Office',
  `L_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`WID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `warehouses` */

insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (1,'Warehouse1','W','USA');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (2,'Warehouse2','W','USA');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (3,'Warehouse3','W','USA');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (4,'Warehouse4','W','Ireland');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (5,'Warehouse5','W','Ireland');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (6,'Office1','O','USA');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (7,'Office2','O','Ireland');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (18,'Warehouse6','W','Netherlands');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (19,'Warehouse7','W','Netherlands');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (20,'Warehouse8','W','Netherlands');
insert  into `warehouses`(`WID`,`W_NAME`,`W_TYPE`,`L_NAME`) values (21,'Warehouse9','W','Netherlands');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
