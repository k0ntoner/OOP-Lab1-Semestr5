package models;

import interfaces.Tariff;

import java.sql.Time;

public abstract class BasicTariff implements Tariff {
    protected int id;
    protected String name;
    protected int restInternet;
    protected int totalInternet;


    public BasicTariff() {}
    public BasicTariff( String name, int totalInternet) {
        this.name = name;
        this.totalInternet = totalInternet;

    }
    public BasicTariff(int id, String name,  int restInternet, int totalInternet) {
        this.name = name;
        this.restInternet = restInternet;
        this.totalInternet = totalInternet;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestInternet() {
        return restInternet;
    }

    public void setRestInternet(int restInternet) {
        this.restInternet = restInternet;
    }

    public int getTotalInternet() {
        return totalInternet;
    }

    public void setTotalInternet(int totalInternet) {
        this.totalInternet = totalInternet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
