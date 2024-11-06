package models;

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

    public ContractTariff(String name, int totalInternet, int totalMessagesCounts, Time totalCallsTime,double price) {
        super(name, totalInternet);
        this.price = price;
        this.totalMessagesCounts = totalMessagesCounts;

        this.totalCallsTime = totalCallsTime;

    }
    public ContractTariff(int id,String name,int totalInternet, int totalMessagesCounts, Time totalCallsTime,   double price ) {
        super(id,name, totalInternet);
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

    @Override
    public String getInsertDerivedQuery() {
        return "INSERT INTO contract_tariffs (id, total_messages_counts, total_calls_time, price) VALUES (?, ?, ?, ?)";
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
        return "INSERT INTO basic_tariffs (name,  total_internet, tariff_type) VALUES (?,?,?)";
    }

    @Override
    public Object[] getInsertBasicParams() {
        return new Object[]{ name, totalInternet, "contract"};
    }

    @Override
    public Object[] getInsertDerivedParams() {
        return new Object[]{id, totalMessagesCounts, totalCallsTime, price};
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
