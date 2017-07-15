CREATE DATABASE  IF NOT EXISTS `mark3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mark3`;
-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: mark3
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` char(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  `user` varchar(150) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferences` (
  `id` char(50) NOT NULL,
  `picture_path` varchar(250) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
INSERT INTO `preferences` VALUES ('00000',NULL,0);
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe`
--

DROP TABLE IF EXISTS `tribe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe` (
  `id` char(50) NOT NULL,
  `title` varchar(150) DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  `time_created` timestamp NULL DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe`
--

LOCK TABLES `tribe` WRITE;
/*!40000 ALTER TABLE `tribe` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_event`
--

DROP TABLE IF EXISTS `tribe_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_event` (
  `id` char(50) NOT NULL,
  `title` varchar(150) DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  `fk_tribe` char(50) DEFAULT NULL,
  `creator` varchar(150) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_event`
--

LOCK TABLES `tribe_event` WRITE;
/*!40000 ALTER TABLE `tribe_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_news`
--

DROP TABLE IF EXISTS `tribe_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_news` (
  `id` char(50) NOT NULL,
  `title` varchar(150) DEFAULT NULL,
  `note` varchar(250) DEFAULT NULL,
  `fk_tribe` char(50) DEFAULT NULL,
  `creator` varchar(150) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_news`
--

LOCK TABLES `tribe_news` WRITE;
/*!40000 ALTER TABLE `tribe_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_picture`
--

DROP TABLE IF EXISTS `tribe_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_picture` (
  `id` char(50) NOT NULL,
  `path` varchar(250) DEFAULT NULL,
  `fk_tribe` char(50) DEFAULT NULL,
  `inserted_by` varchar(250) DEFAULT NULL,
  `inserted_date` timestamp NULL DEFAULT NULL,
  `is_displayed` char(1) DEFAULT '0',
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_picture`
--

LOCK TABLES `tribe_picture` WRITE;
/*!40000 ALTER TABLE `tribe_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_role`
--

DROP TABLE IF EXISTS `tribe_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_role` (
  `id` char(50) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `type` char(35) DEFAULT NULL,
  `fk_tribe` char(50) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_role`
--

LOCK TABLES `tribe_role` WRITE;
/*!40000 ALTER TABLE `tribe_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tribe_status`
--

DROP TABLE IF EXISTS `tribe_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tribe_status` (
  `id` char(50) NOT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `type` char(35) DEFAULT NULL,
  `fk_tribe` char(50) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tribe_status`
--

LOCK TABLES `tribe_status` WRITE;
/*!40000 ALTER TABLE `tribe_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `tribe_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` char(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `password` varchar(250) NOT NULL,
  `is_enabled` char(1) DEFAULT '0',
  `is_activated` char(1) DEFAULT '0',
  `language` char(10) DEFAULT 'EN_US',
  `version` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('00000','admin@mark3.com','System Administrator','2017-07-18','jIfSsj+ceSsamqTfmru/ZlLSRsjwUijQK4CJqi86fLNWb/nqSv2eY2rRrJxgpdDM','1','1','EN_US',1),('0555d8bc-9da5-423d-bb20-9e6c278aa5ed','kiki@ok.com','Kiki Kaki Kiku','2017-06-26','sgX5ahb5ZrGtpYU9Y8/cysULVS4Vv60oT8AeTbXDT8eR5HGexYkti0utzCAtnN/C','1','1','EN_US',4),('3c4daef1-6380-48ab-bbff-4e74287c6d98','roni@gmail.com','roni@gmail.com','2017-07-04','sItccSFmBPl2qTiFK+EqSR9KjZtxeR3EQ23tkPDe5UmuRIHujr3lIyp3mpnRUy7D','0','0','EN_US',0),('46550871-cfe0-4d75-9680-e92fdcb15eae','rudi@tabuti.com','rudi@tabuti.com','2017-07-05','YRFJIwspE2rZGADoA+ZHvk3PiA9bBXEOR3a9ZvqwYReMXaVx5vZFHPtpzEzST96w','0','0','EN_US',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-15 10:39:01
