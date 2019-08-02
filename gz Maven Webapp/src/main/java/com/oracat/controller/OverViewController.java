package com.oracat.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class OverViewController {
 
    @RequestMapping("/overview")
    public String hello(Model model) {         
        model.addAttribute("greeting", "Hello Spring MVC");         
        return"helloworld";         
    } 
}