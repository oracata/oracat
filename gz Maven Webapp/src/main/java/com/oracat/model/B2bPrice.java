package com.oracat.model;

public class B2bPrice {
    private String id              ;
    private String no              ;
    private String name            ;
    private String spec            ;

    private String manufacturer    ;
    private double pfpj            ;
    private double cankcbj         ;
    private double zdxshj          ;
    private double hshj;

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    private String modify_time;
   private int  days;

    private double xsj;

    public double getZy_xsj() {
        return zy_xsj;
    }

    public void setZy_xsj(double zy_xsj) {
        this.zy_xsj = zy_xsj;
    }

    private double zy_xsj;
    private double abs_rate;
    private int stock_num      ;


    public double getXsj() {
        return xsj;
    }

    public void setXsj(double xsj) {
        this.xsj = xsj;
    }


    public double getAbs_rate() {
        return abs_rate;
    }

    public void setAbs_rate(double abs_rate) {
        this.abs_rate = abs_rate;
    }


    public double getHshj() {
        return hshj;
    }

    public void setHshj(double hshj) {
        this.hshj = hshj;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPfpj() {
        return pfpj;
    }

    public void setPfpj(double pfpj) {
        this.pfpj = pfpj;
    }

    public double getCankcbj() {
        return cankcbj;
    }

    public void setCankcbj(double cankcbj) {
        this.cankcbj = cankcbj;
    }

    public double getZdxshj() {
        return zdxshj;
    }

    public void setZdxshj(double zdxshj) {
        this.zdxshj = zdxshj;
    }

    public int getStock_num() {
        return stock_num;
    }

    public void setStock_num(int stock_num) {
        this.stock_num = stock_num;
    }


}
