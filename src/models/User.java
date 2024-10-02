package models;


import interfaces.Tariff;

import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String phoneNumber;
    private double wallet;
    private Date creationDate;
    private Tariff tariff;
    public User() {

    }
    public User( String username, String phoneNumber, double wallet ) {

        this.username = username;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
    }
    public User(int id, String username, String phoneNumber, double wallet, Date creationDate, Tariff tariff) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
        this.creationDate = creationDate;
        this.tariff = tariff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Date getCreationDate(Date createdAt) {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
