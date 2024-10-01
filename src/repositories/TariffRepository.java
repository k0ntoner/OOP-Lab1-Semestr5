package repositories;

import configs.DatabaseConnection;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffRepository {
    private final ContractTariffRepository contractTariffRepository;
    private final PrepaidTariffRepository prepaidTariffRepository;

    public TariffRepository(ContractTariffRepository contractTariffRepository, PrepaidTariffRepository prepaidTariffRepository) {
        this.contractTariffRepository = contractTariffRepository;
        this.prepaidTariffRepository = prepaidTariffRepository;
    }

    public List<User> getAllUsersWithTariff(){
        String query= "Select * " +
                "From contract_tariffs " +
                "Join users on users.id=user_tariffs.user_id" ;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                User user= new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                if(resultSet.getInt("contract_tariffs_id")!=0){
                    user.setTariff(contractTariffRepository.getContractTariffByUserId(user.getId()));
                }
                else{
                    user.setTariff(prepaidTariffRepository.getPrepaidTariffByUserId(user.getId()));
                }
                user.getCreationDate(resultSet.getDate("created_at"));
                users.add(user);
            }
            return users;
        }
        catch(SQLException e){
            return null;
        }
    }
    public ContractTariff findTariffByParameters(int totalMessagesCounts, Time totalCallsTime, int totalInternet,double price){
        String query= "Select * " +
                "From contract_tariffs " +
                "Where total_messages_counts= " +totalMessagesCounts+
                " and total_calls_time="+totalCallsTime+
                " and total_internet="+totalInternet+
                " and price="+price;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ContractTariff contractTariff = new ContractTariff();
            if (resultSet.next()) {
                contractTariff.setId(resultSet.getInt("id"));
                contractTariff.setName(resultSet.getString("name"));
                contractTariff.setTotalMessagesCounts(resultSet.getInt("total_messages_counts"));
                contractTariff.setTotalCallsTime(resultSet.getTime("total_calls_time"));
                contractTariff.setTotalInternet(resultSet.getInt("total_internet"));
                contractTariff.setPrice(resultSet.getDouble("price"));
            }
            return contractTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public PrepaidTariff findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double InternetPrice){
        String query= "Select * " +
                "From contract_tariffs " +
                "Where messages_price= " +messagesPrice+
                " and calls_price="+callsPrice+
                " and total_internet="+totalInternet+
                " and internet_price="+InternetPrice;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            PrepaidTariff prepaidTariff = new PrepaidTariff();
            if (resultSet.next()) {
                prepaidTariff.setId(resultSet.getInt("id"));
                prepaidTariff.setName(resultSet.getString("name"));
                prepaidTariff.setTotalInternet(resultSet.getInt("total_internet"));
                prepaidTariff.setMessagesPrice(resultSet.getDouble("messages_price"));
                prepaidTariff.setCallsPrice(resultSet.getDouble("calls_price"));
                prepaidTariff.setInternetPrice(resultSet.getDouble("internet_price"));

            }
            return prepaidTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
}
