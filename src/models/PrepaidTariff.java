package models;

import interfaces.Tariff;

import java.sql.Time;

public class PrepaidTariff extends BasicTariff {
    protected double messagesPrice;
    protected double callsPrice;
    protected double internetPrice;
    public PrepaidTariff() {}
    public PrepaidTariff(String name, int totalInternet, double messagesPrice, double callsPrice, double internetPrice) {
        super(name,  totalInternet);
        this.messagesPrice = messagesPrice;
        this.callsPrice = callsPrice;
        this.internetPrice = internetPrice;
    }
    public PrepaidTariff(int id,String name, int totalInternet, double messagesPrice, double callsPrice, double internetPrice) {
        super(id,name,  totalInternet);
        this.messagesPrice = messagesPrice;
        this.callsPrice = callsPrice;
        this.internetPrice = internetPrice;
    }
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

    @Override
    public double getPrice() {
        return messagesPrice + callsPrice + internetPrice;
    }

    @Override
    public String getInsertDerivedQuery() {
        return "INSERT INTO prepaid_tariffs (id, messages_price, calls_price, internet_price) VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getFindByNameQuery() {
        return "";
    }

    @Override
    public String getFindByIdQuery() {
        return "";
    }

    @Override
    public String getInsertBasicQuery() {
        return "INSERT INTO basic_tariffs (name, total_internet, tariff_type) VALUES (?, ?, ?)";
    }

    @Override
    public Object[] getInsertBasicParams() {
        return new Object[]{ name, totalInternet, "prepaid"};
    }

    @Override
    public Object[] getInsertDerivedParams() {
        return new Object[]{id, messagesPrice, callsPrice, internetPrice};
    }
}
