package com.oracat.controller;

import com.oracat.model.B2bPrice;
import com.oracat.model.Goods;
import com.oracat.model.RealTime;
import com.oracat.service.DataService;
import com.oracat.util.tools;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class B2bPriceController {
    @Resource
    private DataService dataService;


    @RequestMapping("/b2bprice")
    public ModelAndView getB2bPrice(Model model,@ModelAttribute B2bPrice v_b2bprice){

        ModelAndView mav = new ModelAndView("b2bprice");
        List<B2bPrice> b2bprice = dataService.selectB2bPrice(v_b2bprice);
        mav.addObject("b2bprice", b2bprice);
        mav.addObject("b2bprice_con", v_b2bprice);   //回写查询条件
        return mav;
    }


    @RequestMapping("/pricenotin")
    public ModelAndView getPriceNotin(){

        ModelAndView mav = new ModelAndView("pricenotin");
        List<B2bPrice> pricenotin = dataService.selectPriceNotin();
        mav.addObject("pricenotin", pricenotin);

        return mav;
    }


    @RequestMapping("/export")
    public void getExport(ModelMap model, @ModelAttribute B2bPrice v_b2bprice, HttpServletRequest request, HttpServletResponse response)
            throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {

            response.reset(); //清除buffer缓存
            Map<String,Object> map=new HashMap<String,Object>();


            // 指定下载的文件名，浏览器都会使用本地编码，即GBK，浏览器收到这个文件名后，用ISO-8859-1来解码，然后用GBK来显示
            // 所以我们用GBK解码，ISO-8859-1来编码，在浏览器那边会反过来执行。
            //文件名
            String filename="电商价格"+tools.getTimeDay(0)+".xlsx";
            response.setHeader("Content-Disposition", "attachment;filename=" +new String(filename.getBytes("GBK"),"ISO-8859-1"));
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            XSSFWorkbook workbook=null;

            workbook = dataService.exportExcelInfo(v_b2bprice);
            OutputStream output = null;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);

                bufferedOutPut.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        


    }
}
