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
    public ModelAndView getErpCustom()
    {
        ModelAndView mav = new ModelAndView("erpcustom");
        List<ErpCustom> erpcustom =dataService.selectErpCustom(tools.getTimeDay(-365),tools.getTimeDay(0));
        mav.addObject("erpcustom",erpcustom);

        return mav;

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
