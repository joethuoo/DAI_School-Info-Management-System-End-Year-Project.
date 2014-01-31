-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: SchoolDB
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

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
-- Table structure for table `Academic_YEAR`
--

DROP TABLE IF EXISTS `Academic_YEAR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Academic_YEAR` (
  `Yr_ID` int(11) NOT NULL AUTO_INCREMENT,
  `YEAR` year(4) NOT NULL,
  PRIMARY KEY (`Yr_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Academic_YEAR`
--

LOCK TABLES `Academic_YEAR` WRITE;
/*!40000 ALTER TABLE `Academic_YEAR` DISABLE KEYS */;
INSERT INTO `Academic_YEAR` VALUES (1,2014);
/*!40000 ALTER TABLE `Academic_YEAR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Class_Subscription`
--

DROP TABLE IF EXISTS `Class_Subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Class_Subscription` (
  `Subscription_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Student_ID` int(11) NOT NULL,
  `Yr_ID` int(11) NOT NULL,
  `Class_ID` int(11) NOT NULL,
  PRIMARY KEY (`Subscription_ID`),
  KEY `Student_ID` (`Student_ID`),
  KEY `Yr_ID` (`Yr_ID`),
  KEY `Class_ID` (`Class_ID`),
  CONSTRAINT `Class_Subscription_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`Student_ID`),
  CONSTRAINT `Class_Subscription_ibfk_2` FOREIGN KEY (`Yr_ID`) REFERENCES `Academic_YEAR` (`Yr_ID`),
  CONSTRAINT `Class_Subscription_ibfk_3` FOREIGN KEY (`Class_ID`) REFERENCES `Classes` (`Class_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Class_Subscription`
--

LOCK TABLES `Class_Subscription` WRITE;
/*!40000 ALTER TABLE `Class_Subscription` DISABLE KEYS */;
INSERT INTO `Class_Subscription` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `Class_Subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Classes`
--

DROP TABLE IF EXISTS `Classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Classes` (
  `Class_ID` int(11) NOT NULL,
  `Class_Description` varchar(20) NOT NULL,
  PRIMARY KEY (`Class_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classes`
--

LOCK TABLES `Classes` WRITE;
/*!40000 ALTER TABLE `Classes` DISABLE KEYS */;
INSERT INTO `Classes` VALUES (1,'BABY CLASS'),(2,'PRE UNIT'),(3,'NURSERY'),(4,'STD ONE'),(5,'STD TWO'),(6,'STD THREE'),(7,'STD FOUR'),(8,'STD FIVE'),(9,'STD SIX'),(10,'STD SEVEN'),(11,'STD EIGHT');
/*!40000 ALTER TABLE `Classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fee_Structure`
--

DROP TABLE IF EXISTS `Fee_Structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fee_Structure` (
  `FeesStructureId` int(11) NOT NULL AUTO_INCREMENT,
  `Fees_Mode` varchar(20) NOT NULL,
  `Yr_ID` int(11) NOT NULL,
  `Class_Id` int(11) NOT NULL,
  `Term_Id` int(11) NOT NULL,
  `FeesAmount` double NOT NULL,
  `Entry_Time` date NOT NULL,
  PRIMARY KEY (`FeesStructureId`),
  KEY `Yr_ID` (`Yr_ID`),
  KEY `Term_Id` (`Term_Id`),
  KEY `Class_Id` (`Class_Id`),
  CONSTRAINT `Fee_Structure_ibfk_1` FOREIGN KEY (`Yr_ID`) REFERENCES `Academic_YEAR` (`Yr_ID`),
  CONSTRAINT `Fee_Structure_ibfk_2` FOREIGN KEY (`Term_Id`) REFERENCES `Term` (`Term_ID`),
  CONSTRAINT `Fee_Structure_ibfk_3` FOREIGN KEY (`Class_Id`) REFERENCES `Classes` (`Class_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fee_Structure`
--

LOCK TABLES `Fee_Structure` WRITE;
/*!40000 ALTER TABLE `Fee_Structure` DISABLE KEYS */;
/*!40000 ALTER TABLE `Fee_Structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fee_Validator`
--

DROP TABLE IF EXISTS `Fee_Validator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fee_Validator` (
  `val_id` int(11) NOT NULL AUTO_INCREMENT,
  `Student_ID` int(11) NOT NULL,
  `Yr_ID` int(11) NOT NULL,
  `Class_Id` int(11) NOT NULL,
  `Term_Id` int(11) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `Amount` double NOT NULL,
  `FeesStructureId` int(11) NOT NULL,
  PRIMARY KEY (`val_id`),
  KEY `Student_ID` (`Student_ID`),
  KEY `Yr_ID` (`Yr_ID`),
  KEY `Class_Id` (`Class_Id`),
  KEY `Term_Id` (`Term_Id`),
  KEY `FeesStructureId` (`FeesStructureId`),
  CONSTRAINT `Fee_Validator_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`Student_ID`),
  CONSTRAINT `Fee_Validator_ibfk_2` FOREIGN KEY (`Yr_ID`) REFERENCES `Academic_YEAR` (`Yr_ID`),
  CONSTRAINT `Fee_Validator_ibfk_3` FOREIGN KEY (`Class_Id`) REFERENCES `Classes` (`Class_ID`),
  CONSTRAINT `Fee_Validator_ibfk_4` FOREIGN KEY (`Term_Id`) REFERENCES `Term` (`Term_ID`),
  CONSTRAINT `Fee_Validator_ibfk_5` FOREIGN KEY (`FeesStructureId`) REFERENCES `Fee_Structure` (`FeesStructureId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fee_Validator`
--

LOCK TABLES `Fee_Validator` WRITE;
/*!40000 ALTER TABLE `Fee_Validator` DISABLE KEYS */;
/*!40000 ALTER TABLE `Fee_Validator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Gender`
--

DROP TABLE IF EXISTS `Gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Gender` (
  `Gender_ID` int(11) NOT NULL,
  `SEX` varchar(20) NOT NULL,
  PRIMARY KEY (`Gender_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gender`
--

LOCK TABLES `Gender` WRITE;
/*!40000 ALTER TABLE `Gender` DISABLE KEYS */;
INSERT INTO `Gender` VALUES (1,'MALE'),(2,'FEMALE');
/*!40000 ALTER TABLE `Gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guardian`
--

DROP TABLE IF EXISTS `Guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Guardian` (
  `Guardian_ID` int(11) NOT NULL,
  `Person_ID` int(11) NOT NULL,
  PRIMARY KEY (`Guardian_ID`),
  KEY `Person_ID` (`Person_ID`),
  CONSTRAINT `Guardian_ibfk_1` FOREIGN KEY (`Person_ID`) REFERENCES `Person` (`Person_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guardian`
--

LOCK TABLES `Guardian` WRITE;
/*!40000 ALTER TABLE `Guardian` DISABLE KEYS */;
INSERT INTO `Guardian` VALUES (1,2);
/*!40000 ALTER TABLE `Guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Guardian_Contact`
--

DROP TABLE IF EXISTS `Guardian_Contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Guardian_Contact` (
  `Guardian_ID` int(11) NOT NULL,
  `Guardian_ID_No` varchar(20) NOT NULL,
  `Guardian_Phone` varchar(20) NOT NULL,
  `Guardian_Residence` varchar(20) NOT NULL,
  KEY `Guardian_ID` (`Guardian_ID`),
  CONSTRAINT `Guardian_Contact_ibfk_1` FOREIGN KEY (`Guardian_ID`) REFERENCES `Guardian` (`Guardian_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Guardian_Contact`
--

LOCK TABLES `Guardian_Contact` WRITE;
/*!40000 ALTER TABLE `Guardian_Contact` DISABLE KEYS */;
INSERT INTO `Guardian_Contact` VALUES (1,'64737828','0732777888','Nairobi');
/*!40000 ALTER TABLE `Guardian_Contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Index_Generator`
--

DROP TABLE IF EXISTS `Index_Generator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Index_Generator` (
  `Auto_id` int(11) NOT NULL,
  `Person_ID` int(11) NOT NULL,
  `Student_ID` int(11) NOT NULL,
  `Guardian_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Index_Generator`
--

LOCK TABLES `Index_Generator` WRITE;
/*!40000 ALTER TABLE `Index_Generator` DISABLE KEYS */;
INSERT INTO `Index_Generator` VALUES (1,3,2,2);
/*!40000 ALTER TABLE `Index_Generator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `Staff_ID` int(11) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(30) NOT NULL,
  KEY `Staff_ID` (`Staff_ID`),
  CONSTRAINT `Login_ibfk_1` FOREIGN KEY (`Staff_ID`) REFERENCES `Staff` (`Staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Login`
--

LOCK TABLES `Login` WRITE;
/*!40000 ALTER TABLE `Login` DISABLE KEYS */;
INSERT INTO `Login` VALUES (1,'Admin','Admin');
/*!40000 ALTER TABLE `Login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `Person_ID` int(11) NOT NULL,
  `First_Name` varchar(20) NOT NULL,
  `Middle_Name` varchar(20) NOT NULL,
  `Surname` varchar(20) NOT NULL,
  `Gender_ID` int(11) NOT NULL,
  `Per_Desc_ID` int(11) NOT NULL,
  PRIMARY KEY (`Person_ID`),
  KEY `Per_Desc_ID` (`Per_Desc_ID`),
  KEY `Gender_ID` (`Gender_ID`),
  CONSTRAINT `Person_ibfk_1` FOREIGN KEY (`Per_Desc_ID`) REFERENCES `Person_Description` (`Per_Desc_ID`),
  CONSTRAINT `Person_ibfk_2` FOREIGN KEY (`Gender_ID`) REFERENCES `Gender` (`Gender_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
INSERT INTO `Person` VALUES (1,'MILDRED','Wanjiru','Huro',2,2),(2,'Mohamed','Abduba','Dida',1,3);
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person_Description`
--

DROP TABLE IF EXISTS `Person_Description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person_Description` (
  `Per_Desc_ID` int(11) NOT NULL,
  `Description` varchar(20) NOT NULL,
  PRIMARY KEY (`Per_Desc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person_Description`
--

LOCK TABLES `Person_Description` WRITE;
/*!40000 ALTER TABLE `Person_Description` DISABLE KEYS */;
INSERT INTO `Person_Description` VALUES (1,'STAFF'),(2,'STUDENT'),(3,'GUARDIAN');
/*!40000 ALTER TABLE `Person_Description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Staff` (
  `Staff_ID` int(11) NOT NULL,
  `Description` varchar(20) NOT NULL,
  PRIMARY KEY (`Staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (1,'Administrator');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `Student_ID` int(11) NOT NULL,
  `Student_INDEX` varchar(25) NOT NULL,
  `Person_ID` int(11) NOT NULL,
  `Guardian_ID` int(11) NOT NULL,
  PRIMARY KEY (`Student_ID`),
  KEY `Person_ID` (`Person_ID`),
  KEY `Guardian_ID` (`Guardian_ID`),
  CONSTRAINT `Student_ibfk_1` FOREIGN KEY (`Person_ID`) REFERENCES `Person` (`Person_ID`),
  CONSTRAINT `Student_ibfk_2` FOREIGN KEY (`Guardian_ID`) REFERENCES `Guardian` (`Guardian_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (1,'KRS-A-0001',1,1);
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StudentAccount`
--

DROP TABLE IF EXISTS `StudentAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StudentAccount` (
  `Student_ID` int(11) NOT NULL,
  `Student_Credit_Acc` double NOT NULL,
  `Student_Balance_Acc` double NOT NULL,
  KEY `Student_ID` (`Student_ID`),
  CONSTRAINT `StudentAccount_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`Student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StudentAccount`
--

LOCK TABLES `StudentAccount` WRITE;
/*!40000 ALTER TABLE `StudentAccount` DISABLE KEYS */;
INSERT INTO `StudentAccount` VALUES (1,0,0);
/*!40000 ALTER TABLE `StudentAccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Payment_History`
--

DROP TABLE IF EXISTS `Student_Payment_History`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_Payment_History` (
  `Hist_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Student_ID` int(11) NOT NULL,
  `Yr_ID` int(11) NOT NULL,
  `Amount` double NOT NULL,
  `Payment_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Hist_ID`),
  KEY `Student_ID` (`Student_ID`),
  KEY `Yr_ID` (`Yr_ID`),
  CONSTRAINT `Student_Payment_History_ibfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `Student` (`Student_ID`),
  CONSTRAINT `Student_Payment_History_ibfk_2` FOREIGN KEY (`Yr_ID`) REFERENCES `Academic_YEAR` (`Yr_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Payment_History`
--

LOCK TABLES `Student_Payment_History` WRITE;
/*!40000 ALTER TABLE `Student_Payment_History` DISABLE KEYS */;
/*!40000 ALTER TABLE `Student_Payment_History` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Term`
--

DROP TABLE IF EXISTS `Term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Term` (
  `Term_ID` int(11) NOT NULL,
  `Term_Description` varchar(20) NOT NULL,
  PRIMARY KEY (`Term_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Term`
--

LOCK TABLES `Term` WRITE;
/*!40000 ALTER TABLE `Term` DISABLE KEYS */;
INSERT INTO `Term` VALUES (1,'TERM ONE'),(2,'TERM TWO'),(3,'TERM THREE');
/*!40000 ALTER TABLE `Term` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-26  9:57:04
