package repositories;

import configs.DatabaseConnection;
import models.User;

import java.sql.*;

public class UserRepository {
    private ContractTariffRepository contractTariffRepository;
    public UserRepository(ContractTariffRepository contractTariffRepository) {
        this.contractTariffRepository = contractTariffRepository;
    }
    public User getUserById(int user_id) {
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
                user.getCreationDate(resultSet.getDate("created_at"));
            }
            return user;
        }
        catch(SQLException e){
            return null;
        }
    }
}
