package com.oracat.model;

public class ReportMonth {
    private String rq                ;
    private double hsje              ;
    private String begin_date              ;
    private String end_date                ;


    private double cankml            ;
    private double cankmll           ;
    private int cust_num          ;
    private int login_num         ;
    private int pay_cust          ;
    private int not_pay_cust      ;
    private double not_pay           ;
    private int cart_cust         ;
    private double cart_price        ;

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public double getHsje() {
        return hsje;
    }

    public void setHsje(double hsje) {
        this.hsje = hsje;
    }

    public double getCankml() {
        return cankml;
    }

    public void setCankml(double cankml) {
        this.cankml = cankml;
    }

    public double getCankmll() {
        return cankmll;
    }

    public void setCankmll(double cankmll) {
        this.cankmll = cankmll;
    }

    public int getCust_num() {
        return cust_num;
    }

    public void setCust_num(int cust_num) {
        this.cust_num = cust_num;
    }

    public int getLogin_num() {
        return login_num;
    }

    public void setLogin_num(int login_num) {
        this.login_num = login_num;
    }

    public int getPay_cust() {
        return pay_cust;
    }

    public void setPay_cust(int pay_cust) {
        this.pay_cust = pay_cust;
    }

    public int getNot_pay_cust() {
        return not_pay_cust;
    }

    public void setNot_pay_cust(int not_pay_cust) {
        this.not_pay_cust = not_pay_cust;
    }

    public double getNot_pay() {
        return not_pay;
    }

    public void setNot_pay(double not_pay) {
        this.not_pay = not_pay;
    }

    public int getCart_cust() {
        return cart_cust;
    }

    public void setCart_cust(int cart_cust) {
        this.cart_cust = cart_cust;
    }

    public double getCart_price() {
        return cart_price;
    }

    public void setCart_price(double cart_price) {
        this.cart_price = cart_price;
    }
}
