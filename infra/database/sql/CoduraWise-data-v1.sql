-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: codurawisedb-dev.codurance.io    Database: CoduraWise
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Dumping data for table `Media_Type`
--

LOCK TABLES `Media_Type` WRITE;
/*!40000 ALTER TABLE `Media_Type` DISABLE KEYS */;
INSERT INTO `Media_Type` VALUES ('article'),('course'),('video'),('website');
/*!40000 ALTER TABLE `Media_Type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Resource`
--

LOCK TABLES `Resource` WRITE;
/*!40000 ALTER TABLE `Resource` DISABLE KEYS */;
INSERT INTO `Resource` VALUES (4,'Refactoring Guru: Code Smells','https://refactoring.guru/refactoring','Refactoring','2022-11-30 00:00:00','article',4.50,'david@codurance.com'),(5,'Three Rules of TDD by Uncle Bob','http://www.butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd','Article on TDD','2022-11-30 00:00:00','article',4.20,'tiago@codurance.com'),(6,'Extreme Programming: A gentle introduction','http://www.extremeprogramming.org/','Website','2022-11-30 00:00:00','article',1.30,'david@codurance.com'),(7,'Agile','http://www.agile.org/','Website','2022-11-29 00:00:00','article',2.30,'david@codurance.com'),(8,'Scrum','http://www.scrum.org/','Website','2022-12-01 00:00:00','article',3.00,'david@codurance.com'),(9,'Scrum Guide','http://www.scrumguide.org/','Website','2022-11-30 00:00:00','article',3.00,'david@codurance.com');
/*!40000 ALTER TABLE `Resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Resource_Tag`
--

LOCK TABLES `Resource_Tag` WRITE;
/*!40000 ALTER TABLE `Resource_Tag` DISABLE KEYS */;
INSERT INTO `Resource_Tag` VALUES (6,'agile'),(7,'agile'),(8,'agile'),(9,'agile'),(4,'refactoring'),(5,'tdd');
/*!40000 ALTER TABLE `Resource_Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Review`
--

LOCK TABLES `Review` WRITE;
/*!40000 ALTER TABLE `Review` DISABLE KEYS */;
/*!40000 ALTER TABLE `Review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES ('agile'),('aws'),('java'),('junit'),('refactoring'),('tdd');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('david@codurance.com','David'),('tiago@codurance.com','Tiago');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-01 14:08:13
