package repositories;

import configs.DatabaseConnection;
import models.ContractTariff;
import models.PrepaidTariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrepaidTariffRepository {
    public PrepaidTariff getPrepaidTariffByUserId(int user_id) {
        String query= "Select * " +
                "From user_tariffs " +
                "Join prepaid_tariffs on prepaid_tariffs.id=user_tariffs.prepaid_tariffs_id "+
                "Where user_tariffs.user_id="+user_id;
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            PrepaidTariff prepaidTariff = new PrepaidTariff();
            if (resultSet.next()) {
                prepaidTariff.setId(resultSet.getInt("id"));
                prepaidTariff.setName(resultSet.getString("name"));
                prepaidTariff.setTotalInternet(resultSet.getInt("total_internet"));
                prepaidTariff.setRestInternet(resultSet.getInt("rest_internet"));
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
    public List<PrepaidTariff> getAllPrepaidTariffs() {
        String query= "Select * " +
                "From prepaid_tariffs ";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<PrepaidTariff> prepaidTariffs = new ArrayList<PrepaidTariff>();
            while (resultSet.next()) {
                PrepaidTariff prepaidTariff = new PrepaidTariff();
                prepaidTariff.setId(resultSet.getInt("id"));
                prepaidTariff.setName(resultSet.getString("name"));
                prepaidTariff.setTotalInternet(resultSet.getInt("total_internet"));
                prepaidTariff.setRestInternet(resultSet.getInt("rest_internet"));
                prepaidTariff.setMessagesPrice(resultSet.getDouble("messages_price"));
                prepaidTariff.setCallsPrice(resultSet.getDouble("calls_price"));
                prepaidTariff.setInternetPrice(resultSet.getDouble("internet_price"));
                prepaidTariffs.add(prepaidTariff);
            }
            return prepaidTariffs;
        }
        catch(SQLException e){
            return null;
        }
    }
}
