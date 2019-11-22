package com.oracat.controller;

import com.oracat.model.GoodsForYz;
import com.oracat.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GoodsForGoodsController {

    @Resource
    private DataService dataService;
    //��Ӧ��ͼĿ¼/goodsforgoods/goodsforgoods  ��url�ύ����Ҫʹ��get����
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
     * �����޸Ĳ�������
    // * @param String flag ��ǣ� 1��ʾ��ת���޸�ҳ�棬2��ʾִ���޸Ĳ���
    // * @param  GoodsForYz goodsforyz Ҫ�޸ĵĶ���
   //  * @param ModelAndView mv
     * */
    @RequestMapping(value="/goodsforgoods/updateGoodsForYz")
    public ModelAndView updateGoodsForYz(Model model,
            String flag, String id,
            @ModelAttribute GoodsForYz goodsforyz,
            ModelAndView mv){
        if(flag.equals("1")){
            // ����id��ѯ

            GoodsForYz target = dataService.findGoodsForYzById(id);
            // ����Model����
            mv.addObject("goodsforyz", target);
            // ������ת���޸�ҳ��
            mv.setViewName("goodsforgoods/update_goods_for_goods");
        }else{
            // ִ���޸Ĳ���
            dataService.modifyGoodsForYz(goodsforyz);
            // ���ÿͻ�����ת����ѯ����
            mv.setViewName("/goodsforgoods/goods_for_goods");
        }
        // ����
        return mv;
    }


    @RequestMapping(value="/goodsforgoods/addGoodsForYz")
    public ModelAndView addGoodsForYz(Model model,
                                         String flag,
                                         @ModelAttribute GoodsForYz goodsforyz,
                                         ModelAndView mv){
        if(flag.equals("1")){

            // ������ת���޸�ҳ��
            mv.setViewName("goodsforgoods/add_goods_for_goods");
        }else{
            // ִ���޸Ĳ���
            dataService.addGoodsForYz(goodsforyz);
            // ���ÿͻ�����ת����ѯ����
            mv.setViewName("/goodsforgoods/goods_for_goods");
        }
        // ����
        return mv;
    }


    @RequestMapping(value="/goodsforgoods/deleteGoodsForYz")
    public ModelAndView deleteGoodsForYz(String ids,ModelAndView mv){
        // �ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // ����idɾ������
            dataService.removeGoodsForYzById(id);
        }
        // ���ÿͻ�����ת����ѯ����
        mv.setViewName("/goodsforgoods/goods_for_goods");
        // ����ModelAndView
        return mv;
    }

}
