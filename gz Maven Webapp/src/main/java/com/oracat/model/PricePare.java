package com.oracat.model;

public class PricePare {
    String date               ;
    String no                 ;
    String name               ;
    String spec               ;
    String manufacturer       ;
    double pfpj               ;
    double price              ;
    String yz_spec            ;
    String active_type        ;
    String active_name        ;
    private String begin_date              ;
    private String end_date                ;

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




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getYz_spec() {
        return yz_spec;
    }

    public void setYz_spec(String yz_spec) {
        this.yz_spec = yz_spec;
    }

    public String getActive_type() {
        return active_type;
    }

    public void setActive_type(String active_type) {
        this.active_type = active_type;
    }

    public String getActive_name() {
        return active_name;
    }

    public void setActive_name(String active_name) {
        this.active_name = active_name;
    }


}
