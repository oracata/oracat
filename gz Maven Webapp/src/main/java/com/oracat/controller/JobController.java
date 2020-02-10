package com.oracat.controller;




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


    /**
     * 暂停定时任务
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    @ResponseBody
    public Result pauseJob(int jobId) {
        jobService.pauseJob(jobId);
        return Result.PAUSE_SUCCESS;
    }

    /**
     * 恢复定时任务
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping(value="/resumeJob",method = RequestMethod.POST)
    @ResponseBody
    public Result resumeJob(int jobId){
        jobService.resumeJob(jobId);
        return Result.RESUME_SUCCESS;
    }


    @RequestMapping(value = "/runOnce",method = RequestMethod.POST)
    @ResponseBody
    public Result runOnce(int jobId){
        jobService.runOnce(jobId);
        return Result.RUN_SUCCESS;
    }

    @RequestMapping(value = "/runJob",method = RequestMethod.POST)
    @ResponseBody
    public Result runJob(int jobId){
        jobService.runJob(jobId);
        return Result.RUN_SUCCESS;
    }

    @RequestMapping(value = "/stopJob",method = RequestMethod.POST)
    @ResponseBody
    public Result stopJob(int jobId){
        jobService.stopJob(jobId);
        return Result.STOP_SUCCESS;
    }

/*
    @RequestMapping(value = "/updateCron",method = RequestMethod.POST)
    @ResponseBody
    public Result updateCron(int id,String cronExpression){
        jobService.updateCron(id,cronExpression);
        return new BaseResult(1, "success", "更新时间表达式成功");
    }


    @RequestMapping(value = "/addScheduleJob",method = RequestMethod.POST)
    @ResponseBody
    public Result addScheduleJob(ScheduleJob scheduleJob){
        try {
            jobService.addScheduleJob(scheduleJob);
        }catch (Exception e){
            return new BaseResult(0, "default", "添加定时任务失败");
        }

        return new BaseResult(1, "success", "添加定时任务成功");
    }
 */

}