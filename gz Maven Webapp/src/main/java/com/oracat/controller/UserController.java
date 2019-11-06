package com.oracat.controller;

import com.oracat.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @RequestMapping("/loginform")
    public ModelAndView login(Model model,HttpSession session ,@ModelAttribute User user)
       {
           ModelAndView mv = new ModelAndView();
           System.out.println("��ʼ��֤�û���������");
           System.out.println("name:"+user.getUserName());
           System.out.println("password:"+user.getUserPassword());
           if(user.getUserName()!=null&&user.getUserName().equals("aaa")
              && user.getUserPassword()!=null &&user.getUserPassword().equals("aaa"))
           {
               User user_session=new User();
               user_session.setUserName(user.getUserName());
               user_session.setUserPassword( user.getUserPassword());
               session.setMaxInactiveInterval(600); //��ʱʱ��600s=10����
               session.setAttribute("user_session",user_session);
            mv.setViewName("main");

           }
           else{
               mv.addObject("message","�������");
               mv.setViewName("loginform");
           }

           return  mv;
       }
}
