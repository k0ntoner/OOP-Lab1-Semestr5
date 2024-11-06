package repositories;

import configs.DatabaseTestConnection;
import models.User;

import java.sql.*;

public class UserRepository {

    public static User getUserById(int user_id) {
        String query= "Select * " +
                "From users " +

                "Where users.id="+user_id;
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));

                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static User getUserWithTariffById(int user_id) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join users on users.id=user_tariffs.user_id "+
                "Where user_tariffs.user_id="+user_id;
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                user.setTariff(TariffRepository.getTariffById(resultSet.getInt("tariff_id")));
                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static User getUserWithTariffByPhoneNumber(String phoneNumber) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join users on users.id=user_tariffs.user_id "+
                "Where users.phone_number='"+phoneNumber+"'";
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                user.setTariff(TariffRepository.getTariffById(resultSet.getInt("tariff_id")));
                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static User getUserByPhoneNumber(String phoneNumber) {
        String query= "Select * " +
                "From users " +

                "Where users.phone_number='"+phoneNumber+"'";
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));

                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static User addUser(User user) {
        String query="Insert into users(username, phone_number, wallet, created_at) " +
                "Values('"+user.getUsername()+"','"+user.getPhoneNumber()+"',"+user.getWallet()+", Now() )";
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            int rowAffected= statement.executeUpdate();
            if(rowAffected>0){
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        return user;
                    }
                    return null;
                }
                catch (SQLException e) {
                    return null;
                }
            }
            else{
                return null;
            }

        }
        catch(SQLException e){
            return null;
        }
    }
}
