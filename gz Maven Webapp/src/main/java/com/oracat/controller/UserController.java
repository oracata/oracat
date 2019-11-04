package com.oracat.controller;

import com.oracat.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
       @RequestMapping(value="/loginform")
       public ModelAndView login(String loginname,
                                 String password,
                                 ModelAndView mv,
                                 HttpSession session)
       {
           if(loginname!=null&&loginname.equals("admin")
              && password!=null &&password.equals("1qaz@WSX"))
           {
               User user=new User();
               user.setUserName(loginname);
               user.setUserPassword(password);
               session.setMaxInactiveInterval(30); //超时时间30s
               session.setAttribute("user",user);
            mv.setViewName("redirect:index");

           }
           else{
               mv.addObject("message","输入错误！");
               mv.setViewName("loginform");
           }

           return  mv;
       }
}
