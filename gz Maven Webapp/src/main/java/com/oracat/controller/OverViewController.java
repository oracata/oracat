package com.oracat.controller;
 
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller  
public class OverViewController {  

    //@Resource  
    //private UserService userService; 

    @RequestMapping("/overview")    
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("overview"); 
    //    User user = userService.selectUserById(1); 
        mav.addObject("user", "dfsadfsdf"); 
        return mav; 
    }    
}  