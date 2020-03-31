package com.oracat.model;

public class KhjTask {
    private          int id               ;
    private          String task_name     ;
    private          String task_owner    ;
    private          String plan_start    ;
    private          String plan_end      ;
    private          String actual_start  ;
    private          String actual_end    ;
    private          String delay_start   ;
    private          String delay_end     ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_owner() {
        return task_owner;
    }

    public void setTask_owner(String task_owner) {
        this.task_owner = task_owner;
    }

    public String getPlan_start() {
        return plan_start;
    }

    public void setPlan_start(String plan_start) {
        this.plan_start = plan_start;
    }

    public String getPlan_end() {
        return plan_end;
    }

    public void setPlan_end(String plan_end) {
        this.plan_end = plan_end;
    }

    public String getActual_start() {
        return actual_start;
    }

    public void setActual_start(String actual_start) {
        this.actual_start = actual_start;
    }

    public String getActual_end() {
        return actual_end;
    }

    public void setActual_end(String actual_end) {
        this.actual_end = actual_end;
    }

    public String getDelay_start() {
        return delay_start;
    }

    public void setDelay_start(String delay_start) {
        this.delay_start = delay_start;
    }

    public String getDelay_end() {
        return delay_end;
    }

    public void setDelay_end(String delay_end) {
        this.delay_end = delay_end;
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
