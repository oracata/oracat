package com.oracat.controller;
 
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracat.model.User;
import  com.oracat.service.UserService;
import com.oracat.util.tools;
import com.oracat.util.tag.PageModel;
import com.oracat.model.Goods;
import com.oracat.model.OverViewCatagory;
import com.oracat.service.OverViewCatagoryService;
import com.oracat.service.DataService;



@Controller  
public class YzGoodsController {  

 
    
    @Resource
    private DataService dataService;




    @RequestMapping("/yz_goods")
    public ModelAndView getDcGoodsView(Model model,Integer pageIndex,
			 @ModelAttribute Goods goods){      
        ModelAndView mav = new ModelAndView("yz_goods");
        
    	PageModel pageModel = new PageModel();
		System.out.println("getPageIndex = " + pageModel.getPageIndex());
		System.out.println("getPageSize = " + pageModel.getPageSize());
		System.out.println("getRecordCount = " + pageModel.getRecordCount());
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		
		
		List<Goods> dc_goods =dataService.findYzGoods(goods, pageModel);
   	    mav.addObject("yz_goods", dc_goods);
   	    mav.addObject("pageModel", pageModel);
   	    mav.addObject("goods_con", goods);   //回写查询条件
   	 
		
 
        return mav; 
    }  
    
}  