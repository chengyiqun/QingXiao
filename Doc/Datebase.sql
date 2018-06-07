CREATE DATABASE  IF NOT EXISTS `qing_xiao` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qing_xiao`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qing_xiao
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bonus_points_change`
--

DROP TABLE IF EXISTS `bonus_points_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bonus_points_change` (
  `bonux_points_change_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  `change_reason` varchar(100) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`bonux_points_change_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bonus_points_change_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonus_points_change`
--

LOCK TABLES `bonus_points_change` WRITE;
/*!40000 ALTER TABLE `bonus_points_change` DISABLE KEYS */;
/*!40000 ALTER TABLE `bonus_points_change` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` char(32) NOT NULL,
  `book_name` varchar(50) DEFAULT NULL,
  `publishing_company` varchar(100) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `revision` varchar(50) DEFAULT NULL,
  `publishing_date` date DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `browse_table`
--

DROP TABLE IF EXISTS `browse_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `browse_table` (
  `browse_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `browse_type` smallint(6) DEFAULT NULL,
  `browse_time` datetime DEFAULT NULL,
  `object_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`browse_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `browse_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `browse_table`
--

LOCK TABLES `browse_table` WRITE;
/*!40000 ALTER TABLE `browse_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `browse_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect_table`
--

DROP TABLE IF EXISTS `collect_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collect_table` (
  `collect_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `collect_type` smallint(6) DEFAULT NULL,
  `collect_time` datetime DEFAULT NULL,
  `object_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `collect_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect_table`
--

LOCK TABLES `collect_table` WRITE;
/*!40000 ALTER TABLE `collect_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `collect_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `college_id` char(32) NOT NULL,
  `college_name` varchar(50) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college_major`
--

DROP TABLE IF EXISTS `college_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college_major` (
  `college_major_id` char(32) NOT NULL,
  `university_college_id` char(32) DEFAULT NULL,
  `major_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`college_major_id`),
  KEY `university_college_id` (`university_college_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `college_major_ibfk_1` FOREIGN KEY (`university_college_id`) REFERENCES `university_college` (`university_college_id`),
  CONSTRAINT `college_major_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college_major`
--

LOCK TABLES `college_major` WRITE;
/*!40000 ALTER TABLE `college_major` DISABLE KEYS */;
/*!40000 ALTER TABLE `college_major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_information`
--

DROP TABLE IF EXISTS `contact_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_information` (
  `contact_id` char(32) NOT NULL,
  `university_id` char(32) DEFAULT NULL,
  `contact` varchar(240) DEFAULT NULL,
  `contact_type` smallint(6) DEFAULT NULL,
  `qr_code_store_name` char(200) DEFAULT NULL,
  `qr_code_real_name` char(200) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `university_id` (`university_id`),
  CONSTRAINT `contact_information_ibfk_1` FOREIGN KEY (`university_id`) REFERENCES `university` (`university_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_information`
--

LOCK TABLES `contact_information` WRITE;
/*!40000 ALTER TABLE `contact_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_inform`
--

DROP TABLE IF EXISTS `course_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_inform` (
  `course_id` char(32) NOT NULL,
  `course_code` char(7) DEFAULT NULL,
  `course_name` varchar(40) DEFAULT NULL,
  `course_nature` varchar(14) DEFAULT NULL,
  `course_ascription` varchar(50) DEFAULT NULL,
  `university_id` char(32) DEFAULT NULL,
  `credit` decimal(3,2) DEFAULT NULL,
  `class_hours` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `university_id` (`university_id`),
  CONSTRAINT `course_inform_ibfk_1` FOREIGN KEY (`university_id`) REFERENCES `university` (`university_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_inform`
--

LOCK TABLES `course_inform` WRITE;
/*!40000 ALTER TABLE `course_inform` DISABLE KEYS */;
INSERT INTO `course_inform` VALUES ('2a42e203cd784ef4a6e7a8c9546bb562',NULL,'网络原理',NULL,NULL,NULL,NULL,NULL,NULL),('34190d66fb924c40a3f650a9f9ffad51',NULL,'编译原理实验',NULL,NULL,NULL,NULL,NULL,NULL),('5ec4ead6da2c4a1196ba27ebac39228b',NULL,'专业英语',NULL,NULL,NULL,NULL,NULL,NULL),('62f181766ab749acba65d84f80d56323',NULL,'软件工程课程设计',NULL,NULL,NULL,NULL,NULL,NULL),('65bf120e883341b28b2e4c9db19d2736',NULL,'离散时间系统分析（双语）',NULL,NULL,NULL,NULL,NULL,NULL),('6908a5cd9dae454a802743a7c02fa472',NULL,'数字信号处理（双语）',NULL,NULL,NULL,NULL,NULL,NULL),('69d6e8b628fe453a84500a4b88a7ac25',NULL,'网络原理实验',NULL,NULL,NULL,NULL,NULL,NULL),('7380ce60a8fc4c55a433e260dee9cdb5',NULL,'编译原理',NULL,NULL,NULL,NULL,NULL,NULL),('9e2d7de8d7544cc4a64fb4296b7244b6',NULL,'大学生就业与创业',NULL,NULL,NULL,NULL,NULL,NULL),('cb36b114e9ec4ca08ac46b684d976152',NULL,'计算机科研方法导论与实践',NULL,NULL,NULL,NULL,NULL,NULL),('d414d7d6b0474009b3b47d07078e94e5',NULL,'软件工程概论实验',NULL,NULL,NULL,NULL,NULL,NULL),('de3d4007fdde4e17955d211bad7cdd97',NULL,'软件工程概论',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `course_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_resource`
--

DROP TABLE IF EXISTS `course_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_resource` (
  `course_resource_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `course_id` char(32) DEFAULT NULL,
  `resource_number` char(32) DEFAULT NULL,
  `upload_time` datetime NOT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `resource_store_name` varchar(200) NOT NULL,
  `resource_type` varchar(10) DEFAULT NULL,
  `resource_describe` varchar(140) DEFAULT NULL,
  `resource_path` varchar(300) DEFAULT NULL,
  `comprehensive_score` smallint(6) DEFAULT NULL,
  `like_times` int(10) unsigned DEFAULT '0',
  `comment_times` int(10) unsigned DEFAULT '0',
  `download_times` int(10) unsigned DEFAULT '0',
  `share_times` int(10) unsigned DEFAULT '0',
  `cost_points` smallint(6) DEFAULT '0',
  `cost_points_sum` smallint(6) DEFAULT '0',
  `privileges` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`course_resource_id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_resource_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`),
  CONSTRAINT `course_resource_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course_inform` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_resource`
--

LOCK TABLES `course_resource` WRITE;
/*!40000 ALTER TABLE `course_resource` DISABLE KEYS */;
INSERT INTO `course_resource` VALUES ('1dc604da39df4f35b487b0f687cac5cd','67b18f51a2434a27913d9b03c2d0201d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2018-05-14 18:31:23','.dh','9d4d31efa70346a0a091916420426d3b.dh','.dh','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\9d4d31efa70346a0a091916420426d3b.dh',NULL,0,0,1,0,NULL,NULL,NULL,0),('21904ec9a71d4520b9cac08c7c49f8b6','67b18f51a2434a27913d9b03c2d0201d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2018-05-14 17:55:58','43999559_p0_master1200.jpg','b8f69a15eeb74390a0cb9115c9262d81.jpg','.jpg','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\b8f69a15eeb74390a0cb9115c9262d81.jpg',NULL,0,0,3,0,NULL,NULL,NULL,0),('8a5a78cc1ee549618373b6be79adce17','67b18f51a2434a27913d9b03c2d0201d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2018-05-21 10:51:40','base.apk','0c83ed78b54c478dbd38bef3057ab887.apk','.apk','其他资源','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\0c83ed78b54c478dbd38bef3057ab887.apk',NULL,0,0,0,0,NULL,NULL,NULL,0),('bfc80334a957432d874bb7eae926d649','67b18f51a2434a27913d9b03c2d0201d','2a42e203cd784ef4a6e7a8c9546bb562',NULL,'2018-05-21 23:43:34','网络原理实验PPT - 副本.pptx','4fa946e3a8ed4946bd29bfac92defe70.pptx','.pptx','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\4fa946e3a8ed4946bd29bfac92defe70.pptx',NULL,0,0,2,0,NULL,NULL,NULL,0),('c345901728e44728924d329c9b08259f','67b18f51a2434a27913d9b03c2d0201d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2018-05-14 18:50:28','43999559_p1_master1200.jpg','e7c321fdd5fe4e4d8e389851ab9f240d.jpg','.jpg','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\e7c321fdd5fe4e4d8e389851ab9f240d.jpg',NULL,0,0,1,0,NULL,NULL,NULL,0),('c68f4e70741e46fd9758684e05c196a5','67b18f51a2434a27913d9b03c2d0201d','2a42e203cd784ef4a6e7a8c9546bb562',NULL,'2018-05-29 10:59:16','1473560791225.jpg','2ecb90545ab744daa728cad125807771.jpg','.jpg','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\2ecb90545ab744daa728cad125807771.jpg',NULL,0,0,0,0,NULL,NULL,NULL,0),('f461d473419c4b4cb9e11410b153dc84','67b18f51a2434a27913d9b03c2d0201d','2a42e203cd784ef4a6e7a8c9546bb562',NULL,'2018-05-21 23:29:38','网络原理实验PPT.pptx','7b345e4a183e413e87b66e37ff88bd37.pptx','.pptx','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\7b345e4a183e413e87b66e37ff88bd37.pptx',NULL,0,0,0,0,NULL,NULL,NULL,0),('fca397f8ab24481da36a9851f3895d89','67b18f51a2434a27913d9b03c2d0201d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2018-05-21 10:54:13','43999559_p5_master1200.jpg','66fe37178f844457855fbb1a94fcf427.jpg','.jpg','考试试卷','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\\\courseResource\\66fe37178f844457855fbb1a94fcf427.jpg',NULL,0,0,0,0,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `course_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exp_change`
--

DROP TABLE IF EXISTS `exp_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exp_change` (
  `exp_change_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  `change_reason` varchar(100) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `exp_change_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exp_change`
--

LOCK TABLES `exp_change` WRITE;
/*!40000 ALTER TABLE `exp_change` DISABLE KEYS */;
/*!40000 ALTER TABLE `exp_change` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lable_table`
--

DROP TABLE IF EXISTS `lable_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lable_table` (
  `lable_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `lable` varchar(240) DEFAULT NULL,
  `lable_time` datetime DEFAULT NULL,
  `lable_type` smallint(6) DEFAULT NULL,
  `object_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`lable_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lable_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lable_table`
--

LOCK TABLES `lable_table` WRITE;
/*!40000 ALTER TABLE `lable_table` DISABLE KEYS */;
INSERT INTO `lable_table` VALUES ('00cf4a80a9d2405daaafa9f23a752550','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-29 11:09:05',NULL,'541d4d8718a842ce95a71e9b8231e013',NULL),('01c35652e0e24eaea3a1e42efc144b87','67b18f51a2434a27913d9b03c2d0201d','休闲','2018-05-25 10:49:21',NULL,'b11b4a75883b432a857495636ac4f17e',NULL),('05ca227a7ed64656a1fb7c4c79e17228','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-25 23:08:03',NULL,'57b57ba041f648c683167cd295ad68f0',NULL),('060ff67fc12742fe87ba87baa10a398b','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 16:51:28',NULL,'3502dcbdf0684c81bc74bcf7e99faa20',NULL),('0b69b45bf0f54c9d8ab4755a058e7514','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-29 11:09:05',NULL,'541d4d8718a842ce95a71e9b8231e013',NULL),('0c751406f42441c187e016057c177770','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-25 21:58:10',NULL,'3de8bc96fd3f46a1af3e2ed51cb5408c',NULL),('0fa58bb596e24d9b947b38c7816c4aa4','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-25 23:18:02',NULL,'c8d1bad9071b4778ae998e8ede47ec79',NULL),('12f2f036bc704f80ae005ccc0af31833','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:03:37',NULL,'44fd3c58c05d48e1afa4b7992e5ee450',NULL),('16c9e5844d614d11bc97e805e6f77ae3','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:34',NULL,'5680d0698adc4c40bcd6bde6895d68f6',NULL),('1a920a0af34c4f4580d60e297aafe0da','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:05:22',NULL,'45c34210b99a4ab386b3a099480106d7',NULL),('1fb5050cbd4d4e1caa1539ea6655315f','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 16:51:12',NULL,'6fd1370ca7434bf5b868fdb46d37bd76',NULL),('2369de63f1724ed7873e6780f5614a35','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:29',NULL,'d17b5d3640ec4a4e8da856953bac32a3',NULL),('285341098a8b43a58871a0acd69e85a0','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:44',NULL,'be08634bf11147ba84b1ce39860b5dbf',NULL),('3019e25e5a7d444da4e44e6b54fc7c5c','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:06',NULL,'b8ec83bda49a488cb49cc97f6cb7a1ab',NULL),('35706b5d09c9489d939a5ef867d195f7','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:52',NULL,'25475f74db574b1cb1c1078fb60923aa',NULL),('36b97ff7c3aa412dad51450fe0642e08','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:19',NULL,'40c4514c302e473bb8f974937d676d03',NULL),('380f0c24623e4056a68fce322a91ad44','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-26 15:00:26',NULL,'91374de325c24ba28ee7b7bbf875ba3e',NULL),('3b70bc7d3a784e93903e5c211c992282','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:04',NULL,'f2a7b67b84c94958a13473255130f8b8',NULL),('3bea68b7d00843aeb52cf1db2bd764f5','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:20',NULL,'e0d5f794361d4ee09762d20b4a8cec66',NULL),('3bed2cbb3cae43a487a23619801bbd08','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:39',NULL,'c8fe872b02874301ba518c14bfab2108',NULL),('3f6af823e3f34a82be16a72d232f6783','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:44',NULL,'be08634bf11147ba84b1ce39860b5dbf',NULL),('4654070f85c14236af0fa16c8c1cfcd4','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:12',NULL,'1dab6ba600484365810893e71dc87305',NULL),('47ce2287bc3d4c428224a7261e623204','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:39',NULL,'c8fe872b02874301ba518c14bfab2108',NULL),('4bf8f78e313842829b47eaa5ca660303','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:10',NULL,'bebbd8f9c37e480783d48fde868dbb0c',NULL),('4cf5ff85562b4ea994292bc65867c65f','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-25 23:18:02',NULL,'c8d1bad9071b4778ae998e8ede47ec79',NULL),('4d022c3f939949c2839dd424843a76c1','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:19',NULL,'40c4514c302e473bb8f974937d676d03',NULL),('53051823ef3546d08beb6fa67e9aa365','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:06',NULL,'b8ec83bda49a488cb49cc97f6cb7a1ab',NULL),('574560f464864b669f88fbaf252fdab7','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:01',NULL,'f74bb0d1c9fb4b908cea9fd16860594c',NULL),('5f952c1944fb43a48346bd7af0901dc7','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:41',NULL,'4a53d108ae8f432995696c9797a2db9b',NULL),('613fac42fd87433ba1de51d972d7b276','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:48',NULL,'12018f26ae3c400a9060a2899eadf738',NULL),('6b964899adf94af6963becfb30b980e4','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:31',NULL,'1bb0f861d70543718000ae404581e7a7',NULL),('6cb1613f25b74b21b1181527dd15ca2e','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:05:58',NULL,'8ee18dbf3a6b49b68090cbaf86fd5cc8',NULL),('6de259f2262b406ba780065997f9e47e','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-25 23:08:03',NULL,'57b57ba041f648c683167cd295ad68f0',NULL),('70faa79dad914f0484bbca50483c4321','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:03:37',NULL,'44fd3c58c05d48e1afa4b7992e5ee450',NULL),('7dc23945f7634e838a7e900690991e88','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:52',NULL,'25475f74db574b1cb1c1078fb60923aa',NULL),('7e7ce1a2d2384361ac53aaf71a2c897c','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:29',NULL,'d17b5d3640ec4a4e8da856953bac32a3',NULL),('81ceb119544d4a75b52923f313c3ca2e','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:34',NULL,'5680d0698adc4c40bcd6bde6895d68f6',NULL),('83eeb4fed957436a8ce4b53beec75a01','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:39:10',NULL,'a2b2aafe10fa43f3a16c4afb478db091',NULL),('84c534ba4eff4e8cb9f9693aa26c92cc','67b18f51a2434a27913d9b03c2d0201d','心情','2018-05-25 10:49:21',NULL,'b11b4a75883b432a857495636ac4f17e',NULL),('8f618a338b83433383958e4466c433cf','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-26 22:13:09',NULL,'534ee2965b6e4f249b63fa50cbc70ef7',NULL),('911ce1c4f81146c68362087ecbdf9d64','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:07:02',NULL,'1a1c7d71203a4009828293b59305bbf1',NULL),('930cecf95a97422394acbbbdb086e56c','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:04',NULL,'f2a7b67b84c94958a13473255130f8b8',NULL),('930f9f2803e144a29dfe7905f9002f3d','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:41',NULL,'4a53d108ae8f432995696c9797a2db9b',NULL),('95cc4e7ff0f94d9a86d9fac96d345ce5','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-25 23:16:31',NULL,'0a836d4fd62d4608983fbaa3f6ce483a',NULL),('96290d0729184e278b2cd748265455e6','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:22',NULL,'92499b7e5e234abdbc7e532eb208657e',NULL),('965d11794b544820bfa0edc4a5e1696d','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:39:10',NULL,'a2b2aafe10fa43f3a16c4afb478db091',NULL),('97b64cec04d94796a6a5a6576985eced','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:06:01',NULL,'f74bb0d1c9fb4b908cea9fd16860594c',NULL),('a12d95db12b54d028f75784a488829b6','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 16:51:28',NULL,'3502dcbdf0684c81bc74bcf7e99faa20',NULL),('a34ec02cb8bf41f3803bab358f0a01b7','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:05:52',NULL,'0960c1dfc7c34ff5861eefaf885bcd2c',NULL),('acc6dd667d43468191ced29ec54cd498','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:25',NULL,'6d0ab7b6d3ee4643aa422f703926f22b',NULL),('b2b03d623a0e4f33b009a3c36adc6199','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:25',NULL,'6d0ab7b6d3ee4643aa422f703926f22b',NULL),('b82c8e4b1ef5445a8d347fd44a8df97f','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:05:58',NULL,'8ee18dbf3a6b49b68090cbaf86fd5cc8',NULL),('c1ea2c809f4f4b8683f830f8ab4d2371','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:31',NULL,'1bb0f861d70543718000ae404581e7a7',NULL),('c3b470ad1a4145719e2487ef3c93fdb6','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:10',NULL,'bebbd8f9c37e480783d48fde868dbb0c',NULL),('c59591b7415648508dddd89e3b5b46a7','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-25 21:58:09',NULL,'3de8bc96fd3f46a1af3e2ed51cb5408c',NULL),('c6e0addd03b7448385e41a4b6d65bdc2','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:07:02',NULL,'1a1c7d71203a4009828293b59305bbf1',NULL),('c78bb3c280cf4634a1f2f9b8c6cbf6d4','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:20',NULL,'e0d5f794361d4ee09762d20b4a8cec66',NULL),('cb3a239af2e0431fa0efe599467c9e32','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-25 23:16:31',NULL,'0a836d4fd62d4608983fbaa3f6ce483a',NULL),('cb67f0e8c96e463f9ed65650d2dc257f','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-26 22:13:09',NULL,'534ee2965b6e4f249b63fa50cbc70ef7',NULL),('d2757781dc804288b4cc4de2c84a52d6','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:05:22',NULL,'45c34210b99a4ab386b3a099480106d7',NULL),('d409941730794004a89d2eb48d4a9605','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:02:38',NULL,'086e8a35c1f047b8a065ed2eb95aabff',NULL),('e11e1c9e4da44e0faaf0ab7ce62682f0','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:02:38',NULL,'086e8a35c1f047b8a065ed2eb95aabff',NULL),('e1c450c4d4644ae890eec7fbae5e3581','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 13:43:48',NULL,'12018f26ae3c400a9060a2899eadf738',NULL),('e43e04a9449d4688a3aa79e5b5959cdb','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-26 15:00:26',NULL,'91374de325c24ba28ee7b7bbf875ba3e',NULL),('ee528aa210284c8fa4bdd1c489def935','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 18:05:52',NULL,'0960c1dfc7c34ff5861eefaf885bcd2c',NULL),('f1ebf04c57c64cc793ec4acaec988b47','67b18f51a2434a27913d9b03c2d0201d','lable0','2018-05-27 16:51:12',NULL,'6fd1370ca7434bf5b868fdb46d37bd76',NULL),('f948f90e0b244d47b401e13d0d032814','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 18:06:12',NULL,'1dab6ba600484365810893e71dc87305',NULL),('fb4ac0929294454986649cd626488a48','67b18f51a2434a27913d9b03c2d0201d','lable1','2018-05-27 13:43:22',NULL,'92499b7e5e234abdbc7e532eb208657e',NULL);
/*!40000 ALTER TABLE `lable_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_unlike_table`
--

DROP TABLE IF EXISTS `like_unlike_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_unlike_table` (
  `like_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `like_time` datetime DEFAULT NULL,
  `like_unlike` smallint(6) DEFAULT NULL,
  `like_type` smallint(6) DEFAULT NULL,
  `object_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`like_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `like_unlike_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_unlike_table`
--

LOCK TABLES `like_unlike_table` WRITE;
/*!40000 ALTER TABLE `like_unlike_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_unlike_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_information`
--

DROP TABLE IF EXISTS `login_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_information` (
  `login_information_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_place` varchar(200) DEFAULT NULL,
  `login_ipv4` char(15) DEFAULT NULL,
  `login_ipv6` char(40) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `login_information_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_information`
--

LOCK TABLES `login_information` WRITE;
/*!40000 ALTER TABLE `login_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `login_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `major_id` char(32) NOT NULL,
  `major_code` char(10) DEFAULT NULL,
  `major_name` varchar(50) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major_course`
--

DROP TABLE IF EXISTS `major_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major_course` (
  `major_course_id` char(32) NOT NULL,
  `course_id` char(32) DEFAULT NULL,
  `major_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`major_course_id`),
  KEY `course_id` (`course_id`),
  KEY `major_id` (`major_id`),
  CONSTRAINT `major_course_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_inform` (`course_id`),
  CONSTRAINT `major_course_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `college_major` (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major_course`
--

LOCK TABLES `major_course` WRITE;
/*!40000 ALTER TABLE `major_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `major_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationship`
--

DROP TABLE IF EXISTS `relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationship` (
  `relationship_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `relation_user_id` char(32) DEFAULT NULL,
  `is_friend` smallint(6) DEFAULT NULL,
  `friend_level` smallint(6) DEFAULT NULL,
  `is_defriend` smallint(6) DEFAULT NULL,
  `defriend_level` smallint(6) DEFAULT NULL,
  `relationship_time` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`relationship_id`),
  KEY `user_id` (`user_id`),
  KEY `relation_user_id` (`relation_user_id`),
  CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`),
  CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`relation_user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationship`
--

LOCK TABLES `relationship` WRITE;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reputation_change`
--

DROP TABLE IF EXISTS `reputation_change`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reputation_change` (
  `reputation_change_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `change_time` datetime DEFAULT NULL,
  `change_reason` varchar(100) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `reputation_change_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reputation_change`
--

LOCK TABLES `reputation_change` WRITE;
/*!40000 ALTER TABLE `reputation_change` DISABLE KEYS */;
/*!40000 ALTER TABLE `reputation_change` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_comment`
--

DROP TABLE IF EXISTS `resource_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_comment` (
  `resource_comment_id` char(32) NOT NULL,
  `course_resource_id` varchar(32) DEFAULT NULL,
  `comment_type` smallint(6) DEFAULT NULL,
  `root_comment` char(32) DEFAULT NULL,
  `object_id` varchar(32) DEFAULT NULL,
  `to_user_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `reply_times` int(11) DEFAULT '0',
  `like_times` int(11) DEFAULT '0',
  `share_times` int(11) DEFAULT '0',
  `content` varchar(140) DEFAULT NULL,
  `score` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`resource_comment_id`),
  KEY `course_resource_id` (`course_resource_id`),
  KEY `object_id` (`object_id`),
  KEY `to_user_id` (`to_user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `resource_comment_ibfk_1` FOREIGN KEY (`course_resource_id`) REFERENCES `course_resource` (`course_resource_id`),
  CONSTRAINT `resource_comment_ibfk_3` FOREIGN KEY (`to_user_id`) REFERENCES `user_inform` (`user_id`),
  CONSTRAINT `resource_comment_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_comment`
--

LOCK TABLES `resource_comment` WRITE;
/*!40000 ALTER TABLE `resource_comment` DISABLE KEYS */;
INSERT INTO `resource_comment` VALUES ('2474d0e908de4c0ab193b0f3b51994dc','1dc604da39df4f35b487b0f687cac5cd',0,NULL,'2474d0e908de4c0ab193b0f3b51994dc','67b18f51a2434a27913d9b03c2d0201d','67b18f51a2434a27913d9b03c2d0201d','2018-05-16 23:45:03',0,0,0,'333',0,0),('b8ad423c8c75466e8296e3b508b3a150','1dc604da39df4f35b487b0f687cac5cd',0,NULL,'b8ad423c8c75466e8296e3b508b3a150','67b18f51a2434a27913d9b03c2d0201d','67b18f51a2434a27913d9b03c2d0201d','2018-05-16 23:56:04',0,0,0,'a',0,0),('bf854eb6b7614e7085a9fe6f7cc4b6a9','1dc604da39df4f35b487b0f687cac5cd',0,NULL,'bf854eb6b7614e7085a9fe6f7cc4b6a9','67b18f51a2434a27913d9b03c2d0201d','67b18f51a2434a27913d9b03c2d0201d','2018-05-16 23:45:02',0,0,0,'222',0,0),('e6d93d9e2fdd4192a306203a22bc79d6','1dc604da39df4f35b487b0f687cac5cd',0,NULL,'e6d93d9e2fdd4192a306203a22bc79d6','67b18f51a2434a27913d9b03c2d0201d','67b18f51a2434a27913d9b03c2d0201d','2018-05-16 23:45:00',0,0,0,'111',0,0);
/*!40000 ALTER TABLE `resource_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_download_inform`
--

DROP TABLE IF EXISTS `resource_download_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_download_inform` (
  `resource_download_id` char(32) NOT NULL,
  `course_resource_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `download_time` datetime NOT NULL,
  `download_times` int(11) DEFAULT '0',
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`resource_download_id`),
  KEY `course_resource_id` (`course_resource_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `resource_download_inform_ibfk_1` FOREIGN KEY (`course_resource_id`) REFERENCES `course_resource` (`course_resource_id`),
  CONSTRAINT `resource_download_inform_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_download_inform`
--

LOCK TABLES `resource_download_inform` WRITE;
/*!40000 ALTER TABLE `resource_download_inform` DISABLE KEYS */;
INSERT INTO `resource_download_inform` VALUES ('30136fbe15ee4425b331f2161721ab08','bfc80334a957432d874bb7eae926d649','67b18f51a2434a27913d9b03c2d0201d','2018-05-29 10:59:00',0,NULL),('47b4f9580d2247dfb9563b41b2e9c6fa','21904ec9a71d4520b9cac08c7c49f8b6','67b18f51a2434a27913d9b03c2d0201d','2018-05-21 10:55:10',0,NULL),('48e5c6064b844695975052958018dff1','1dc604da39df4f35b487b0f687cac5cd','67b18f51a2434a27913d9b03c2d0201d','2018-05-14 18:51:03',0,NULL),('914a97fee87947ba91fe51c76cdfcc3f','21904ec9a71d4520b9cac08c7c49f8b6','67b18f51a2434a27913d9b03c2d0201d','2018-05-14 18:48:45',0,NULL),('ab3431bc8a0643bcb01dc4236a513a37','21904ec9a71d4520b9cac08c7c49f8b6','67b18f51a2434a27913d9b03c2d0201d','2018-05-14 18:51:01',0,NULL),('ae5c658e4a014b7baa327d3669471d75','c345901728e44728924d329c9b08259f','67b18f51a2434a27913d9b03c2d0201d','2018-05-14 18:50:59',0,NULL),('b86ce052239b4ba1b6c0f7ad349221e2','bfc80334a957432d874bb7eae926d649','67b18f51a2434a27913d9b03c2d0201d','2018-05-22 00:15:40',0,NULL),('feb5dda27ac44e7a92ca4c1145d108b0',NULL,'67b18f51a2434a27913d9b03c2d0201d','2018-05-14 18:17:00',0,NULL);
/*!40000 ALTER TABLE `resource_download_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `share_table`
--

DROP TABLE IF EXISTS `share_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `share_table` (
  `share_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `share_type` smallint(6) DEFAULT NULL,
  `share_destination` smallint(6) DEFAULT NULL,
  `share_time` datetime DEFAULT NULL,
  `object_id` char(32) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`share_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `share_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share_table`
--

LOCK TABLES `share_table` WRITE;
/*!40000 ALTER TABLE `share_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `share_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_information`
--

DROP TABLE IF EXISTS `sign_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sign_information` (
  `sign_information_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `sign_place` varchar(200) DEFAULT NULL,
  `sign_mood` varchar(20) DEFAULT NULL,
  `sign_text` varchar(240) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `sign_information_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_information`
--

LOCK TABLES `sign_information` WRITE;
/*!40000 ALTER TABLE `sign_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `student_course_id` char(32) NOT NULL,
  `teach_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `choose_course_date` date DEFAULT NULL,
  `score` smallint(6) DEFAULT NULL,
  `grade_point` decimal(3,2) DEFAULT NULL,
  `resit_rebuild_tag` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`student_course_id`),
  KEY `teach_id` (`teach_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `teach_inform` (`teach_id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES ('0ae40607e8fd4460b156f7a9e9a5ce15','d34839b262d54d4ca04e8a13a82a5ab5','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('17d68496a9594356b3461b336ef2e68a','84418e2eb2a44d03ad587b9749b6533f','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('95f23a0786c74f4c8ba1b014ca367d97','958558e0224f4ad488fd553b0c7e1142','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('97cd75ae1b774e8cae28c8f4502af69d','8650d345f93a45d281bf15a848ca7327','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('b8b6ffcadf294d29bf99c63f5dd25295','7a44be1f721b46aab148aa7737e4b542','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('c6de7a51e6274dafb368e49fdab3dd30','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('cabd9f1bf15e49a4af440c57aab4fe8b','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('d0fa42c8c3ec4700a33d96114620e294','2763c3a5a9d942c2b8d30ec3982f2a7c','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('db550b90688840729a2fdc1ec49d115e','e454b47d4fd84ae68bd4336c08009161','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('e7757501ccc0496faee75606ba2fac5d','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('e8a06070daca453fa1319f29002ed6f0','77ad15bbaefd4482ae15c002b7d79e21','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL),('eaba293cf65c4f0e8a55e1da9fd12f9a','5275a63f961440b7978a669d667957f6','67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_id` char(32) NOT NULL,
  `creator_id` char(32) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `subject_type` varchar(30) DEFAULT NULL,
  `topics` int(11) DEFAULT NULL,
  `Image` varchar(300) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_book`
--

DROP TABLE IF EXISTS `teach_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_book` (
  `teach_book_id` char(32) NOT NULL,
  `teach_id` char(32) DEFAULT NULL,
  `book_id` char(32) DEFAULT NULL,
  `book_type` char(20) DEFAULT NULL,
  PRIMARY KEY (`teach_book_id`),
  KEY `teach_id` (`teach_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `teach_book_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `teach_inform` (`teach_id`),
  CONSTRAINT `teach_book_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_book`
--

LOCK TABLES `teach_book` WRITE;
/*!40000 ALTER TABLE `teach_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `teach_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_comment`
--

DROP TABLE IF EXISTS `teach_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_comment` (
  `teach_comment_id` char(32) NOT NULL,
  `teach_id` varchar(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `comment_type` smallint(6) DEFAULT NULL,
  `root_comment` char(32) DEFAULT NULL,
  `object_id` varchar(32) DEFAULT NULL,
  `to_user_id` char(32) DEFAULT NULL,
  `score` smallint(6) DEFAULT NULL,
  `comment_times` int(11) DEFAULT '0',
  `like_times` int(11) DEFAULT '0',
  `share_times` int(11) DEFAULT '0',
  `content` varchar(280) DEFAULT NULL,
  `comment_time` datetime NOT NULL,
  `is_anonymous` smallint(6) DEFAULT '0',
  `status` smallint(6) DEFAULT '0',
  PRIMARY KEY (`teach_comment_id`),
  KEY `teach_id` (`teach_id`),
  KEY `object_id` (`object_id`),
  KEY `to_user_id` (`to_user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `teach_comment_ibfk_1` FOREIGN KEY (`teach_id`) REFERENCES `teach_inform` (`teach_id`),
  CONSTRAINT `teach_comment_ibfk_3` FOREIGN KEY (`to_user_id`) REFERENCES `user_inform` (`user_id`),
  CONSTRAINT `teach_comment_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_comment`
--

LOCK TABLES `teach_comment` WRITE;
/*!40000 ALTER TABLE `teach_comment` DISABLE KEYS */;
INSERT INTO `teach_comment` VALUES ('065de737f49a470aa4db7e33372e1205','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','e95737223b6c4fd19fa14b5d9f8b3a6c',NULL,NULL,0,0,0,'这是测试','2018-05-16 17:24:52',0,0),('0d85d98936544472aad779983d4215cc','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',4,0,0,0,'这老师海星','2018-05-21 19:03:02',0,0),('16f052a086f54e17992fb5424b4bb7b0','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',3,1,0,0,'老师作业布置的多','2018-05-21 19:14:23',0,0),('1a410dd6a06749a99ca1c31636ed25a1','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',NULL,0,0,0,'呵呵，不能用脚本改变量','2018-05-29 11:22:44',0,0),('5ab192371782411d9701476953bec67c','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',1,'6f24e9132c644700928654376c3a6fb0','b04558fdf1144b0d93176d047f391a1b',NULL,NULL,0,0,0,'22222','2018-05-29 11:30:10',0,0),('6f24e9132c644700928654376c3a6fb0','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',NULL,3,0,0,'aaaaaa','2018-05-29 11:24:36',0,0),('7b1a7f5f45c5461191d0763a46256743','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','e95737223b6c4fd19fa14b5d9f8b3a6c',NULL,NULL,0,0,0,'测试','2018-05-16 17:11:30',0,0),('92ec8978790b4bf8b9a2226c0d837025','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','c27c0fef9dfb442497a562d78b68fa7b',NULL,NULL,0,0,0,'没错你就是','2018-05-16 18:22:59',0,0),('a498b3f0acdd401b82f30ee28c021a8c','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','b7e6789ec288493aa2bc08ce7d7094f7',NULL,NULL,0,0,0,'测试回复','2018-05-16 18:07:21',0,0),('a7d693e676d34284949fa9a36e321603','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','a498b3f0acdd401b82f30ee28c021a8c',NULL,NULL,0,0,0,'1234','2018-05-16 18:19:58',0,0),('adb1696ec0c3429ea12645ac9cec1691','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',NULL,0,0,0,'aaaaaa','2018-05-29 11:24:29',0,0),('b04558fdf1144b0d93176d047f391a1b','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',1,'6f24e9132c644700928654376c3a6fb0','6f24e9132c644700928654376c3a6fb0',NULL,NULL,0,0,0,'1111111','2018-05-29 11:30:00',0,0),('b7e6789ec288493aa2bc08ce7d7094f7','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','065de737f49a470aa4db7e33372e1205',NULL,NULL,0,0,0,'回复回复','2018-05-16 18:00:27',0,0),('b9a173b51a5642aaa9cfc15717d9e9e5','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',4,0,0,0,'老师人不错','2018-05-21 19:06:46',0,0),('beaa4e6d1c0b42a8b7b234375a3157e2','c03d6300b79e4bc982b4ed8d6a64aa95','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',NULL,0,0,0,'ggggg','2018-05-29 11:15:52',0,0),('c27c0fef9dfb442497a562d78b68fa7b','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',1,'e95737223b6c4fd19fa14b5d9f8b3a6c','e95737223b6c4fd19fa14b5d9f8b3a6c',NULL,NULL,0,0,0,'我是傻子','2018-05-16 18:22:52',0,0),('c4ae44ab3238468480e176bd5b53dfd0','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',4,1,0,0,'这是测试评论','2018-05-22 01:17:57',0,0),('dd3cc259806d49c1bf4fcfbfa3f36139','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',1,'16f052a086f54e17992fb5424b4bb7b0','16f052a086f54e17992fb5424b4bb7b0',NULL,NULL,0,0,0,'老师那是对你好','2018-05-21 19:14:41',0,0),('e95737223b6c4fd19fa14b5d9f8b3a6c','dce1ba26ebfe48d58246b05b1378f484','67b18f51a2434a27913d9b03c2d0201d',0,NULL,NULL,'67b18f51a2434a27913d9b03c2d0201d',4,7,0,0,'测试','2018-05-16 17:11:12',0,0),('ed9aa52962e64c9b9cca6594366b4034','397d460b653f48f9840c4265ff4610f2','67b18f51a2434a27913d9b03c2d0201d',1,'c4ae44ab3238468480e176bd5b53dfd0','c4ae44ab3238468480e176bd5b53dfd0',NULL,NULL,0,0,0,'这是测试回复','2018-05-22 01:22:15',0,0);
/*!40000 ALTER TABLE `teach_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach_inform`
--

DROP TABLE IF EXISTS `teach_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teach_inform` (
  `teach_id` char(32) NOT NULL,
  `teacher_id` char(32) DEFAULT NULL,
  `course_id` char(32) DEFAULT NULL,
  `comprehensive_score` smallint(6) DEFAULT NULL,
  `teach_time` varchar(40) DEFAULT NULL,
  `start_date` date NOT NULL,
  `comment_times` int(11) DEFAULT '0',
  `start_year` smallint(6) DEFAULT NULL,
  `end_year` smallint(6) DEFAULT NULL,
  `semester` smallint(6) DEFAULT NULL,
  `classroom` varchar(40) DEFAULT NULL,
  `day_of_week` smallint(6) DEFAULT NULL,
  `start_section` smallint(6) DEFAULT NULL,
  `end_section` smallint(6) DEFAULT NULL,
  `start_week` smallint(6) DEFAULT NULL,
  `end_week` smallint(6) DEFAULT NULL,
  `every_week` smallint(6) DEFAULT NULL,
  `same_time` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`teach_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach_inform`
--

LOCK TABLES `teach_inform` WRITE;
/*!40000 ALTER TABLE `teach_inform` DISABLE KEYS */;
INSERT INTO `teach_inform` VALUES ('2763c3a5a9d942c2b8d30ec3982f2a7c','26c5b2e14b5c473199b0c42c66c26bb5','cb36b114e9ec4ca08ac46b684d976152',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,5,5,7,1,6,0,0,NULL),('397d460b653f48f9840c4265ff4610f2','61b70120a4bd4192a17f4bfd4b1db281','6908a5cd9dae454a802743a7c02fa472',NULL,'2017-2018-2','2018-05-07',6,2017,2018,2,NULL,4,9,11,1,17,0,0,NULL),('5275a63f961440b7978a669d667957f6','96515b38610e40e189c29d98c973a9e3','5ec4ead6da2c4a1196ba27ebac39228b',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,5,3,4,1,17,0,0,NULL),('77ad15bbaefd4482ae15c002b7d79e21','f9d13678c8364048a4499a68f8984c57','7380ce60a8fc4c55a433e260dee9cdb5',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,1,5,6,1,13,0,0,NULL),('7a44be1f721b46aab148aa7737e4b542','7197266f4a7443adafbd70744503d5df','65bf120e883341b28b2e4c9db19d2736',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,5,1,2,1,17,0,0,NULL),('84418e2eb2a44d03ad587b9749b6533f','59c492ee9da84cf08bf399aad2d2ccf7','62f181766ab749acba65d84f80d56323',NULL,'2017-2018-1','2018-05-07',0,2017,2018,1,NULL,1,1,2,1,17,1,0,NULL),('8650d345f93a45d281bf15a848ca7327','de7fbbca5f234096984eb913dd905b75','9e2d7de8d7544cc4a64fb4296b7244b6',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,4,1,3,1,6,0,0,NULL),('958558e0224f4ad488fd553b0c7e1142','c732e3f6f99443d994a9dd59f94940b7','d414d7d6b0474009b3b47d07078e94e5',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,1,9,11,2,16,2,0,NULL),('c03d6300b79e4bc982b4ed8d6a64aa95','ee5410b968844244a820f89dc007363d','2a42e203cd784ef4a6e7a8c9546bb562',NULL,'2017-2018-2','2018-05-07',7,2017,2018,2,NULL,3,1,3,1,17,0,0,NULL),('d34839b262d54d4ca04e8a13a82a5ab5','f9d13678c8364048a4499a68f8984c57','34190d66fb924c40a3f650a9f9ffad51',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,1,9,11,1,17,1,1,NULL),('dce1ba26ebfe48d58246b05b1378f484','ee5410b968844244a820f89dc007363d','69d6e8b628fe453a84500a4b88a7ac25',NULL,'2017-2018-2','2018-05-07',37,2017,2018,2,NULL,2,1,4,1,17,1,0,NULL),('e454b47d4fd84ae68bd4336c08009161','c732e3f6f99443d994a9dd59f94940b7','de3d4007fdde4e17955d211bad7cdd97',NULL,'2017-2018-2','2018-05-07',0,2017,2018,2,NULL,1,3,4,1,17,0,0,NULL);
/*!40000 ALTER TABLE `teach_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_inform`
--

DROP TABLE IF EXISTS `teacher_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_inform` (
  `teacher_id` char(32) NOT NULL,
  `employee_number` char(10) DEFAULT NULL,
  `teacher_name` varchar(50) DEFAULT NULL,
  `department` varchar(30) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `teacher_email` varchar(50) DEFAULT NULL,
  `teacher_tel` char(10) DEFAULT NULL,
  `office` varchar(50) DEFAULT NULL,
  `academic_title` varchar(10) DEFAULT NULL,
  `induction_time` date DEFAULT NULL,
  `brief_introduction` varchar(300) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_inform`
--

LOCK TABLES `teacher_inform` WRITE;
/*!40000 ALTER TABLE `teacher_inform` DISABLE KEYS */;
INSERT INTO `teacher_inform` VALUES ('26c5b2e14b5c473199b0c42c66c26bb5',NULL,'杜秀全',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('59c492ee9da84cf08bf399aad2d2ccf7',NULL,'小明',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('61b70120a4bd4192a17f4bfd4b1db281',NULL,'吴小培',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('7197266f4a7443adafbd70744503d5df',NULL,'陶亮',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('96515b38610e40e189c29d98c973a9e3',NULL,'徐沁',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('c732e3f6f99443d994a9dd59f94940b7',NULL,'徐怡',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('de7fbbca5f234096984eb913dd905b75',NULL,'丛言',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ee5410b968844244a820f89dc007363d',NULL,'方贤勇',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('f9d13678c8364048a4499a68f8984c57',NULL,'王爱平',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `teacher_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `topic_id` char(32) NOT NULL,
  `user_id` char(32) DEFAULT NULL,
  `subject_id` char(32) DEFAULT NULL,
  `topic_time` datetime NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `topic_place` varchar(100) DEFAULT NULL,
  `comment_times` int(10) unsigned DEFAULT '0',
  `like_times` int(10) unsigned DEFAULT '0',
  `share_times` int(10) unsigned DEFAULT '0',
  `browse_times` int(10) unsigned DEFAULT '0',
  `dont_mask_stranger` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `user_id` (`user_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES ('0960c1dfc7c34ff5861eefaf885bcd2c','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:05:52','33333333333333','安徽大学罄苑校区',0,0,0,0,0,0),('1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:07:02','wwwwwwwwww','安徽大学罄苑校区',6,0,0,0,0,0),('1dab6ba600484365810893e71dc87305','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:12','999999999999','安徽大学罄苑校区',0,0,0,0,0,0),('25475f74db574b1cb1c1078fb60923aa','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:52','qqqqqqqqq','安徽大学罄苑校区',0,0,0,0,0,0),('44fd3c58c05d48e1afa4b7992e5ee450','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:03:37','动态1.。。。。','安徽大学罄苑校区',0,0,0,0,0,0),('45c34210b99a4ab386b3a099480106d7','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:05:22','222222222','安徽大学罄苑校区',0,0,0,0,0,0),('541d4d8718a842ce95a71e9b8231e013','67b18f51a2434a27913d9b03c2d0201d','','2018-05-29 11:09:05','随便写点东西','安徽大学罄苑校区',2,0,0,0,0,0),('8ee18dbf3a6b49b68090cbaf86fd5cc8','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:05:58','4444444444','安徽大学罄苑校区',0,0,0,0,0,0),('b8ec83bda49a488cb49cc97f6cb7a1ab','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:06','777777777777','安徽大学罄苑校区',0,0,0,0,0,0),('bebbd8f9c37e480783d48fde868dbb0c','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:10','888888888','安徽大学罄苑校区',0,0,0,0,0,0),('e0d5f794361d4ee09762d20b4a8cec66','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:20','000000000000','安徽大学罄苑校区',0,0,0,0,0,0),('f2a7b67b84c94958a13473255130f8b8','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:04','666666666666','安徽大学罄苑校区',0,0,0,0,0,0),('f74bb0d1c9fb4b908cea9fd16860594c','67b18f51a2434a27913d9b03c2d0201d','','2018-05-27 18:06:01','5555555555','安徽大学罄苑校区',0,0,0,0,0,0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_comment`
--

DROP TABLE IF EXISTS `topic_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_comment` (
  `comment_id` char(32) NOT NULL,
  `topic_id` char(32) DEFAULT NULL,
  `user_id` char(32) DEFAULT NULL,
  `comment_type` smallint(6) DEFAULT NULL,
  `root_comment` char(32) DEFAULT NULL,
  `comment_time` datetime NOT NULL,
  `object_id` char(32) DEFAULT NULL,
  `to_user_id` char(32) DEFAULT NULL,
  `like_times` int(10) unsigned DEFAULT '0',
  `reply_times` int(10) unsigned DEFAULT '0',
  `share_times` int(10) unsigned DEFAULT '0',
  `content` varchar(240) DEFAULT NULL,
  `score` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `topic_id` (`topic_id`),
  KEY `object_id` (`object_id`),
  KEY `to_user_id` (`to_user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `topic_comment_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`),
  CONSTRAINT `topic_comment_ibfk_2` FOREIGN KEY (`object_id`) REFERENCES `topic_comment` (`comment_id`),
  CONSTRAINT `topic_comment_ibfk_3` FOREIGN KEY (`to_user_id`) REFERENCES `user_inform` (`user_id`),
  CONSTRAINT `topic_comment_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `user_inform` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_comment`
--

LOCK TABLES `topic_comment` WRITE;
/*!40000 ALTER TABLE `topic_comment` DISABLE KEYS */;
INSERT INTO `topic_comment` VALUES ('000c7d71203a4009828293b59305b000','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-27 14:00:16',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,0,0,'呵呵',0,0),('04634951bae14aada71a779e59eeae3b','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'8cf0f70a94574c27a45868b8bb3d8e52','2018-05-28 19:20:40','8cf0f70a94574c27a45868b8bb3d8e52',NULL,0,0,0,'就是个测试',NULL,0),('0b45c1bafed641e690e8fde19b707852','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-28 09:53:03',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,0,0,'啦啦啦',NULL,0),('14e2d333f5f44a06a8258c7be1baafa4','541d4d8718a842ce95a71e9b8231e013','67b18f51a2434a27913d9b03c2d0201d',1,'55c6cf6eb00b4810b643218f60133e84','2018-05-29 11:09:56','55c6cf6eb00b4810b643218f60133e84',NULL,0,0,0,'啊啊啊',NULL,0),('20465ceabf884a30b07ec6c4607c9da1','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 18:34:51','e0657f1bb9a7436893899a38e3e1982e',NULL,0,0,0,'评论回复测试',NULL,0),('51a191ce3c2e48c39fe980be3f7ed86d','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-28 12:31:47',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,0,0,'就是一个测试',NULL,0),('5469460d8fef443f86602d1b26e329af','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 18:57:48','a3887336d104457a966845a405f941eb','67b18f51a2434a27913d9b03c2d0201d',0,0,0,'回复的回复1',NULL,0),('55c6cf6eb00b4810b643218f60133e84','541d4d8718a842ce95a71e9b8231e013','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-29 11:09:49',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,2,0,'哇哇哇哇哇哇哇哇',NULL,0),('5b254176aa99495597fa360e79ce53e0','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 16:41:16','e0657f1bb9a7436893899a38e3e1982e',NULL,0,0,0,'这是评论回复',NULL,0),('675e509596534f0381c511ed025a1ea9','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-28 11:55:39',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,0,0,'评论',NULL,0),('7150d10eaedf441d875ff67e68ce6da3','541d4d8718a842ce95a71e9b8231e013','67b18f51a2434a27913d9b03c2d0201d',1,'55c6cf6eb00b4810b643218f60133e84','2018-05-29 11:10:01','14e2d333f5f44a06a8258c7be1baafa4','67b18f51a2434a27913d9b03c2d0201d',0,0,0,'灌灌灌灌灌灌灌灌灌',NULL,0),('82acf5ec59ed4074b0f02ebe2f923e2e','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'8cf0f70a94574c27a45868b8bb3d8e52','2018-05-28 19:20:47','04634951bae14aada71a779e59eeae3b','67b18f51a2434a27913d9b03c2d0201d',0,0,0,'回复测试',NULL,0),('87bfae9786d44c92b8b40f900eaac727','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 19:06:04','d74d2ffc104b41f2b6974a09b2ed217c','67b18f51a2434a27913d9b03c2d0201d',0,0,0,'回复的回复2',NULL,0),('8cf0f70a94574c27a45868b8bb3d8e52','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-28 12:32:19',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,2,0,'不是测试还能怎么滴',NULL,0),('a3887336d104457a966845a405f941eb','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 18:39:13','e0657f1bb9a7436893899a38e3e1982e',NULL,0,0,0,'11111111',NULL,0),('aa08d13c9e6f4edcac82b44250f3ac62','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 16:42:08','e0657f1bb9a7436893899a38e3e1982e',NULL,0,0,0,'这是第二条评论回复',NULL,0),('d74d2ffc104b41f2b6974a09b2ed217c','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',1,'e0657f1bb9a7436893899a38e3e1982e','2018-05-28 19:05:53','e0657f1bb9a7436893899a38e3e1982e',NULL,0,0,0,'评论的回复1',NULL,0),('e0657f1bb9a7436893899a38e3e1982e','1a1c7d71203a4009828293b59305bbf1','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-28 12:33:20',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,7,0,'111222',NULL,0),('eaee9f325895421c88ed45e93877196a','541d4d8718a842ce95a71e9b8231e013','67b18f51a2434a27913d9b03c2d0201d',0,NULL,'2018-05-29 11:09:19',NULL,'67b18f51a2434a27913d9b03c2d0201d',0,0,0,'啊啊啊',NULL,0);
/*!40000 ALTER TABLE `topic_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_image`
--

DROP TABLE IF EXISTS `topic_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_image` (
  `image_id` char(32) NOT NULL,
  `topic_id` char(32) DEFAULT NULL,
  `pic_order` smallint(6) DEFAULT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `image_store_name` varchar(50) DEFAULT NULL,
  `image_describe` varchar(280) DEFAULT NULL,
  `image_path` varchar(300) DEFAULT NULL,
  `image_type` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `topic_image_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_image`
--

LOCK TABLES `topic_image` WRITE;
/*!40000 ALTER TABLE `topic_image` DISABLE KEYS */;
INSERT INTO `topic_image` VALUES ('056554bfc8fe4cc380387b68c4025066','0960c1dfc7c34ff5861eefaf885bcd2c',3,'file','3252bded6e3641229ea258933530a0ed.jpg',NULL,'topicPicture\\2018-05-27\\3252bded6e3641229ea258933530a0ed.jpg',1,0),('1a412f43d1c74696b1505ef4360dd201','541d4d8718a842ce95a71e9b8231e013',2,'file','7220ed6ac32b487b9e82924a146df94d.jpg',NULL,'topicPicture\\2018-05-29\\7220ed6ac32b487b9e82924a146df94d.jpg',1,0),('2485223fd0204ab98c4d23d1cf8db02b','44fd3c58c05d48e1afa4b7992e5ee450',2,'file','aabaf6dcc90b48d69165cd9dc0eb98d2.jpg',NULL,'topicPicture\\2018-05-27\\aabaf6dcc90b48d69165cd9dc0eb98d2.jpg',1,0),('2af94fa5b729428c9bcd2fbd9487102d','e0d5f794361d4ee09762d20b4a8cec66',0,'file','25eaf8354f834aeba0be4ca8dc4c1528.jpg',NULL,'topicPicture\\2018-05-27\\25eaf8354f834aeba0be4ca8dc4c1528.jpg',1,0),('2de482120212467ab261ace8a824b840','1a1c7d71203a4009828293b59305bbf1',0,'file','b192e8102b0d4173b7aefc66b8116f24.jpg',NULL,'topicPicture\\2018-05-27\\b192e8102b0d4173b7aefc66b8116f24.jpg',1,0),('3073ad642b34407c9f58b2d4c9b18a23','45c34210b99a4ab386b3a099480106d7',2,'file','95237636b45943728616329449d214be.jpg',NULL,'topicPicture\\2018-05-27\\95237636b45943728616329449d214be.jpg',1,0),('3a2138e68f4742d492458c0eae25c544','0960c1dfc7c34ff5861eefaf885bcd2c',1,'file','bf3b86ae25714b0b9c520dc876e2d69b.jpg',NULL,'topicPicture\\2018-05-27\\bf3b86ae25714b0b9c520dc876e2d69b.jpg',1,0),('4028b8dd619243f59e8ae13ebf7b718b','e0d5f794361d4ee09762d20b4a8cec66',1,'file','4fa32a976fd44e2892d45789250dc40e.jpg',NULL,'topicPicture\\2018-05-27\\4fa32a976fd44e2892d45789250dc40e.jpg',1,0),('5f87221491bf45c5a37b3168d3e2884b','45c34210b99a4ab386b3a099480106d7',1,'file','5e0f6933b1e84653baf3b409322e45a9.jpg',NULL,'topicPicture\\2018-05-27\\5e0f6933b1e84653baf3b409322e45a9.jpg',1,0),('692d26421a774422a7d0982e24d2f0aa','44fd3c58c05d48e1afa4b7992e5ee450',5,'file','1f7c7d6310c64495be1c34e12a70b590.jpg',NULL,'topicPicture\\2018-05-27\\1f7c7d6310c64495be1c34e12a70b590.jpg',1,0),('75c52f6b1c444e7fa0d10e662c34cf80','45c34210b99a4ab386b3a099480106d7',5,'file','9635729a7de44095aa472fc27291321f.jpg',NULL,'topicPicture\\2018-05-27\\9635729a7de44095aa472fc27291321f.jpg',1,0),('7def6332b3114986ae0a0007c49aa816','541d4d8718a842ce95a71e9b8231e013',0,'file','2f8423e77bbb47ef85d80ac9b0365b8f.jpg',NULL,'topicPicture\\2018-05-29\\2f8423e77bbb47ef85d80ac9b0365b8f.jpg',1,0),('7ec608afbfa84ac48f44da535c2f21da','45c34210b99a4ab386b3a099480106d7',3,'file','c398912d606c4d9e98b16600608eb6c7.jpg',NULL,'topicPicture\\2018-05-27\\c398912d606c4d9e98b16600608eb6c7.jpg',1,0),('8530ac9f1a9043c388bc16afe205ccae','0960c1dfc7c34ff5861eefaf885bcd2c',2,'file','01c94e1cb1434d2cba4960eb6f12f4ec.jpg',NULL,'topicPicture\\2018-05-27\\01c94e1cb1434d2cba4960eb6f12f4ec.jpg',1,0),('8836997e0e364966a1a6636dec242a6b','25475f74db574b1cb1c1078fb60923aa',0,'file','45bdfb261611479d93415f629baf44d7.jpg',NULL,'topicPicture\\2018-05-27\\45bdfb261611479d93415f629baf44d7.jpg',1,0),('88d7b93841bc46bfad01c295ea11630d','25475f74db574b1cb1c1078fb60923aa',2,'file','d46cc3ecaa11474ea642c55c4043d1fe.jpg',NULL,'topicPicture\\2018-05-27\\d46cc3ecaa11474ea642c55c4043d1fe.jpg',1,0),('89294de190534ffda218d13bd5df501d','1a1c7d71203a4009828293b59305bbf1',2,'file','f7844e8d0c9c4ea38c5da5e0ff5ed03f.jpg',NULL,'topicPicture\\2018-05-27\\f7844e8d0c9c4ea38c5da5e0ff5ed03f.jpg',1,0),('89e7766633a048e086782ce62b23269e','1a1c7d71203a4009828293b59305bbf1',5,'file','45dfe7b0e9e84b0285c03debef66ac44.jpg',NULL,'topicPicture\\2018-05-27\\45dfe7b0e9e84b0285c03debef66ac44.jpg',1,0),('9117df10d31b4b0baf460921bc646987','25475f74db574b1cb1c1078fb60923aa',1,'file','04cb48574f494a6aa99701ea19f5c3fc.jpg',NULL,'topicPicture\\2018-05-27\\04cb48574f494a6aa99701ea19f5c3fc.jpg',1,0),('9372f7c9d7054d49ae09c1bf3e94b143','44fd3c58c05d48e1afa4b7992e5ee450',8,'file','de72f7f13d684ec8bae61fd4df55b45a.jpg',NULL,'topicPicture\\2018-05-27\\de72f7f13d684ec8bae61fd4df55b45a.jpg',1,0),('945b204a997f425e8f4092a54d24a4b5','e0d5f794361d4ee09762d20b4a8cec66',2,'file','bb923f28baa245aba0f771410769ec6e.jpg',NULL,'topicPicture\\2018-05-27\\bb923f28baa245aba0f771410769ec6e.jpg',1,0),('9f92e98110d5400cbdbed38a76ff83c1','44fd3c58c05d48e1afa4b7992e5ee450',0,'file','f256e784a47845909e2ea8606d213001.jpg',NULL,'topicPicture\\2018-05-27\\f256e784a47845909e2ea8606d213001.jpg',1,0),('9fec7f05f91b4c2d8d8cdb8c29c4e257','0960c1dfc7c34ff5861eefaf885bcd2c',4,'file','9a149e6cc48d4d0d8a68668053d3b1ee.jpg',NULL,'topicPicture\\2018-05-27\\9a149e6cc48d4d0d8a68668053d3b1ee.jpg',1,0),('a90a7fb1516e42949e45fb2ed83aa3af','44fd3c58c05d48e1afa4b7992e5ee450',7,'file','b7d3a8261a5045f3ba262412c273e98a.jpg',NULL,'topicPicture\\2018-05-27\\b7d3a8261a5045f3ba262412c273e98a.jpg',1,0),('ac167d7e492547b7ad163e55268ca841','45c34210b99a4ab386b3a099480106d7',4,'file','cc5512f9dcef4cfbbb5e72984346be77.jpg',NULL,'topicPicture\\2018-05-27\\cc5512f9dcef4cfbbb5e72984346be77.jpg',1,0),('b41247d3030041e5a3e4ac0908c8cf3f','1a1c7d71203a4009828293b59305bbf1',1,'file','9c69defced3e40bab75da5cf8eb6a2c3.jpg',NULL,'topicPicture\\2018-05-27\\9c69defced3e40bab75da5cf8eb6a2c3.jpg',1,0),('cf712607718542708bec5b56ac2c012c','44fd3c58c05d48e1afa4b7992e5ee450',4,'file','3f068974e4884528a7953ec53d9b9bad.jpg',NULL,'topicPicture\\2018-05-27\\3f068974e4884528a7953ec53d9b9bad.jpg',1,0),('d1d2648d4f604c30a2c445d1c0bf8955','0960c1dfc7c34ff5861eefaf885bcd2c',0,'file','c3f0503cf16441b6bde27b72ee41552b.jpg',NULL,'topicPicture\\2018-05-27\\c3f0503cf16441b6bde27b72ee41552b.jpg',1,0),('d420a7a2c5624289bdd5334905dfe6a8','45c34210b99a4ab386b3a099480106d7',0,'file','c7358f9021244ca0b19bd2ae34538139.jpg',NULL,'topicPicture\\2018-05-27\\c7358f9021244ca0b19bd2ae34538139.jpg',1,0),('d99c912b2d7f4bd993bc1e20e62da402','541d4d8718a842ce95a71e9b8231e013',1,'file','5d25eba150144a4591eb90925d27f032.jpg',NULL,'topicPicture\\2018-05-29\\5d25eba150144a4591eb90925d27f032.jpg',1,0),('dcb0f3f2d8e040128c32be051b6b8fff','45c34210b99a4ab386b3a099480106d7',6,'file','6365a61b97ca463fb1b2c61d4b33defc.jpg',NULL,'topicPicture\\2018-05-27\\6365a61b97ca463fb1b2c61d4b33defc.jpg',1,0),('ef7f173a382a4b629ae783bceb87dd8d','541d4d8718a842ce95a71e9b8231e013',3,'file','36c0f3fe9a3b4870b017a2d13e6fd7d8.jpg',NULL,'topicPicture\\2018-05-29\\36c0f3fe9a3b4870b017a2d13e6fd7d8.jpg',1,0),('f05a8d4d339e422b913b7d8c638463ca','44fd3c58c05d48e1afa4b7992e5ee450',1,'file','153e86636fc74a3895965ce52c20aa3c.jpg',NULL,'topicPicture\\2018-05-27\\153e86636fc74a3895965ce52c20aa3c.jpg',1,0),('f08f8eb8a90e42d3a7a56e1f2098abda','1a1c7d71203a4009828293b59305bbf1',3,'file','2131ef89a55f42ac8ff5bd1861eea8b3.jpg',NULL,'topicPicture\\2018-05-27\\2131ef89a55f42ac8ff5bd1861eea8b3.jpg',1,0),('f56d81e93089482cb35ea0730e394665','44fd3c58c05d48e1afa4b7992e5ee450',6,'file','d6ef5150df6f4bb995f8d2d688a4fba8.jpg',NULL,'topicPicture\\2018-05-27\\d6ef5150df6f4bb995f8d2d688a4fba8.jpg',1,0),('fa3454d830a04319a2731f3c47b519b2','44fd3c58c05d48e1afa4b7992e5ee450',3,'file','5ae1d021f5a847c7b48e14c362a4b464.jpg',NULL,'topicPicture\\2018-05-27\\5ae1d021f5a847c7b48e14c362a4b464.jpg',1,0),('ff53877ff5b24a2399d8da34a06c6db8','1a1c7d71203a4009828293b59305bbf1',4,'file','524722e7b2cc446a861b18c834137246.jpg',NULL,'topicPicture\\2018-05-27\\524722e7b2cc446a861b18c834137246.jpg',1,0);
/*!40000 ALTER TABLE `topic_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university` (
  `university_id` char(32) NOT NULL,
  `university_name` varchar(50) DEFAULT NULL,
  `province` char(10) DEFAULT NULL,
  `university_type` char(20) DEFAULT NULL,
  `university_property` char(8) DEFAULT NULL,
  `edu_directly` smallint(6) DEFAULT NULL,
  `is985` smallint(6) DEFAULT NULL,
  `is211` smallint(6) DEFAULT NULL,
  `level` char(6) DEFAULT NULL,
  `autonomyrs` smallint(6) DEFAULT NULL,
  `membership` varchar(50) DEFAULT NULL,
  `university_nature` char(4) DEFAULT NULL,
  `tuition` varchar(500) DEFAULT NULL,
  `brief_introduction` varchar(500) DEFAULT NULL,
  `university_code` char(10) DEFAULT NULL,
  `official_website` varchar(100) DEFAULT NULL,
  `old_name` varchar(200) DEFAULT NULL,
  `is_firstrate_university` smallint(6) DEFAULT NULL,
  `is_firstrate_class` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`university_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `university_college`
--

DROP TABLE IF EXISTS `university_college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `university_college` (
  `university_college_id` char(32) NOT NULL,
  `university_id` char(32) DEFAULT NULL,
  `college_id` char(32) DEFAULT NULL,
  `offical_website` varchar(150) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`university_college_id`),
  KEY `university_id` (`university_id`),
  KEY `college_id` (`college_id`),
  CONSTRAINT `university_college_ibfk_1` FOREIGN KEY (`university_id`) REFERENCES `university` (`university_id`),
  CONSTRAINT `university_college_ibfk_2` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university_college`
--

LOCK TABLES `university_college` WRITE;
/*!40000 ALTER TABLE `university_college` DISABLE KEYS */;
/*!40000 ALTER TABLE `university_college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_inform`
--

DROP TABLE IF EXISTS `user_inform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_inform` (
  `user_id` char(32) NOT NULL,
  `student_id` char(9) DEFAULT NULL,
  `id_card` char(18) DEFAULT NULL,
  `id_verified` smallint(6) DEFAULT NULL,
  `qualification` varchar(20) DEFAULT NULL,
  `qualification_verified` smallint(6) DEFAULT NULL,
  `access_token` varchar(500) DEFAULT NULL,
  `expires_in` datetime DEFAULT NULL,
  `refresh_token` varchar(500) DEFAULT NULL,
  `avatar_store_name` char(200) DEFAULT NULL,
  `avatar_real_name` char(200) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `phone_num` varchar(11) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `qq` varchar(13) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `department` varchar(40) DEFAULT NULL,
  `grade` char(4) DEFAULT NULL,
  `entrance_date` date DEFAULT NULL,
  `user_introduction` varchar(400) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `private_space` bigint(20) unsigned DEFAULT NULL,
  `bonus_points` bigint(20) DEFAULT NULL,
  `reputation_points` bigint(20) DEFAULT NULL,
  `exp` bigint(20) unsigned DEFAULT NULL,
  `level` int(10) unsigned DEFAULT NULL,
  `dont_allow_find_by_username` smallint(6) DEFAULT NULL,
  `dont_allow_find_by_tel` smallint(6) DEFAULT NULL,
  `dont_allow_find_by_qq` smallint(6) DEFAULT NULL,
  `dont_allow_found_nearby` smallint(6) DEFAULT NULL,
  `dont_see_stranger` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_inform`
--

LOCK TABLES `user_inform` WRITE;
/*!40000 ALTER TABLE `user_inform` DISABLE KEYS */;
INSERT INTO `user_inform` VALUES ('00b18f51a2434a27913d9b03c2d02011',NULL,NULL,NULL,NULL,NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidW5pcXVlX25hbWUiOiLmtYvor5UiLCJ1c2VyaWQiOiIwMGIxOGY1MWEyNDM0YTI3OTEzZDliMDNjMmQwMjAxMSIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTMwMTU0NDg1LCJuYmYiOjE1Mjc1NjI0ODV9.oONmlHONtGroDCjtuqTN4V-8fEt8-iidZsrjeawrr9Y','2018-06-28 10:54:46',NULL,NULL,NULL,NULL,'测试',NULL,'000','000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('67b18f51a2434a27913d9b03c2d0201d',NULL,NULL,NULL,NULL,NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ29tbW9uIiwidW5pcXVlX25hbWUiOiLnqIvkuYnnvqQiLCJ1c2VyaWQiOiI2N2IxOGY1MWEyNDM0YTI3OTEzZDliMDNjMmQwMjAxZCIsImlzcyI6IlFpbmdYaWFvIiwiYXVkIjoiIiwiZXhwIjoxNTMwMTU1MjgwLCJuYmYiOjE1Mjc1NjMyODB9.qQDZPXZ2IZI67ZJF95bOmWekE9eCSVmONFjYzooyjZA','2018-06-28 11:08:01',NULL,'1e404cfb8b8c439fbe3b10031e60fd0e.jpg','user-avatar.jpg',NULL,'程义群','E:\\apache-tomcat-8.5.23\\webapps\\QingXiao\\avatar\\','123456','15605653880',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-05-02 14:17:39',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_inform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'qing_xiao'
--

--
-- Dumping routines for database 'qing_xiao'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-07 21:31:55
