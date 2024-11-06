package services;

import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;
import models.User;
import repositories.TariffRepository;

import java.sql.*;
import java.util.List;

public class TariffService {

    public List<User> getAllUsersWithTariff(){
        return TariffRepository.getAllUsersWithTariff();
    }
    public ContractTariff findTariffByParameters(int totalMessagesCounts, Time totalCallsTime, int totalInternet, double price){
        return TariffRepository.findTariffByParameters(totalMessagesCounts, totalCallsTime, totalInternet, price);
    }
    public PrepaidTariff findTariffByParameters(double messagesPrice, double callsPrice, int totalInternet, double InternetPrice){
        return TariffRepository.findTariffByParameters(messagesPrice, callsPrice, totalInternet, InternetPrice);
    }
    public Tariff addTariff(Tariff tariff){
        return TariffRepository.addTariff(tariff);
    }
    public User addTariffForUser(User user, Tariff tariff){
        return TariffRepository.addTariffForUser(user, tariff);
    }
    public List<Tariff> getAllTariffs(){ return TariffRepository.getAllTariffs(); }
    public List<Tariff> getAllSortingTariffsByPrice(List<Tariff> tariffs){ return TariffRepository.sortTariffsByPrice(tariffs); }
}
