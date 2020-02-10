package com.oracat.model;

import java.io.Serializable;
import java.util.Date;
 /*����Ҫ��implements Serializable ��Ȼ�ᱨ�޷�Serializable�Ĵ��� */
public class ScheduleJob  implements Serializable {
    /**
     * ����id
     */
    private Integer id;
    /**
     * ������
     */
    private String job_name;
    /**
     * ������
     */
    private String job_group;
    /**
     * Ҫִ�еķ���������
     */
    private String method_name;
    /**
     * Ҫִ�еķ������ڵ�class·��
     */
    private String bean_class;
    /**
     * ��ʱ����״̬��0��ʾ������1��ʾֹͣ
     */
    private Integer status;
    /**
     * ʱ����ʽ
     */
    private String cron_expression;
    /**
     * ����
     */
    private String params;
    /**
     * ��ע
     */
    private String remark;
    /**
     * ����ʱ��
     */
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getBean_class() {
        return bean_class;
    }

    public void setBean_class(String bean_class) {
        this.bean_class = bean_class;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCron_expression() {
        return cron_expression;
    }

    public void setCron_expression(String cron_expression) {
        this.cron_expression = cron_expression;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    /**
     * �޸�ʱ��
     */
    private Date modify_time;

    public ScheduleJob() {
    }

    public ScheduleJob(Integer id, String job_name, String job_group, String method_name, String bean_class, Integer status,
                       String cron_expression, String params, String remark, Date create_time, Date modify_time) {
        this.id = id;
        this.job_name = job_name;
        this.job_group = job_group;
        this.method_name = method_name;
        this.bean_class = bean_class;
        this.status = status;
        this.cron_expression = cron_expression;
        this.params = params;
        this.remark = remark;
        this.create_time = create_time;
        this.modify_time = modify_time;
    }



    @Override
    public String toString() {
        return "ScheduleJob{" +
                "id=" + id +
                ", job_name='" + job_name + '\'' +
                ", job_group='" + job_group + '\'' +
                ", method_name='" + method_name + '\'' +
                ", bean_class='" + bean_class + '\'' +
                ", status=" + status +
                ", cron_expression='" + cron_expression + '\'' +
                ", params='" + params + '\'' +
                ", remark='" + remark + '\'' +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                '}';
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
