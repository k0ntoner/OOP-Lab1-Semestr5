package models;

import java.sql.Date;
import java.sql.Time;

public class ContractTariff extends BasicTariff{

    protected int totalMessagesCounts;
    protected int restMessagesCounts;
    protected Time totalCallsTime;
    protected Time restCallsTime;
    protected double price;
    public ContractTariff() {
        super();
    }

    public ContractTariff(String name, double price, int totalMessagesCounts, Time totalCallsTime,   int totalInternet) {
        super(name, totalInternet);
        this.price = price;
        this.totalMessagesCounts = totalMessagesCounts;

        this.totalCallsTime = totalCallsTime;

    }
    public ContractTariff(int id,String name, double price, int totalMessagesCounts, int restMessagesCounts, Time totalCallsTime, Time restCallsTime, int restInternet, int totalInternet) {
        super(id,name, restInternet, totalInternet);
        this.price = price;
        this.totalMessagesCounts = totalMessagesCounts;
        this.restMessagesCounts = restMessagesCounts;
        this.totalCallsTime = totalCallsTime;
        this.restCallsTime = restCallsTime;
    }

    public int getTotalMessagesCounts() {
        return totalMessagesCounts;
    }

    public void setTotalMessagesCounts(int totalMessagesCounts) {
        this.totalMessagesCounts = totalMessagesCounts;
    }

    public int getRestMessagesCounts() {
        return restMessagesCounts;
    }

    public void setRestMessagesCounts(int restMessagesCounts) {
        this.restMessagesCounts = restMessagesCounts;
    }

    public Time getTotalCallsTime() {
        return totalCallsTime;
    }

    public void setTotalCallsTime(Time totalCallsTime) {
        this.totalCallsTime = totalCallsTime;
    }

    public Time getRestCallsTime() {
        return restCallsTime;
    }

    public void setRestCallsTime(Time restCallsTime) {
        this.restCallsTime = restCallsTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
