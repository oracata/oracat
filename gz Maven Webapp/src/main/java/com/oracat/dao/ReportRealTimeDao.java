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




        @Select("select * from report_b2b_data_realtime where shengfen='����ʡ' AND chengshi=' �ϼ�' order by rq ")
        List<RealTime> selectRealTime();



        @Select("select " +
                "CASE WHEN chengshi='��������������' THEN '����'\n" +
                "       WHEN chengshi='�������������' THEN '����'\n" +
                "       WHEN chengshi='�º���徰����������' THEN '�º�'\n" +
                "       WHEN chengshi='�������������' THEN '����'\n" +
                "       WHEN chengshi='��ӹ���������������' THEN '���'\n" +
                "       WHEN chengshi='˼é����' THEN '�ն�'\n" +
                "       WHEN chengshi='��ɽ׳������������' THEN '��ɽ'\n" +
                "       WHEN chengshi='��˫���ɴ���������' THEN '����' \n" +
                " when chengshi='��ɽ��'     then   '��ɽ'\n" +
                " when chengshi='��ͨ��'     then   '��ͨ'\n" +
                " when chengshi='��ɽ��'     then   '��ɽ'\n" +
                " when chengshi='�ٲ���'     then   '�ٲ�'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�ն���'     then   '�ն�'\n" +
                " when chengshi='��˫������' then   '����'\n" +
                " when chengshi='��Ϫ��'     then   '��Ϫ'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�����'     then   '���'\n" +
                " when chengshi='ŭ����'     then   'ŭ��'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='������'     then   '����'\n" +
                " when chengshi='�º���'     then   '�º�'\n"+
                "  else   '����'  END  AS chengshi \n"+
                ",order_pay_price from report_b2b_data_realtime where shengfen='����ʡ' AND quyufl=' �ϼ�' AND chengshi<>' �ϼ�' order by rq ")
        List<RealTime> selectArea();



        @Select("SELECT state_code,state,COUNT(*) num FROM (\n" +
                "SELECT a.rq,a.djbh,\n" +
                "case when a.is_wms='��'\n" +
                "     then (case when a.handle=-1 then '��������ʧ��'\n" +
                "                    when x.instruct_state=9 then 'WMSָ��ȡ��,�����޸ĵ���'\n" +
                "\t\t      when a.handle<>9 then 'δ��������'\n" +
                "\t\t      when x.instruct_state is null then '���ݵȴ�������WMS'\n" +
                "\t\t\t  when x.instruct_state=0 then '�����Ѵ���WMS,�ȴ�����'\n" +
                "\t\t\t  when x.instruct_state=1 then '���ڼ����'\n" +
                "\t\t\t  when x.instruct_state=2 then '׼������'\n" +
                "\t\t\t  when x.instruct_state=3 then '�ѳ����'\n" +
                "\t\t\t   WHEN x.Instruct_State=10 THEN '��������'\n" +
                "\t\t\t  else '' end)\n" +
                "     else\n" +
                "\t  (case when a.handle=-1  then '��������ʧ��'\n" +
                "\n" +
                "\t        when a.is_zx='��' then '׼������'\n" +
                "\t\t\twhen a.is_zx='��' then '�ѳ����'\n" +
                "\t\t\t WHEN c.shenhe='��' AND a.djbh IS NULL AND c.is_zx='��' AND c.wldwid<>'WLD00005953' THEN '֧��δ��Ʊ'\n" +
                "\t\t\telse ''end)\n" +
                "\t end as state,\n" +
                "\t case when a.is_wms='��'\n" +
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
                "\t        when a.is_zx='��' then '7'\n" +
                "\t\t\twhen a.is_zx='��' then '8'\n" +
                "\t\t\t WHEN c.shenhe='��' AND a.djbh IS NULL AND c.is_zx='��' AND c.wldwid<>'WLD00005953' THEN '0'\n" +
                "\t\t\telse ''end)\n" +
                "\t end as state_code \n" +
                "\n" +
                " \n" +
                "FROM b_gxddhz c  \n" +
                "LEFT JOIN gxkphz a (nolock) ON c.order_id=a.dsdjbh  \n" +
                "and  a.djbh like 'XHB%'\n" +
                "and a.is_zx <> '��' and a.jigid='000' AND  a.dsdjbh<>''\n" +
                " LEFT join wldwzl b(nolock) on b.wldwid=a.wldwid\n" +
                "LEFT join bmzl e(nolock) on a.bmid=e.bmid\n" +
                "left join wms_systeminstruct_state x (nolock) on a.djbh=x.instruct_djbh\n" +
                "INNER JOIN openquery(b2b,'select * from   order_for_goods') f ON c.order_id=f.id AND f.is_pay=1 AND f.return_state=0 \n" +
                ") a WHERE  state_code <>''\n" +
                "GROUP BY state,STATE_code  ORDER BY state_code;\n ")
        List<SaleFlow> selectSaleFlow();

    @Select("SELECT state_code,state,COUNT(*) num FROM (\n" +
            "SELECT a.rq,a.djbh,\n" +
            "case when a.is_wms='��'\n" +
            "     then (case when a.handle=-1 then '��������ʧ��'\n" +
            "                    when x.instruct_state=9 then 'WMSָ��ȡ��,�����޸ĵ���'\n" +
            "\t\t      when a.handle<>9 then 'δ��������'\n" +
            "\t\t      when x.instruct_state is null then '���ݵȴ�������WMS'\n" +
            "\t\t\t  when x.instruct_state=0 then '�����Ѵ���WMS,�ȴ�����'\n" +
            "\t\t\t  when x.instruct_state=1 then '���ڼ����'\n" +
            "\t\t\t  when x.instruct_state=2 then '׼������'\n" +
            "\t\t\t  when x.instruct_state=3 then '�ѳ����'\n" +
            "\t\t\t   WHEN x.Instruct_State=10 THEN '��������'\n" +
            "\t\t\t  else '' end)\n" +
            "     else\n" +
            "\t  (case when a.handle=-1  then '��������ʧ��'\n" +
            "\n" +
            "\t        when a.is_zx='��' then '׼������'\n" +
            "\t\t\twhen a.is_zx='��' then '�ѳ����'\n" +
            "\t\t \n" +
            "\t\t\telse ''end)\n" +
            "\t end as state,\n" +
            "\t case when a.is_wms='��'\n" +
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
            "\t        when a.is_zx='��' then '7'\n" +
            "\t\t\twhen a.is_zx='��' then '8'\n" +
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
            "and a.is_zx <> '��' and a.jigid='000'  AND a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��')\n" +
            ") a\n" +
            "GROUP BY state,STATE_code  ORDER BY state_code;\n ")
        List<SaleFlow> selectSaleFlowErp();


    //�ͻ��ڱ���
   //khjactive

    @Select("select " +
            "CASE WHEN chengshi='��������������' THEN '����'\n" +
            "       WHEN chengshi='�������������' THEN '����'\n" +
            "       WHEN chengshi='�º���徰����������' THEN '�º�'\n" +
            "       WHEN chengshi='�������������' THEN '����'\n" +
            "       WHEN chengshi='��ӹ���������������' THEN '���'\n" +
            "       WHEN chengshi='˼é����' THEN '�ն�'\n" +
            "       WHEN chengshi='��ɽ׳������������' THEN '��ɽ'\n" +
            "       WHEN chengshi='��˫���ɴ���������' THEN '����' \n" +
            " when chengshi='��ɽ��'     then   '��ɽ'\n" +
            " when chengshi='��ͨ��'     then   '��ͨ'\n" +
            " when chengshi='��ɽ��'     then   '��ɽ'\n" +
            " when chengshi='�ٲ���'     then   '�ٲ�'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='�ն���'     then   '�ն�'\n" +
            " when chengshi='��˫������' then   '����'\n" +
            " when chengshi='��Ϫ��'     then   '��Ϫ'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='�����'     then   '���'\n" +
            " when chengshi='ŭ����'     then   'ŭ��'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='������'     then   '����'\n" +
            " when chengshi='�º���'     then   '�º�'\n"+
            "  else   '����'  END  AS chengshi \n"+
            ",custom_num,login_pay_custom+login_nopay_custom login_num ,round((login_pay_custom+login_nopay_custom)*1.00*100/custom_num,2)  login_rate\n" +
            "  from report_b2b_data_realtime where shengfen='����ʡ' AND quyufl=' �ϼ�' AND chengshi<>' �ϼ�' \n" +
            "and rq IN (SELECT MAX(rq) FROM report_b2b_data_realtime) ")
    List<RealTime> selectActiveArea();




    @Select("select " +
        "rq \n"+
            ",login_pay_custom,login_nopay_custom ,login_pay_custom+login_nopay_custom login_num \n" +
            "  from report_b2b_data where  shengfen='����ʡ' and  quyufl=' �ϼ�' AND chengshi=' �ϼ�' \n" +
            "and rq between  '${begin_date}' and '${end_date}' order by rq ")
    List<RealTime> selectActiveLogin(@Param("begin_date") String begin_date,
                                     @Param("end_date") String end_date);



    @Select("   SELECT  chengshi, SUM(case when order_type=1 then order_pay_price ELSE 0 END ) offline_price, SUM(case when order_type=0 then order_pay_price ELSE 0 END ) online_price FROM report_b2b_order_detail  WHERE  \n" +
            "           rq between  '${begin_date}' and '${end_date}' and   order_type IN (1,0) AND shengfen='����ʡ' GROUP BY  chengshi    ORDER BY   SUM(case when order_type=0 then order_pay_price ELSE 0 END ) DESC \n  ")
    List<Order> selectOrderNum(@Param("begin_date") String begin_date,
                               @Param("end_date") String end_date);



    @Select("    SELECT top 10 wldwname  ,SUM(order_pay_price) order_pay_price \n" +
            "           FROM report_b2b_order_detail  WHERE  rq between  '${begin_date}' and '${end_date}'\n" +
            "            and order_type IN (0) AND shengfen='����ʡ'  GROUP BY  wldwname  ORDER BY order_pay_price DESC  \n  ")
    List<Order> selectOrderTop10(@Param("begin_date") String begin_date,
                               @Param("end_date") String end_date);


    @Select("SELECT    case when ms_flag=1 THEN '������ɱ��Ʒ' ELSE  '������ɱ��Ʒ' end  ms ,SUM(order_pay_price) order_pay_price \n" +
            "FROM report_b2b_order_detail WHERE rq   between  '${begin_date}' and '${end_date}'\n" +
            "and order_type IN (0) AND shengfen='����ʡ' GROUP BY  case when ms_flag=1 THEN '������ɱ��Ʒ' ELSE  '������ɱ��Ʒ' end  ORDER BY order_pay_price DESC \n  ")
    List<Order> selectOrderMs(@Param("begin_date") String begin_date,
                              @Param("end_date") String end_date);


    @Select("SELECT    case when order_type=0 THEN '����' ELSE  '����' end  line ,SUM(order_pay_price) order_pay_price \n" +
            "FROM report_b2b_order_detail WHERE rq   between  '${begin_date}' and '${end_date}'\n" +
            "and order_type IN (0,1) AND shengfen='����ʡ' GROUP BY   case when order_type=0 THEN '����' ELSE  '����' end   ORDER BY order_pay_price DESC  ")
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

    @Select("SELECT    case when ms_flag=1 THEN '��ɱ��Ʒ' ELSE  '����ɱ��Ʒ' end  ms ,SUM(order_pay_price) order_pay_price \n" +
            ",SUM(order_pay_price) order_pay_price FROM report_b2b_goods_detail a "+
            "WHERE  "+
            "         a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "GROUP BY\n" +
            "    case when ms_flag=1 THEN '��ɱ��Ʒ' ELSE  '����ɱ��Ʒ' end     \n" +
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
