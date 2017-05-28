-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: world
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `ID`         INT(11) NOT NULL AUTO_INCREMENT,
  `Name`       VARCHAR(45)      DEFAULT NULL,
  `Country`    VARCHAR(10)      DEFAULT NULL,
  `District`   VARCHAR(45)      DEFAULT NULL,
  `Population` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`ID`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities`
  DISABLE KEYS */;
INSERT INTO `cities` VALUES (1, 'Shanghai', 'China', 'Shanghai', 9696300), (2, 'New York', 'USA', 'New York', 8008278),
  (3, 'Peking', 'China', 'Peking', 7472000), (4, 'Chongqing', 'China', 'Chongqing', 6351600),
  (5, 'Tianjin', 'China', 'Tianjin', 5286800), (6, 'Wuhan', 'China', 'Hubei', 4344600),
  (7, 'Harbin', 'China', 'Heilongjiang', 4289800), (8, 'Shenyang', 'China', 'Liaoning', 4265200),
  (9, 'Los Angeles', 'USA', 'California', 3694820), (10, 'Chengdu', 'China', 'Sichuan', 3361500),
  (11, 'Chicago', 'USA', 'Illinois', 2896016), (12, 'Changchun', 'China', 'Jilin', 2812000),
  (13, 'Dalian', 'China', 'Liaoning', 2697000), (14, 'Qingdao', 'China', 'Shandong', 2596000),
  (15, 'Jinan', 'China', 'Shandong', 2278100), (16, 'Hangzhou', 'China', 'Zhejiang', 2190500),
  (17, 'Zhengzhou', 'China', 'Henan', 2107200), (18, 'Shijiazhuang', 'China', 'Hebei', 2041500),
  (19, 'Taiyuan', 'China', 'Shanxi', 1968400), (20, 'Houston', 'USA', 'Texas', 1953631),
  (21, 'Kunming', 'China', 'Yunnan', 1829500), (22, 'Changsha', 'China', 'Hunan', 1809800),
  (23, 'Nanchang', 'China', 'Jiangxi', 1691600), (24, 'Fuzhou', 'China', 'Fujian', 1593800),
  (25, 'Lanzhou', 'China', 'Gansu', 1565800), (26, 'Philadelphia', 'USA', 'Pennsylvania', 1517550),
  (27, 'Guiyang', 'China', 'Guizhou', 1465200), (28, 'Ningbo', 'China', 'Zhejiang', 1371200),
  (29, 'Hefei', 'China', 'Anhui', 1369100), (30, 'Phoenix', 'USA', 'Arizona', 1321045),
  (31, 'San Diego', 'USA', 'California', 1223400), (32, 'Anshan', 'China', 'Liaoning', 1200000),
  (33, 'Fushun', 'China', 'Liaoning', 1200000), (34, 'Dallas', 'USA', 'Texas', 1188580),
  (35, 'Nanning', 'China', 'Guangxi', 1161800), (36, 'San Antonio', 'USA', 'Texas', 1144646),
  (37, 'Zibo', 'China', 'Shandong', 1140000), (38, 'Qiqihar', 'China', 'Heilongjiang', 1070000),
  (39, 'Jilin', 'China', 'Jilin', 1040000), (40, 'Tangshan', 'China', 'Hebei', 1040000),
  (41, 'Baotou', 'China', 'Inner Mongolia', 980000), (42, 'Detroit', 'USA', 'Michigan', 951270),
  (43, 'Shenzhen', 'China', 'Guangdong', 950500), (44, 'Hohhot', 'China', 'Inner Mongolia', 916700),
  (45, 'San Jose', 'USA', 'California', 894943), (46, 'Handan', 'China', 'Hebei', 840000);
/*!40000 ALTER TABLE `cities`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-04-30 13:23:05
