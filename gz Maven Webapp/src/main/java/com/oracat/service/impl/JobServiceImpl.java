package com.oracat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oracat.dao.*;
import com.oracat.job.QuartzJobFactory;

import com.oracat.model.ScheduleJob;
import com.oracat.service.JobService;
import com.oracat.util.Constants;
import com.oracat.util.DataGridView;
import com.oracat.util.DynamicDataSourceHolder;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("jobService")
public class JobServiceImpl implements JobService {
    private Logger log = LoggerFactory.getLogger(JobServiceImpl.class);


    @Resource
    private Scheduler scheduler;


    @Autowired  //�Զ�װ��
    private JobDao jobDao ;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * ��Ŀ����ʱ��ʼ����ʱ��
     */
    @PostConstruct
    public void init() {
        DynamicDataSourceHolder.setDataSource("mysql");
        //��ȡ���еĶ�ʱ����
        List<ScheduleJob> scheduleJobList = jobDao.listAllJob();
        if (scheduleJobList.size() != 0) {
            for (ScheduleJob scheduleJob : scheduleJobList) {
                if(scheduleJob.getStatus()==0) {
                    addJob(scheduleJob);
                }
            }
        }
    }

    /**
     * �������
     * @param job
     */
    private void addJob(ScheduleJob job) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {

            log.info("��ʼ��");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJob_name(), job.getJob_group());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //�����ڣ��򴴽�
            if (null == trigger) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.
                        newJob(clazz).
                        withIdentity(job.getJob_name(), job.getJob_group()).
                        build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                //withIdentity��дjobName��groupName
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJob_name(), job.getJob_group())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                /*
                //�����ʱ��������ͣ״̬
                if(job.getStatus() == Constants.STATUS_NOT_RUNNING){
                    pauseJob(job.getId());
                }
                //�����ʱ������ֹͣ״̬
                else if(job.getStatus() == Constants.STATUS_STOP){
                    stopJob(job.getId());
                }
                */

            } else {
                // Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                // ���µ�cronExpression���ʽ���¹���trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // ���µ�trigger��������jobִ��
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            log.error("�������ʧ��", e);
        }
    }


    /**
     * �������
     * @param job
     */
    private void newJob(ScheduleJob job) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {

            log.info("��ʼ��");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJob_name(), job.getJob_group());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //�����ڣ��򴴽�
            if (null == trigger) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.
                        newJob(clazz).
                        withIdentity(job.getJob_name(), job.getJob_group()).
                        build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                //withIdentity��дjobName��groupName
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJob_name(), job.getJob_group())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);

            } else {
                // Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                // ���µ�cronExpression���ʽ���¹���trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // ���µ�trigger��������jobִ��
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            log.error("�������ʧ��", e);
        }
    }





    @Override
    public DataGridView selectAllJobAndTrigger(ScheduleJob scheduleJob)
    {
        DynamicDataSourceHolder.setDataSource("mysql");
        Page<Object> page= PageHelper.startPage(scheduleJob.getPage(),scheduleJob.getLimit());
        List<ScheduleJob> data=jobDao.queryAllJob(scheduleJob);
        return new DataGridView(page.getTotal(),data);

    }

    /**
     * ��ͣ��ʱ����
     * @param jobId
     */
    @Override
    public void pauseJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //�޸Ķ�ʱ����״̬
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setId(jobId);
        scheduleJob.setStatus(Constants.STATUS_NOT_RUNNING);
        updateJobStatusById(scheduleJob);
        try {
            //��ͣһ��job
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.pauseJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:��ͣ����ʧ��",e);
        }
    }



    /**
     * ֹͣ��ʱ����
     * @param jobId
     */
    @Override
    public void stopJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //�޸Ķ�ʱ����״̬
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setId(jobId);
        scheduleJob.setStatus(Constants.STATUS_STOP);
        updateJobStatusById(scheduleJob);
        try {
            //һ��job
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.deleteJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:ֹͣ����ʧ��",e);
        }
    }



    /**
     * �ָ�һ����ʱ����
     * @param jobId
     */
    @Override
    public void resumeJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //�޸Ķ�ʱ����״̬
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setStatus(Constants.STATUS_RUNNING);
        updateJobStatusById(scheduleJob);
        try{
            //�ָ�һ����ʱ����
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.resumeJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:�ָ���ʱ����ʧ��",e);
        }
    }

    /**
     * ����ִ��һ����ʱ����
     * @param jobId
     */
    @Override
    public void runOnce(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try{
            ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
            addJob(scheduleJob);
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.triggerJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:ִ�ж�ʱ����ʧ��",e);
        }
    }


    /**
     * ����ִ��һ����ʱ����
     * @param jobId
     */
    @Override
    public void runJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try{
            ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
            scheduleJob.setStatus(Constants.STATUS_RUNNING);
            updateJobStatusById(scheduleJob);
            newJob(scheduleJob);
        }catch (Exception e){
            log.error("CatchException:ִ�ж�ʱ����ʧ��",e);
        }
    }

    /**
     * ����ʱ����ʽ
     * @param id
     * @param cronExpression
     */
    @Override
    public void updateCron(int id, String cronExpression) {
        DynamicDataSourceHolder.setDataSource("mysql");
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(id);
        scheduleJob.setCron_expression(cronExpression);
        updateJobCronExpressionById(scheduleJob);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCron_expression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey,trigger);
        }catch(Exception e){
            log.error("CatchException:����ʱ����ʽʧ��",e);
        }

    }

    /**
     * ��Ӷ�ʱ����
     * @param scheduleJob
     */
    @Override
    public void addScheduleJob(ScheduleJob scheduleJob) throws Exception {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {
            jobDao.addScheduleJob(scheduleJob);
            addJob(scheduleJob);
        }catch (Exception e){
            throw new Exception("���ʧ��");
        }

    }

    /**
     * �޸Ķ�ʱ����״̬
     * @param scheduleJob
     */
    private void updateJobStatusById(ScheduleJob scheduleJob){
        DynamicDataSourceHolder.setDataSource("mysql");

        jobDao.updateJobStatusById(scheduleJob);
    }

    /**
     * �޸Ķ�ʱ����ʱ��
     */
    private void updateJobCronExpressionById(ScheduleJob scheduleJob){
        DynamicDataSourceHolder.setDataSource("mysql");
        jobDao.updateJobCronExpressionById(scheduleJob);
    }

}
