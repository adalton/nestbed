-- $Id$
-- MySQL dump 9.11
--
-- Host: localhost    Database: tosbed
-- ------------------------------------------------------
-- Server version	4.0.25

--
-- Current Database: tosbed
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ tosbed;

USE tosbed;

--
-- Table structure for table `MoteDeploymentConfigurations`
--

DROP TABLE IF EXISTS MoteDeploymentConfigurations;
CREATE TABLE MoteDeploymentConfigurations (
  id int(4) unsigned NOT NULL auto_increment,
  projectDeploymentConfigurationID int(4) unsigned NOT NULL default '0',
  moteID int(4) unsigned NOT NULL default '0',
  programID int(4) unsigned NOT NULL default '0',
  radioPowerLevel int(4) unsigned NOT NULL default '0',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY projectDeploymentConfigurationID (projectDeploymentConfigurationID,moteID),
  KEY IX_MoteDeploymentConfigurations_MoteID (moteID),
  KEY IX_MoteDeploymentConfigurations_ProgramID (programID),
  CONSTRAINT `FK_ActiveMoteDeployments_Motes` FOREIGN KEY (`moteID`) REFERENCES `Motes` (`id`),
  CONSTRAINT `FK_MoteDeploymentConfigurations_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`),
  CONSTRAINT `FK_MoteDeploymentConfigurations_ProjectDeploymentConfigurations` FOREIGN KEY (`projectDeploymentConfigurationID`) REFERENCES `ProjectDeploymentConfigurations` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `MoteDeploymentConfigurations`
--


/*!40000 ALTER TABLE MoteDeploymentConfigurations DISABLE KEYS */;
LOCK TABLES MoteDeploymentConfigurations WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE MoteDeploymentConfigurations ENABLE KEYS */;

--
-- Table structure for table `MoteTestbedAssignments`
--

DROP TABLE IF EXISTS MoteTestbedAssignments;
CREATE TABLE MoteTestbedAssignments (
  id int(4) unsigned NOT NULL auto_increment,
  testbedID int(4) unsigned NOT NULL default '0',
  moteID int(4) unsigned NOT NULL default '0',
  moteAddress int(4) unsigned NOT NULL default '0',
  moteLocationX int(4) unsigned NOT NULL default '0',
  moteLocationY int(4) unsigned NOT NULL default '0',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY moteID (moteID),
  UNIQUE KEY testbedID (testbedID,moteAddress),
  UNIQUE KEY id (id,moteLocationX,moteLocationY),
  KEY IX_MoteTestbedAssignments_MoteID (moteID),
  KEY IX_MoteTestbedAssignments_TestbedID (testbedID),
  CONSTRAINT `FK_MoteAssignments_Motes` FOREIGN KEY (`moteID`) REFERENCES `Motes` (`id`),
  CONSTRAINT `FK_MoteAssignments_Testbeds` FOREIGN KEY (`testbedID`) REFERENCES `Testbeds` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `MoteTestbedAssignments`
--


/*!40000 ALTER TABLE MoteTestbedAssignments DISABLE KEYS */;
LOCK TABLES MoteTestbedAssignments WRITE;
INSERT INTO MoteTestbedAssignments VALUES (1,1,6,0,0,0,20050904012826),(2,1,1,1,1,0,20050904012905),(3,1,3,2,2,0,20050904012941),(4,1,2,3,3,0,20050904012957),(5,1,4,4,0,1,20050904013014),(6,1,5,5,1,1,20050904013029),(7,1,7,6,2,1,20050904013052),(8,1,8,7,3,1,20050904013112),(9,1,9,8,0,2,20050904013133),(10,1,15,9,1,2,20050904013152),(11,1,16,10,2,2,20050904013215),(12,1,13,11,3,2,20050904013234),(13,1,10,12,0,3,20050904013254),(14,1,11,13,1,3,20050904013312);
UNLOCK TABLES;
/*!40000 ALTER TABLE MoteTestbedAssignments ENABLE KEYS */;

--
-- Table structure for table `MoteTypes`
--

DROP TABLE IF EXISTS MoteTypes;
CREATE TABLE MoteTypes (
  id int(4) unsigned NOT NULL auto_increment,
  name varchar(25) NOT NULL default '',
  totalROM int(4) unsigned NOT NULL default '0',
  totalRAM int(4) unsigned NOT NULL default '0',
  totalEEPROM int(4) unsigned NOT NULL default '0',
  image blob NOT NULL,
  tosPlatform varchar(25) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id)
) TYPE=InnoDB;

--
-- Dumping data for table `MoteTypes`
--


/*!40000 ALTER TABLE MoteTypes DISABLE KEYS */;
LOCK TABLES MoteTypes WRITE;
INSERT INTO MoteTypes VALUES (1,'Telos',61440,2048,0,'GIF89a2\0 \0¥?\0¯±°mqsZvŒ,Ln‡˜JNP,NmÈÊÍŠnØÔÍTfuØØ×IWjGpTŠ3k’\")-ÔÏÉ©¥›—˜—5CW…‡‡š¦­îïïŒ›¥ÏÇ¶VJ7Áºªkg\\…zf‡”œ¾ÄÇÍÏÒ$X‚˜”Š¶»½‚Œ•¡”ÛŞá`ĞÓÖŒ…aYJ;`}àãæv}~š V]\\;81ŒàÜÖ6>?=hW Ÿ£ÁÀÀ^_c\Z=a}~‚CCE““^ÒÓÏ×ÏĞÿÿÿ!şCreated with The GIMP\0!ù\0\0?\0,\0\0\0\02\0 \0\0şÀŸpH,\ZGÓ	Él:‘,F€y<¯Ø¡)à¨ŸFˆ-\'_®ãtj0\0ŠÙl^	\rGèäXr\nqtN,?!7y+kl++\'(†G7+!yhz\n9&œ?\Z:»\n\r:#\'\r\'#<stÅ:3/4\n\r8¹/Æ!.8¥\r1†+»38õ8Ö/-İ\"k8\0‚N…9$´§Ë\0<\np4˜ˆƒÁ\0P ‡Ì\00¡Â\nTÄQ‡€*Y*áQ†8<ø@+‹ş\r\07`ÜĞ¡f€ëÁT©²E:l0À!@Á,Z\0`Â´(àâ…a*]3B‚†\rc\\ô¼Â\" 3n¸°¯À‹\0$rYü&–%	\0\nø;Pf„Ü‘` \"€£\n×ü•\03—~Šh±©Š9\0ìH1™AŒ\0$†Ãa.èhq\0€P†L€¸¡8`‘Q»0\\<8vû{ÕP‚â×#(@†\"À€°\ZDˆ\'G…x¿!û×%¸`à!æR`Bà¡ˆqp¸—€\rÙ%A	P@€LÀCJK¹’!¨\0ƒ€7Æ(P_ù=2\00)àÅEAè^1Šy0ô½²”XÕ™‘¢ğ%p€€%p ƒ`A£u A\rp\"\'Dàh\0t ‚3på\"x\'@\0OrBŠÅ¨dI# \0\0pŞ0L¨W‹ €\0@Š2`YB	\0qÀP_&ŞYØ%HPÂÑ(89—¢x2\nƒ¤€\"Áè•rjÄH jiH Àª©D €j©°\ZrÀ¦µb\0;','telos',20050831050652),(2,'Telos-b',49152,10240,128,'GIF89a2\0 \0¥?\0¯±°mqsZvŒ,Ln‡˜JNP,NmÈÊÍŠnØÔÍTfuØØ×IWjGpTŠ3k’\")-ÔÏÉ©¥›—˜—5CW…‡‡š¦­îïïŒ›¥ÏÇ¶VJ7Áºªkg\\…zf‡”œ¾ÄÇÍÏÒ$X‚˜”Š¶»½‚Œ•¡”ÛŞá`ĞÓÖŒ…aYJ;`}àãæv}~š V]\\;81ŒàÜÖ6>?=hW Ÿ£ÁÀÀ^_c\Z=a}~‚CCE““^ÒÓÏ×ÏĞÿÿÿ!şCreated with The GIMP\0!ù\0\0?\0,\0\0\0\02\0 \0\0şÀŸpH,\ZGÓ	Él:‘,F€y<¯Ø¡)à¨ŸFˆ-\'_®ãtj0\0ŠÙl^	\rGèäXr\nqtN,?!7y+kl++\'(†G7+!yhz\n9&œ?\Z:»\n\r:#\'\r\'#<stÅ:3/4\n\r8¹/Æ!.8¥\r1†+»38õ8Ö/-İ\"k8\0‚N…9$´§Ë\0<\np4˜ˆƒÁ\0P ‡Ì\00¡Â\nTÄQ‡€*Y*áQ†8<ø@+‹ş\r\07`ÜĞ¡f€ëÁT©²E:l0À!@Á,Z\0`Â´(àâ…a*]3B‚†\rc\\ô¼Â\" 3n¸°¯À‹\0$rYü&–%	\0\nø;Pf„Ü‘` \"€£\n×ü•\03—~Šh±©Š9\0ìH1™AŒ\0$†Ãa.èhq\0€P†L€¸¡8`‘Q»0\\<8vû{ÕP‚â×#(@†\"À€°\ZDˆ\'G…x¿!û×%¸`à!æR`Bà¡ˆqp¸—€\rÙ%A	P@€LÀCJK¹’!¨\0ƒ€7Æ(P_ù=2\00)àÅEAè^1Šy0ô½²”XÕ™‘¢ğ%p€€%p ƒ`A£u A\rp\"\'Dàh\0t ‚3på\"x\'@\0OrBŠÅ¨dI# \0\0pŞ0L¨W‹ €\0@Š2`YB	\0qÀP_&ŞYØ%HPÂÑ(89—¢x2\nƒ¤€\"Áè•rjÄH jiH Àª©D €j©°\ZrÀ¦µb\0;','telosb',20050831050619),(3,'T-Mote Sky',49152,10240,128,'GIF89a2\0 \0¥?\0¯±°mqsZvŒ,Ln‡˜JNP,NmÈÊÍŠnØÔÍTfuØØ×IWjGpTŠ3k’\")-ÔÏÉ©¥›—˜—5CW…‡‡š¦­îïïŒ›¥ÏÇ¶VJ7Áºªkg\\…zf‡”œ¾ÄÇÍÏÒ$X‚˜”Š¶»½‚Œ•¡”ÛŞá`ĞÓÖŒ…aYJ;`}àãæv}~š V]\\;81ŒàÜÖ6>?=hW Ÿ£ÁÀÀ^_c\Z=a}~‚CCE““^ÒÓÏ×ÏĞÿÿÿ!şCreated with The GIMP\0!ù\0\0?\0,\0\0\0\02\0 \0\0şÀŸpH,\ZGÓ	Él:‘,F€y<¯Ø¡)à¨ŸFˆ-\'_®ãtj0\0ŠÙl^	\rGèäXr\nqtN,?!7y+kl++\'(†G7+!yhz\n9&œ?\Z:»\n\r:#\'\r\'#<stÅ:3/4\n\r8¹/Æ!.8¥\r1†+»38õ8Ö/-İ\"k8\0‚N…9$´§Ë\0<\np4˜ˆƒÁ\0P ‡Ì\00¡Â\nTÄQ‡€*Y*áQ†8<ø@+‹ş\r\07`ÜĞ¡f€ëÁT©²E:l0À!@Á,Z\0`Â´(àâ…a*]3B‚†\rc\\ô¼Â\" 3n¸°¯À‹\0$rYü&–%	\0\nø;Pf„Ü‘` \"€£\n×ü•\03—~Šh±©Š9\0ìH1™AŒ\0$†Ãa.èhq\0€P†L€¸¡8`‘Q»0\\<8vû{ÕP‚â×#(@†\"À€°\ZDˆ\'G…x¿!û×%¸`à!æR`Bà¡ˆqp¸—€\rÙ%A	P@€LÀCJK¹’!¨\0ƒ€7Æ(P_ù=2\00)àÅEAè^1Šy0ô½²”XÕ™‘¢ğ%p€€%p ƒ`A£u A\rp\"\'Dàh\0t ‚3på\"x\'@\0OrBŠÅ¨dI# \0\0pŞ0L¨W‹ €\0@Š2`YB	\0qÀP_&ŞYØ%HPÂÑ(89—¢x2\nƒ¤€\"Áè•rjÄH jiH Àª©D €j©°\ZrÀ¦µb\0;','telosb',20050831050620);
UNLOCK TABLES;
/*!40000 ALTER TABLE MoteTypes ENABLE KEYS */;

--
-- Table structure for table `Motes`
--

DROP TABLE IF EXISTS Motes;
CREATE TABLE Motes (
  id int(4) unsigned NOT NULL auto_increment,
  moteSerialID varchar(10) NOT NULL default '',
  moteTypeID int(4) unsigned NOT NULL default '0',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY moteSerialID (moteSerialID),
  KEY IX_Motes_MoteTypeID (moteTypeID),
  CONSTRAINT `FK_Motes_MoteTypes` FOREIGN KEY (`moteTypeID`) REFERENCES `MoteTypes` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `Motes`
--


/*!40000 ALTER TABLE Motes DISABLE KEYS */;
LOCK TABLES Motes WRITE;
INSERT INTO Motes VALUES (1,'M49UG00A',2,20050822000905),(2,'M49WC0NL',3,20050829161745),(3,'M49UG00C',2,20050827211620),(4,'M49W90GM',3,20050829161724),(5,'M49WC0LE',3,20050829161701),(6,'M49UG00M',2,20050827212135),(7,'M49W90F0',3,20050829160746),(8,'M49WC0L1',3,20050829160853),(9,'M49WC0NC',3,20050829160938),(10,'M49W90F8',3,20050829161015),(11,'M49UG00N',2,20050829161054),(12,'M49UG00D',2,20050829161155),(13,'M49W90G2',3,20050829161301),(14,'M4MVASRF',1,20050829161407),(15,'M49UG00P',2,20050829161447),(16,'M49WC0LV',3,20050829161556);
UNLOCK TABLES;
/*!40000 ALTER TABLE Motes ENABLE KEYS */;

--
-- Table structure for table `ProgramMessageSymbols`
--

DROP TABLE IF EXISTS ProgramMessageSymbols;
CREATE TABLE ProgramMessageSymbols (
  id int(4) unsigned NOT NULL auto_increment,
  programID int(4) unsigned NOT NULL default '0',
  name varchar(255) NOT NULL default '',
  bytecode blob NOT NULL,
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY programID_name (programID,name),
  KEY IX_ProgramMessageSymbols_ProgramID (programID),
  CONSTRAINT `FK_ProgramMessageSymbols_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProgramMessageSymbols`
--


/*!40000 ALTER TABLE ProgramMessageSymbols DISABLE KEYS */;
LOCK TABLES ProgramMessageSymbols WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE ProgramMessageSymbols ENABLE KEYS */;

--
-- Table structure for table `ProgramMessageTypes`
--

DROP TABLE IF EXISTS ProgramMessageTypes;
CREATE TABLE ProgramMessageTypes (
  id int(4) unsigned NOT NULL auto_increment,
  programID int(4) unsigned NOT NULL default '0',
  name varchar(255) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY programID_name (programID,name),
  KEY IX_ProgramMessageTypes_ProgramID (programID),
  CONSTRAINT `FK_ProgramMessageTypes_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProgramMessageTypes`
--


/*!40000 ALTER TABLE ProgramMessageTypes DISABLE KEYS */;
LOCK TABLES ProgramMessageTypes WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE ProgramMessageTypes ENABLE KEYS */;

--
-- Table structure for table `ProgramProfilingMessageSymbols`
--

DROP TABLE IF EXISTS ProgramProfilingMessageSymbols;
CREATE TABLE ProgramProfilingMessageSymbols (
  id int(4) unsigned NOT NULL auto_increment,
  projectDeploymentConfigurationID int(4) unsigned NOT NULL default '0',
  programMessageSymbolID int(4) unsigned NOT NULL default '0',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY projectDeploymentConfigurationID_programMessageSymbolID (projectDeploymentConfigurationID,programMessageSymbolID),
  KEY IX_ProgramProfilingMessageSymbols_ProgramMessageSymbolID (programMessageSymbolID),
  CONSTRAINT `FK_ProgramProfilingMessageSymbols_ProgramMessageSymbols` FOREIGN KEY (`programMessageSymbolID`) REFERENCES `ProgramMessageSymbols` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProgramProfilingMessageSymbols`
--


/*!40000 ALTER TABLE ProgramProfilingMessageSymbols DISABLE KEYS */;
LOCK TABLES ProgramProfilingMessageSymbols WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE ProgramProfilingMessageSymbols ENABLE KEYS */;

--
-- Table structure for table `ProgramProfilingSymbols`
--

DROP TABLE IF EXISTS ProgramProfilingSymbols;
CREATE TABLE ProgramProfilingSymbols (
  id int(4) unsigned NOT NULL auto_increment,
  projectDeploymentConfigurationID int(4) unsigned NOT NULL default '0',
  programSymbolID int(4) unsigned NOT NULL default '0',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY projectDeploymentConfigurationID (projectDeploymentConfigurationID,programSymbolID),
  KEY IX_ProgramProfilingSymbols_ProgramSymbolID (programSymbolID),
  CONSTRAINT `FK_ProgramProfilingSymbols_ProgramSymbols` FOREIGN KEY (`programSymbolID`) REFERENCES `ProgramSymbols` (`id`),
  CONSTRAINT `FK_ProgramProfilingSymbols_ProjectDeploymentConfigurations` FOREIGN KEY (`projectDeploymentConfigurationID`) REFERENCES `ProjectDeploymentConfigurations` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProgramProfilingSymbols`
--


/*!40000 ALTER TABLE ProgramProfilingSymbols DISABLE KEYS */;
LOCK TABLES ProgramProfilingSymbols WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE ProgramProfilingSymbols ENABLE KEYS */;

--
-- Table structure for table `ProgramSymbols`
--

DROP TABLE IF EXISTS ProgramSymbols;
CREATE TABLE ProgramSymbols (
  id int(4) unsigned NOT NULL auto_increment,
  programID int(4) unsigned NOT NULL default '0',
  module varchar(50) NOT NULL default '',
  symbol varchar(50) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY symbol (programID,module,symbol),
  KEY IX_ProgramSymbols_Programs (programID),
  CONSTRAINT `FK_ProgramSymbols_Programs` FOREIGN KEY (`programID`) REFERENCES `Programs` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProgramSymbols`
--


/*!40000 ALTER TABLE ProgramSymbols DISABLE KEYS */;
LOCK TABLES ProgramSymbols WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE ProgramSymbols ENABLE KEYS */;

--
-- Table structure for table `Programs`
--

DROP TABLE IF EXISTS Programs;
CREATE TABLE Programs (
  id int(4) unsigned NOT NULL auto_increment,
  projectID int(4) unsigned NOT NULL default '0',
  name varchar(25) NOT NULL default '',
  description varchar(255) NOT NULL default '',
  sourcePath varchar(255) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY projectID (projectID,name),
  UNIQUE KEY sourcePath (sourcePath),
  CONSTRAINT `FK_Programs_Projects` FOREIGN KEY (`projectID`) REFERENCES `Projects` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `Programs`
--


/*!40000 ALTER TABLE Programs DISABLE KEYS */;
LOCK TABLES Programs WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE Programs ENABLE KEYS */;

--
-- Table structure for table `ProjectDeploymentConfigurations`
--

DROP TABLE IF EXISTS ProjectDeploymentConfigurations;
CREATE TABLE ProjectDeploymentConfigurations (
  id int(4) unsigned NOT NULL auto_increment,
  projectID int(4) unsigned NOT NULL default '0',
  name varchar(25) NOT NULL default '',
  description varchar(255) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY projectID (projectID,name),
  CONSTRAINT `FK_ProjectDeploymentConfigurations_Projects` FOREIGN KEY (`projectID`) REFERENCES `Projects` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `ProjectDeploymentConfigurations`
--


/*!40000 ALTER TABLE ProjectDeploymentConfigurations DISABLE KEYS */;
LOCK TABLES ProjectDeploymentConfigurations WRITE;
INSERT INTO ProjectDeploymentConfigurations VALUES (6,5,'hi','ho',20050911201841);
UNLOCK TABLES;
/*!40000 ALTER TABLE ProjectDeploymentConfigurations ENABLE KEYS */;

--
-- Table structure for table `Projects`
--

DROP TABLE IF EXISTS Projects;
CREATE TABLE Projects (
  id int(4) unsigned NOT NULL auto_increment,
  testbedID int(4) unsigned NOT NULL default '0',
  name varchar(25) NOT NULL default '',
  description varchar(255) NOT NULL default '',
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY testbedID (testbedID,name),
  KEY IX_Projects_TestBedID (testbedID),
  CONSTRAINT `FK_Projects_Testbeds` FOREIGN KEY (`testbedID`) REFERENCES `Testbeds` (`id`)
) TYPE=InnoDB;

--
-- Dumping data for table `Projects`
--


/*!40000 ALTER TABLE Projects DISABLE KEYS */;
LOCK TABLES Projects WRITE;
INSERT INTO Projects VALUES (5,1,'test','test',20050911201835);
UNLOCK TABLES;
/*!40000 ALTER TABLE Projects ENABLE KEYS */;

--
-- Table structure for table `Testbeds`
--

DROP TABLE IF EXISTS Testbeds;
CREATE TABLE Testbeds (
  id int(4) unsigned NOT NULL auto_increment,
  name varchar(25) NOT NULL default '',
  description varchar(255) NOT NULL default '',
  image blob NOT NULL,
  timestamp timestamp(14) NOT NULL,
  PRIMARY KEY  (id),
  UNIQUE KEY name (name)
) TYPE=InnoDB;

--
-- Dumping data for table `Testbeds`
--


/*!40000 ALTER TABLE Testbeds DISABLE KEYS */;
LOCK TABLES Testbeds WRITE;
INSERT INTO Testbeds VALUES (1,'Telos-b Testbed','Room 304-C Testbed','',20050709013932);
UNLOCK TABLES;
/*!40000 ALTER TABLE Testbeds ENABLE KEYS */;

