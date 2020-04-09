package com.oracat.dao;

import com.oracat.model.Order;
import com.oracat.model.RealTime;
import com.oracat.model.SaleFlow;
import com.oracat.model.Sp;
import com.oracat.util.DynamicDataSourceHolder;
import com.oracat.util.tools;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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



        @Select("SELECT state_code,state,COUNT(*) num FROM (\n" +
                "SELECT a.rq,a.djbh,\n" +
                "case when a.is_wms='是'\n" +
                "     then (case when a.handle=-1 then '分配批号失败'\n" +
                "                    when x.instruct_state=9 then 'WMS指令取消,允许修改单据'\n" +
                "\t\t      when a.handle<>9 then '未分配批号'\n" +
                "\t\t      when x.instruct_state is null then '单据等待传送至WMS'\n" +
                "\t\t\t  when x.instruct_state=0 then '单据已传送WMS,等待波次'\n" +
                "\t\t\t  when x.instruct_state=1 then '正在拣货中'\n" +
                "\t\t\t  when x.instruct_state=2 then '准备出库'\n" +
                "\t\t\t  when x.instruct_state=3 then '已出库打单'\n" +
                "\t\t\t   WHEN x.Instruct_State=10 THEN '整单差异'\n" +
                "\t\t\t  else '' end)\n" +
                "     else\n" +
                "\t  (case when a.handle=-1  then '分配批号失败'\n" +
                "\n" +
                "\t        when a.is_zx='否' then '准备出库'\n" +
                "\t\t\twhen a.is_zx='是' then '已出库打单'\n" +
                "\t\t\t WHEN c.shenhe='是' AND a.djbh IS NULL AND c.is_zx='否' AND c.wldwid<>'WLD00005953' THEN '支付未开票'\n" +
                "\t\t\telse ''end)\n" +
                "\t end as state,\n" +
                "\t case when a.is_wms='是'\n" +
                "     then (case when a.handle=-1 then '2'\n" +
                "                    when x.instruct_state=9 then '3'\n" +
                "\t\t      when a.handle<>9 then '1'\n" +
                "\t\t      when x.instruct_state is null then '4'\n" +
                "\t\t\t  when x.instruct_state=0 then '5'\n" +
                "\t\t\t  when x.instruct_state=1 then '6'\n" +
                "\t\t\t  when x.instruct_state=2 then '7'\n" +
                "\t\t\t  when x.instruct_state=3 then '8'\n" +
                "\t\t\t   WHEN x.Instruct_State=10 THEN '9'\n" +
                "\t\t\t  else '' end)\n" +
                "     else\n" +
                "\t  (case when a.handle=-1  then '2'\n" +
                "\n" +
                "\t        when a.is_zx='否' then '7'\n" +
                "\t\t\twhen a.is_zx='是' then '8'\n" +
                "\t\t\t WHEN c.shenhe='是' AND a.djbh IS NULL AND c.is_zx='否' AND c.wldwid<>'WLD00005953' THEN '0'\n" +
                "\t\t\telse ''end)\n" +
                "\t end as state_code \n" +
                "\n" +
                " \n" +
                "FROM b_gxddhz c  \n" +
                "LEFT JOIN gxkphz a (nolock) ON c.order_id=a.dsdjbh  \n" +
                "and  a.djbh like 'XHB%'\n" +
                "and a.is_zx <> '清' and a.jigid='000' AND  a.dsdjbh<>''\n" +
                " LEFT join wldwzl b(nolock) on b.wldwid=a.wldwid\n" +
                "LEFT join bmzl e(nolock) on a.bmid=e.bmid\n" +
                "left join wms_systeminstruct_state x (nolock) on a.djbh=x.instruct_djbh\n" +
                "INNER JOIN openquery(b2b,'select * from   order_for_goods') f ON c.order_id=f.id AND f.is_pay=1 AND f.return_state=0 \n" +
                ") a WHERE  state_code <>''\n" +
                "GROUP BY state,STATE_code  ORDER BY state_code;\n ")
        List<SaleFlow> selectSaleFlow();

    @Select("SELECT state_code,state,COUNT(*) num FROM (\n" +
            "SELECT a.rq,a.djbh,\n" +
            "case when a.is_wms='是'\n" +
            "     then (case when a.handle=-1 then '分配批号失败'\n" +
            "                    when x.instruct_state=9 then 'WMS指令取消,允许修改单据'\n" +
            "\t\t      when a.handle<>9 then '未分配批号'\n" +
            "\t\t      when x.instruct_state is null then '单据等待传送至WMS'\n" +
            "\t\t\t  when x.instruct_state=0 then '单据已传送WMS,等待波次'\n" +
            "\t\t\t  when x.instruct_state=1 then '正在拣货中'\n" +
            "\t\t\t  when x.instruct_state=2 then '准备出库'\n" +
            "\t\t\t  when x.instruct_state=3 then '已出库打单'\n" +
            "\t\t\t   WHEN x.Instruct_State=10 THEN '整单差异'\n" +
            "\t\t\t  else '' end)\n" +
            "     else\n" +
            "\t  (case when a.handle=-1  then '分配批号失败'\n" +
            "\n" +
            "\t        when a.is_zx='否' then '准备出库'\n" +
            "\t\t\twhen a.is_zx='是' then '已出库打单'\n" +
            "\t\t \n" +
            "\t\t\telse ''end)\n" +
            "\t end as state,\n" +
            "\t case when a.is_wms='是'\n" +
            "     then (case when a.handle=-1 then '2'\n" +
            "                    when x.instruct_state=9 then '3'\n" +
            "\t\t      when a.handle<>9 then '1'\n" +
            "\t\t      when x.instruct_state is null then '4'\n" +
            "\t\t\t  when x.instruct_state=0 then '5'\n" +
            "\t\t\t  when x.instruct_state=1 then '6'\n" +
            "\t\t\t  when x.instruct_state=2 then '7'\n" +
            "\t\t\t  when x.instruct_state=3 then '8'\n" +
            "\t\t\t   WHEN x.Instruct_State=10 THEN '9'\n" +
            "\t\t\t  else '' end)\n" +
            "     else\n" +
            "\t  (case when a.handle=-1  then '2'\n" +
            "\n" +
            "\t        when a.is_zx='否' then '7'\n" +
            "\t\t\twhen a.is_zx='是' then '8'\n" +
            "\t\t\t \n" +
            "\t\t\telse ''end)\n" +
            "\t end as state_code \n" +
            "\n" +
            " \n" +
            "FROM  gxkphz a (nolock)  \n" +
            " inner join wldwzl b(nolock) on b.wldwid=a.wldwid\n" +
            "inner join bmzl e(nolock) on a.bmid=e.bmid\n" +
            "left join wms_systeminstruct_state x (nolock) on a.djbh=x.instruct_djbh\n" +
            "where a.djbh like 'XHB%'\n" +
            "and a.is_zx <> '清' and a.jigid='000'  AND a.bmname IN ('电商事业部','终端普药事业部')\n" +
            ") a\n" +
            "GROUP BY state,STATE_code  ORDER BY state_code;\n ")
        List<SaleFlow> selectSaleFlowErp();


    //客户节报表
   //khjactive

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
            ",custom_num,login_pay_custom+login_nopay_custom login_num ,round((login_pay_custom+login_nopay_custom)*1.00*100/custom_num,2)  login_rate\n" +
            "  from report_b2b_data_realtime where shengfen='云南省' AND quyufl=' 合计' AND chengshi<>' 合计' \n" +
            "and rq IN (SELECT MAX(rq) FROM report_b2b_data_realtime) ")
    List<RealTime> selectActiveArea();




    @Select("select " +
        "rq \n"+
            ",login_pay_custom,login_nopay_custom ,login_pay_custom+login_nopay_custom login_num \n" +
            "  from report_b2b_data where  shengfen='云南省' and  quyufl=' 合计' AND chengshi=' 合计' \n" +
            "and rq between  '${begin_date}' and '${end_date}' order by rq ")
    List<RealTime> selectActiveLogin(@Param("begin_date") String begin_date,
                                     @Param("end_date") String end_date);



    @Select("   SELECT  chengshi, SUM(case when order_type=1 then order_pay_price ELSE 0 END ) offline_price, SUM(case when order_type=0 then order_pay_price ELSE 0 END ) online_price FROM report_b2b_order_detail  WHERE  \n" +
            "           rq between  '${begin_date}' and '${end_date}' and   order_type IN (1,0) AND shengfen='云南省' GROUP BY  chengshi    ORDER BY   SUM(case when order_type=0 then order_pay_price ELSE 0 END ) DESC \n  ")
    List<Order> selectOrderNum(@Param("begin_date") String begin_date,
                               @Param("end_date") String end_date);



    @Select("    SELECT top 10 wldwname  ,SUM(order_pay_price) order_pay_price \n" +
            "           FROM report_b2b_order_detail  WHERE  rq between  '${begin_date}' and '${end_date}'\n" +
            "            and order_type IN (0) AND shengfen='云南省'  GROUP BY  wldwname  ORDER BY order_pay_price DESC  \n  ")
    List<Order> selectOrderTop10(@Param("begin_date") String begin_date,
                               @Param("end_date") String end_date);


    @Select("SELECT    case when ms_flag=1 THEN '含有秒杀商品' ELSE  '不含秒杀商品' end  ms ,SUM(order_pay_price) order_pay_price \n" +
            "FROM report_b2b_order_detail WHERE rq   between  '${begin_date}' and '${end_date}'\n" +
            "and order_type IN (0) AND shengfen='云南省' GROUP BY  case when ms_flag=1 THEN '含有秒杀商品' ELSE  '不含秒杀商品' end  ORDER BY order_pay_price DESC \n  ")
    List<Order> selectOrderMs(@Param("begin_date") String begin_date,
                              @Param("end_date") String end_date);


    @Select("SELECT    case when order_type=0 THEN '线上' ELSE  '线下' end  line ,SUM(order_pay_price) order_pay_price \n" +
            "FROM report_b2b_order_detail WHERE rq   between  '${begin_date}' and '${end_date}'\n" +
            "and order_type IN (0,1) AND shengfen='云南省' GROUP BY   case when order_type=0 THEN '线上' ELSE  '线下' end   ORDER BY order_pay_price DESC  ")
    List<Order> selectOrderLine(@Param("begin_date") String begin_date,
                              @Param("end_date") String end_date);




    @Select( "SELECT top 10    b.spmch+'|'+b.shpgg+'|'+b.shengccj  spmch \n" +
            ",SUM(order_pay_price) order_pay_price FROM report_b2b_goods_detail a INNER JOIN spzl b  ON a.spid=b.spid "+
            "WHERE  "+
            "         a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "GROUP BY\n" +
            "      b.spmch+'|'+b.shpgg+'|'+b.shengccj     \n" +
            "ORDER BY  SUM(order_pay_price)  DESC "  )
    List<Sp> selectSp(@Param("begin_date") String begin_date,
                           @Param("end_date") String end_date);

    @Select("SELECT    case when ms_flag=1 THEN '秒杀商品' ELSE  '非秒杀商品' end  ms ,SUM(order_pay_price) order_pay_price \n" +
            ",SUM(order_pay_price) order_pay_price FROM report_b2b_goods_detail a "+
            "WHERE  "+
            "         a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "GROUP BY\n" +
            "    case when ms_flag=1 THEN '秒杀商品' ELSE  '非秒杀商品' end     \n" +
            "ORDER BY  SUM(order_pay_price)  DESC "  )
    List<Sp> selectSpMs(@Param("begin_date") String begin_date,
                      @Param("end_date") String end_date);


    @Select( "SELECT top 10    b.spmch+'|'+b.shpgg+'|'+b.shengccj  spmch \n" +
            ",SUM(order_pay_price) order_pay_price FROM report_b2b_goods_detail a INNER JOIN spzl b  ON a.spid=b.spid "+
            "WHERE  "+
            "         a.rq BETWEEN '${begin_date}' AND '${end_date}'  AND a.ms_flag=1\n" +
            "GROUP BY\n" +
            "      b.spmch+'|'+b.shpgg+'|'+b.shengccj     \n" +
            "ORDER BY  SUM(order_pay_price)  DESC "  )
    List<Sp> selectSpMsTop10(@Param("begin_date") String begin_date,
                      @Param("end_date") String end_date);



}
