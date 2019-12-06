package com.oracat.controller;

import com.oracat.model.ErpCustom;
import com.oracat.model.GoodsForYz;
import com.oracat.service.DataService;
import com.oracat.util.tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
@Controller
public class ErpCustomController {

    @Resource
    private DataService dataService;

    @RequestMapping("/erpcustom")
    public ModelAndView getErpCustom(Model model,   String flag,
                                     @ModelAttribute ErpCustom erpcustom_v)
    {   ModelAndView mav = new ModelAndView("erpcustom");
           if(flag!=null) {

               List<ErpCustom> erpcustom = dataService.selectErpCustom(tools.getTimeDay(-365), tools.getTimeDay(0));
               mav.addObject("erpcustom", erpcustom);
               mav.addObject("erpcustom_con", erpcustom_v);
               mav.addObject("flag", flag);
               System.out.println("********flag:"+flag);
           }else
           {
               List<ErpCustom> erpcustom = dataService.selectErpCustom(tools.getTimeDay(-365), tools.getTimeDay(0));
               mav.addObject("erpcustom", erpcustom);
               mav.addObject("erpcustom_con", erpcustom_v);

           }

        return mav;

    }

    @RequestMapping(value="/updateerpcustom")
    public ModelAndView updateErpCustom(Model model,
                                         @ModelAttribute ErpCustom erpcustom,
                                         ModelAndView mv){

            int flag=0;
           if(erpcustom.getWldwid()!=""&&erpcustom.getWldwid()!=null) {
               // 执行修改操作

               flag=dataService.modifyErpCustom(erpcustom);
               System.out.println("###########flag:"+flag);
           }
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/erpcustom.do?flag='"+flag+"'");

        return mv;
    }



    @RequestMapping("/fgscustom")
    public ModelAndView getFgsCustom()
    {
        ModelAndView mav = new ModelAndView("fgscustom");
        List<ErpCustom> fgscustom =dataService.selectFgsCustom();
        mav.addObject("fgscustom",fgscustom);

        return mav;

    }
}
