package com.oracat.controller;

import com.oracat.model.Goods;
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
public class ReportController {
    @Resource
    private DataService dataService;




    @RequestMapping("/reportday")
    public ModelAndView getReportDayView(Model model, Integer pageIndex,
                                       @ModelAttribute ReportDay v_reportday){

        //��ʼ����ѯ����
        if(v_reportday.getBegin_date()==null && v_reportday.getEnd_date()==null&&v_reportday.getShengfen()==null&&v_reportday.getChengshi()==null&&v_reportday.getQuyufl()==null) {
            v_reportday.setBegin_date(tools.getTimeDay(0));
            v_reportday.setEnd_date(tools.getTimeDay(0));
            v_reportday.setShengfen("����ʡ");
            v_reportday.setChengshi("");
            v_reportday.setQuyufl(" �ϼ�");
        }

        ModelAndView mav = new ModelAndView("reportday");

        PageModel pageModel = new PageModel();
        System.out.println("getPageIndex = " + pageModel.getPageIndex());
        System.out.println("getPageSize = " + pageModel.getPageSize());
        System.out.println("getRecordCount = " + pageModel.getRecordCount());
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }


        List<ReportDay> reportday =dataService.findReportDay(v_reportday, pageModel);
        mav.addObject("reportday", reportday);
        mav.addObject("pageModel", pageModel);
        mav.addObject("reportday_con", v_reportday);   //��д��ѯ����




        return mav;
    }



}
