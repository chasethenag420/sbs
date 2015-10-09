-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USERID` int(20) NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(30) NOT NULL,
  `MIDDLENAME` varchar(30) DEFAULT NULL,
  `LASTNAME` varchar(30) NOT NULL,
  `GENDER` varchar(6) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(256) DEFAULT NULL,
  `ROLEID` int(20) DEFAULT NULL,
  `USERSTATUS` varchar(10) DEFAULT NULL,
  `REGISTRATION_DATE` date DEFAULT NULL,
  `LAST_MODIFIED_DATE` date DEFAULT NULL,
  `EMAILID` varchar(30) NOT NULL,
  `PHONENUM` varchar(15) NOT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `CITY` varchar(30) DEFAULT NULL,
  `STATE` varchar(30) DEFAULT NULL,
  `COUNTRY` varchar(30) DEFAULT NULL,
  `ZIPCODE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'j','j','k','Male','q','n',0,NULL,NULL,NULL,'j','h',NULL,NULL,NULL,NULL,NULL),(2,'khj','khj','khj','Male','dkjhf','khj',0,'InActive',NULL,NULL,'jhgjh@sbs.com','872837468273',NULL,NULL,NULL,NULL,NULL),(3,'Ganesh','R','Zilpe','Male','ganeshzilpe','ganesh',0,'InActive',NULL,NULL,'ganesh@sbs.com','4802890918',NULL,NULL,NULL,NULL,NULL),(4,'gfhgf','fghfg','fgh','Female','dyfhf','fghgfh',0,'InActive',NULL,NULL,'dfgdf@sbs.com','54646546546',NULL,NULL,NULL,NULL,NULL),(5,'rajesh','r','surana','Male','rajesh','rajesh',0,'InActive',NULL,NULL,'rajesh@sbs.com','4802898922',NULL,NULL,NULL,NULL,NULL),(6,'Garry','R','Zilpe','Male','ganeshzilpe','asdakja',1,'InActive',NULL,NULL,'wfds@','234324234234',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-08 20:53:16
