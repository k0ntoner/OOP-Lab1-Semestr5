package repositories;

import configs.DatabaseConnection;
import models.User;

import java.sql.*;

public class UserRepository {
    private ContractTariffRepository contractTariffRepository;
    private PrepaidTariffRepository prepaidTariffRepository;
    public UserRepository(ContractTariffRepository contractTariffRepository, PrepaidTariffRepository prepaidTariffRepository) {
        this.contractTariffRepository = contractTariffRepository;
        this.prepaidTariffRepository = prepaidTariffRepository;
    }
    public User getUserById(int user_id) {
        String query= "Select * " +
                "From users " +

                "Where users.id="+user_id;
        try{
            Connection connection = DatabaseConnection.getConnection();
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
    public User getUserWithTariffById(int user_id) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join users on users.id=user_tariffs.user_id "+
                "Where user_tariffs.user_id="+user_id;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                if(resultSet.getInt("contract_tariffs_id")!=0){
                    user.setTariff(contractTariffRepository.getContractTariffByUserId(user_id));
                }
                else if(resultSet.getInt("prepaid_tariffs_id")!=0){
                    user.setTariff(prepaidTariffRepository.getPrepaidTariffByUserId(user_id));
                }
                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public User getUserWithTariffByPhoneNumber(String phoneNumber) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join users on users.id=user_tariffs.user_id "+
                "Where users.phone_number='"+phoneNumber+"'";
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                if(resultSet.getInt("contract_tariffs_id")!=0){
                    user.setTariff(contractTariffRepository.getContractTariffByUserId(user.getId()));
                }
                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
    public User getUserByPhoneNumber(String phoneNumber) {
        String query= "Select * " +
                "From users " +

                "Where users.phone_number='"+phoneNumber+"'";
        try{
            Connection connection = DatabaseConnection.getConnection();
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
    public User addUser(User user) {
        String query="Insert into users(username, phone_number, wallet, created_at) " +
                "Values('"+user.getUsername()+"','"+user.getPhoneNumber()+"',"+user.getWallet()+", Now() )";
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            int rowAffected= statement.executeUpdate();
            if(rowAffected>0){
                return getUserByPhoneNumber(user.getPhoneNumber());
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
