package models;

import interfaces.Tariff;

import java.sql.Time;

public class PrepaidTariff extends BasicTariff {
    protected double messagesPrice;
    protected double callsPrice;
    protected double internetPrice;
    public PrepaidTariff() {}

    public PrepaidTariff(int id, String name, int restInternet, int totalInternet, double messagesPrice, double callsPrice, double internetPrice) {
        super(id, name, restInternet, totalInternet);
        this.messagesPrice = messagesPrice;
        this.callsPrice = callsPrice;
        this.internetPrice = internetPrice;
    }

    public double getMessagesPrice() {
        return messagesPrice;
    }

    public void setMessagesPrice(double messagesPrice) {
        this.messagesPrice = messagesPrice;
    }

    public double getCallsPrice() {
        return callsPrice;
    }

    public void setCallsPrice(double callsPrice) {
        this.callsPrice = callsPrice;
    }

    public double getInternetPrice() {
        return internetPrice;
    }

    public void setInternetPrice(double internetPrice) {
        this.internetPrice = internetPrice;
    }
}
