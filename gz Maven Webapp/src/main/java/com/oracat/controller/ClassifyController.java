package com.oracat.controller;

import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;
import com.oracat.service.ClassifyService;
import com.oracat.service.JobService;
import com.oracat.util.DataGridView;
import com.oracat.util.Result;
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


    @ResponseBody
    @RequestMapping( "/classify/findFenlei2" )
    public DataGridView findFenlei2(Classify classify){

        return classifyService.findFenlei2(classify);

    }


    @ResponseBody
    @RequestMapping( "/classify/findFenlei3" )
    public DataGridView findFenlei3(Classify classify){

        return classifyService.findFenlei3(classify);

    }




    @ResponseBody
    @RequestMapping( "/classify/findFenlei4" )
    public DataGridView findFenlei4(Classify classify){

        return classifyService.findFenlei4(classify);

    }

    @ResponseBody
    @RequestMapping( "/classify/findFenleibm" )
    public DataGridView findFenleibm(Classify classify){

        return classifyService.findFenleibm(classify);

    }



    @ResponseBody
    @RequestMapping( "/classify/updateandsaveclassify" )
    public Result updateAndSaveClassify(Classify classify){
        try {
         classifyService.updateAndSaveClassify(classify);
            return Result.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }


    }

    @ResponseBody
    @RequestMapping( "/classify/updateandsaveclassify2" )
    public Result updateAndSaveClassify2(Classify classify){

        try {
             classifyService.updateAndSaveClassify(classify);
            return Result.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return Result.UPDATE_ERROR;
        }

    }


}
