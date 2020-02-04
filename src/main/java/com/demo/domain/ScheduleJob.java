package com.demo.domain;

import java.util.Date;

/**
 * ��ʱ����ʵ����
 * @author admin
 * @date 2017-11-25 ���� 19:06
 */
public class ScheduleJob {
    /**
     * ����id
     */
    private Integer id;
    /**
     * ������
     */
    private String jobName;
    /**
     * ������
     */
    private String jobGroup;
    /**
     * Ҫִ�еķ���������
     */
    private String methodName;
    /**
     * Ҫִ�еķ������ڵ�class·��
     */
    private String beanClass;
    /**
     * ��ʱ����״̬��0��ʾ������1��ʾֹͣ
     */
    private Integer status;
    /**
     * ʱ����ʽ
     */
    private String cronExpression;
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
    private Date createTime;
    /**
     * �޸�ʱ��
     */
    private Date modifyTime;

    public ScheduleJob() {
    }

    public ScheduleJob(Integer id, String jobName, String jobGroup, String methodName, String beanClass, Integer status,
                       String cronExpression, String params, String remark, Date createTime, Date modifyTime) {
        this.id = id;
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.methodName = methodName;
        this.beanClass = beanClass;
        this.status = status;
        this.cronExpression = cronExpression;
        this.params = params;
        this.remark = remark;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", methodName='" + methodName + '\'' +
                ", beanClass='" + beanClass + '\'' +
                ", status=" + status +
                ", cronExpression='" + cronExpression + '\'' +
                ", params='" + params + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}