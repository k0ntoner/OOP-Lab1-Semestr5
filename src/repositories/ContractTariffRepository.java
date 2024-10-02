package repositories;

import configs.DatabaseConnection;
import models.BasicTariff;
import models.ContractTariff;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractTariffRepository {
    public ContractTariff getContractTariffByUserId(int user_id) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join contract_tariffs on contract_tariffs.id=user_tariffs.contract_tariffs_id "+
                "Where user_tariffs.user_id="+user_id;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ContractTariff contractTariff = new ContractTariff();
            if (resultSet.next()) {
                contractTariff.setId(resultSet.getInt("id"));
                contractTariff.setName(resultSet.getString("name"));
                contractTariff.setTotalMessagesCounts(resultSet.getInt("total_messages_counts"));
                contractTariff.setRestMessagesCounts(resultSet.getInt("rest_messages_counts"));
                contractTariff.setTotalCallsTime(resultSet.getTime("total_calls_time"));
                contractTariff.setRestCallsTime(resultSet.getTime("rest_calls_time"));
                contractTariff.setTotalInternet(resultSet.getInt("total_internet"));
                contractTariff.setRestInternet(resultSet.getInt("rest_internet"));
                contractTariff.setPrice(resultSet.getDouble("price"));

            }

            return contractTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public List<ContractTariff> getAllContractTariffs() {
        String query= "Select * " +
                "From contract_tariffs ";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<ContractTariff> contractTariffs = new ArrayList<ContractTariff>();
            while (resultSet.next()) {
                ContractTariff contractTariff = new ContractTariff();
                contractTariff.setId(resultSet.getInt("id"));
                contractTariff.setName(resultSet.getString("name"));
                contractTariff.setTotalMessagesCounts(resultSet.getInt("total_messages_counts"));
                contractTariff.setRestMessagesCounts(resultSet.getInt("rest_messages_counts"));
                contractTariff.setTotalCallsTime(resultSet.getTime("total_calls_time"));
                contractTariff.setRestCallsTime(resultSet.getTime("rest_calls_time"));
                contractTariff.setTotalInternet(resultSet.getInt("total_internet"));
                contractTariff.setRestInternet(resultSet.getInt("rest_internet"));
                contractTariff.setPrice(resultSet.getDouble("price"));
                contractTariffs.add(contractTariff);
            }
            return contractTariffs;
        }
        catch(SQLException e){
            return null;
        }
    }
}
