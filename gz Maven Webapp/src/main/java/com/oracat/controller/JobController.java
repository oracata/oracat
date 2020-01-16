package com.oracat.controller;

import com.oracat.job.HelloJob;
import com.oracat.job.NewJob;

import com.oracat.model.JobandTrigger;
import com.oracat.model.RealTime;
import com.oracat.service.DataService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class JobController {

    @Resource
    private DataService dataService;


    @ResponseBody
    @RequestMapping("/queryjob")
    public Map<String, Object> queryAll( String job_name ,String flag ){

         Map<String, Object> map = new HashMap<>();
         List<JobandTrigger> jobAndtrigger = dataService.getJobAndTrigger();

         int count = dataService.queryJobCount();
         map.put("data", jobAndtrigger);
         map.put("code", 0);
         map.put("count", count);
         map.put("msg", "");

         return map;



     }


    @RequestMapping("/job")
    public ModelAndView getJob(){

        ModelAndView mav = new ModelAndView("job");

        return mav;
    }






    @ResponseBody
    @RequestMapping(value="/addjob", method = RequestMethod.POST)
    public void addjob(@RequestParam(value="jobClassName")String jobClassName) throws Exception
    {
        setJob(jobClassName);
    }

    public static void setJob(String jobClassName) throws Exception
    {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();

        Scheduler sched = sf.getScheduler();

        // 启动调度器
        sched.start();

        switch (jobClassName)
        {
            case "HelloJob":
                JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("HelloJob", "group1").build();
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity("HelloJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job, trigger);
                break;

            case "NewJob":
                JobDetail job2 = JobBuilder.newJob(NewJob.class).withIdentity("NewJob", "group1").build();
                Trigger trigger2 =  TriggerBuilder.newTrigger().withIdentity("NewJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job2, trigger2);
                break;

            default:
                break;
        }
    }



    //暂停任务
    @ResponseBody
    @RequestMapping(value="/pausejob", method = RequestMethod.POST)
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName) throws Exception
    {
        jobPause(jobClassName);
    }

    public static void jobPause(String jobClassName) throws Exception
    {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        switch (jobClassName) {
            case "HelloJob":
                sched.pauseJob(JobKey.jobKey("HelloJob", "group1"));
                break;

            case "NewJob":
                sched.pauseJob(JobKey.jobKey("NewJob", "group1"));
                break;

            default:
                break;
        }
    }

    @ResponseBody
    @RequestMapping(value="/resumejob", method = RequestMethod.POST)
    public void resumejob(@RequestParam(value="jobClassName")String jobClassName) throws Exception
    {
        jobresume(jobClassName);
    }

    public static void jobresume(String jobClassName) throws Exception
    {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        if(sched != null){
            switch (jobClassName) {
                case "HelloJob":
                    sched.resumeJob(JobKey.jobKey("HelloJob", "group1"));
                    break;

                case "NewJob":
                    sched.resumeJob(JobKey.jobKey("NewJob", "group1"));
                    break;

                default:
                    break;
            }
        }
    }

    @ResponseBody
    @RequestMapping(value="/deletejob", method = RequestMethod.POST)
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName) throws Exception
    {
        jobdelete(jobClassName);
    }

    public static void jobdelete(String jobClassName) throws Exception
    {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        switch (jobClassName) {
            case "HelloJob":
                sched.pauseTrigger(TriggerKey.triggerKey("HelloJob", "group1"));
                sched.unscheduleJob(TriggerKey.triggerKey("HelloJob", "group1"));
                sched.deleteJob(JobKey.jobKey("HelloJob", "group1"));
                break;

            case "NewJob":
                sched.pauseTrigger(TriggerKey.triggerKey("NewJob", "group1"));
                sched.unscheduleJob(TriggerKey.triggerKey("NewJob", "group1"));
                sched.deleteJob(JobKey.jobKey("NewJob", "group1"));
                break;

            default:
                break;
        }

    }



}