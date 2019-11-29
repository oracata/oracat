package com.oracat.controller;

import com.oracat.model.PricePare;
import com.oracat.model.ReportDay;
import com.oracat.service.DataService;
import com.oracat.util.tag.PageModel;
import com.oracat.util.tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PricePareController {

    @Resource
    private DataService dataService;




    @RequestMapping("/pricepare")
    public ModelAndView getReportDayView(Model model, Integer pageIndex,
                                         @ModelAttribute PricePare v_pricepare){

        //初始化查询条件
        if(v_pricepare.getBegin_date()==null && v_pricepare.getEnd_date()==null) {
            v_pricepare.setBegin_date(tools.getTimeDay(-30));
            v_pricepare.setEnd_date(tools.getTimeDay(0));

        }

        ModelAndView mav = new ModelAndView("pricepare");

        PageModel pageModel = new PageModel();
        pageModel.setPageSize(31);
        System.out.println("getPageIndex = " + pageModel.getPageIndex());
        System.out.println("getPageSize = " + pageModel.getPageSize());
        System.out.println("getRecordCount = " + pageModel.getRecordCount());


        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);


        }


        List<PricePare> pricepare =dataService.findPricePare(v_pricepare, pageModel);
        mav.addObject("pricepare", pricepare);
        mav.addObject("pageModel", pageModel);
        mav.addObject("pricepare_con", v_pricepare);   //回写查询条件




        return mav;
    }


}
