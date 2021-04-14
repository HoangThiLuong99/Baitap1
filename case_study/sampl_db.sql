CREATE DATABASE  IF NOT EXISTS `sample_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sample_db`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sample_db
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `apartment_id` int NOT NULL AUTO_INCREMENT,
  `users_id` int NOT NULL,
  `apartment_name` varchar(255) NOT NULL,
  `capacity` int NOT NULL,
  `area` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `min_day` int NOT NULL,
  `max_day` int NOT NULL,
  PRIMARY KEY (`apartment_id`),
  KEY `FKapartment637945` (`users_id`),
  CONSTRAINT `FKapartment637945` FOREIGN KEY (`users_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,1,'house1',5,30,'2 le thanh nghi',560,2,6),(2,1,'house2',4,25,'7 bach mai',400,3,7),(3,2,'house3',10,70,'8 bach mai',700,2,7),(5,2,'house5',5,30,'2 le thanh nghi',560,0,0),(6,8,'house6',3,30,'2 le thanh nghi',560,3,7),(7,2,'house5',5,30,'2 le thanh nghi',560,0,0),(8,2,'house5',5,30,'2 le thanh nghi',560,0,0),(9,2,'house5',5,20,'2 le thanh nghi',560,0,5),(10,2,'house5',5,20,'2 le thanh nghi',560,2,5);
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `apartments_id` int NOT NULL,
  `users_id` int NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_phone` varchar(255) NOT NULL,
  `check_in` datetime NOT NULL,
  `check_out` datetime NOT NULL,
  `number_of_guest` int NOT NULL,
  `booking_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL,
  `total_price` double NOT NULL,
  `statement_pay` int NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKbooking469197` (`users_id`),
  KEY `FKbooking919501` (`apartments_id`),
  CONSTRAINT `FKbooking469197` FOREIGN KEY (`users_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKbooking919501` FOREIGN KEY (`apartments_id`) REFERENCES `apartment` (`apartment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,'luong','0965320958','2021-04-10 10:00:00','2021-04-12 10:00:00',4,'2021-04-06 10:00:00',1,2150,1),(2,3,2,'minh','0385675262','2021-04-12 10:00:00','2021-04-15 10:00:00',8,'2021-04-06 10:00:00',1,2100,1),(3,2,1,'linh','0765986355','2021-04-12 10:00:00','2021-04-12 10:00:00',4,'2021-04-06 10:00:00',1,1650,1),(4,3,4,'thao','0962388547','2021-04-12 10:00:00','2021-04-12 10:00:00',10,'2021-04-06 10:00:00',1,3500,1),(5,2,1,'luong','0965852668','2021-04-12 10:00:00','2021-04-12 10:00:00',4,'2021-04-09 23:14:28',1,2500,1);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Hoang Linh','0965320958','linh@gmail.com','linh.123456'),(2,'Nguyen A','0365984256','nguyena@gmail.com','nguyena.123'),(3,'Nguyen B','0736259588','nguyenb@gmail.com','nguyenb.123'),(4,'Nguyen A','0365984256','nguyena@gmail.com','nguyena.123'),(5,'Le Thi A','0965320559','a@gmail.com','lea.123456'),(6,'thao','0965820958','thao@gmail.com','thao1234456'),(7,'minh','0965820958','thao@gmail.com','thao1234456'),(8,'thao','0965820958','thao@gmail.com','thao1234456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sample_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-13  1:16:30
