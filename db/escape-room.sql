SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema escape_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `escape_db` ;

-- -----------------------------------------------------
-- Schema escape_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `escape_db` DEFAULT CHARACTER SET utf8 ;
USE `escape_db` ;

-- -----------------------------------------------------
-- Table `escape_db`.`escape_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`escape_room` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`escape_room` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`room` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`room` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `difficulty` ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `escape_room_id` INT NOT NULL,
  INDEX `room_escape_room_idx` (`escape_room_id` ASC) VISIBLE,
  CONSTRAINT `room_escape_room`
    FOREIGN KEY (`escape_room_id`)
    REFERENCES `escape_db`.`escape_room` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`decoration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`decoration` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`decoration` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `material` VARCHAR(100) NOT NULL,
  `value` DECIMAL(10,2) NOT NULL,
  `room_id` INT NOT NULL,
  INDEX `decoration_room_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `decoration_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `escape_db`.`room` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`hint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`hint` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`hint` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `text` VARCHAR(400) NOT NULL,
  `theme` VARCHAR(100) NOT NULL,
  `value` DECIMAL(10,2) NOT NULL,
  `room_id` INT NOT NULL,
  INDEX `hint_room_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `hint_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `escape_db`.`room` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`user` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`user` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `surnames` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`ticket` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`ticket` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `date` DATE NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `ticket_room_idx` (`room_id` ASC) VISIBLE,
  INDEX `ticket_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `ticket_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `escape_db`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ticket_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `escape_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`reward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`reward` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`reward` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `reward_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `reward_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `escape_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_db`.`certification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `escape_db`.`certification` ;

CREATE TABLE IF NOT EXISTS `escape_db`.`certification` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  INDEX `certification_room_idx` (`room_id` ASC) VISIBLE,
  INDEX `certification_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `certification_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `escape_db`.`room` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `certification_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `escape_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
