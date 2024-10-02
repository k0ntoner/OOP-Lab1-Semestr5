package repositories;

import configs.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseRepository {
    public void clearDatabase(){
        String[] queries = {
                "SET FOREIGN_KEY_CHECKS = 0;",
                "DROP TABLE IF EXISTS contract_tariffs;",
                "DROP TABLE IF EXISTS prepaid_tariffs;",
                "DROP TABLE IF EXISTS user_tariffs;",
                "DROP TABLE IF EXISTS users;",
                "SET FOREIGN_KEY_CHECKS = 1;"
        };
        try {
            Connection connection = DatabaseConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    public void fillDatabase(){
        String[] queries = {"CREATE TABLE IF NOT EXISTS `tariffsdb`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(45) NOT NULL,\n" +
                "  `phone_number` VARCHAR(65) NOT NULL,\n" +
                "  `wallet` double default 0,\n" +
                "  `created_at` timestamp,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;\n",



                "CREATE TABLE IF NOT EXISTS `tariffsdb`.`contract_tariffs` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `total_messages_counts` int Default NULL,\n" +
                "  `total_calls_time` Time Default NULL,\n" +
                "  `total_internet`  int Default NULL,\n" +
                "  `price` double not null,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;\n",

                "CREATE TABLE IF NOT EXISTS `tariffsdb`.`prepaid_tariffs` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `messages_price` double Default NULL,\n" +
                "  `calls_price` double Default NULL,\n" +
                "  `total_internet`  int Default NULL,\n" +
                "  `internet_price` double Default null,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;",

                "Create table if not exists `tariffsdb`.`user_tariffs`(\n" +
                        "  `id` int not null AUTO_INCREMENT,\n" +
                        "  `user_id` INT Not NULL,\n" +
                        "  `contract_tariffs_id` int default null,\n" +
                        "  `prepaid_tariffs_id` int default NULL,\n" +
                        "  `rest_messages_counts` int Default NULL,\n" +
                        "  `rest_calls_time` Time Default NULL,\n" +
                        "  `rest_internet`  int Default NULL,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  foreign key(`user_id`) references  `tariffsdb`.`users`(`id`) on delete cascade on update cascade,\n" +
                        "  foreign key(`contract_tariffs_id`) references  `tariffsdb`.`contract_tariffs`(`id`) on delete cascade on update cascade,\n" +
                        "  foreign key(`prepaid_tariffs_id`) references  `tariffsdb`.`prepaid_tariffs`(`id`) on delete cascade on update cascade)\n" +
                        "ENGINE = InnoDB;\n"
        };
        try {
            Connection connection = DatabaseConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){

        }

    }
    private void addTriggers(){
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
            Connection connection = DatabaseConnection.getConnection();

            for(String query : queries){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException e){

        }
    }
    public void RestartDatabase(){
        clearDatabase();
        fillDatabase();
        addTriggers();
    }


}
