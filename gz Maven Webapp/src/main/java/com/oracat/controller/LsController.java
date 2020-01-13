package com.oracat.controller;

import com.oracat.model.RealTime;
import com.oracat.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LsController {

    @Resource
    private DataService dataService;





    @RequestMapping(value = "/ls/lsflow")
    public ModelAndView getLsFlow(){

        ModelAndView mav = new ModelAndView("/ls/lsflow");
      //  List<RealTime> realtime = dataService.selectRealTime();

      //  mav.addObject("realtime", realtime);

        return mav;
    }
}
