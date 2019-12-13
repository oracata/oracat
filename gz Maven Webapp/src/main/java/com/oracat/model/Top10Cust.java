package com.oracat.model;

public class Top10Cust {
    private String wldwname                ;



    private String spmch                ;



    private String coupon                ;

    private String begin_date              ;
    private String end_date                ;
    private double hsje              ;
    private double cankml            ;
    private double cankmll           ;



    private int  num;
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
    public String getSpmch() {
        return spmch;
    }

    public void setSpmch(String spmch) {
        this.spmch = spmch;
    }

    public String getWldwname() {
        return wldwname;
    }

    public void setWldwname(String wldwname) {
        this.wldwname = wldwname;
    }

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
}
