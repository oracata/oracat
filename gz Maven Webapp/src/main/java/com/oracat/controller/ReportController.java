package com.oracat.controller;

import com.oracat.model.B2bPrice;
import com.oracat.model.Goods;
import com.oracat.model.ReportDay;
import com.oracat.model.ReportYear;
import com.oracat.service.DataService;
import com.oracat.util.ToJson;
import com.oracat.util.tag.PageModel;
import com.oracat.util.tools;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReportController {
    @Resource
    private DataService dataService;




    @RequestMapping("/reportday")
    public ModelAndView getReportDayView(Model model, Integer pageIndex,
                                       @ModelAttribute ReportDay v_reportday){

        //初始化查询条件
        if(v_reportday.getBegin_date()==null && v_reportday.getEnd_date()==null&&v_reportday.getShengfen()==null&&v_reportday.getChengshi()==null&&v_reportday.getQuyufl()==null) {
            v_reportday.setBegin_date(tools.getTimeDay(0));
            v_reportday.setEnd_date(tools.getTimeDay(0));
            v_reportday.setShengfen("云南省");
            v_reportday.setChengshi("");
            v_reportday.setQuyufl(" 合计");
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
        mav.addObject("reportday_con", v_reportday);   //回写查询条件




        return mav;
    }




    @RequestMapping("/selectAllProvince")
    @ResponseBody
    //挑选全部的省份信息
    public void selectAll(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("typeId") int typeId) {
        List<String> list=new ArrayList<String>();
        try {
            list=dataService.selectShengfen();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToJson.toJson(list, request, response);
    }

    @RequestMapping("/selectAllCityProvince")
    @ResponseBody
    //挑选全部的城市信息
    public void selectAllCity(HttpServletRequest request,HttpServletResponse response,
                              @RequestParam("provinceId") String provinceId) {
        List<String> list=new ArrayList<String>();
        try {
            list=dataService.selectChengshi(provinceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToJson.toJson(list, request, response);
    }

    @RequestMapping("/selectAllAreaProvince")
    @ResponseBody
    //挑选全部的区信息
    public void selectAllArea(HttpServletRequest request,HttpServletResponse response
                              ,@RequestParam("provinceId") String provinceId,@RequestParam("cityId") String cityId) {
        List<String> list=new ArrayList<String>();
        try {

            list=dataService.selectQuyufl(provinceId,cityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);
        ToJson.toJson(list, request, response);
    }





    @RequestMapping("/reportyear")
    public ModelAndView getReportYearView(){


        ModelAndView mav = new ModelAndView("reportyear");



        List<ReportYear> reportyear =dataService.selectReportYear("","");
        mav.addObject("reportyear", reportyear);

        return mav;
    }


    @RequestMapping("/exportreportyear")
    public void getExport( HttpServletRequest request, HttpServletResponse response)
            throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {

        response.reset(); //清除buffer缓存
        Map<String,Object> map=new HashMap<String,Object>();


        // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
        // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
        //文件名
        String filename="年报"+tools.getTimeDay(0)+".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename=" +new String(filename.getBytes("GBK"),"ISO-8859-1"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        XSSFWorkbook workbook=null;

        workbook = dataService.exportReportYear();
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
