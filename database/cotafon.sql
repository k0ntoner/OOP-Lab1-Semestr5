SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `cotafon` DEFAULT CHARACTER SET utf8 ;
USE `cotafon` ;

CREATE TABLE IF NOT EXISTS `cotafon`.`users` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `username` VARCHAR(45) NOT NULL,
    `phone_number` VARCHAR(65) NOT NULL,
    `wallet` double default 0,
    `created_at` timestamp,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `cotafon`.`basic_tariffs` (
                                                         `id` INT NOT NULL AUTO_INCREMENT,
                                                         `name` VARCHAR(45) NOT NULL,
    `total_internet`  int Default NULL,
    `tariff_type` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `cotafon`.`contract_tariffs` (
                                                            `id` INT,
                                                            `total_messages_counts` int Default NULL,
                                                            `total_calls_time` Time Default NULL,
                                                            `price` double not null,
                                                            PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `cotafon`.`basic_tariffs`(`id`))
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `cotafon`.`prepaid_tariffs` (
                                                           `id` INT,
                                                           `messages_price` double Default NULL,
                                                           `calls_price` double Default NULL,
                                                           `internet_price` double Default null,
                                                           PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES `cotafon`.`basic_tariffs`(`id`))
    ENGINE = InnoDB;
Create table if not exists `cotafon`.`user_tariffs`(
                                                       `id` int not null AUTO_INCREMENT,
                                                       `user_id` INT Not NULL,
                                                       `tariff_id` int default null,
                                                       `rest_messages_counts` int Default NULL,
                                                       `rest_calls_time` Time Default NULL,
                                                       `rest_internet`  int Default NULL,
                                                       PRIMARY KEY (`id`),
    foreign key(`user_id`) references  `cotafon`.`users`(`id`) on delete cascade on update cascade,
    foreign key(`tariff_id`) references  `cotafon`.`basic_tariffs`(`id`) on delete cascade on update cascade)
    ENGINE = InnoDB;

Insert into users(username, phone_number,wallet,created_at) Values('user','+3806666666666',100, Now());
Insert into users(username, phone_number,wallet,created_at) Values('user','+3806666666667',100, Now());
Insert into users(username, phone_number,wallet,created_at) Values('user','+3806666666668',100, Now());

INSERT INTO basic_tariffs (name, total_internet, tariff_type) VALUES
                                                                  ('Contract Tariff 1', 5000, 'contract'),
                                                                  ('Contract Tariff 2', 7000, 'contract'),
                                                                  ('Contract Tariff 3', 10000, 'contract');


INSERT INTO basic_tariffs (name, total_internet, tariff_type) VALUES
                                                                  ('Prepaid Tariff 1', 3000, 'prepaid'),
                                                                  ('Prepaid Tariff 2', 4000, 'prepaid'),
                                                                  ('Prepaid Tariff 3', 8000, 'prepaid');

INSERT INTO contract_tariffs (id, total_messages_counts, total_calls_time, price) VALUES
                                                                                      (1, 1000, '01:30:00', 200),
                                                                                      (2, 1500, '02:00:00', 300),
                                                                                      (3, 2000, '03:00:00', 400);

INSERT INTO prepaid_tariffs (id, messages_price, calls_price, internet_price) VALUES
                                                                                  (4, 0.10, 0.20, 0.05),
                                                                                  (5, 0.15, 0.25, 0.08),
                                                                                  (6, 0.20, 0.30, 0.10);

INSERT INTO user_tariffs (user_id, tariff_id, rest_messages_counts, rest_calls_time, rest_internet) VALUES
                                                                                                        (1, 1, 500, '00:30:00', 2000),
                                                                                                        (2, 4, NULL, NULL, 1000),
                                                                                                        (3, 2, 800, '01:00:00', 3500);

