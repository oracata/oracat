package com.oracat.controller;

import com.oracat.model.OverViewCatagory;
import com.oracat.model.RealTime;
import com.oracat.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RealTimeController {

    @Resource
    private DataService dataService;


    @RequestMapping("/realtime")
    public ModelAndView getRealTime(){

        ModelAndView mav = new ModelAndView("realtime");
        List<RealTime> realtime = dataService.selectRealTime();
        List<RealTime> area = dataService.selectArea();
        mav.addObject("realtime", realtime);
        mav.addObject("area", area);
        return mav;
    }



    @RequestMapping("/tab")
    public ModelAndView getTab(){

        ModelAndView mav = new ModelAndView("tab");

        return mav;
    }
}
