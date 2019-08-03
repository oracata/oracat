package com.oracat.controller;
 
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracat.model.User;
import  com.oracat.service.UserService;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.OverViewCatagoryService;



@Controller  
public class OverViewController {  

    @Resource  
    private UserService userService; 
    private OverViewCatagoryService overViewCatagoryService;

    @RequestMapping("/")    
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("index"); 
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
        OverViewCatagory overViewCatagory = overViewCatagoryService.selectOverViewCatagory();
        mav.addObject("overViewCatagory", overViewCatagory); 
        return mav; 
    }  
    
    @RequestMapping("/yz_overview")    
    public ModelAndView getYzOverView(){      
        ModelAndView mav = new ModelAndView("yz_overview"); 
        User user = userService.selectUserById(1); 
        mav.addObject("user", user); 
        return mav; 
    }  
    
    
}  