package com.oracat.controller;

import com.oracat.model.*;
import com.oracat.service.DataService;
import com.oracat.service.OverViewCatagoryService;
import com.oracat.util.tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    //客户节

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
    public ModelAndView getKhjorder(Model model, @ModelAttribute Order v_order){

        ModelAndView mav = new ModelAndView("khjorder");
        List<Order> order = dataService.selectOrder(v_order.getBegin_date(),v_order.getEnd_date());
        List<Order> top10 = dataService.selectOrderTop10(v_order.getBegin_date(),v_order.getEnd_date());
        List<Order> ms = dataService.selectOrderMs(v_order.getBegin_date(),v_order.getEnd_date());
        List<Order> line = dataService.selectOrderLine(v_order.getBegin_date(),v_order.getEnd_date());
        mav.addObject("order", order);
        mav.addObject("top10", top10);
        mav.addObject("ms", ms);
        mav.addObject("line", line);

        mav.addObject("order_con", v_order);   //回写查询条件
        return mav;


    }



    @RequestMapping("/khjgoods")
    public ModelAndView getKhjGoods(Model model, @ModelAttribute Sp v_goods){

        ModelAndView mav = new ModelAndView("khjgoods");

        List<Sp> sp = dataService.selectSp(v_goods.getBegin_date(),v_goods.getEnd_date());
        List<Sp> spms = dataService.selectSpMs(v_goods.getBegin_date(),v_goods.getEnd_date());
        List<Sp> spmstop10 = dataService.selectSpMsTop10(v_goods.getBegin_date(),v_goods.getEnd_date());
        mav.addObject("sp", sp);
        mav.addObject("spms", spms);
        mav.addObject("spmstop10", spmstop10);
        mav.addObject("goods_con", v_goods);   //回写查询条件
        return mav;


    }




    @RequestMapping("/khjspml")
    public ModelAndView getKhjSpml(){

        ModelAndView mav = new ModelAndView("khjspml");

        List<Spml> spml = dataService.selectSpml();
        List<Spml> spmlmiss = dataService.selectSpmlMiss();
        List<Spml> spmlstock = dataService.selectSpmlStock();
        List<Spml> spmlnostock = dataService.selectSpmlNoStock();

        mav.addObject("spml", spml);
        mav.addObject("spmlmiss", spmlmiss);
        mav.addObject("spmlstock", spmlstock);
        mav.addObject("spmlnostock", spmlnostock);

        return mav;


    }


    @RequestMapping("/khjcust")
    public ModelAndView getKhjCust(){

        ModelAndView mav = new ModelAndView("khjcust");

        List<Cust>  custadd= dataService.selectCustAdd(tools.getTimeDay(-15),tools.getTimeDay(0));
        List<Cust>  custaddorder= dataService.selectCustAddOrder(tools.getTimeDay(0),tools.getTimeDay(0));
        mav.addObject("custadd", custadd);
        mav.addObject("custaddorder", custaddorder);
        return mav;


    }

}
