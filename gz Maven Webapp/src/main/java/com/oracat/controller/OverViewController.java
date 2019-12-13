package com.oracat.controller;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracat.model.User;
import  com.oracat.service.UserService;
import com.oracat.util.tag.PageModel;
import com.oracat.model.Goods;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.OverViewCatagoryService;
import com.oracat.service.DataService;



@Controller  
public class OverViewController {  

    @Resource  
    private UserService userService; 
    
    @Resource
    private OverViewCatagoryService overViewCatagoryService; 
    
    @Resource
    private DataService dataService; 

    @RequestMapping("/")    
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("main");
        User user = userService.selectUserById(1); 
        mav.addObject("user", user); 
        return mav; 
    }    


    @RequestMapping("/menu")    
    public ModelAndView getMenu(){      
        ModelAndView mav = new ModelAndView("menu"); 
        User user = userService.selectUserById(1); 
        mav.addObject("user", user); 
        return mav; 
    }   
    
 
       
    @RequestMapping("/overview")    
    public ModelAndView getOverView(){      
        ModelAndView mav = new ModelAndView("overview"); 
        List<OverViewCatagory> overViewCatagory = overViewCatagoryService.selectOverViewCatagory();
        List<Map<String,Integer>> fenlei = overViewCatagoryService.selectYzFenlei();
        List<Map<String,Integer>> jndfl = overViewCatagoryService.selectJndfl();
        mav.addObject("overViewCatagory", overViewCatagory);
        mav.addObject("Fenlei", fenlei);
        mav.addObject("jndfl", jndfl);
        return mav; 
    }  
    
    @RequestMapping("/yz_overview")    
    public ModelAndView getYzOverView(){      
        ModelAndView mav = new ModelAndView("yz_overview"); 
        User user = userService.selectUserById(1); 
        mav.addObject("user", user); 
        return mav; 
    }  
    
  
    @RequestMapping("/dc_goods")    
    public ModelAndView getDcGoodsView(Model model,Integer pageIndex,
			 @ModelAttribute Goods goods){      
        ModelAndView mav = new ModelAndView("dc_goods");
        
    	PageModel pageModel = new PageModel();
		System.out.println("getPageIndex = " + pageModel.getPageIndex());
		System.out.println("getPageSize = " + pageModel.getPageSize());
		System.out.println("getRecordCount = " + pageModel.getRecordCount());
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		
		
		List<Goods> dc_goods =dataService.findDcGoods(goods, pageModel);
   	    mav.addObject("dc_goods", dc_goods);
   	    mav.addObject("pageModel", pageModel);
   	    mav.addObject("goods_con", goods);   //回写查询条件
   	 
		
		/*
		
        if(goods.getDate()!= null )   {
        	List<Goods> dc_goods =dataService.findDcGoods(goods, pageModel);
        	 mav.addObject("dc_goods", dc_goods);
        	 mav.addObject("pageModel", pageModel);
        }
        else {
        List<Goods> dc_goods =dataService.findAllDcGoods(tools.getTimeDay(-1));
        mav.addObject("dc_goods", dc_goods);
        }
         
         */
        return mav; 
    }  
    
}  