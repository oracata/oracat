package com.oracat.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class OverViewController {
 
    @RequestMapping("/overview")
    public String overView(Model model) {         
        model.addAttribute("greeting", "xxxxx");         
        return"overview";         
    } 
}