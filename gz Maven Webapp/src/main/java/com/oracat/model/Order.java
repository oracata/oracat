package com.oracat.model;

public class Order {
    private String chengshi;
    private int offline;
    private int online;
    private double offlineprice;
    private double onlineprice;

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public int getOffline() {
        return offline;
    }

    public void setOffline(int offline) {
        this.offline = offline;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public double getOfflineprice() {
        return offlineprice;
    }

    public void setOfflineprice(double offlineprice) {
        this.offlineprice = offlineprice;
    }

    public double getOnlineprice() {
        return onlineprice;
    }

    public void setOnlineprice(double onlineprice) {
        this.onlineprice = onlineprice;
    }
}
