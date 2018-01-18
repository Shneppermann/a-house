CREATE DATABASE  IF NOT EXISTS `auction_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `auction_db`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: auction_db
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
-- Table structure for table `auction_type`
--

DROP TABLE IF EXISTS `auction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction_type` (
  `id_type` tinyint(2) NOT NULL AUTO_INCREMENT COMMENT 'Содержит идентификационный номер типа аукциона. Изначально предполагается наличие только двух типов "прямой" и "обратный". ',
  `type` varchar(20) NOT NULL COMMENT 'Содержит наименования типов аукциона.',
  PRIMARY KEY (`id_type`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Таблица описывает типы аукциона. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_type`
--

LOCK TABLES `auction_type` WRITE;
/*!40000 ALTER TABLE `auction_type` DISABLE KEYS */;
INSERT INTO `auction_type` VALUES (1,'direct'),(2,'reverse');
/*!40000 ALTER TABLE `auction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `id_bid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Стобец содержит идентификационный номер ставки.',
  `owner_id` int(11) NOT NULL COMMENT 'Содержит ID клиента, который сделал ставку.',
  `lot_id` int(11) NOT NULL COMMENT 'Содержит ID лота на который была сделана ставка.',
  `bid` decimal(10,2) NOT NULL COMMENT 'Содержит информацию о ставке в денежном эквиваленте. Точность выбрана с запасом и соотносится с точностью баланса пользователя. ',
  PRIMARY KEY (`id_bid`,`owner_id`,`lot_id`),
  KEY `fk_tbl_bid_tbl_user2_idx` (`owner_id`),
  KEY `fk_tbl_bid_tbl_lot1_idx` (`lot_id`),
  CONSTRAINT `fk_tbl_bid_tbl_lot1` FOREIGN KEY (`lot_id`) REFERENCES `lot` (`id_lot`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_bid_tbl_user2` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='Таблица содержит информацию о всех ставках. Используется для определения победителя и фиксирования денежных операций.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (1,10,4,30.00),(2,7,4,45.00),(3,10,4,60.00),(4,8,4,75.00),(5,5,8,15.00),(6,6,8,20.00),(7,6,13,5990.00),(8,4,13,5980.00),(9,12,5,3000.00),(10,12,13,5970.00),(11,12,4,90.00),(12,3,13,5960.00),(13,9,4,105.00),(14,3,4,120.00),(15,8,3,450.00),(16,12,3,600.00),(17,5,8,25.00),(18,6,8,30.00),(19,5,8,35.00),(20,6,8,40.00),(21,5,8,45.00),(22,3,8,50.00),(24,5,8,55.00),(25,5,8,60.00),(26,5,8,65.00),(27,5,4,135.00),(28,5,5,4500.00),(29,5,3,750.00),(30,5,8,70.00),(31,5,4,150.00),(32,12,5,6000.00),(33,12,5,7500.00),(34,13,8,75.00),(35,5,1,1250.00),(36,5,1,1500.00),(37,13,1,1750.00),(38,5,1,2000.00),(39,13,6,1.50),(40,12,8,80.00),(41,12,13,5950.00),(42,5,8,85.00),(43,13,8,90.00),(44,13,7,30.00),(50,5,8,95.00),(51,14,8,100.00),(52,5,8,105.00),(53,12,1,2250.00),(54,12,3,900.00),(55,12,8,110.00),(56,12,7,50.00),(57,5,8,115.00),(58,5,3,1050.00),(59,12,8,120.00),(60,5,8,125.00),(61,5,1,2500.00),(62,12,3,1200.00),(63,12,8,130.00),(68,5,8,135.00),(69,5,3,1350.00),(70,14,4,165.00),(71,8,18,695.00),(72,12,18,690.00),(73,12,8,140.00);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot` (
  `id_lot` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Содержит идентификационный номер лота.',
  `lot_state` tinyint(2) NOT NULL COMMENT 'Содержит информацию о состоянии лота. Используется для верного отображения лотов у  пользователей.',
  `owner_id` int(11) NOT NULL COMMENT 'Содержит ID клиента, который выставил лот на аукцион и является владельцем.',
  `auction_type` tinyint(2) NOT NULL COMMENT 'Содержит информацию о типе аукциона (прямой или обратный).',
  `lot_name` varchar(45) NOT NULL COMMENT 'Содержит название лота.',
  `step` decimal(8,2) NOT NULL COMMENT 'Содержит шаг повышения или понижения цены в зависимости от вида аукциона.',
  `start_price` decimal(10,2) NOT NULL COMMENT 'Содержит стартовую цену торгов.',
  PRIMARY KEY (`id_lot`,`lot_state`,`owner_id`,`auction_type`),
  KEY `fk_tbl_lot_tbl_user1_idx` (`owner_id`),
  KEY `fk_tbl_lot_tbl_lot_state1_idx` (`lot_state`),
  KEY `fk_tbl_lot_tbl_auction_type1_idx` (`auction_type`),
  CONSTRAINT `fk_tbl_lot_tbl_auction_type1` FOREIGN KEY (`auction_type`) REFERENCES `auction_type` (`id_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_lot_tbl_lot_state1` FOREIGN KEY (`lot_state`) REFERENCES `lot_state` (`id_state`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_lot_tbl_user1` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Таблица содержит информацию о лотах аукциона.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
INSERT INTO `lot` VALUES (1,2,3,1,'18th Century Painting',250.00,1000.00),(2,4,14,1,'Smuggling',6000.00,5500.00),(3,2,11,1,'Antique cupboard',150.00,300.00),(4,2,5,1,'Tea-set',15.00,15.00),(5,2,8,1,'Stradivarius Violin',1500.00,1500.00),(6,2,5,1,'Broken shovel',0.50,1.00),(7,2,5,1,'Gramophone',20.00,10.00),(8,2,8,1,'Sofa',5.00,10.00),(9,1,8,1,'Cactus',40000.00,500000.00),(10,1,5,1,'Strange book',85000.00,40000.00),(11,2,7,2,'Pump station',5000.00,1000000.00),(12,2,7,1,'The Elvis Presley car',30000.00,150000.00),(13,2,5,2,'Acient book',10.00,6000.00),(14,4,14,1,'Prohibited substances',560.00,1200.00),(16,1,5,2,'Картошечка',20.00,4000.00),(17,4,12,1,'Пряники',2.00,5.00),(18,2,5,2,'Big VMF',5.00,700.00);
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot_state`
--

DROP TABLE IF EXISTS `lot_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot_state` (
  `id_state` tinyint(2) NOT NULL AUTO_INCREMENT COMMENT 'Идентификационный номер состояния аукциона.',
  `state` varchar(45) NOT NULL COMMENT 'Содержит наименования состояний аукциона.',
  PRIMARY KEY (`id_state`),
  UNIQUE KEY `state_UNIQUE` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Таблица описывает все возможные состояния лота.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot_state`
--

LOCK TABLES `lot_state` WRITE;
/*!40000 ALTER TABLE `lot_state` DISABLE KEYS */;
INSERT INTO `lot_state` VALUES (4,'banned'),(2,'bidding_lot'),(1,'offered'),(3,'purchased');
/*!40000 ALTER TABLE `lot_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` tinyint(2) NOT NULL AUTO_INCREMENT COMMENT 'Содержит ID ролей пользователей. ',
  `role_name` varchar(25) NOT NULL COMMENT 'Содержит названия ролей пользователей.',
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Таблица описывает роли пользователей. ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(3,'Banned user'),(2,'Customer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Содержит идентификационный номер пользователя.',
  `id_role` tinyint(2) NOT NULL COMMENT 'Содержит информацию о роли пользователя\n',
  `login` varchar(30) NOT NULL COMMENT 'Содержит логин пользователя.',
  `password` char(40) NOT NULL COMMENT 'Содержит хэшированный пароль пользователя (SHA-1).',
  `first_name` varchar(30) NOT NULL COMMENT 'Содержит имя пользователя.',
  `second_name` varchar(30) NOT NULL COMMENT 'Содержит фамилию пользователя.',
  `balance` decimal(10,2) DEFAULT NULL COMMENT 'Содержит информацию о денежном балансе пользователя. Баланс может быть отрицательным. Если баланс отрицательный, то это значит, что пользователь сделал ставку в кредит. Точность выбрана с запасом, так как неизвестно количество денег, которое пользователи захотят внести на свой баланс.',
  PRIMARY KEY (`id_user`,`id_role`),
  UNIQUE KEY `login` (`login`),
  KEY `fk_tbl_user_tbl_role1_idx` (`id_role`),
  CONSTRAINT `fk_tbl_user_tbl_role1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Таблица содержит информацию о пользователях аукциона.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'admin@gmail.ru','efacc4001e857f7eba4ae781c2932dedf843865e','Jhon','Snow',0.00),(2,1,'admin2@gmail.ru','efacc4001e857f7eba4ae781c2932dedf843865e','Addie','Walker',0.00),(3,2,'customer1@gmail.ru','e2ea3c6b50c654e7c809c252b97d94386fb283fc','Kelley','Tailor',13130.00),(4,2,'customer2@gmail.ru','80dae43ddfcfbd7a5e75b83260eab8fd35fd6778','Kevin','Smith',3000.00),(5,2,'customer3@gmail.ru','6df036de595c98ba47361a68c18f0fa2f97854ed','Basil','White',4945.00),(6,2,'customer4@gmail.ru','9264deb662735da0309e56db556e36ceae25278e','Abbi','Smile',6000.00),(7,2,'customer5@gmail.ru','bf3dab9d79bb0451c24b615d245ac0295407b023','Lisa','Grey',12000.00),(8,2,'customer6@gmail.ru','88fd21a7683b39d37e45e40cd74ce1f106704f55','Nia','Summer',5000.00),(9,2,'customer7@gmail.ru','4809ec31dfc37ba00a1b019bcaece606ef4a783e','Gabe','Brown',12000.00),(10,2,'customer8@gmail.ru','f51726d4852c970663410c5a99d3e81c1bf327b2','Logan','Moore',3000.00),(11,2,'customer9@gmail.ru','8766f9a0d7e758a06fbe632473e6171748ac84d7','Jack','Anderson',500.00),(12,2,'customer10@gmail.ru','4a4ab05c13de27274d0e4900e06d0568cc2d42e5','Ellie','Thompson',139270.00),(13,3,'customer11@gmail.ru','27f45b25fad76ef0cba1b25589ba8292c5302e5e','Harper','King',1958.50),(14,2,'customer12@gmail.ru','72e8799f0c9475c2cfd3d3131c6f87c43cc0b17c','Zoe','Baker',285.00),(15,2,'Hzztop','a4fae4b2e2131128631ab6fdfb353aef7eb30b2f','Игорь','Иванов',0.00),(16,2,'Hzztop1','93321401a99271568961b239fff7b2d9a694a8c9','Jordan','Brown',0.00),(17,3,'Hzztop2','93321401a99271568961b239fff7b2d9a694a8c9','Georg','White',0.00),(18,3,'Hzztop3','93321401a99271568961b239fff7b2d9a694a8c9','Richard','Second',0.00);
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

-- Dump completed on 2018-01-18 12:43:07
