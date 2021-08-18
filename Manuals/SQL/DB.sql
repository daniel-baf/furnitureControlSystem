-- creation

DROP SCHEMA IF EXISTS `FurnitureAssemblyPjct`;
CREATE SCHEMA `FurnitureAssemblyPjct`;

-- use schame

USE `FurnitureAssemblyPjct`;

-- tables creation

DROP TABLE IF EXISTS `Furniture`;

CREATE TABLE `Furniture` (
  `name` varchar(100) NOT NULL,
  `sellPrice` decimal(7,2) NOT NULL,
  PRIMARY KEY (`name`)
);


DROP TABLE IF EXISTS `FurniturePiece`;

CREATE TABLE `FurniturePiece` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `cost` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `User`;

CREATE TABLE `User` (
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `areaCode` tinyint(1) NOT NULL,
  PRIMARY KEY (`name`)
);

DROP TABLE IF EXISTS `Client`;

CREATE TABLE `Client` (
  `nit` varchar(12) NOT NULL,
  `name` varchar(75) NOT NULL,
  `adress` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`NIT`)
);


-- unions

DROP TABLE IF EXISTS `PieceAssembly`;

CREATE TABLE `PieceAssembly` (
  `furnitureName` varchar(100) NOT NULL,
  `pieceName` varchar(150) NOT NULL,
  `cuantity` int NOT NULL,
  PRIMARY KEY (`furnitureName`,`pieceName`),
  CONSTRAINT `fk_funit_assem` FOREIGN KEY (`furnitureName`) 
   REFERENCES `Furniture` (`name`) 
    ON DELETE NO ACTION -- i always want to know the unions
    ON UPDATE CASCADE
);
    
DROP TABLE IF EXISTS `FurnitureAssembly`;

CREATE TABLE `FurnitureAssembly` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `sold` tinyint(1) NOT NULL DEFAULT '0', -- 0 = no sold, 1 = sold, 3 = refounded an re integred
  `furnitureName` varchar(100) NOT NULL,
  `assemblyPrice` decimal(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_furn_furnAssemb` 
    FOREIGN KEY (`furnitureName`) 
    REFERENCES `Furniture` (`name`) 
    ON DELETE NO ACTION -- the sold inventory is never deleted, so name cannot be deleted
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_furnAssemb`
    FOREIGN KEY (`userName`)
    REFERENCES `User` (`name`) 
    ON UPDATE CASCADE
    ON DELETE NO ACTION -- I don't want to lose the worker id
);

DROP TABLE IF EXISTS `Bill`;

CREATE TABLE `Bill` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `furnitureAssembyId` INT NOT NULL,
  `ammount` DECIMAL(7,2) NOT NULL,
  `clientNIT` VARCHAR(12) NOT NULL,
  `buyDate` DATE NOT NULL,
  PRIMARY KEY (`code`),
  CONSTRAINT `fk_client_bill` 
    FOREIGN KEY (`clientNIT`)
    REFERENCES `Client` (`NIT`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_furnAssem_bill` 
    FOREIGN KEY (`furnitureAssembyId`) 
    REFERENCES `FurnitureAssembly` (`id`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_bill` 
    FOREIGN KEY (`userName`) 
    REFERENCES `User` (`name`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);
    
DROP TABLE IF EXISTS `Refund`;

CREATE TABLE `Refund` (
  `billCode` int NOT NULL,
  `date` date NOT NULL,
  `ammount` decimal(7,2) NOT NULL,
  PRIMARY KEY (`billCode`,`date`),
  CONSTRAINT `fk_bill_refund` 
    FOREIGN KEY (`billCode`) 
    REFERENCES `Bill` (`code`) 
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);