# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.15)
# Database: my_db
# Generation Time: 2018-03-30 07:33:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table valueset_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `valueset_category`;

CREATE TABLE `valueset_category` (
  `code` int(11) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `valueset_category` WRITE;
/*!40000 ALTER TABLE `valueset_category` DISABLE KEYS */;

INSERT INTO `valueset_category` (`code`, `category`)
VALUES
	(1,'Drug Abuse'),
	(2,'Alcohol use and alcoholism'),
	(3,'Mental health'),
	(4,'HIV/AIDS and other communicable disease'),
	(5,'Genetic disease'),
	(7,'Sexuality and Reproductive health'),
	(8,'Other addictions'),
	(9,'Other'),
	(10,'Unsure');

/*!40000 ALTER TABLE `valueset_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table valueset_rxnorm
# ------------------------------------------------------------

DROP TABLE IF EXISTS `valueset_rxnorm`;

CREATE TABLE `valueset_rxnorm` (
  `code` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `code_system` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `oid` varchar(255) DEFAULT NULL,
  `tty` varchar(255) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  KEY `category` (`category`),
  CONSTRAINT `valueset_rxnorm_ibfk_1` FOREIGN KEY (`category`) REFERENCES `valueset_category` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `valueset_rxnorm` WRITE;
/*!40000 ALTER TABLE `valueset_rxnorm` DISABLE KEYS */;

INSERT INTO `valueset_rxnorm` (`code`, `description`, `code_system`, `version`, `oid`, `tty`, `category`)
VALUES
	(581646,'Budeprion','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(602959,'Campral','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(152535,'Zovirax','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',4),
	(281,'Acyclovir','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',4),
	(10355,'Temazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(1040032,'Latuda','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(10502,'Thioridazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(10510,'Thiothixene','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(10737,'Trazodone','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(10800,'Trifluoperazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(10805,'Triflupromazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(1086773,'Viibryd','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(1099563,'24 HR Divalproex Sodium 250 MG Extended Release Oral Tablet','RXNORM','2016-09','2.16.840.1.113883.6.88','SCD',3),
	(1099596,'Divalproex Sodium 125 MG Delayed Release Oral Capsule','RXNORM','2016-09','2.16.840.1.113883.6.88','SCD',3),
	(1101926,'24 HR dexmethylphenidate hydrochloride 25 MG Extended Release Oral Capsule','RXNORM','2016-09','2.16.840.1.113883.6.88','SCD',3),
	(11118,'Valproic Acid','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(11270,'Vivactil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(114228,'Paxil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(131725,'Ambien','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(134774,'Remeron','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(151532,'Cogentin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(151612,'Dexedrine','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(151679,'Serzone','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(151692,'Effexor','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(151839,'Haldol','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(152318,'Risperdal','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(154990,'Buspar','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(15996,'Mirtazapine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(1749,'Bromazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(196502,'Lamictal','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(202363,'Xanax','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(202472,'Valium','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(202479,'Ativan','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(202585,'Klonopin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(202966,'Tranxene','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203116,'buspirone hydrochloride','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(203245,'Depakene','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203321,'Lithobid','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203322,'Lithane','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203347,'Loxitane','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203771,'Norpramin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203799,'Nardil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(203832,'Parnate','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(215928,'Celexa','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(216102,'Clozaril','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(216459,'Depakote','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(219641,'Restoril','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(224917,'Ritalin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(225029,'Surmontil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(227423,'Halcion','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(227557,'Moban','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(2403,'Chlorpromazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(2406,'Chlorprothixene','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(25190,'fluphenazine decanoate','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(2556,'Citalopram','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(2598,'Clonazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(261572,'Methylin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(2626,'Clozapine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(26412,'halazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(26420,'haloperidol decanoate','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(28439,'lamotrigine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(284704,'Concerta','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(285064,'Geodon','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(28697,'Librax','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(31565,'nefazodone','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(321988,'Escitalopram','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(3247,'Desipramine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(3287,'Dextroamphetamine Sulfate','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(32937,'Paroxetine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(3322,'Diazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(352393,'Abilify','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(352654,'Focalin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(352741,'Lexapro','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(358330,'Strattera','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(3638,'Doxepin','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(3639,'Doxorubicin','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(36437,'Sertraline','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(370331,'Methylphenidate Extended Release Oral Tablet [Concerta]','RXNORM','2016-09','2.16.840.1.113883.6.88','SBDF',3),
	(39786,'venlafaxine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(39993,'zolpidem','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(405343,'Symbyax','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(4077,'Estazolam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(42351,'Lithium Carbonate','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(42568,'Wellbutrin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(42687,'Luvox','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(4493,'Fluoxetine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(4496,'Fluphenazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(482574,'Cymbalta','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(5093,'Haloperidol','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(52105,'lithium citrate','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(540404,'Lunesta','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(5691,'Imipramine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(583135,'Equetro','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(58827,'Prozac','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(594111,'Metadate','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(596,'Alprazolam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(6011,'Isocarboxazid','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(61381,'olanzapine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(617716,'Emsam','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(6470,'Lorazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(6475,'Loxapine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(6646,'Maprotiline','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(6716,'Tofranil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(6779,'Mesoridazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(686438,'Invega','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(6901,'Methylphenidate','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(7019,'Molindone','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(704,'Amitriptyline','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(711043,'Vyvanse','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(711372,'Pristiq','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(722,'Amoxapine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(72625,'duloxetine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(7531,'Nortriptyline','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(756,'Anafranil','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(7781,'Oxazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(7866,'Pamelor','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(7966,'Pemoline','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(8076,'Perphenazine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(81984,'Clomipramine Hydrochloride','RXNORM','2016-09','2.16.840.1.113883.6.88','PIN',3),
	(82728,'Zoloft','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(835486,'Aplenzin','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(83553,'Seroquel','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(84815,'Adderall','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(854882,'Zolpidem tartrate 12.5 MG Extended Release Oral Tablet [Ambien]','RXNORM','2016-09','2.16.840.1.113883.6.88','SBD',3),
	(859976,'Saphris','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',3),
	(8627,'Prazepam','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',3),
	(152092,'ReVia','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(1819,'Buprenorphine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',1),
	(42347,'Bupropion','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',1),
	(636556,'Vivitrol','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(6813,'Methadone','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',1),
	(7243,'Naltrexone','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',1),
	(218734,'Nicotrol','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(636230,'Chantix','RXNORM','2016-09','2.16.840.1.113883.6.88','BN',1),
	(7407,'Nicotine','RXNORM','2016-09','2.16.840.1.113883.6.88','IN',1);

/*!40000 ALTER TABLE `valueset_rxnorm` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
