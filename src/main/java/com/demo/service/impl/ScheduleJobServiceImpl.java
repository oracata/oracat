package com.demo.service.impl;

import com.demo.common.result.BootstrapTableResult;
import com.demo.common.result.Constant;
import com.demo.dao.ScheduleJobMapper;
import com.demo.domain.ScheduleJob;
import com.demo.schedule.QuartzJobFactory;
import com.demo.service.ScheduleJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @date 2017-11-21 ���� 9:36
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
    private Logger log = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);

    @Resource
    private ScheduleJobMapper scheduleJobMapper;
    @Resource
    private Scheduler scheduler;

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
        //��ȡ���еĶ�ʱ����
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.listAllJob();
        if (scheduleJobList.size() != 0) {
            for (ScheduleJob scheduleJob : scheduleJobList) {
                    addJob(scheduleJob);
            }
        }
    }

    /**
     * �������
     * @param job
     */
    private void addJob(ScheduleJob job) {
        try {
            log.info("��ʼ��");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //�����ڣ��򴴽�
            if (null == trigger) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.
                        newJob(clazz).
                        withIdentity(job.getJobName(), job.getJobGroup()).
                        build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

                //withIdentity��дjobName��groupName
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJobName(), job.getJobGroup())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                //�����ʱ��������ͣ״̬
                if(job.getStatus() == Constant.STATUS_NOT_RUNNING){
                    pauseJob(job.getId());
                }
            } else {
                // Trigger�Ѵ��ڣ���ô������Ӧ�Ķ�ʱ����
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

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
     * ��ѯ���еĶ�ʱ����
     * @return BootstrapTableResult
     */
    @Override
    public BootstrapTableResult listAllJob(int pageSize, int pageNumber) {
        PageHelper.startPage(pageNumber, pageSize);
        List<ScheduleJob> scheduleJobList = scheduleJobMapper.listAllJob();
        PageInfo pageInfo = new PageInfo(scheduleJobList, Constant.PAGENUMBER);
        int total = (int) pageInfo.getTotal();
        BootstrapTableResult bootstrapTableResult = new BootstrapTableResult(total, scheduleJobList);
        return bootstrapTableResult;
    }

    /**
     * ��ͣ��ʱ����
     * @param jobId
     */
    @Override
    public void pauseJob(int jobId) {
        //�޸Ķ�ʱ����״̬
        ScheduleJob scheduleJob = getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setId(jobId);
        scheduleJob.setStatus(Constant.STATUS_NOT_RUNNING);
        updateJobStatusById(scheduleJob);
        try {
            //��ͣһ��job
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            scheduler.pauseJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:��ͣ����ʧ��",e);
        }
    }

    /**
     * �ָ�һ����ʱ����
     * @param jobId
     */
    @Override
    public void resumeJob(int jobId) {
        //�޸Ķ�ʱ����״̬
        ScheduleJob scheduleJob = getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setStatus(Constant.STATUS_RUNNING);
        updateJobStatusById(scheduleJob);
        try{
            //�ָ�һ����ʱ����
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
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
        try{
            ScheduleJob scheduleJob = getScheduleJobByPrimaryKey(jobId);
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            scheduler.triggerJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:�ָ���ʱ����ʧ��",e);
        }
    }

    /**
     * ����ʱ����ʽ
     * @param id
     * @param cronExpression
     */
    @Override
    public void updateCron(int id, String cronExpression) {
        ScheduleJob scheduleJob = getScheduleJobByPrimaryKey(id);
        scheduleJob.setCronExpression(cronExpression);
        updateJobCronExpressionById(scheduleJob);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
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
        try {
            scheduleJobMapper.addScheduleJob(scheduleJob);
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
        scheduleJobMapper.updateJobStatusById(scheduleJob);
    }

    /**
     * �޸Ķ�ʱ����ʱ��
     */
    private void updateJobCronExpressionById(ScheduleJob scheduleJob){
        scheduleJobMapper.updateJobCronExpressionById(scheduleJob);
    }

    /**
     * ͨ������id���Ҷ�ʱ����
     * @param id
     * @return ScheduleJob
     */
    private ScheduleJob getScheduleJobByPrimaryKey(int id){
        return scheduleJobMapper.getScheduleJobByPrimaryKey(id);
    }


}
