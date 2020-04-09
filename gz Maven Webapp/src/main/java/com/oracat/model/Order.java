package com.oracat.model;

public class Order {
    private String chengshi;

    public String getWldwname() {
        return wldwname;
    }

    public void setWldwname(String wldwname) {
        this.wldwname = wldwname;
    }

    private String wldwname;

    public String getOrder_type_name() {
        return order_type_name;
    }

    public void setOrder_type_name(String order_type_name) {
        this.order_type_name = order_type_name;
    }

    private String order_type_name;

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getOrder_pay_price() {
        return order_pay_price;
    }

    public void setOrder_pay_price(double order_pay_price) {
        this.order_pay_price = order_pay_price;
    }

    private int order_type;
    private int num;
    private double order_pay_price;

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    private  String ms ;


    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    private  double offline_price;

    public double getOffline_price() {
        return offline_price;
    }

    public void setOffline_price(double offline_price) {
        this.offline_price = offline_price;
    }

    public double getOnline_price() {
        return online_price;
    }

    public void setOnline_price(double online_price) {
        this.online_price = online_price;
    }

    private  double online_price;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    private String line ;

}
