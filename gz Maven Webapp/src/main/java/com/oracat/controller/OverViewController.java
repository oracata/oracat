package com.oracat.controller;
 
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oracat.model.User;
import  com.oracat.service.UserService;



@Controller  
public class OverViewController {  

    @Resource  
    private UserService userService; 

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
        User user = userService.selectUserById(1); 
        mav.addObject("user", user); 
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