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

-- Dump completed on 2018-06-07 21:54:38

#（34）文章表
CREATE TABLE  article_table
(
  article_id  CHAR(32) PRIMARY KEY ,
  title  varchar(140),
  author  varchar(140),
  author_id CHAR(32),
  summary varchar(200),   #文章摘要，提取前86个字。
  content  varchar(14000),
  lable  varchar(100),
  release_time   DATETIME,
  path  varchar(100),
  comment_times int,
  like_times  int,
  collect_times int,
  share_times int,
  privileges SMALLINT,
  status  SMALLINT,
  FOREIGN KEY (author_id) REFERENCES user_inform(user_id)

);

CREATE TABLE  verify_code_table
(
  verify_code_id  CHAR(32) PRIMARY KEY ,
  phone_number  char(11),
  verify_code  char(4),
  imei    char(17),
  meid     char(15),
  lable  varchar(100),
  send_time   DATETIME,
  privileges SMALLINT,
  status  SMALLINT
);