-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: yourpc
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `billable`
--

DROP TABLE IF EXISTS `billable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billableTime` datetime DEFAULT NULL,
  `price` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm90e3b8i2f61n1hwyf9sq8gvr` (`user_id`),
  CONSTRAINT `FKm90e3b8i2f61n1hwyf9sq8gvr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billable`
--

LOCK TABLES `billable` WRITE;
/*!40000 ALTER TABLE `billable` DISABLE KEYS */;
INSERT INTO `billable` VALUES (14,'2017-06-27 20:52:06',540,2),(15,'2017-06-27 21:01:04',360,2),(16,'2017-06-27 21:02:25',360,2),(17,'2017-06-27 21:14:02',450,2),(18,'2017-06-27 22:05:21',850,2),(19,'2017-06-27 22:13:27',360,2),(20,'2017-06-27 22:16:06',760,2),(21,'2017-06-27 22:19:00',1290,2),(22,'2017-06-28 19:17:44',900,2);
/*!40000 ALTER TABLE `billable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'CPU'),(2,'GPU'),(3,'RAM'),(4,'PSU'),(5,'Case'),(6,'Storage'),(7,'Motherboard');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemContent` varchar(255) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `itemPrice` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2n9w8d0dp4bsfra9dcg0046l4` (`category_id`),
  CONSTRAINT `FK2n9w8d0dp4bsfra9dcg0046l4` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Intel Core 2 Quad Q6600','Intel Core 2 Quad Q6600','resources/Q6600/index.jpg',120,1,6),(2,'HyperX FURY Blue HX313C9FK2/8 DDR3-1333 8GB(2x 4GB)/512Mx64 CL9','Kingston HyperX Fury','resources/Kingston HyperX Fury/s-l215.jpg',150,3,6),(3,'ASUS GeForce GT 440 1GB DDR5 HDMI DVI VGA PCI-e Graphics Card','ASUS GeForce GT440','resources/GT440/myF9E7-STYxr99CmVLZxkbw.jpg',90,2,0),(4,'Kingston Ssdnow Uv400 240Gb Sata Iii Solid State Drive','Kingston Ssdnow Uv400','resources/Kingston Ssdnow/s-l225.jpg',140,6,1),(5,'CORSAIR CS550M PSU MODULARE - 550 WATT','CORSAIR CS550M','resources/CORSAIR CS550M/s-l325.jpg',95,4,6),(6,'Game Max Proteus Black Gaming PC Midi Tower Case - USB 3.0','Game Max Proteus','resources/Game Max Proteus/m6yO04d6vRhOSxoIjmAuCbQ.jpg',70,5,4),(7,'ASUS P5Q Deluxe Motherboard, LGA775, Intel P45 + ICH10R, Dual DDR2, SATAII','ASUS P5Q Deluxe','resources/Asus P5Q Deluxe/28022777.jpg',40,7,0);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_billable`
--

DROP TABLE IF EXISTS `item_billable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_billable` (
  `billableId` int(11) NOT NULL,
  `itemId` int(11) NOT NULL,
  PRIMARY KEY (`itemId`,`billableId`),
  KEY `FKu1h60i0fx294lm10wvh8yn7k` (`billableId`),
  CONSTRAINT `FKlvn5656gdfm6alrxlf1tr97t8` FOREIGN KEY (`itemId`) REFERENCES `item` (`id`),
  CONSTRAINT `FKu1h60i0fx294lm10wvh8yn7k` FOREIGN KEY (`billableId`) REFERENCES `billable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_billable`
--

LOCK TABLES `item_billable` WRITE;
/*!40000 ALTER TABLE `item_billable` DISABLE KEYS */;
INSERT INTO `item_billable` VALUES (14,2),(17,2),(18,5),(18,6),(20,1),(20,6),(21,1),(21,5),(22,2);
/*!40000 ALTER TABLE `item_billable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userAddress` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `uuId` varchar(255) DEFAULT NULL,
  `block` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Lviv','lokatheloka@gmail.com','','Vlad','$2a$10$JFtgFRKqBVVeWgX5/xd2RuZTw/HWeAVnDPb5TOQUHZGUqLyy9PCO6',0,'7c4f60e0-bfe0-4740-8f8c-aa1c65530231','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_item`
--

DROP TABLE IF EXISTS `user_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_item` (
  `itemId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`itemId`),
  KEY `FKr45cdg3l6qycg84idd2b8hlp1` (`itemId`),
  CONSTRAINT `FKqxpjkd4r6vmxbxs07q5caya0f` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr45cdg3l6qycg84idd2b8hlp1` FOREIGN KEY (`itemId`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_item`
--

LOCK TABLES `user_item` WRITE;
/*!40000 ALTER TABLE `user_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-29 13:31:23
