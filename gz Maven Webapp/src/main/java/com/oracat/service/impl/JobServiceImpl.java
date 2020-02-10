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


    @Autowired  //自动装配
    private JobDao jobDao ;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 项目启动时初始化定时器
     */
    @PostConstruct
    public void init() {
        DynamicDataSourceHolder.setDataSource("mysql");
        //获取所有的定时任务
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
     * 添加任务
     * @param job
     */
    private void addJob(ScheduleJob job) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {

            log.info("初始化");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJob_name(), job.getJob_group());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，则创建
            if (null == trigger) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.
                        newJob(clazz).
                        withIdentity(job.getJob_name(), job.getJob_group()).
                        build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                //withIdentity中写jobName和groupName
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJob_name(), job.getJob_group())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
                /*
                //如果定时任务是暂停状态
                if(job.getStatus() == Constants.STATUS_NOT_RUNNING){
                    pauseJob(job.getId());
                }
                //如果定时任务是停止状态
                else if(job.getStatus() == Constants.STATUS_STOP){
                    stopJob(job.getId());
                }
                */

            } else {
                // Trigger已存在，那么更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            log.error("添加任务失败", e);
        }
    }


    /**
     * 添加任务
     * @param job
     */
    private void newJob(ScheduleJob job) {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {

            log.info("初始化");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJob_name(), job.getJob_group());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，则创建
            if (null == trigger) {
                Class clazz = QuartzJobFactory.class;
                JobDetail jobDetail = JobBuilder.
                        newJob(clazz).
                        withIdentity(job.getJob_name(), job.getJob_group()).
                        build();
                jobDetail.getJobDataMap().put("scheduleJob", job);

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                //withIdentity中写jobName和groupName
                trigger = TriggerBuilder.
                        newTrigger().
                        withIdentity(job.getJob_name(), job.getJob_group())
                        .withSchedule(scheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);

            } else {
                // Trigger已存在，那么更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron_expression());

                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            log.error("添加任务失败", e);
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
     * 暂停定时任务
     * @param jobId
     */
    @Override
    public void pauseJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //修改定时任务状态
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setId(jobId);
        scheduleJob.setStatus(Constants.STATUS_NOT_RUNNING);
        updateJobStatusById(scheduleJob);
        try {
            //暂停一个job
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.pauseJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:暂停任务失败",e);
        }
    }



    /**
     * 停止定时任务
     * @param jobId
     */
    @Override
    public void stopJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //修改定时任务状态
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setId(jobId);
        scheduleJob.setStatus(Constants.STATUS_STOP);
        updateJobStatusById(scheduleJob);
        try {
            //一个job
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.deleteJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:停止任务失败",e);
        }
    }



    /**
     * 恢复一个定时任务
     * @param jobId
     */
    @Override
    public void resumeJob(int jobId) {
        DynamicDataSourceHolder.setDataSource("mysql");
        //修改定时任务状态
        ScheduleJob scheduleJob = jobDao.getScheduleJobByPrimaryKey(jobId);
        scheduleJob.setStatus(Constants.STATUS_RUNNING);
        updateJobStatusById(scheduleJob);
        try{
            //恢复一个定时任务
            JobKey jobKey = JobKey.jobKey(scheduleJob.getJob_name(), scheduleJob.getJob_group());
            scheduler.resumeJob(jobKey);
        }catch (Exception e){
            log.error("CatchException:恢复定时任务失败",e);
        }
    }

    /**
     * 立即执行一个定时任务
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
            log.error("CatchException:执行定时任务失败",e);
        }
    }


    /**
     * 立即执行一个定时任务
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
            log.error("CatchException:执行定时任务失败",e);
        }
    }

    /**
     * 更新时间表达式
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
            log.error("CatchException:更新时间表达式失败",e);
        }

    }

    /**
     * 添加定时任务
     * @param scheduleJob
     */
    @Override
    public void addScheduleJob(ScheduleJob scheduleJob) throws Exception {
        DynamicDataSourceHolder.setDataSource("mysql");
        try {
            jobDao.addScheduleJob(scheduleJob);
            addJob(scheduleJob);
        }catch (Exception e){
            throw new Exception("添加失败");
        }

    }

    /**
     * 修改定时任务状态
     * @param scheduleJob
     */
    private void updateJobStatusById(ScheduleJob scheduleJob){
        DynamicDataSourceHolder.setDataSource("mysql");

        jobDao.updateJobStatusById(scheduleJob);
    }

    /**
     * 修改定时任务时间
     */
    private void updateJobCronExpressionById(ScheduleJob scheduleJob){
        DynamicDataSourceHolder.setDataSource("mysql");
        jobDao.updateJobCronExpressionById(scheduleJob);
    }

}
