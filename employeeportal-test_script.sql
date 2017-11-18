-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema employeeportal-test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema employeeportal-test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employeeportal-test` DEFAULT CHARACTER SET latin1 ;
USE `employeeportal-test` ;

-- -----------------------------------------------------
-- Table `employeeportal-test`.`USstate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`USstate` (
  `stateId` INT(11) NOT NULL AUTO_INCREMENT,
  `state_code` VARCHAR(2) NULL DEFAULT NULL,
  `state_name` VARCHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`stateId`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`Client` (
  `clientId` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `address1` VARCHAR(100) NOT NULL,
  `address2` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(30) NOT NULL,
  `postal_zip_code` VARCHAR(20) NOT NULL,
  `email` VARCHAR(60) NULL,
  `home_phone` VARCHAR(45) NOT NULL,
  `mobile_phone` VARCHAR(45) NULL DEFAULT NULL,
  `USstate_stateId` INT(11) NOT NULL,
  `Status` TINYINT(1) NULL,
  PRIMARY KEY (`clientId`),
  INDEX `fk_Client_USstate1_idx` (`USstate_stateId` ASC),
  CONSTRAINT `fk_Client_USstate1`
    FOREIGN KEY (`USstate_stateId`)
    REFERENCES `employeeportal-test`.`USstate` (`stateId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`Title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`Title` (
  `titleId` INT(11) NOT NULL,
  `jobTitle` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`titleId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`Employee` (
  `employeeId` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `address1` VARCHAR(100) NOT NULL,
  `address2` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(30) NOT NULL,
  `postal_zip_code` VARCHAR(20) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `home_phone` VARCHAR(45) NOT NULL,
  `mobile_phone` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(15) NOT NULL,
  `USstate_stateId` INT(11) NOT NULL,
  `Title_titleId` INT(11) NOT NULL,
  `Status` TINYINT(1) NULL,
  PRIMARY KEY (`employeeId`),
  INDEX `fk_Employee_USstate1_idx` (`USstate_stateId` ASC),
  INDEX `fk_Employee_Title1_idx` (`Title_titleId` ASC),
  CONSTRAINT `fk_Employee_Title1`
    FOREIGN KEY (`Title_titleId`)
    REFERENCES `employeeportal-test`.`Title` (`titleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Employee_USstate1`
    FOREIGN KEY (`USstate_stateId`)
    REFERENCES `employeeportal-test`.`USstate` (`stateId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`ClientNote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`ClientNote` (
  `client_noteId` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATE NULL DEFAULT NULL,
  `care_time` DECIMAL(10,0) NULL DEFAULT NULL,
  `description` LONGTEXT NULL DEFAULT NULL,
  `comments` LONGTEXT NULL DEFAULT NULL,
  `Client_clientId` INT(11) NOT NULL,
  `Employee_employeeId` INT(11) NOT NULL,
  PRIMARY KEY (`client_noteId`),
  INDEX `fk_ClientNote_Client1_idx` (`Client_clientId` ASC),
  INDEX `fk_ClientNote_Employee1_idx` (`Employee_employeeId` ASC),
  CONSTRAINT `fk_ClientNote_Client1`
    FOREIGN KEY (`Client_clientId`)
    REFERENCES `employeeportal-test`.`Client` (`clientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClientNote_Employee1`
    FOREIGN KEY (`Employee_employeeId`)
    REFERENCES `employeeportal-test`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`EmployeeClient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`EmployeeClient` (
  `Employee_employeeId` INT(11) NOT NULL,
  `Client_clientId` INT(11) NOT NULL,
  INDEX `fk_EmployeeClient_Employee_idx` (`Employee_employeeId` ASC),
  INDEX `fk_EmployeeClient_Client1_idx` (`Client_clientId` ASC),
  CONSTRAINT `fk_EmployeeClient_Client1`
    FOREIGN KEY (`Client_clientId`)
    REFERENCES `employeeportal-test`.`Client` (`clientId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeClient_Employee`
    FOREIGN KEY (`Employee_employeeId`)
    REFERENCES `employeeportal-test`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `employeeportal-test`.`EmployeeRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employeeportal-test`.`EmployeeRole` (
  `employee_rolesId` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `Employee_employeeId` INT(11) NOT NULL,
  PRIMARY KEY (`employee_rolesId`),
  INDEX `fk_EmployeeRole_Employee1_idx` (`Employee_employeeId` ASC),
  CONSTRAINT `fk_EmployeeRole_Employee1`
    FOREIGN KEY (`Employee_employeeId`)
    REFERENCES `employeeportal-test`.`Employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
