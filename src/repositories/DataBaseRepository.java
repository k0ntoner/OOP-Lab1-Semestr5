package repositories;

import configs.DatabaseTestConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseRepository {
    public static void clearDatabase(){
        String[] queries = {
                "SET FOREIGN_KEY_CHECKS = 0;",
                "DROP TABLE IF EXISTS contract_tariffs;",
                "DROP TABLE IF EXISTS prepaid_tariffs;",
                "DROP TABLE IF EXISTS basic_tariffs;",
                "DROP TABLE IF EXISTS user_tariffs;",
                "DROP TABLE IF EXISTS users;",
                "SET FOREIGN_KEY_CHECKS = 1;"
        };
        try {
            Connection connection = DatabaseTestConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public  static void fillDatabase(){
        String[] queries = {"CREATE TABLE IF NOT EXISTS `tariffsdb`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(45) NOT NULL,\n" +
                "  `phone_number` VARCHAR(65) NOT NULL,\n" +
                "  `wallet` double default 0,\n" +
                "  `created_at` timestamp,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;\n" ,
                "CREATE TABLE IF NOT EXISTS `tariffsdb`.`basic_tariffs` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `total_internet`  int Default NULL,\n" +
                "  `tariff_type` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;\n" +
                "\n" ,
                "CREATE TABLE IF NOT EXISTS `tariffsdb`.`contract_tariffs` (\n" +
                "  `id` INT,\n" +
                "  `total_messages_counts` int Default NULL,\n" +
                "  `total_calls_time` Time Default NULL,\n" +
                "  `price` double not null,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  FOREIGN KEY (`id`) REFERENCES `tariffsdb`.`basic_tariffs`(`id`))\n" +
                "ENGINE = InnoDB;\n" +
                "\n" ,
                "CREATE TABLE IF NOT EXISTS `tariffsdb`.`prepaid_tariffs` (\n" +
                "  `id` INT,\n" +
                "  `messages_price` double Default NULL,\n" +
                "  `calls_price` double Default NULL,\n" +
                "  `internet_price` double Default null,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  FOREIGN KEY (`id`) REFERENCES `tariffsdb`.`basic_tariffs`(`id`))\n" +
                "ENGINE = InnoDB;\n" ,
                "Create table if not exists `tariffsdb`.`user_tariffs`(\n" +
                "  `id` int not null AUTO_INCREMENT,\n" +
                "  `user_id` INT Not NULL,\n" +
                "  `tariff_id` int default null,\n" +
                "  `rest_messages_counts` int Default NULL,\n" +
                "  `rest_calls_time` Time Default NULL,\n" +
                "  `rest_internet`  int Default NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  foreign key(`user_id`) references  `tariffsdb`.`users`(`id`) on delete cascade on update cascade,\n" +
                "  foreign key(`tariff_id`) references  `tariffsdb`.`basic_tariffs`(`id`) on delete cascade on update cascade)\n" +
                "ENGINE = InnoDB;\n"
        };
        try {
            Connection connection = DatabaseTestConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){

        }

    }
    private static void addTriggers(){
        String queries[]={"DROP TRIGGER IF EXISTS after_insert_user_tariffs;\n",
                "DELIMITER //\n" +
                "Create trigger after_insert_user_tariffs\n" +
                "After update on `tariffsdb`.`user_tariffs`\n" +
                "For each row\n" +
                "Begin\n" +
                "\tDeclare messages_counts int ;\n" +
                "    Declare calls_time Time ;\n" +
                "    Declare internet  int;\n" +
                "    \n" +
                "    Select total_messages_counts, total_calls_time, total_internet into messages_counts, calls_time, internet\n" +
                "    From user_tariffs \n" +
                "    Join contract_tariffs on contract_tariffs.id=user_tariffs.contract_tariffs_id\n" +
                "    Join prepaid_tariffs on prepaid_tariffs.id=user_tariffs.prepaid_tariffs_id\n" +
                "    Where user_tariffs.id=NEw.id;\n" +
                "    \n" +
                "    Update user_tariffs as ut\n" +
                "    Set  ut.rest_messages_counts=messages_counts, ut.rest_calls_time=calls_time, ut.rest_internet=internet\n" +
                "    Where user_tariffs.id=New.id;\n" +
                "    \n" +
                "    \n" +
                "\t\n" +
                "End //\n" +
                "DELIMITER ;"
        };
        try {
            Connection connection = DatabaseTestConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){

        }
    }
    public static void RestartDatabase(){
        clearDatabase();
        fillDatabase();
    }


}
