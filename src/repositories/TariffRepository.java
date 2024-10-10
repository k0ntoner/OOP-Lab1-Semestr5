package repositories;

import configs.DatabaseConnection;
import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffRepository {
    private final ContractTariffRepository contractTariffRepository;
    private final PrepaidTariffRepository prepaidTariffRepository;
    private final UserRepository userRepository;
    public TariffRepository(ContractTariffRepository contractTariffRepository, PrepaidTariffRepository prepaidTariffRepository, UserRepository userRepository) {
        this.contractTariffRepository = contractTariffRepository;
        this.prepaidTariffRepository = prepaidTariffRepository;
        this.userRepository = userRepository;
    }
    public User addTariffForUser(User user, Tariff tariff) {
        String query;
        if (tariff instanceof ContractTariff){
            query = "Insert into user_tariffs (user_id, contract_tariffs_id) " +
                    "Values(?,?)";

        }
        else if (tariff instanceof PrepaidTariff){
            query = "Insert into user_tariffs (user_id, prepaid_tariffs_id) " +
                    "Values(?,?)";
        }
        else{
            return null;
        }
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,  user.getId());
            statement.setInt(2,  tariff.getId());
            int rowAffected= statement.executeUpdate();
            if(rowAffected>0){
                return userRepository.getUserWithTariffById(user.getId());
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }

    }

    public List<User> getAllUsersWithTariff(){
        String query= "Select * " +
                "From user_tariffs " +
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
                " and total_calls_time= '"+totalCallsTime+"'"+
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
            else{
                return null;
            }
            return contractTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public PrepaidTariff findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double InternetPrice){
        String query= "Select * " +
                "From prepaid_tariffs " +
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
            else{
                return null;
            }
            return prepaidTariff;
        }
        catch(SQLException e){
            return null;
        }
    }
    public Tariff getTariffByName(String name){
        String queryContract= "Select * " +
                "From contract_tariffs " +
                "Where name= '" +name+"'";
        String queryPrepaid= "Select * " +
                "From prepaid_tariffs " +
                "Where name= '" +name+"'";
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(queryPrepaid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PrepaidTariff prepaidTariff = new PrepaidTariff();
                prepaidTariff.setId(resultSet.getInt("id"));
                prepaidTariff.setName(resultSet.getString("name"));
                prepaidTariff.setTotalInternet(resultSet.getInt("total_internet"));
                prepaidTariff.setMessagesPrice(resultSet.getDouble("messages_price"));
                prepaidTariff.setCallsPrice(resultSet.getDouble("calls_price"));
                prepaidTariff.setInternetPrice(resultSet.getDouble("internet_price"));
                return prepaidTariff;
            }
            statement = connection.prepareStatement(queryContract);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ContractTariff contractTariff = new ContractTariff();
                contractTariff.setId(resultSet.getInt("id"));
                contractTariff.setName(resultSet.getString("name"));
                contractTariff.setTotalMessagesCounts(resultSet.getInt("total_messages_counts"));
                contractTariff.setTotalCallsTime(resultSet.getTime("total_calls_time"));
                contractTariff.setTotalInternet(resultSet.getInt("total_internet"));
                contractTariff.setPrice(resultSet.getDouble("price"));
                return contractTariff;
            }
            return null;
        }
        catch(SQLException e){
            return null;
        }
    }

    public Tariff addTariff(Tariff tariff){
        String query;
        if (tariff instanceof ContractTariff){
            ContractTariff contractTariff = (ContractTariff) tariff;
            query = "Insert into contract_tariffs (name, total_messages_counts, total_calls_time, total_internet, price) " +
                    "Values('"+contractTariff.getName()+"',"+contractTariff.getTotalMessagesCounts()+",'"+contractTariff.getTotalCallsTime()+"',"+contractTariff.getTotalInternet()+","+contractTariff.getPrice()+")";

        }
        else if (tariff instanceof PrepaidTariff){
            PrepaidTariff prepaidTariff = (PrepaidTariff) tariff;
            query = "Insert into prepaid_tariffs (name, messages_price, calls_price, total_internet, internet_price) " +
                    "Values('"+prepaidTariff.getName()+"',"+prepaidTariff.getMessagesPrice()+","+prepaidTariff.getCallsPrice()+","+prepaidTariff.getTotalInternet()+","+prepaidTariff.getInternetPrice()+")";
        }
        else{
            return null;
        }
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            int rowAffected= statement.executeUpdate();
            if(rowAffected>0){
                return getTariffByName(tariff.getName());
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }
    private List<Tariff> getAllContractTariff(){
        String query= "Select * " +
                "From contract_tariffs ";

        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Tariff> tariffs = new ArrayList<>();

            while (resultSet.next()) {
                ContractTariff contractTariff = new ContractTariff();
                contractTariff.setId(resultSet.getInt("id"));
                contractTariff.setName(resultSet.getString("name"));
                contractTariff.setTotalMessagesCounts(resultSet.getInt("total_messages_counts"));
                contractTariff.setTotalCallsTime(resultSet.getTime("total_calls_time"));
                contractTariff.setTotalInternet(resultSet.getInt("total_internet"));
                contractTariff.setPrice(resultSet.getDouble("price"));
                tariffs.add(contractTariff);

            }
            return tariffs;
        }
        catch(SQLException e){
            return null;
        }
    }
    private List<Tariff> getAllPrepaidTariff(){
        String query= "Select * " +
                "From prepaid_tariffs ";

        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Tariff> tariffs = new ArrayList<>();

            while (resultSet.next()) {
                PrepaidTariff prepaidTariff = new PrepaidTariff();
                prepaidTariff.setId(resultSet.getInt("id"));
                prepaidTariff.setName(resultSet.getString("name"));
                prepaidTariff.setTotalInternet(resultSet.getInt("total_internet"));
                prepaidTariff.setMessagesPrice(resultSet.getDouble("messages_price"));
                prepaidTariff.setCallsPrice(resultSet.getDouble("calls_price"));
                prepaidTariff.setInternetPrice(resultSet.getDouble("internet_price"));
                tariffs.add(prepaidTariff);

            }
            return tariffs;
        }
        catch(SQLException e){
            return null;
        }
    }
    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        try{
            if(getAllContractTariff()!=null){
                tariffs.addAll( getAllContractTariff() );
            }
            if(getAllPrepaidTariff()!=null){
                tariffs.addAll( getAllPrepaidTariff() );
            }
            return tariffs;
        }
        catch (Exception e){
            return null;
        }

    }
    public List<Tariff> sortTariffsByPrice(List<Tariff> tariffs){
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
