package com.oracat.model;

public class JobandTrigger {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String[]ids;
    public String[] getIds() {
        return ids;
    }
    private Integer id;
    private String job_name;
    private String job_group;
    private String job_class_name;
    private String trigger_name;
    private String trigger_group;
    private Integer repeat_interval;
    private Integer times_triggered;

    private Integer page=1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    private Integer limit=10;


    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    private  String createtime;

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_group() {
        return job_group;
    }

    public void setJob_group(String job_group) {
        this.job_group = job_group;
    }

    public String getJob_class_name() {
        return job_class_name;
    }

    public void setJob_class_name(String job_class_name) {
        this.job_class_name = job_class_name;
    }

    public String getTrigger_name() {
        return trigger_name;
    }

    public void setTrigger_name(String trigger_name) {
        this.trigger_name = trigger_name;
    }

    public String getTrigger_group() {
        return trigger_group;
    }

    public void setTrigger_group(String trigger_group) {
        this.trigger_group = trigger_group;
    }

    public Integer getRepeat_interval() {
        return repeat_interval;
    }

    public void setRepeat_interval(Integer repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public Integer getTimes_triggered() {
        return times_triggered;
    }

    public void setTimes_triggered(Integer times_triggered) {
        this.times_triggered = times_triggered;
    }
}
