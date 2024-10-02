SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `tariffsdb` DEFAULT CHARACTER SET utf8 ;
USE `tariffsdb` ;

CREATE TABLE IF NOT EXISTS `tariffsdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(65) NOT NULL,
  `wallet` double default 0,
  `created_at` timestamp,
  
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `tariffsdb`.`contract_tariffs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `total_messages_counts` int Default NULL,
  `total_calls_time` Time Default NULL,
  `total_internet`  int Default NULL,
  `price` double not null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `tariffsdb`.`prepaid_tariffs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `messages_price` double Default NULL,
  `calls_price` double Default NULL,
  `total_internet`  int Default NULL,
  `internet_price` double Default null,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
Create table if not exists `tariffsdb`.`user_tariffs`(
  `id` int not null AUTO_INCREMENT,
  `user_id` INT Not NULL,
  `contract_tariffs_id` int default null,
  `prepaid_tariffs_id` int default NULL,
  `rest_messages_counts` int Default NULL,
  `rest_calls_time` Time Default NULL,
  `rest_internet`  int Default NULL,
  PRIMARY KEY (`id`),
  foreign key(`user_id`) references  `tariffsdb`.`users`(`id`) on delete cascade on update cascade,
  foreign key(`contract_tariffs_id`) references  `tariffsdb`.`contract_tariffs`(`id`) on delete cascade on update cascade,
  foreign key(`prepaid_tariffs_id`) references  `tariffsdb`.`prepaid_tariffs`(`id`) on delete cascade on update cascade)
ENGINE = InnoDB;





Insert into `tariffsdb`.`users`(username, phone_number, wallet, created_at)
Values('usertest', 3808888888888, 0, Now());

insert into `tariffsdb`.`contract_tariffs`(name, total_messages_counts, total_calls_time, total_internet, price)
Values('tariffTest', 5, '00:05:00', 8000, 320);

insert into `tariffsdb`.`user_tariffs`(user_id, contract_tariffs_id, rest_messages_counts, rest_calls_time, rest_internet)
Values(1, 1, 5,'00:05:00', 8000);

