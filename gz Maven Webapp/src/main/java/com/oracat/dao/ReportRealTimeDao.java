package com.oracat.dao;

import com.oracat.model.RealTime;
import com.oracat.util.DynamicDataSourceHolder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportRealTimeDao {




        @Select("select * from report_b2b_data_realtime where shengfen='云南省' AND chengshi=' 合计' order by rq ")
        List<RealTime> selectRealTime();



        @Select("select " +
                "CASE WHEN chengshi='楚雄彝族自治州' THEN '楚雄'\n" +
                "       WHEN chengshi='大理白族自治州' THEN '大理'\n" +
                "       WHEN chengshi='德宏傣族景颇族自治州' THEN '德宏'\n" +
                "       WHEN chengshi='迪庆藏族自治州' THEN '迪庆'\n" +
                "       WHEN chengshi='红河哈尼族彝族自治州' THEN '红河'\n" +
                "       WHEN chengshi='思茅地区' THEN '普洱'\n" +
                "       WHEN chengshi='文山壮族苗族自治州' THEN '文山'\n" +
                "       WHEN chengshi='西双版纳傣族自治州' THEN '版纳' \n" +
                " when chengshi='文山州'     then   '文山'\n" +
                " when chengshi='昭通市'     then   '昭通'\n" +
                " when chengshi='保山市'     then   '保山'\n" +
                " when chengshi='临沧市'     then   '临沧'\n" +
                " when chengshi='楚雄州'     then   '楚雄'\n" +
                " when chengshi='曲靖市'     then   '曲靖'\n" +
                " when chengshi='普洱市'     then   '普洱'\n" +
                " when chengshi='西双版纳州' then   '版纳'\n" +
                " when chengshi='玉溪市'     then   '玉溪'\n" +
                " when chengshi='丽江市'     then   '丽江'\n" +
                " when chengshi='昆明市'     then   '昆明'\n" +
                " when chengshi='红河州'     then   '红河'\n" +
                " when chengshi='怒江州'     then   '怒江'\n" +
                " when chengshi='迪庆州'     then   '迪庆'\n" +
                " when chengshi='大理州'     then   '大理'\n" +
                " when chengshi='德宏州'     then   '德宏'\n"+
                "  else   '其它'  END  AS chengshi \n"+
                ",order_pay_price from report_b2b_data_realtime where shengfen='云南省' AND quyufl=' 合计' AND chengshi<>' 合计' order by rq ")
        List<RealTime> selectArea();


}
