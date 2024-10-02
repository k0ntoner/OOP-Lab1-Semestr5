package services;

import configs.DatabaseConnection;
import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;
import repositories.TariffRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffService {
    private final TariffRepository tariffRepository;
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }
    public List<User> getAllUsersWithTariff(){
        return tariffRepository.getAllUsersWithTariff();
    }
    public ContractTariff findTariffByParameters(int totalMessagesCounts, Time totalCallsTime, int totalInternet, double price){
        return tariffRepository.findTariffByParameters(totalMessagesCounts, totalCallsTime, totalInternet, price);
    }
    public PrepaidTariff findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double InternetPrice){
        return tariffRepository.findTariffByParameters(messagesPrice, callsPrice, totalInternet, InternetPrice);
    }
    public Tariff addTariff(Tariff tariff){
        return tariffRepository.addTariff(tariff);
    }
    public User addTariffForUser(User user, Tariff tariff){
        return tariffRepository.addTariffForUser(user, tariff);
    }
}
