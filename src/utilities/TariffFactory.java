package utilities;

import interfaces.Tariff;
import models.ContractTariff;
import models.PrepaidTariff;

import java.sql.Time;

public class TariffFactory {
    public static Tariff createTariff(int id, String name, int totalInternet, int totalMessagesCounts, Time totalCallsTime,double price) {
        return new ContractTariff(id,name,totalInternet,totalMessagesCounts,totalCallsTime,price);
    }
    public static Tariff createTariff(int id,String name, int totalInternet, double messagesPrice, double callsPrice, double internetPrice) {
        return new PrepaidTariff(id,name,totalInternet,messagesPrice,callsPrice,internetPrice);
    }
}
