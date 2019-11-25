package com.oracat.controller;

import com.oracat.model.GoodsForYz;
import com.oracat.service.DataService;
import com.oracat.util.ToJson;
import com.oracat.util.tools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GoodsForGoodsController {

    @Resource
    private DataService dataService;
    //对应视图目录/goodsforgoods/goodsforgoods  有url提交所以要使用get方法
    @RequestMapping(value = "/goodsforgoods/goodsforgoods",method = RequestMethod.GET)
    public ModelAndView getGoodsForGoods(Model model, @ModelAttribute GoodsForYz v_goodsforyz)
    {
        ModelAndView mav = new ModelAndView("/goodsforgoods/goods_for_goods");
        List<GoodsForYz> goodsforyz =dataService.selectGoodsForYz(v_goodsforyz);
        mav.addObject("goodsforyz",goodsforyz);
        mav.addObject("goodsforyz_con",v_goodsforyz);
        return mav;

    }


    /**
     * 处理修改部门请求
    // * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
    // * @param  GoodsForYz goodsforyz 要修改的对象
   //  * @param ModelAndView mv
     * */
    @RequestMapping(value="/goodsforgoods/updateGoodsForYz")
    public ModelAndView updateGoodsForYz(Model model,
            String flag, String id,
            @ModelAttribute GoodsForYz goodsforyz,
            ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询

            GoodsForYz target = dataService.findGoodsForYzById(id);
            // 设置Model数据
            mv.addObject("goodsforyz", target);
            // 设置跳转到修改页面
            mv.setViewName("goodsforgoods/update_goods_for_goods");
        }else{
            // 执行修改操作
            dataService.modifyGoodsForYz(goodsforyz);
            // 设置客户端跳转到查询请求
            mv.setViewName("/goodsforgoods/goods_for_goods");
        }
        // 返回
        return mv;
    }


    @RequestMapping(value="/goodsforgoods/addGoodsForYz")
    public ModelAndView addGoodsForYz(Model model,
                                         String flag,
                                         @ModelAttribute GoodsForYz goodsforyz,
                                         ModelAndView mv){
        if(flag.equals("1")){

            // 设置跳转到修改页面
            mv.setViewName("goodsforgoods/add_goods_for_goods");
        }else{
            // 执行修改操作
            dataService.addGoodsForYz(goodsforyz);
            // 设置客户端跳转到查询请求
            mv.setViewName("/goodsforgoods/goods_for_goods");
        }
        // 返回
        return mv;
    }


    @RequestMapping(value="/goodsforgoods/deleteGoodsForYz")
    public ModelAndView deleteGoodsForYz(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除部门
            dataService.removeGoodsForYzById(id);
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("/goodsforgoods/goods_for_goods");
        // 返回ModelAndView
        return mv;
    }



    /**
     * 增加记录 检索jnd_spid
     * **/
    @RequestMapping("/goodsforgoods/selectGoodsForYzNotin")
    @ResponseBody
    //挑选全部的省份信息
    public void selectGoodsForYzNotin(HttpServletRequest request, HttpServletResponse response
                         ) {
        List<Map<String,String>> list=new ArrayList<Map<String,String>>();
        try {
            list=dataService.findGoodsForYzNotin(tools.getTimeDay(-1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ToJson.toJson(list, request, response);
    }


}
