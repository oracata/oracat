package com.oracat.model;

public class Classify {

    private String spid                 ;
    private String spbm                 ;
    private String spmch                ;
    private String spec                 ;
    private String manufacturer         ;
    private String approval_number      ;
    private String flbm                 ;
    private String fenlei1              ;
    private String fenleibm1              ;

    public String getFenleibm1() {
        return fenleibm1;
    }

    public void setFenleibm1(String fenleibm1) {
        this.fenleibm1 = fenleibm1;
    }

    public String getFenleibm2() {
        return fenleibm2;
    }

    public void setFenleibm2(String fenleibm2) {
        this.fenleibm2 = fenleibm2;
    }

    public String getFenleibm3() {
        return fenleibm3;
    }

    public void setFenleibm3(String fenleibm3) {
        this.fenleibm3 = fenleibm3;
    }

    public String getFenleibm4() {
        return fenleibm4;
    }

    public void setFenleibm4(String fenleibm4) {
        this.fenleibm4 = fenleibm4;
    }

    private String fenleibm2              ;
    private String fenleibm3              ;
    private String fenleibm4              ;
    private String fenlei2              ;
    private String fenlei3              ;
    private String fenlei4              ;

    public String getIson() {
        return ison;
    }

    public void setIson(String ison) {
        this.ison = ison;
    }

    private String ison ;
    public String getIsnull() {
        return isnull;
    }

    public void setIsnull(String isnull) {
        this.isnull = isnull;
    }

    private  String isnull;
    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getSpmch() {
        return spmch;
    }

    public void setSpmch(String spmch) {
        this.spmch = spmch;
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

    public String getApproval_number() {
        return approval_number;
    }

    public void setApproval_number(String approval_number) {
        this.approval_number = approval_number;
    }

    public String getFlbm() {
        return flbm;
    }

    public void setFlbm(String flbm) {
        this.flbm = flbm;
    }

    public String getFenlei1() {
        return fenlei1;
    }

    public void setFenlei1(String fenlei1) {
        this.fenlei1 = fenlei1;
    }

    public String getFenlei2() {
        return fenlei2;
    }

    public void setFenlei2(String fenlei2) {
        this.fenlei2 = fenlei2;
    }

    public String getFenlei3() {
        return fenlei3;
    }

    public void setFenlei3(String fenlei3) {
        this.fenlei3 = fenlei3;
    }

    public String getFenlei4() {
        return fenlei4;
    }

    public void setFenlei4(String fenlei4) {
        this.fenlei4 = fenlei4;
    }

    private Integer page=1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    private Integer limit=10;
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
