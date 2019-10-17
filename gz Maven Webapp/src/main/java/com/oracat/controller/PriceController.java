package com.oracat.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracat.model.Goods;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.DataService;
import com.oracat.util.tag.PageModel;

public class PriceController {

	
    @Resource
    private DataService dataService; 
    
    
    
  
    
 @RequestMapping("/overview")    
 public ModelAndView getOverView(){      
     ModelAndView mav = new ModelAndView("overview"); 
     List<OverViewCatagory> overViewCatagory = dataService.selectOverViewCatagory();
     mav.addObject("overViewCatagory", overViewCatagory); 
     return mav; 
 }  
	
}
