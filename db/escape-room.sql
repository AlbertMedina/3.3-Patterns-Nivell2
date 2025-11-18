SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`escape_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`escape_room` ;

CREATE TABLE IF NOT EXISTS `mydb`.`escape_room` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`room` ;

CREATE TABLE IF NOT EXISTS `mydb`.`room` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `difficulty` ENUM('EASY', 'MEDIUM', 'HARD') NOT NULL,
  `price` DECIMAL NOT NULL,
  `escape_room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_escape_room_idx` (`escape_room_id` ASC) VISIBLE,
  CONSTRAINT `room_escape_room`
    FOREIGN KEY (`escape_room_id`)
    REFERENCES `mydb`.`escape_room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`decoration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`decoration` ;

CREATE TABLE IF NOT EXISTS `mydb`.`decoration` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `material` VARCHAR(100) NOT NULL,
  `value` DECIMAL NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `decoration_room_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `decoration_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`hint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`hint` ;

CREATE TABLE IF NOT EXISTS `mydb`.`hint` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `text` VARCHAR(400) NOT NULL,
  `value` DECIMAL NOT NULL,
  `room_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `hint_room_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `hint_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `surnames` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ticket` ;

CREATE TABLE IF NOT EXISTS `mydb`.`ticket` (
  `id` INT NOT NULL,
  `date` DATE NOT NULL,
  `price` DECIMAL NOT NULL,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `ticket_room_idx` (`room_id` ASC) VISIBLE,
  INDEX `ticket_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `ticket_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ticket_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reward` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reward` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `date` DATE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `reward_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `reward_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`certification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`certification` ;

CREATE TABLE IF NOT EXISTS `mydb`.`certification` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `date` DATE NOT NULL,
  `room_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `certification_room_idx` (`room_id` ASC) VISIBLE,
  INDEX `certification_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `certification_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `mydb`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `certification_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
