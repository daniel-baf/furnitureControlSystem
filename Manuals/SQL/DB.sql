-- creation

DROP SCHEMA IF EXISTS `Furniture_Assembly_Pjct`;
CREATE SCHEMA `Furniture_Assembly_Pjct`;

-- use schame

USE `Furniture_Assembly_Pjct`;

-- tables creation

DROP TABLE IF EXISTS `Furniture`;

CREATE TABLE `Furniture` (
  `name` varchar(100) NOT NULL,
  `sell_Price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`name`)
);

DROP TABLE IF EXISTS `Furniture_Piece`;

CREATE TABLE `Furniture_Piece` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `cost` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `name` varchar(45) NOT NULL,
  `password` varchar(80) NOT NULL,
  `area_Code` tinyint(1) NOT NULL,
  `authorized` INT NULL DEFAULT 1,
  PRIMARY KEY (`name`)
);

DROP TABLE IF EXISTS `Client`;

CREATE TABLE `Client` (
  `nit` varchar(12) NOT NULL,
  `name` varchar(75) NOT NULL,
  `adress` varchar(120) NOT NULL,
  `municipality` VARCHAR(100) NULL,
  `department` VARCHAR(100) NULL,
  PRIMARY KEY (`NIT`)
);

-- unions

DROP TABLE IF EXISTS `Piece_Assembly`;

CREATE TABLE `Piece_Assembly` (
  `furniture_Name` varchar(100) NOT NULL,
  `piece_Name` varchar(150) NOT NULL,
  `cuantity` int NOT NULL,
  PRIMARY KEY (`furniture_Name`,`piece_Name`),
  CONSTRAINT `fk_funit_assem` FOREIGN KEY (`furniture_Name`) 
   REFERENCES `Furniture` (`name`) 
    ON DELETE NO ACTION -- i always want to know the unions
    ON UPDATE CASCADE
);
    
DROP TABLE IF EXISTS `Furniture_Assembly`;

CREATE TABLE `Furniture_Assembly` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `sold` tinyint(1) NOT NULL DEFAULT '0', -- 0 = no sold, 1 = sold, 2 = refounded an re integred
  `furniture_Name` varchar(100) NOT NULL,
  `assembly_Price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_furn_furnAssemb` 
    FOREIGN KEY (`furniture_Name`) 
    REFERENCES `Furniture` (`name`) 
    ON DELETE NO ACTION -- the sold inventory is never deleted, so name cannot be deleted
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_furnAssemb`
    FOREIGN KEY (`user_Name`)
    REFERENCES `User` (`name`) 
    ON UPDATE CASCADE
    ON DELETE NO ACTION -- I don't want to lose the worker id
);

DROP TABLE IF EXISTS `Bill`;

CREATE TABLE `Bill` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `user_Name` VARCHAR(45) NOT NULL,
  `furniture_Assemby_Id` INT NOT NULL,
  `ammount` DECIMAL(7,2) NOT NULL,
  `client_NIT` VARCHAR(12) NOT NULL,
  `buy_Date` DATE NOT NULL,
  PRIMARY KEY (`code`),
  CONSTRAINT `fk_client_bill` 
    FOREIGN KEY (`client_NIT`)
    REFERENCES `Client` (`NIT`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_furnAssem_bill` 
    FOREIGN KEY (`furniture_Assemby_Id`) 
    REFERENCES `Furniture_Assembly` (`id`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_bill` 
    FOREIGN KEY (`user_Name`) 
    REFERENCES `User` (`name`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);
    
DROP TABLE IF EXISTS `Refund`;

CREATE TABLE `Refund` (
  `bill_Code` int NOT NULL,
  `date` date NOT NULL,
  `ammount` decimal(7,2) NOT NULL,
  PRIMARY KEY (`bill_Code`,`date`),
  CONSTRAINT `fk_bill_refund` 
    FOREIGN KEY (`bill_Code`) 
    REFERENCES `Bill` (`code`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);

-- DEFAULT USER area 3 for data insert by file
INSERT INTO `User` (`name`, `password`, `area_Code`) VALUES ('admin', 'JEZ/Oj4hmSIMgj0eRCUqnQ==', '3');

-- WAR user

DROP USER IF EXISTS 'ipc2'@'localhost';
CREATE USER 'ipc2'@'localhost' IDENTIFIED BY 'ipc2+contraPjct0s';
GRANT ALL PRIVILEGES ON Furniture_Assembly_Pjct.* TO 'ipc2'@'localhost';
FLUSH PRIVILEGES;