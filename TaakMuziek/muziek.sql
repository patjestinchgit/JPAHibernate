-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: muziek
-- ------------------------------------------------------
-- Server version	5.6.19

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

create database if not exists muziek;
use muziek;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `albums` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `artiestid` int(10) unsigned NOT NULL DEFAULT '0',
  `naam` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `albums_artiesten_idx` (`artiestid`),
  CONSTRAINT `albums_artiesten` FOREIGN KEY (`artiestid`) REFERENCES `artiesten` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,1,'Retro - John McCready FAN'),(2,1,'Substance (Disc 2)'),(3,1,'Retro - Miranda Sawyer POP'),(4,1,'Retro - New Order / Bobby Gillespie LIVE'),(5,1,'Power, Corruption & Lies'),(6,1,'Substance 1987 (Disc 1)'),(7,1,'Brotherhood'),(8,2,'Let Love In'),(9,3,'Live Around The World'),(10,3,'In A Silent Way'),(11,4,'Exile On Main Street'),(12,5,'Second Coming'),(13,6,'Light Years');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artiesten`
--

DROP TABLE IF EXISTS `artiesten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artiesten` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artiesten`
--

LOCK TABLES `artiesten` WRITE;
/*!40000 ALTER TABLE `artiesten` DISABLE KEYS */;
INSERT INTO `artiesten` VALUES (1,'New Order'),(2,'Nick Cave & The Bad Seeds'),(3,'Miles Davis'),(4,'The Rolling Stones'),(5,'The Stone Roses'),(6,'Kylie Minogue');
/*!40000 ALTER TABLE `artiesten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracks`
--

DROP TABLE IF EXISTS `tracks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracks` (
  `albumid` int(10) unsigned NOT NULL,
  `naam` varchar(255) NOT NULL,
  `tijd` decimal(5,2) NOT NULL,
  KEY `tracksAlbums_idx` (`albumid`),
  CONSTRAINT `tracks_albums` FOREIGN KEY (`albumid`) REFERENCES `albums` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracks`
--

LOCK TABLES `tracks` WRITE;
/*!40000 ALTER TABLE `tracks` DISABLE KEYS */;
INSERT INTO `tracks` VALUES (1,'Elegia',4.93),(1,'In A Lonely Place',6.26),(1,'Procession',4.47),(1,'Your Silent Face',5.99),(1,'Sunrise',6.01),(1,'Let\'s Go',3.90),(1,'Broken Promise',3.76),(1,'Dreams Never End',3.20),(1,'Cries And Whispers',3.42),(1,'All Day Long',5.18),(1,'Sooner Than You Think',5.21),(1,'Leave Me Alone',4.67),(1,'Lonesome Tonight',5.19),(1,'Every Little Counts',4.47),(1,'Run Wild',3.95),(2,'In A Lonely Place',6.30),(2,'Procession',4.46),(2,'Mesh',3.44),(2,'Hurt',6.98),(2,'The Beach',7.32),(2,'Confusion',7.64),(2,'Lonesome Tonight',5.20),(2,'Murder',3.93),(2,'Thieves Like Us',6.95),(2,'Kiss Of Death',7.05),(2,'Shame Of The Nation',7.91),(2,'1963',5.63),(3,'Fine Time',4.71),(3,'Temptation',8.71),(3,'True Faith',5.88),(3,'The Perfect Kiss',4.83),(3,'Ceremony',4.41),(3,'Regret',4.14),(3,'Crystal',6.83),(3,'Bizarre Love Triangle',4.35),(3,'Confusion',8.22),(3,'Round And Round',4.52),(3,'Blue Monday',7.48),(3,'Brutal',4.83),(3,'Slow Jam',4.88),(3,'Everyone Everywhere',4.43),(4,'Ceremony [Studio 54, Barcelona 7/7/84]',4.82),(4,'Procession [Polytechnic of Central London, London 6/12/85]',3.57),(4,'Everything\'s Gone Green [Tolworth Recreation Centre, London 12/3/85]',5.25),(4,'In A Lonely Place [Glastonbury Festival 20/6/81]',5.55),(4,'Age Of Consent [Spectrum Arena, Warrington 1/3/86]',5.04),(4,'Elegia [Glastonbury Festival 19/6/87]',4.77),(4,'The Perfect Kiss [Glastonbury Festival 19/6/87]',9.73),(4,'Fine Time [Popular Creek Music Theatre, Chicago 30/6/89]',5.04),(4,'World [Starplex Amphitheatre, Dallas 21/7/93]',4.81),(4,'Regret [Reading Festival 29/8/93]',4.03),(4,'As It Is When It Was [Reading Festival 29/8/93]',3.80),(4,'Intermission By Alan Wise [Olympia, Paris 12/11/01]',1.34),(4,'Crystal [Big Day Out, Gold Coast 20/1/02]',6.86),(4,'Turn My Way [Olympia, Liverpool 18/7/01]',4.96),(4,'Temptation [Big Day Out, Gold Coast 20/1/02]',7.79),(5,'Age Of Consent',5.26),(5,'We All Stand',5.24),(5,'The Village',4.62),(5,'5 8 6',7.52),(5,'Your Silent Face',6.00),(5,'Ultraviolence',4.87),(5,'Ecstasy',4.42),(5,'Leave Me Alone',4.69),(6,'Ceremony',4.42),(6,'Everything\'s Gone Green',5.51),(6,'Temptation',6.99),(6,'Blue Monday',7.49),(6,'Confusion',4.72),(6,'Thieves Like Us',6.61),(6,'Perfect Kiss',8.04),(6,'Subculture',4.80),(6,'Shellshock',6.48),(6,'State of the Nation',6.54),(6,'Bizarre Love Triangle',6.74),(6,'True Faith',5.93),(7,'State of the Nation',6.56),(7,'Every Little Counts',4.48),(7,'Angel Dust',3.73),(7,'All Day Long',5.21),(7,'Bizarre Love Triangle',4.37),(7,'Way of Life',4.11),(7,'Broken Promise',3.80),(7,'As It Is When It Was',3.77),(7,'Weirdo',3.89),(7,'Paradise',3.86),(8,'Do You Love Me?',5.95),(8,'Nobody\'s Baby Now',3.87),(8,'Loverman',6.37),(8,'Jangling Jack',2.78),(8,'Red Right Hand',6.18),(8,'I Let Love In',4.25),(8,'Thirsty Dog',3.81),(8,'Ain\'t Gonna Rain Anymore',3.77),(8,'Lay Me Low',5.15),(8,'Do You Love Me? (Part Two)',6.23),(9,'In A Silent Way',1.81),(9,'Intruder',4.87),(9,'New Blues',5.58),(9,'Human Nature',12.80),(9,'Mr. Pastorius',3.54),(9,'Amandla',5.87),(9,'Wrinkle',7.28),(9,'Tutu',8.89),(9,'Full Nelson',2.81),(9,'Time After Time',9.98),(9,'Hannibal',7.37),(10,'Shhh/Peaceful',16.67),(10,'In A Silent Way/It\'s About That Time',16.67),(11,'Rocks Off',4.54),(11,'Rip This Joint',2.38),(11,'Shake Your Hips',3.00),(11,'Casino Boogie',3.57),(11,'Tumbling Dice',3.79),(11,'Sweet Virginia',4.44),(11,'Torn & Frayed',4.30),(11,'Sweet Black Angel',2.97),(11,'Loving Cup',4.43),(11,'Happy',3.08),(11,'Turd On The Run',2.64),(11,'Ventilator Blues',3.40),(11,'I Just Want To See His Face',2.90),(11,'Let It Loose',5.31),(11,'All Down The Line',3.84),(11,'Stop Breaking Down',4.57),(11,'Shine A Light',4.28),(11,'Soul Survivor',3.82),(12,'Breaking Into Heaven',11.37),(12,'Driving South',5.17),(12,'Ten Storey Love Song',4.50),(12,'Daybreak',6.56),(12,'Your Star Will Shine',2.99),(12,'Straight To The Man',3.26),(12,'Begging You',4.94),(12,'Tightrope',4.45),(12,'Good Times',5.67),(12,'Tears',6.84),(12,'How Do You Sleep',4.99),(12,'Love Spreads',5.79),(12,'Untitled',6.43),(13,'Spinning Around',3.46),(13,'On A Night Like This',3.55),(13,'So Now Goodbye',3.62),(13,'Disco Down',3.96),(13,'Loveboat',4.18),(13,'Koocachoo',4.00),(13,'Your Disco Needs You',3.56),(13,'Please Stay',4.14),(13,'Bittersweet Goodbye',3.72),(13,'Butterfly',4.16),(13,'Under The Influence Of Love',3.40),(13,'I\'m So High',3.55),(13,'Kids',4.34);
/*!40000 ALTER TABLE `tracks` ENABLE KEYS */;
UNLOCK TABLES;

grant all on muziek.* to cursist;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-07 12:37:54
