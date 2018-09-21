/*
SQLyog Ultimate v8.71 
MySQL - 5.1.40-community : Database - ibss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ibss` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `ibss`;

/*Table structure for table `per_buyrecord` */

DROP TABLE IF EXISTS `per_buyrecord`;

CREATE TABLE `per_buyrecord` (
  `buyRecordKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '购买记录主键',
  `userKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户主键',
  `commodityKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品主键',
  `recordCount` tinyint(10) DEFAULT NULL COMMENT '购买数量',
  `recordDate` datetime DEFAULT NULL COMMENT '购买时间',
  `cost` double DEFAULT NULL COMMENT '花费',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`buyRecordKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='购买记录表';

/*Data for the table `per_buyrecord` */

insert  into `per_buyrecord`(`buyRecordKey`,`userKey`,`commodityKey`,`recordCount`,`recordDate`,`cost`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('08c2cd24-abae-40dd-8830-9cfaaecf04f7','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','db4bb584-cbb6-41a9-a131-98c357f207f2',2,'2018-06-27 08:00:00',1,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:13:22',NULL,NULL,0),('17bc783c-4a59-44fe-852b-8b732bd17af6','38b1881a-21c2-4d54-af57-24810dd49a61','0aa9ee34-d882-431a-8e31-a949eafe5b32',1,'2018-06-26 11:38:00',12,NULL,NULL,NULL,NULL,0),('232ae38b-d032-4631-ab08-152b7e6eb85b','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','4ff91f66-1f61-4372-8191-4593b9e75164',1,'2018-06-27 09:10:00',4.5,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:26:58',NULL,NULL,0),('43a140a0-a9bc-42ea-82b2-b6c741887d23','38b1881a-21c2-4d54-af57-24810dd49a61','0aa9ee34-d882-431a-8e31-a949eafe5b32',2,'2018-06-27 09:23:00',6,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 19:27:48',NULL,NULL,0),('4bcba526-44b9-418d-86a6-784515555d52','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','cc46ae29-9709-403c-aec7-9915c92aedb7',1,'2018-06-27 09:42:00',4,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:13:00',NULL,NULL,0),('62c2ff8b-c577-486a-8633-7eeda203b5d3','15c6fa41-fe6f-4b82-aa73-c56c8c889123','cc46ae29-9709-403c-aec7-9915c92aedb7',2,'2018-06-27 09:56:00',8,'15c6fa41-fe6f-4b82-aa73-c56c8c889123','2018-06-27 21:35:33',NULL,NULL,0),('6743dbf5-efbe-412a-bd48-f09536bd4c32','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','afbcdce5-5a35-488d-8a0b-b2012972228f',2,'2018-06-27 10:10:00',4,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:18:50',NULL,NULL,0),('6842c00f-25b7-49d0-9282-71eb93c0bc43','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','51644fd9-dd24-434f-b0fe-8f8b7baff39d',1,'2018-06-27 12:35:00',5.5,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:16:45',NULL,NULL,0),('8523001f-77b2-47e3-938a-8c2b857df25a','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','67d469a6-71e7-41ce-b969-5a7ebbbeea19',1,'2018-06-27 17:00:00',1,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:19:51',NULL,NULL,0),('8e58df24-eda3-4bc9-8168-ab54eb991265','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','3676e6da-2e21-4515-bb04-566f6d405cfe',1,'2018-06-27 20:55:00',5,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:27:34',NULL,NULL,0),('bb4fee11-8bee-46d1-988c-142685ebcd54','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','db4bb584-cbb6-41a9-a131-98c357f207f2',1,'2018-06-27 21:20:00',0.5,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:26:41',NULL,NULL,0),('d24b51e9-b132-46be-a22d-db3368e9dd60','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','314eb8d2-d1ad-484d-b7fc-942dcea11295',1,'2018-06-27 22:30:00',2,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:14:39',NULL,NULL,0),('d6f73d2b-9b0b-4b1f-b080-8597527283fe','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','346a95c7-82f5-4ab7-adf8-bef6be6e3e51',1,'2018-06-27 23:32:00',5.5,'f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:26:18',NULL,NULL,0),('ec950be4-2303-421c-9a02-75d18d40134f','15c6fa41-fe6f-4b82-aa73-c56c8c889123','4ff91f66-1f61-4372-8191-4593b9e75164',2,'2018-06-28 12:47:13',9,'15c6fa41-fe6f-4b82-aa73-c56c8c889123','2018-06-28 12:47:13',NULL,NULL,0);

/*Table structure for table `per_commodity` */

DROP TABLE IF EXISTS `per_commodity`;

CREATE TABLE `per_commodity` (
  `commodityKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品主键',
  `commodityClassKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品类别主键',
  `commodityName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `commodityID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品ID',
  `price` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '单价',
  `stock` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '库存',
  `remark` text COLLATE utf8_unicode_ci COMMENT '备注',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`commodityKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商品表';

/*Data for the table `per_commodity` */

insert  into `per_commodity`(`commodityKey`,`commodityClassKey`,`commodityName`,`commodityID`,`price`,`stock`,`remark`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('0aa9ee34-d882-431a-8e31-a949eafe5b32','38b1881a-21c2-4d54-af57-24810de49a62','娃哈哈','YW001','3','20','好喝',NULL,NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 11:01:42',0),('314eb8d2-d1ad-484d-b7fc-942dcea11295','38b1881a-21c2-4d54-af57-24810de49a62','农夫山泉矿泉水','YN001','2','67','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:02:34',NULL,NULL,0),('328519d6-0a1a-4c47-86e3-42dd5d2761bb','38b1881a-21c2-4d54-af57-24810de49a62','怡宝矿泉水','YY001','2','59','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:02:06',NULL,NULL,0),('346a95c7-82f5-4ab7-adf8-bef6be6e3e51','717f171d-0d24-4135-8ad2-93e260970b8e','康师傅小鸡炖蘑菇面','PK002','5.5','35','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:04:08',NULL,NULL,0),('3648c13f-810e-497d-8613-85e8d7f20026','38b1881a-21c2-4d54-af57-24810de49a61','奥利奥','BA001','12','35','','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 12:24:02','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 12:24:31',0),('3676e6da-2e21-4515-bb04-566f6d405cfe','ea08b053-0dba-45d4-ae70-2bc3e2e44225','双汇','XS001','5','24','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:09:45',NULL,NULL,0),('4ff91f66-1f61-4372-8191-4593b9e75164','ea08b053-0dba-45d4-ae70-2bc3e2e44225','金锣','XJ001','4.5','21','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:10:54',NULL,NULL,0),('51644fd9-dd24-434f-b0fe-8f8b7baff39d','717f171d-0d24-4135-8ad2-93e260970b8e','康师傅红烧牛肉面','PK001','5.5','26','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:03:25',NULL,NULL,0),('67d469a6-71e7-41ce-b969-5a7ebbbeea19','615a0457-1374-4a62-b6bb-7ad45d600201','纸巾','SZ001','1','50','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:05:41',NULL,NULL,0),('afbcdce5-5a35-488d-8a0b-b2012972228f','7dab55f9-0444-4c6a-8dec-1d995a32aa63','卫龙','LW001','2','46','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:07:30',NULL,NULL,0),('cc46ae29-9709-403c-aec7-9915c92aedb7','92ac4d4d-7e9c-4051-9cd0-70537ab86512','桃李面包','MT001','4','12','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:08:38',NULL,NULL,0),('db4bb584-cbb6-41a9-a131-98c357f207f2','c5b86789-e24a-449b-84ee-e60b10d9010b','小当家干脆面','GX001','0.5','89','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:07:09',NULL,NULL,0);

/*Table structure for table `per_commodityclass` */

DROP TABLE IF EXISTS `per_commodityclass`;

CREATE TABLE `per_commodityclass` (
  `commodityClassKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品类别主键',
  `commodityClassName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品类别名称',
  `commodityClassID` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品类别ID',
  `remark` text COLLATE utf8_unicode_ci COMMENT '备注',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`commodityClassKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='商品类别表';

/*Data for the table `per_commodityclass` */

insert  into `per_commodityclass`(`commodityClassKey`,`commodityClassName`,`commodityClassID`,`remark`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('38b1881a-21c2-4d54-af57-24810de49a61','饼干','B001','',NULL,NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 13:54:44',0),('38b1881a-21c2-4d54-af57-24810de49a62','饮品','Y001',NULL,NULL,NULL,NULL,NULL,0),('615a0457-1374-4a62-b6bb-7ad45d600201','生活用品','S001','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:05:10',NULL,NULL,0),('717f171d-0d24-4135-8ad2-93e260970b8e','泡面','P001','','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 13:55:22','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 13:56:54',0),('7dab55f9-0444-4c6a-8dec-1d995a32aa63','辣条','L001','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:06:28',NULL,NULL,0),('92ac4d4d-7e9c-4051-9cd0-70537ab86512','面包','M001','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:08:06',NULL,NULL,0),('c5b86789-e24a-449b-84ee-e60b10d9010b','干脆面','G001','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:06:41',NULL,NULL,0),('ea08b053-0dba-45d4-ae70-2bc3e2e44225','香肠','X001','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 21:09:07',NULL,NULL,0);

/*Table structure for table `per_music` */

DROP TABLE IF EXISTS `per_music`;

CREATE TABLE `per_music` (
  `musicKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '音乐主键',
  `musicName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '音乐名称',
  `singer` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '歌手',
  `album` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '专辑',
  `musicStyle` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '音乐风格',
  `minute` tinyint(2) DEFAULT NULL COMMENT '时长(分)',
  `second` tinyint(2) DEFAULT NULL COMMENT '时长(秒)',
  `issueDate` date DEFAULT NULL COMMENT '发行时间',
  `language` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '歌曲语言',
  `doWords` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '填词',
  `writeMusic` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '谱曲',
  `remark` text COLLATE utf8_unicode_ci COMMENT '备注',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`musicKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='音乐表';

/*Data for the table `per_music` */

insert  into `per_music`(`musicKey`,`musicName`,`singer`,`album`,`musicStyle`,`minute`,`second`,`issueDate`,`language`,`doWords`,`writeMusic`,`remark`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('0c232974-2d9a-4d7e-a27a-4461efed6bbb','白羊','徐秉龙/沈以诚','《白羊》','0',2,48,'2017-10-26','0','徐秉龙','徐秉龙','好听',NULL,NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 09:28:37',0),('10456399-feb6-4631-9fad-5b3f5e67d813','浮夸','陈奕迅','《U87》','1',4,46,'2005-06-07','1','黄伟文','江志仁','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 20:34:20',NULL,NULL,0),('8609c24f-3ecd-4775-bd55-bcf25ecfd198','PLANET','ラムジ','3ラムジ (3 Lambsey)','1',4,3,'2006-06-07','4','ラムジ','ラムジ','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 20:44:27',NULL,NULL,0),('87dacb07-9608-4b57-8654-926951f5f725','because of you','gummy','《사랑은 없다》','1',3,58,'2013-05-16','3','','','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 20:50:19',NULL,NULL,0),('9f5f657f-1e35-48b3-930f-721ccf3ed0ab','Something just like this','The Chainsmokers/Coldplay','《Memories...Do Not Open》','3',4,8,'2017-02-22','2','Andrew Taggart','Chris Martin','','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-27 20:40:04',NULL,NULL,0),('f453ec36-ece2-42b2-ab0c-223accb4f70a','青花瓷','周杰伦','《我很忙》','1',3,59,'2007-11-02','0','方文山','周杰伦','','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 09:04:09',NULL,NULL,0);

/*Table structure for table `per_musicrecord` */

DROP TABLE IF EXISTS `per_musicrecord`;

CREATE TABLE `per_musicrecord` (
  `musicRecordKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '点歌记录主键',
  `userKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户主键',
  `musicKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '音乐主键',
  `recordDate` datetime DEFAULT NULL COMMENT '点歌时间',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`musicRecordKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='点歌记录表';

/*Data for the table `per_musicrecord` */

insert  into `per_musicrecord`(`musicRecordKey`,`userKey`,`musicKey`,`recordDate`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('0ce2d663-30bc-41ab-81d8-2ec19c5159a4','15c6fa41-fe6f-4b82-aa73-c56c8c889123','f453ec36-ece2-42b2-ab0c-223accb4f70a','2018-06-28 12:48:18','15c6fa41-fe6f-4b82-aa73-c56c8c889123','2018-06-28 12:48:18',NULL,NULL,0),('2dc9a5ce-d35a-4eac-8949-a0bf4700a261','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','10456399-feb6-4631-9fad-5b3f5e67d813','2018-06-27 08:12:00','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:16:21',NULL,NULL,0),('63e9d5da-d625-4a03-a295-b3b4139e753c','15c6fa41-fe6f-4b82-aa73-c56c8c889123','8609c24f-3ecd-4775-bd55-bcf25ecfd198','2018-06-27 10:36:00','15c6fa41-fe6f-4b82-aa73-c56c8c889123','2018-06-27 21:36:01',NULL,NULL,0),('94af8455-d9ed-4c56-89d7-3c43c756da22','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','0c232974-2d9a-4d7e-a27a-4461efed6bbb','2018-06-28 12:46:19','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-28 12:46:19',NULL,NULL,0),('ad3e7e91-89d2-4d58-8278-589dcf56eb79','38b1881a-21c2-4d54-af57-24810dd49a61','0c232974-2d9a-4d7e-a27a-4461efed6bbb','2018-06-25 15:20:00',NULL,NULL,NULL,NULL,0),('bb46ede4-fe97-4a91-9fc0-d5c857f74ad6','38b1881a-21c2-4d54-af57-24810dd49a61','0c232974-2d9a-4d7e-a27a-4461efed6bbb','2018-06-27 18:00:00','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-27 16:44:27',NULL,NULL,0),('bd3e7e91-89d2-4d58-8278-589dcf56eb79','38b1881a-21c2-4d54-af57-24810dd49a61','f453ec36-ece2-42b2-ab0c-223accb4f70a','2018-06-26 16:40:00',NULL,NULL,NULL,NULL,0),('df86c607-bc60-43f1-a23a-974a57704279','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','9f5f657f-1e35-48b3-930f-721ccf3ed0ab','2018-06-27 19:27:00','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','2018-06-27 21:12:29',NULL,NULL,0);

/*Table structure for table `sys_dictionary` */

DROP TABLE IF EXISTS `sys_dictionary`;

CREATE TABLE `sys_dictionary` (
  `DictionaryKey` char(36) NOT NULL,
  `GroupCode` varchar(30) NOT NULL,
  `GroupLabel` varchar(30) NOT NULL,
  `ItemCode` varchar(30) NOT NULL,
  `ItemLabel` varchar(30) NOT NULL,
  `ItemSequence` int(11) NOT NULL,
  `Remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`DictionaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dictionary` */

insert  into `sys_dictionary`(`DictionaryKey`,`GroupCode`,`GroupLabel`,`ItemCode`,`ItemLabel`,`ItemSequence`,`Remark`) values ('bed3daea-9d09-4a7c-903e-99e4f32f9d01','userType','用户类型','1','管理员',1,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d02','userType','用户类型','2','普通用户',2,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d03','userType','用户类型','3','会员',3,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d04','gender','性别','0','男',0,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d05','gender','性别','1','女',1,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d06','newUserType','用户类型','2','普通用户',2,NULL),('bed3daea-9d09-4a7c-903e-99e4f32f9d07','newUserType','用户类型','3','会员',3,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d01','menuLevel','菜单等级','1','一级',1,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d02','menuLevel','菜单等级','2','二级',2,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d45','musicStyle','音乐风格','0','民谣',0,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d46','musicStyle','音乐风格','1','流行',1,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d47','musicStyle','音乐风格','2','摇滚',2,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d53','musicStyle','音乐风格','3','电子',3,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d54','musicStyle','音乐风格','4','舞曲',4,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d55','musicStyle','音乐风格','5','说唱',5,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d57','musicStyle','音乐风格','6','轻音乐',6,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d58','musicStyle','音乐风格','7','古典',7,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d59','musicStyle','音乐风格','8','蓝调',8,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d70','musicStyle','音乐风格','9','古风',9,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d71','musicStyle','音乐风格','10','其他',10,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d72','language','语言','0','国语',0,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d73','language','语言','1','粤语',1,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d74','language','语言','2','英语',2,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d75','language','语言','3','韩语',3,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d76','language','语言','4','日语',4,NULL),('bed3daea-9d09-4a7c-90de-99e4f32f9d77','checkFlag','语言','5','其他',5,NULL);

/*Table structure for table `sys_loginrecord` */

DROP TABLE IF EXISTS `sys_loginrecord`;

CREATE TABLE `sys_loginrecord` (
  `loginRecordKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `userKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ipAddr` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seessionId` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `loginFlag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`loginRecordKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sys_loginrecord` */

insert  into `sys_loginrecord`(`loginRecordKey`,`userKey`,`ipAddr`,`seessionId`,`loginTime`,`loginFlag`) values ('cdf40980-332f-4435-b3b3-ce1b1cb0a2b1','05c6fa41-fe6f-4b82-aa73-c56c8c889466','0:0:0:0:0:0:0:1','AA47CA00E832BD05FFF81D6F29A24CE5','2018-06-29 20:20:54',1);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menuKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单主键',
  `menuName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `menuLevel` tinyint(2) DEFAULT NULL COMMENT '菜单等级',
  `parentMenuKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级菜单主键',
  `groupSequence` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '分组序号',
  `menuSequence` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单序号',
  `menuURL` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单网址',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `remark` text COLLATE utf8_unicode_ci COMMENT '备注',
  `menuLogo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标logo地址',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`menuKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='菜单管理表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menuKey`,`menuName`,`menuLevel`,`parentMenuKey`,`groupSequence`,`menuSequence`,`menuURL`,`userType`,`remark`,`menuLogo`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('02df64f6-2666-4200-9e03-3c0abe4fa610','点歌',1,NULL,'1',NULL,NULL,2,NULL,'fa-music',NULL,NULL,NULL,NULL,0),('02df64f6-2666-4200-9e03-3c0abe4fa611','点歌',1,NULL,'2',NULL,NULL,3,'','fa-music',NULL,NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 13:41:02',0),('461f72f9-230d-405d-b63c-3daa260799da','商品库',2,'869f1a53-9ea9-439c-9678-c85d74def5f0',NULL,'5','/ibss/selectCommodity/list',2,NULL,NULL,NULL,NULL,NULL,NULL,0),('461f72f9-230d-405d-b63c-3daa260799dd','商品库',2,'869f1a53-9ea9-439c-9678-c85d74def5f1',NULL,'6','/ibss/selectCommodity/list',3,'',NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 14:17:55',NULL,NULL,0),('869f1a53-9ea9-439c-9678-c85d74def5f0','购物',1,NULL,'3',NULL,NULL,2,NULL,'fa-cutlery',NULL,NULL,NULL,NULL,0),('869f1a53-9ea9-439c-9678-c85d74def5f1','购物',1,NULL,'4',NULL,NULL,3,'备注','fa-cutlery','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 13:26:46','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 13:40:16',0),('94863a1a-77ed-4644-a091-ca142a6f5220','购买记录',2,'869f1a53-9ea9-439c-9678-c85d74def5f0',NULL,'8','/ibss/buyRecord/list',2,NULL,NULL,NULL,NULL,NULL,NULL,0),('94863a1a-77ed-4644-a091-ca142a6f5221','购买记录',2,'869f1a53-9ea9-439c-9678-c85d74def5f1',NULL,'7','/ibss/buyRecord/list',3,'',NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 14:18:28',NULL,NULL,0),('ac5d7e7c-c9da-4fde-b80e-0fd6d5888e00','音乐库',2,'02df64f6-2666-4200-9e03-3c0abe4fa610',NULL,'1','/ibss/selectMusic/list',2,NULL,NULL,NULL,NULL,NULL,NULL,0),('ac5d7e7c-c9da-4fde-b80e-0fd6d5888e01','音乐库',2,'02df64f6-2666-4200-9e03-3c0abe4fa611',NULL,'2','/ibss/selectMusic/list',3,NULL,NULL,NULL,NULL,NULL,NULL,0),('ac5d7e7c-c9da-4fde-b80e-0fd6d5888e03','点歌记录',2,'02df64f6-2666-4200-9e03-3c0abe4fa610',NULL,'3','/ibss/musicRecord/list',2,NULL,NULL,NULL,NULL,NULL,NULL,0),('ac5d7e7c-c9da-4fde-b80e-0fd6d5888e04','点歌记录',2,'02df64f6-2666-4200-9e03-3c0abe4fa611',NULL,'4','/ibss/musicRecord/list',3,NULL,NULL,NULL,NULL,'38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 13:45:54',0),('b6ca23d8-a60b-4e8e-9582-93fba171fde0','系统管理',1,NULL,'5',NULL,NULL,1,NULL,'fa-gear',NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde1','用户管理',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde0',NULL,'9','/ibss/userInfo/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde2','菜单管理',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde0',NULL,'10','/ibss/menu/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde4','音乐管理',1,NULL,'6',NULL,NULL,1,NULL,'fa-music',NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde5','音乐库管理',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde4',NULL,'11','/ibss/music/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde6','点歌记录',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde4',NULL,'12','/ibss/musicRecord/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde7','商品管理',1,NULL,'7',NULL,NULL,1,NULL,'fa-cutlery',NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde8','商品库管理',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde7',NULL,'13','/ibss/commodity/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fde9','商品类别管理',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde7',NULL,'14','/ibss/commodityClass/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0),('b6ca23d8-a60b-4e8e-9582-93fba171fdf1','购买记录',2,'b6ca23d8-a60b-4e8e-9582-93fba171fde7',NULL,'15','/ibss/buyRecord/list',1,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `sys_message` */

DROP TABLE IF EXISTS `sys_message`;

CREATE TABLE `sys_message` (
  `messageKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '消息主键',
  `userKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户主键',
  `template` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模块ID',
  `content` text COLLATE utf8_unicode_ci COMMENT '内容',
  `result` text COLLATE utf8_unicode_ci COMMENT '发送结果',
  `errorMessage` text COLLATE utf8_unicode_ci COMMENT '错误消息',
  `sendUser` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发送人主键',
  `sendTime` datetime DEFAULT NULL COMMENT '发送时间',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`messageKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `sys_message` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `userKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户主键',
  `userName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `userID` int(4) DEFAULT NULL COMMENT '用户ID',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型标识 1-管理员 2-普通用户 3-会员',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT '0' COMMENT '删除标志 0-未删除 1已删除',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`userKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户账号表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`userKey`,`userName`,`userID`,`password`,`userType`,`lastLoginTime`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`,`version`) values ('05c6fa41-fe6f-4b82-aa73-c56c8c889466','useradmin',1,'4564BD833ECC19CA',1,'2018-06-29 20:20:54',NULL,'2018-06-26 14:41:57',NULL,NULL,0,0),('15c6fa41-fe6f-4b82-aa73-c56c8c889123','431101198607019197',2,'4564BD833ECC19CA',2,'2018-06-28 12:46:52',NULL,'2018-06-26 14:41:57',NULL,NULL,0,2),('38b1881a-21c2-4d54-af57-24810dd49a61','652825197410189885',3,'4564BD833ECC19CA',3,'2018-06-27 19:24:19',NULL,'2018-06-26 14:41:57',NULL,NULL,0,2),('f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','211481199608162018',4,'D108833CA76DF15D7517F3861C853C32',3,'2018-06-28 22:22:17','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 19:26:23','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 19:30:56',0,1);

/*Table structure for table `sys_userinfo` */

DROP TABLE IF EXISTS `sys_userinfo`;

CREATE TABLE `sys_userinfo` (
  `userInfoKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户信息主键',
  `userKey` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户主键',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 0-男 1-女',
  `birth` date DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `balance` double DEFAULT NULL COMMENT '余额',
  `remark` text COLLATE utf8_unicode_ci COMMENT '备注',
  `createrKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifierKey` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `deleteFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`userInfoKey`,`userKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户信息表';

/*Data for the table `sys_userinfo` */

insert  into `sys_userinfo`(`userInfoKey`,`userKey`,`name`,`gender`,`birth`,`phone`,`email`,`balance`,`remark`,`createrKey`,`createDate`,`modifierKey`,`modifyDate`,`deleteFlag`) values ('38b1881a-21c2-4d54-af57-24810dd41111','15c6fa41-fe6f-4b82-aa73-c56c8c889123','王红',1,'1998-02-12','18340829824','132456789qq.com',30,NULL,NULL,'2018-02-22 14:41:57','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-28 11:58:16',0),('61b90ead-070a-46be-82f4-8e6c4d760ad7','05c6fa41-fe6f-4b82-aa73-c56c8c889466','管理员01',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),('67bfdfba-5108-48b3-8c98-ea84a69acd67','38b1881a-21c2-4d54-af57-24810dd49a61','李刚',0,'1996-11-10','18941166645','328897847@qq.com',46,'备注','1b3f2861-1929-4ec1-8ea1-4c133b1541d9','2018-02-22 14:41:57','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-25 15:59:31',0),('d0512bc7-27a9-41b0-89e5-62721e227436','f477bc7d-aa65-4cbf-a4e2-941a5a0bbfdb','赵志沅',0,'1996-08-16','18941166645','328897847@qq.com',196,'','38b1881a-21c2-4d54-af57-24810dd49a61','2018-06-26 19:26:23','05c6fa41-fe6f-4b82-aa73-c56c8c889466','2018-06-28 11:41:40',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
