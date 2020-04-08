package com.oracat.controller;

import com.oracat.model.Order;
import com.oracat.model.OverViewCatagory;
import com.oracat.model.RealTime;
import com.oracat.model.SaleFlow;
import com.oracat.service.DataService;
import com.oracat.service.OverViewCatagoryService;
import com.oracat.util.tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class RealTimeController {

    @Resource
    private DataService dataService;

    @Resource
    private OverViewCatagoryService overViewCatagoryService;


    @RequestMapping("/realtime")
    public ModelAndView getRealTime(){

        ModelAndView mav = new ModelAndView("realtime");
        List<RealTime> realtime = dataService.selectRealTime();
        List<RealTime> area = dataService.selectArea();
        List<SaleFlow> saleflow=dataService.selectSaleFlow();
        List<SaleFlow> saleflowerp=dataService.selectSaleFlowErp();
        mav.addObject("realtime", realtime);
        mav.addObject("area", area);
        mav.addObject("saleflow", saleflow);
        mav.addObject("saleflowerp", saleflowerp);
        return mav;
    }



    @RequestMapping("/saleflow")
    public ModelAndView getTab(){

        ModelAndView mav = new ModelAndView("saleflow");
        List<RealTime> realtime = dataService.selectRealTime();

        mav.addObject("realtime", realtime);

        return mav;
    }

    //¿Í»§½Ú

    @RequestMapping("/khjactive")
    public ModelAndView getKhjactive(){

        ModelAndView mav = new ModelAndView("khjactive");
        List<RealTime> realtime = dataService.selectRealTime();
        List<RealTime> activearea = dataService.selectActiveArea();
        List<RealTime> activelogin = dataService.selectActiveLogin(tools.getTimeDay(-7),tools.getTimeDay(0));

        mav.addObject("realtime", realtime);
        mav.addObject("activearea", activearea);
        mav.addObject("activelogin", activelogin);

        return mav;
    }




    @RequestMapping("/khjorder")
    public ModelAndView getKhjorder(){

        ModelAndView mav = new ModelAndView("khjorder");
        List<Order> order = dataService.selectOrder();

        mav.addObject("order", order);
        return mav;


    }


}
