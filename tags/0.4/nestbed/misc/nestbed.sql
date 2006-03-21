-- MySQL dump 10.9
--
-- Host: localhost    Database: nestbed
-- ------------------------------------------------------
-- Server version	4.1.14-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `nestbed`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nestbed` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `nestbed`;

--
-- Table structure for table `MoteDeploymentConfigurations`
--

DROP TABLE IF EXISTS `MoteDeploymentConfigurations`;
CREATE TABLE `MoteDeploymentConfigurations` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `projectDeploymentConfigurationID` int(4) unsigned NOT NULL default '0',
  `moteID` int(4) unsigned NOT NULL default '0',
  `programID` int(4) unsigned NOT NULL default '0',
  `radioPowerLevel` int(4) unsigned NOT NULL default '0',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `projectDeploymentConfigurationID` (`projectDeploymentConfigurationID`,`moteID`),
  KEY `IX_MoteDeploymentConfigurations_MoteID` (`moteID`),
  KEY `IX_MoteDeploymentConfigurations_ProgramID` (`programID`),
  CONSTRAINT `FK_ActiveMoteDeployments_Motes` FOREIGN KEY (`moteID`) REFERENCES `Motes` (`id`),
  CONSTRAINT `FK_MoteDeploymentConfigurations_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`),
  CONSTRAINT `FK_MoteDeploymentConfigurations_ProjectDeploymentConfigurations` FOREIGN KEY (`projectDeploymentConfigurationID`) REFERENCES `ProjectDeploymentConfigurations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `MoteTestbedAssignments`
--

DROP TABLE IF EXISTS `MoteTestbedAssignments`;
CREATE TABLE `MoteTestbedAssignments` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `testbedID` int(4) unsigned NOT NULL default '0',
  `moteID` int(4) unsigned NOT NULL default '0',
  `moteAddress` int(4) unsigned NOT NULL default '0',
  `moteLocationX` int(4) unsigned NOT NULL default '0',
  `moteLocationY` int(4) unsigned NOT NULL default '0',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `moteID` (`moteID`),
  UNIQUE KEY `testbedID` (`testbedID`,`moteAddress`),
  UNIQUE KEY `id` (`id`,`moteLocationX`,`moteLocationY`),
  KEY `IX_MoteTestbedAssignments_MoteID` (`moteID`),
  KEY `IX_MoteTestbedAssignments_TestbedID` (`testbedID`),
  CONSTRAINT `FK_MoteAssignments_Motes` FOREIGN KEY (`moteID`) REFERENCES `Motes` (`id`),
  CONSTRAINT `FK_MoteAssignments_Testbeds` FOREIGN KEY (`testbedID`) REFERENCES `Testbeds` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `MoteTypes`
--

DROP TABLE IF EXISTS `MoteTypes`;
CREATE TABLE `MoteTypes` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `name` varchar(25) NOT NULL default '',
  `totalROM` int(4) unsigned NOT NULL default '0',
  `totalRAM` int(4) unsigned NOT NULL default '0',
  `totalEEPROM` int(4) unsigned NOT NULL default '0',
  `image` blob NOT NULL,
  `tosPlatform` varchar(25) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `MoteTypes`
--

/*!40000 ALTER TABLE `MoteTypes` DISABLE KEYS */;
LOCK TABLES `MoteTypes` WRITE;
INSERT INTO `MoteTypes` VALUES (1,'Telos',61440,2048,0,'GIF89a2\0 \0�?\0���mqsZv�,Ln��JNP,Nm��͝�n���Tfu���IWjGpT�3k�\")-��ɩ�����5CW����������ǶVJ7���kg\\�zf���������$X����������������`���֐��aYJ;`}���v}~���V]\\;81������6>?=hW������^_c\Z=a}~�CCE���^���������!�Created with The GIMP\0!�\0\0?\0,\0\0\0\02\0 \0\0���pH,\Z�G�	�l:�,F�y<�ء)��F��-\'_��tj0\0��l^	\rG��X�r\nqtN,?!7y+kl++\'(�G7+!yhz\n9&�?\Z:�\n\r:#\'\r\'#<st�:3/4�\n\r8�/�!.8�\r1�+�38��8�/-�\"k8\0�N�9$����\0<\np4����\0P� ��\00��\nT�Q��*Y*�Q�8<�@+��\r\07`�Сf���T��E:l0�!@�,Z\0`��(�ⅎa*]3B��\rc\\���\"�3n�����\0$rY�&�%	\0\n�;Pf�ܑ`�\"��\n����\03��~��h���9\0�H1�A�\0$���a.�hq\0�P�L���8`�Q�0\\<8v�{�P���#(@�\"����\ZD�\'G�x��!���%�`�!�R`Bࡈqp���\r�%�A	P@�L�CJK��!�\0��7�(P_�=2\00)��E�A�^1�y0�����Xՙ���%p��%p��`A�u A\r�p\"\'D��h\0t��3p�\"x\'@\0OrB���dI#�\0\0p�0L�W� �\0@�2`YB	\0q�P�_&�Y��%HP��(89��x2\n���\"���rj�H�jiH����D��j��\Zr���b\0;','telos','2005-08-31 05:06:52'),(2,'Telos-b',49152,10240,128,'GIF89a2\0 \0�?\0���mqsZv�,Ln��JNP,Nm��͝�n���Tfu���IWjGpT�3k�\")-��ɩ�����5CW����������ǶVJ7���kg\\�zf���������$X����������������`���֐��aYJ;`}���v}~���V]\\;81������6>?=hW������^_c\Z=a}~�CCE���^���������!�Created with The GIMP\0!�\0\0?\0,\0\0\0\02\0 \0\0���pH,\Z�G�	�l:�,F�y<�ء)��F��-\'_��tj0\0��l^	\rG��X�r\nqtN,?!7y+kl++\'(�G7+!yhz\n9&�?\Z:�\n\r:#\'\r\'#<st�:3/4�\n\r8�/�!.8�\r1�+�38��8�/-�\"k8\0�N�9$����\0<\np4����\0P� ��\00��\nT�Q��*Y*�Q�8<�@+��\r\07`�Сf���T��E:l0�!@�,Z\0`��(�ⅎa*]3B��\rc\\���\"�3n�����\0$rY�&�%	\0\n�;Pf�ܑ`�\"��\n����\03��~��h���9\0�H1�A�\0$���a.�hq\0�P�L���8`�Q�0\\<8v�{�P���#(@�\"����\ZD�\'G�x��!���%�`�!�R`Bࡈqp���\r�%�A	P@�L�CJK��!�\0��7�(P_�=2\00)��E�A�^1�y0�����Xՙ���%p��%p��`A�u A\r�p\"\'D��h\0t��3p�\"x\'@\0OrB���dI#�\0\0p�0L�W� �\0@�2`YB	\0q�P�_&�Y��%HP��(89��x2\n���\"���rj�H�jiH����D��j��\Zr���b\0;','telosb','2005-08-31 05:06:19'),(3,'T-Mote Sky',49152,10240,128,'GIF89a2\0 \0�?\0���mqsZv�,Ln��JNP,Nm��͝�n���Tfu���IWjGpT�3k�\")-��ɩ�����5CW����������ǶVJ7���kg\\�zf���������$X����������������`���֐��aYJ;`}���v}~���V]\\;81������6>?=hW������^_c\Z=a}~�CCE���^���������!�Created with The GIMP\0!�\0\0?\0,\0\0\0\02\0 \0\0���pH,\Z�G�	�l:�,F�y<�ء)��F��-\'_��tj0\0��l^	\rG��X�r\nqtN,?!7y+kl++\'(�G7+!yhz\n9&�?\Z:�\n\r:#\'\r\'#<st�:3/4�\n\r8�/�!.8�\r1�+�38��8�/-�\"k8\0�N�9$����\0<\np4����\0P� ��\00��\nT�Q��*Y*�Q�8<�@+��\r\07`�Сf���T��E:l0�!@�,Z\0`��(�ⅎa*]3B��\rc\\���\"�3n�����\0$rY�&�%	\0\n�;Pf�ܑ`�\"��\n����\03��~��h���9\0�H1�A�\0$���a.�hq\0�P�L���8`�Q�0\\<8v�{�P���#(@�\"����\ZD�\'G�x��!���%�`�!�R`Bࡈqp���\r�%�A	P@�L�CJK��!�\0��7�(P_�=2\00)��E�A�^1�y0�����Xՙ���%p��%p��`A�u A\r�p\"\'D��h\0t��3p�\"x\'@\0OrB���dI#�\0\0p�0L�W� �\0@�2`YB	\0q�P�_&�Y��%HP��(89��x2\n���\"���rj�H�jiH����D��j��\Zr���b\0;','telosb','2005-08-31 05:06:20');
UNLOCK TABLES;
/*!40000 ALTER TABLE `MoteTypes` ENABLE KEYS */;

--
-- Table structure for table `Motes`
--

DROP TABLE IF EXISTS `Motes`;
CREATE TABLE `Motes` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `moteSerialID` varchar(10) NOT NULL default '',
  `moteTypeID` int(4) unsigned NOT NULL default '0',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `moteSerialID` (`moteSerialID`),
  KEY `IX_Motes_MoteTypeID` (`moteTypeID`),
  CONSTRAINT `FK_Motes_MoteTypes` FOREIGN KEY (`moteTypeID`) REFERENCES `MoteTypes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProgramMessageSymbols`
--

DROP TABLE IF EXISTS `ProgramMessageSymbols`;
CREATE TABLE `ProgramMessageSymbols` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `programID` int(4) unsigned NOT NULL default '0',
  `name` varchar(255) NOT NULL default '',
  `bytecode` blob NOT NULL,
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `programID_name` (`programID`,`name`),
  KEY `IX_ProgramMessageSymbols_ProgramID` (`programID`),
  CONSTRAINT `FK_ProgramMessageSymbols_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProgramMessageTypes`
--

DROP TABLE IF EXISTS `ProgramMessageTypes`;
CREATE TABLE `ProgramMessageTypes` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `programID` int(4) unsigned NOT NULL default '0',
  `name` varchar(255) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `programID_name` (`programID`,`name`),
  KEY `IX_ProgramMessageTypes_ProgramID` (`programID`),
  CONSTRAINT `FK_ProgramMessageTypes_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProgramProfilingMessageSymbols`
--

DROP TABLE IF EXISTS `ProgramProfilingMessageSymbols`;
CREATE TABLE `ProgramProfilingMessageSymbols` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `projectDeploymentConfigurationID` int(4) unsigned NOT NULL default '0',
  `programMessageSymbolID` int(4) unsigned NOT NULL default '0',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `projectDeploymentConfigurationID_programMessageSymbolID` (`projectDeploymentConfigurationID`,`programMessageSymbolID`),
  KEY `IX_ProgramProfilingMessageSymbols_ProgramMessageSymbolID` (`programMessageSymbolID`),
  CONSTRAINT `FK_ProgramProfilingMessageSymbols_ProgramMessageSymbols` FOREIGN KEY (`programMessageSymbolID`) REFERENCES `ProgramMessageSymbols` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProgramProfilingSymbols`
--

DROP TABLE IF EXISTS `ProgramProfilingSymbols`;
CREATE TABLE `ProgramProfilingSymbols` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `projectDeploymentConfigurationID` int(4) unsigned NOT NULL default '0',
  `programSymbolID` int(4) unsigned NOT NULL default '0',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `projectDeploymentConfigurationID` (`projectDeploymentConfigurationID`,`programSymbolID`),
  KEY `IX_ProgramProfilingSymbols_ProgramSymbolID` (`programSymbolID`),
  CONSTRAINT `FK_ProgramProfilingSymbols_ProgramSymbols` FOREIGN KEY (`programSymbolID`) REFERENCES `ProgramSymbols` (`id`),
  CONSTRAINT `FK_ProgramProfilingSymbols_ProjectDeploymentConfigurations` FOREIGN KEY (`projectDeploymentConfigurationID`) REFERENCES `ProjectDeploymentConfigurations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProgramSymbols`
--

DROP TABLE IF EXISTS `ProgramSymbols`;
CREATE TABLE `ProgramSymbols` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `programID` int(4) unsigned NOT NULL default '0',
  `module` varchar(50) NOT NULL default '',
  `symbol` varchar(50) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `symbol` (`programID`,`module`,`symbol`),
  KEY `IX_ProgramSymbols_Programs` (`programID`),
  CONSTRAINT `FK_ProgramSymbols_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `Programs`
--

DROP TABLE IF EXISTS `Programs`;
CREATE TABLE `Programs` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `projectID` int(4) unsigned NOT NULL default '0',
  `name` varchar(25) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  `sourcePath` varchar(255) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `projectID` (`projectID`,`name`),
  UNIQUE KEY `sourcePath` (`sourcePath`),
  CONSTRAINT `FK_Programs_Projects` FOREIGN KEY (`projectID`) REFERENCES `Projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `ProjectDeploymentConfigurations`
--

DROP TABLE IF EXISTS `ProjectDeploymentConfigurations`;
CREATE TABLE `ProjectDeploymentConfigurations` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `projectID` int(4) unsigned NOT NULL default '0',
  `name` varchar(25) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `projectID` (`projectID`,`name`),
  CONSTRAINT `FK_ProjectDeploymentConfigurations_Projects` FOREIGN KEY (`projectID`) REFERENCES `Projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `Projects`
--

DROP TABLE IF EXISTS `Projects`;
CREATE TABLE `Projects` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `testbedID` int(4) unsigned NOT NULL default '0',
  `name` varchar(25) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `testbedID` (`testbedID`,`name`),
  KEY `IX_Projects_TestBedID` (`testbedID`),
  CONSTRAINT `FK_Projects_Testbeds` FOREIGN KEY (`testbedID`) REFERENCES `Testbeds` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Testbeds`
--

DROP TABLE IF EXISTS `Testbeds`;
CREATE TABLE `Testbeds` (
  `id` int(4) unsigned NOT NULL auto_increment,
  `name` varchar(25) NOT NULL default '',
  `description` varchar(255) NOT NULL default '',
  `image` blob NOT NULL,
  `timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

