package com.oracat.controller;



import com.oracat.model.JobandTrigger;
import com.oracat.model.RealTime;
import com.oracat.model.ScheduleJob;
import com.oracat.service.DataService;
import com.oracat.service.JobService;
import com.oracat.util.DataGridView;
import com.oracat.util.Result;
import com.oracat.util.tools;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class JobController {

    @Resource
    private JobService jobService;


    @ResponseBody
    @RequestMapping("/queryjob")
    public DataGridView queryAll(ScheduleJob scheduleJob){




         return jobService.selectAllJobAndTrigger(scheduleJob);



     }


    @RequestMapping("/job")
    public ModelAndView getJob(){

        ModelAndView mav = new ModelAndView("job");

        return mav;
    }

/*
//注解ResponseBody是把返回结果写到response中
    @RequestMapping("/addJobandTrigger")
    @ResponseBody
    public Result addJobandTrigger(JobandTrigger JobandTrigger){
        try {
            JobandTrigger.setCreatetime(tools.getTimeDay(0));

            jobService.addJobandTrigger(JobandTrigger);

            return Result.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.ADD_ERROR;
        }
    }


    @RequestMapping("/updateJobandTrigger")
    @ResponseBody
    public Result updateJobandTrigger(JobandTrigger JobandTrigger){

        try {
            JobandTrigger.setCreatetime(tools.getTimeDay(0));

            jobService.updateJobandTrigger(JobandTrigger);
            return Result.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }
    }



    @RequestMapping("/deleteJobandTrigger")
    @ResponseBody
    public Result deleteJobandTrigger(JobandTrigger JobandTrigger){
        try {

            jobService.deleteJobandTrigger(JobandTrigger.getJob_name());
            return Result.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     * @param 
     * @return
     */
/*
    @RequestMapping("/deleteBatchJobandTrigger")
    @ResponseBody
    public Result deleteBatchJobandTrigger(JobandTrigger JobandTrigger){
        try {
            jobService.deleteBatchJobandTrigger(JobandTrigger.getIds());
            return Result.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }

    */
    


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