package com.oracat.controller;

import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;
import com.oracat.service.ClassifyService;
import com.oracat.service.JobService;
import com.oracat.util.DataGridView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
//RestController=ResponseBody+Controller
@Controller
public class ClassifyController {

    @Resource
    private ClassifyService classifyService;



    @RequestMapping("/classify/classify")
    public ModelAndView getClassify(){

        ModelAndView mav = new ModelAndView("/classify/classify");

        return mav;
    }


   @ResponseBody
   @RequestMapping( "/classify/listclassify" )
    public DataGridView queryAll(Classify classify){

        return classifyService.selectClassify(classify);

    }



    @ResponseBody
    @RequestMapping( "/classify/findfenleibybm" )
    public DataGridView findFenleibybm(Classify classify){

        return classifyService.findFenleibybm(classify);

    }


    @ResponseBody
    @RequestMapping( "/classify/findFenlei1" )
    public DataGridView findFenlei1(Classify classify){

        return classifyService.findFenlei1(classify);

    }

}
