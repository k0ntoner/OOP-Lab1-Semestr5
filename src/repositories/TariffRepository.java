package repositories;

import configs.DatabaseTestConnection;
import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;
import utilities.TariffFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffRepository {

    public static User addTariffForUser(User user, Tariff tariff) {
        String query= "Insert into user_tariffs (user_id, tariff_id) " +
                "Values(?,?)";

        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,  user.getId());
            statement.setInt(2,  tariff.getId());
            int rowAffected= statement.executeUpdate();
            if(rowAffected>0){
                return UserRepository.getUserWithTariffById(user.getId());
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }

    }

    public static List<User> getAllUsersWithTariff(){
        String query= "Select * " +
                "From user_tariffs " +
                "Join users on users.id=user_tariffs.user_id" ;

        try {
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                User user= new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setWallet(resultSet.getInt("wallet"));
                user.setTariff(TariffRepository.getTariffById(resultSet.getInt("tariff_id")));
                user.getCreationDate(resultSet.getDate("created_at"));
                users.add(user);
            }
            return users;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static ContractTariff findTariffByParameters(int totalMessagesCounts, Time totalCallsTime, int totalInternet,double price){
        String query= "Select * " +
                "From basic_tariffs " +
                "Left Join contract_tariffs on contract_tariffs.id=basic_tariffs.id " +
                "Where total_messages_counts= " +totalMessagesCounts+
                " and total_calls_time= '"+totalCallsTime+"'"+
                " and total_internet="+totalInternet+
                " and price="+price;
        try {
            Connection connection = DatabaseTestConnection.getConnection();
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
            else{
                return null;
            }
            return contractTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static PrepaidTariff findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double InternetPrice){
        String query= "Select * " +
                "From basic_tariffs " +
                "Left Join prepaid_tariffs on prepaid_tariffs.id=basic_tariffs.id " +
                "Where messages_price= " +messagesPrice+
                " and calls_price="+callsPrice+
                " and total_internet="+totalInternet+
                " and internet_price="+InternetPrice;
        try{
            Connection connection = DatabaseTestConnection.getConnection();
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
            else{
                return null;
            }
            return prepaidTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public static Tariff getTariffById(int id){
        String query= "Select * " +
                "From basic_tariffs " +
                "Left Join contract_tariffs on contract_tariffs.id=basic_tariffs.id " +
                "Left Join prepaid_tariffs on prepaid_tariffs.id=basic_tariffs.id "+
                "Where basic_tariffs.id="+ id;
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt("id");
                String tariffType = resultSet.getString("tariff_type");
                String tariffName = resultSet.getString("name");
                int totalInternet = resultSet.getInt("total_internet");
                switch(tariffType.toLowerCase()){
                    case "prepaid":
                        double messagesPrice = resultSet.getDouble("messages_price");
                        double callsPrice = resultSet.getDouble("calls_price");
                        double internetPrice = resultSet.getDouble("internet_price");
                        return TariffFactory.createTariff(id,tariffName,totalInternet,messagesPrice,callsPrice,internetPrice);

                    case "contract":
                        int totalMessagesCounts = resultSet.getInt("total_messages_counts");
                        Time totalCallsTime = resultSet.getTime("total_calls_time");
                        double price = resultSet.getDouble("price");
                        return TariffFactory.createTariff(id,tariffName,totalInternet,totalMessagesCounts,totalCallsTime,price);
                    default:
                        throw new IllegalArgumentException("Unknown tariff type: " + tariffType);
                }
            }
            return null;

        }
        catch(SQLException e){
            return null;
        }
    }
    public static Tariff getTariffByName(String name){
        String query= "Select * " +
                "From basic_tariffs " +
                "Left Join contract_tariffs on contract_tariffs.id=basic_tariffs.id " +
                "Left Join prepaid_tariffs on prepaid_tariffs.id=basic_tariffs.id "+
                "Where name= '" +name+"'";
        try{
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String tariffType = resultSet.getString("tariff_type");
                String tariffName = resultSet.getString("name");
                int totalInternet = resultSet.getInt("total_internet");
                switch(tariffType.toLowerCase()){
                    case "prepaid":
                        double messagesPrice = resultSet.getDouble("messages_price");
                        double callsPrice = resultSet.getDouble("calls_price");
                        double internetPrice = resultSet.getDouble("internet_price");
                        return TariffFactory.createTariff(id,tariffName,totalInternet,messagesPrice,callsPrice,internetPrice);

                    case "contract":
                        int totalMessagesCounts = resultSet.getInt("total_messages_counts");
                        Time totalCallsTime = resultSet.getTime("total_calls_time");
                        double price = resultSet.getDouble("price");
                        return TariffFactory.createTariff(id,tariffName,totalInternet,totalMessagesCounts,totalCallsTime,price);
                    default:
                        throw new IllegalArgumentException("Unknown tariff type: " + tariffType);
                }
            }
            return null;

        }
        catch(SQLException e){
            return null;
        }
    }

    public static Tariff addTariff(Tariff tariff){

        try{
            Connection connection = DatabaseTestConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement basicStatement = connection.prepareStatement(
                    tariff.getInsertBasicQuery(),
                    Statement.RETURN_GENERATED_KEYS
            );
            Object[] basicParams = tariff.getInsertBasicParams();
            for (int i = 0; i < basicParams.length; i++) {
                basicStatement.setObject(i + 1, basicParams[i]);
            }
            int rowAffected= basicStatement.executeUpdate();
            if(rowAffected==0){
                connection.rollback();
                return null;
            }
            ResultSet generatedKeys = basicStatement.getGeneratedKeys();
            if(!generatedKeys.next()){
                connection.rollback();
                return null;
            }
            tariff.setId(generatedKeys.getInt(1));
            PreparedStatement derivedStatement = connection.prepareStatement(
                    tariff.getInsertDerivedQuery()

            );
            Object[] derivedParams = tariff.getInsertDerivedParams();
            for (int i = 0; i < derivedParams.length; i++) {
                derivedStatement.setObject(i + 1, derivedParams[i]);
            }
            rowAffected = derivedStatement.executeUpdate();
            if(rowAffected==0){
                connection.rollback();
                return null;
            }
            connection.commit();
            return getTariffByName(tariff.getName());
        }
        catch (Exception e){
            return null;
        }
    }

    public static List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        try{
           String query= "Select * " +
                   "From basic_tariffs "+
                   "Left Join contract_tariffs on contract_tariffs.id=basic_tariffs.id " +
                   "Left Join prepaid_tariffs on prepaid_tariffs.id=basic_tariffs.id ";
            Connection connection = DatabaseTestConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String tariffType = resultSet.getString("tariff_type");
                String tariffName = resultSet.getString("name");
                int totalInternet = resultSet.getInt("total_internet");
                switch(tariffType.toLowerCase()){
                    case "prepaid":
                        double messagesPrice = resultSet.getDouble("messages_price");
                        double callsPrice = resultSet.getDouble("calls_price");
                        double internetPrice = resultSet.getDouble("internet_price");
                        tariffs.add( TariffFactory.createTariff(id,tariffName,totalInternet,messagesPrice,callsPrice,internetPrice));
                        break;
                    case "contract":
                        int totalMessagesCounts = resultSet.getInt("total_messages_counts");
                        Time totalCallsTime = resultSet.getTime("total_calls_time");
                        double price = resultSet.getDouble("price");
                        tariffs.add( TariffFactory.createTariff(id,tariffName,totalInternet,totalMessagesCounts,totalCallsTime,price));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown tariff type: " + tariffType);
                }
            }
            return tariffs;
        }
        catch (Exception e){
            return null;
        }

    }
    public static List<Tariff> sortTariffsByPrice(List<Tariff> tariffs){
        for(int i=0; i<tariffs.size()-1; i++){
            Tariff smallestTariff = tariffs.get(i);
            for(int j=i+1; j<tariffs.size(); j++){
                Tariff tariff = tariffs.get(j);
                if(tariff.getPrice()<smallestTariff.getPrice()){
                    Tariff mix=smallestTariff;
                    smallestTariff=tariff;
                    tariffs.set(j,mix);
                }
            }
            tariffs.set(i,smallestTariff);
        }
        return tariffs;
    }
}
