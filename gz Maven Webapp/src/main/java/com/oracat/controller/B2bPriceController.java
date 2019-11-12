package com.oracat.controller;

import com.oracat.model.B2bPrice;
import com.oracat.model.Goods;
import com.oracat.model.RealTime;
import com.oracat.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
}
