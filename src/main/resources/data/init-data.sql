# ************************************************************
# Sequel Ace SQL dump
# Version 2082
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 5.7.24)
# Database: gift-app-db-main
# Generation Time: 2020-10-16 14:57:46 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table addresses
# ------------------------------------------------------------

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;

INSERT IGNORE INTO `addresses` (`id`, `apartment`, `house`, `street_address`, `zip_code`, `state`, `city`)
VALUES
(1,'apartment-1','house-1','street-1','1000','demo-state','NY');

/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table privileges
# ------------------------------------------------------------


LOCK TABLES `privileges` WRITE;
/*!40000 ALTER TABLE `privileges` DISABLE KEYS */;

INSERT IGNORE INTO `privileges` (`id`, `name`)
VALUES
(1,'USER_PRIVILEGE'),
(2,'ADMIN_PRIVILEGE');

/*!40000 ALTER TABLE `privileges` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `roles` WRITE;

INSERT IGNORE INTO `roles` (`id`, `name`)
VALUES
(1,'Administrator'),
(2,'CrewMember'),
(3, 'Supervisor'),
(4, 'Dispatcher'),
(5, 'Biller'),
(6, 'QAReviewer'),
(7, 'HumanResource'),
(8, 'Mechanic'),
(9, 'SalesPerson'),
(10, 'MedicalDirector'),
(11, 'Onlooker');

UNLOCK TABLES;

ALTER TABLE `users` CHANGE `last_updated_on` `last_updated_on` DATETIME  NOT NULL  ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE `addresses` ADD `country` VARCHAR(255)  NOT NULL  DEFAULT ''  AFTER `zip_code`;

INSERT INTO service_level(id, name) VALUES(1, 'ALS'), (2, 'BLS');

INSERT INTO `vehicle_type`(`id`,`name`) VALUES(1, 'Car'), (2, 'Ambulance');

INSERT INTO `vehicles`
(`id`,
 `beacon_app_token`,
 `fuel_tank_range`,
 `call_sign`,
 `status`,
 `service_level_id`,
 `vehicle_type_id`, `odometer`, `patient_capacity`)
VALUES
(1, '1', 0, 'A-001', '1', 1, 1, 0, 0),
(2, '2', 0, 'B-001', '1', 1, 1, 0, 0),
(3, '2', 0, 'C-001', '1', 1, 1, 0, 0);

INSERT INTO `location` (`id`, `name`)
VALUES(1,'Location 1'),(2,'Location 2');

INSERT INTO `check_list` (`id`, `name`)
VALUES(1,'CheckList 1'),(2,'CheckList 2');

INSERT INTO `certificates` (`id`, `name`) VALUES
(1,'Advanced Cardiac Life Support[ACLS]'),
(2,'ALS - Intermediate'),
(3,'BLS - BASIC/EMT'),
(4,'Cardio - Pulmonary Resuscitation CPR'),
(5,'National Registry - AEMT'),
(6,'Rescue - Structure / Trench'),
(7,'Wheelchair Transport');

INSERT INTO station (id, name)
VALUES(1,'station 1'),(2,'station 2');
