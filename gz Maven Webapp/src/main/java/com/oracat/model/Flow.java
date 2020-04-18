package com.oracat.model;

public class Flow {
    private String begin_name;

    public String getBegin_name() {
        return begin_name;
    }

    public void setBegin_name(String begin_name) {
        this.begin_name = begin_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getEnd_name() {
        return end_name;
    }

    public void setEnd_name(String end_name) {
        this.end_name = end_name;
    }

    private    int id;
    private  double  value;
    private  String end_name;

}
