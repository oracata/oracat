package com.oracat.controller;

import com.oracat.model.Classify;
import com.oracat.model.KhjTask;
import com.oracat.model.RealTime;
import com.oracat.model.SaleFlow;
import com.oracat.service.DataService;
import com.oracat.service.TaskService;
import com.oracat.util.DataGridView;
import com.oracat.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class KhjController {

   @Resource
   private TaskService taskService;


    @RequestMapping("/khjwork")
    public ModelAndView getKhjWork( ){

        ModelAndView mav = new ModelAndView("khjwork");
        List<KhjTask> task = taskService.selectAllTask();

        mav.addObject("task", task);

        return mav;
    }



    @RequestMapping("/khjtask/task")
    public ModelAndView getKhjTask(){

        ModelAndView mav = new ModelAndView("/khjtask/task");

        return mav;
    }



    @ResponseBody
    @RequestMapping( "/khjtask/listtask" )
    public DataGridView queryAll(KhjTask khjTask){

        return taskService.selectTask(khjTask);

    }



    @ResponseBody
    @RequestMapping( "/khjtask/findtaskbyid" )
    public DataGridView findTaskByid(KhjTask khjTask){

        return taskService.findTaskByid(khjTask);

    }


    @ResponseBody
    @RequestMapping( "/khjtask/updateandsavetask" )
    public Result updateAndSaveTask(KhjTask khjTask){
        try {
            taskService.updateAndSaveTask(khjTask);
            return Result.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }


    }


    @ResponseBody
    @RequestMapping("/khjtask/deleteBatchTask")
    public Result deleteBatchTask(KhjTask khjTask){
        try {
            taskService.deleteBatchTask(khjTask.getIds());
            return Result.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.DELETE_ERROR;
        }
    }


}
