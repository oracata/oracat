package com.oracat.dao;

import com.oracat.model.*;
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
            "         a.rq BETWEEN '${begin_date}' AND '${end_date}' and  ms_flag<>1\n" +
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






    @Select( " SELECT   \n" +
            "                CASE \n" +
            "                WHEN b.state=1 AND  b.stock_num=99999 THEN 0 \n" +
            "                WHEN b.state=1 AND  b.stock_num>0 and  b.stock_num<>99999 THEN 1 \n" +
            "                          WHEN b.state=1 AND  b.stock_num=0 THEN 2 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num>0 THEN 3 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num=0 THEN 4 \n" +
            "                             WHEN b.id IS NULL    THEN 5 \n" +
            "                           ELSE 6 END id , \n" +
            "               CASE \n" +
            "                WHEN b.state=1 AND    b.stock_num=99999 THEN '���ϼ�������'  \n" +
            "               WHEN b.state=1 AND  b.stock_num>0 and  b.stock_num<>99999 THEN '���ϼ��п��'  \n" +
            "                          WHEN b.state=1 AND  b.stock_num=0 THEN '���ϼ��޿��'  \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num>0 THEN 'δ�ϼ��п��'  \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num=0 THEN 'δ�ϼ��޿��'  \n" +
            "                             WHEN b.id IS NULL    THEN '����'  \n" +
            "                           ELSE '����' END  TYPE ,COUNT(*) num FROM ds_spml_mx (NOLOCK) a  LEFT JOIN ( \n" +
            "               SELECT a.id,a.state,b.stock_num FROM openquery(b2b,'select * from   goods') a inner JOIN  openquery(b2b,'select * from   mv_khlb_kc_hshj')b ON a.id=b.id  \n" +
            "               WHERE    kehulb='1'    \n" +
            "               ) b  ON a.spid=b.id \n" +
            "               GROUP BY  case  WHEN b.state=1 AND    b.stock_num=99999 THEN '���ϼ�������'  \n" +
            "                        WHEN b.state=1 AND  b.stock_num>0 and  b.stock_num<>99999 THEN '���ϼ��п��'  \n" +
            "                          WHEN b.state=1 AND  b.stock_num=0 THEN '���ϼ��޿��'  \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num>0 THEN 'δ�ϼ��п��'  \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num=0 THEN 'δ�ϼ��޿��'  \n" +
            "                             WHEN b.id IS NULL    THEN '����'  \n" +
            "                           ELSE '����' END , \n" +
            "                               \n" +
            "                CASE      WHEN b.state=1 AND  b.stock_num=99999 THEN 0 \n" +
            "                WHEN b.state=1 AND  b.stock_num>0 and  b.stock_num<>99999 THEN 1 \n" +
            "                          WHEN b.state=1 AND  b.stock_num=0 THEN 2 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num>0 THEN 3 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num=0 THEN 4 \n" +
            "                             WHEN b.id IS NULL    THEN 5 \n" +
            "                           ELSE 6 END           \n" +
            "                            \n" +
            "               ORDER BY  case     WHEN b.state=1 AND  b.stock_num=99999 THEN 0 \n" +
            "                WHEN b.state=1 AND  b.stock_num>0 and  b.stock_num<>99999 THEN 1 \n" +
            "                          WHEN b.state=1 AND  b.stock_num=0 THEN 2 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num>0 THEN 3 \n" +
            "                          WHEN b.state IN(2,3,4) AND  b.stock_num=0 THEN 4 \n" +
            "                             WHEN b.id IS NULL    THEN 5 \n" +
            "                           ELSE 6 END  "  )
    List<Spml> selectSpml();






    @Select( "   SELECT *FROM (\n" +
            "   SELECT  \n" +
            "   CASE WHEN a.image_list IS NULL  THEN '���ϼ���ͼƬ' \n" +
            "              WHEN a.pfpj IS NULL  THEN '���ϼ��޼۸�' \n" +
            "               ELSE '����' END  TYPE ,COUNT(*) num FROM  \n" +
            "   openquery(b2b,'select * from   goods') a  \n" +
            "   WHERE    state=1 \n" +
            " \n" +
            "   GROUP BY       CASE WHEN a.image_list IS NULL  THEN '���ϼ���ͼƬ' \n" +
            "              WHEN a.pfpj IS NULL  THEN '���ϼ��޼۸�' \n" +
            "               ELSE '����' END   \n" +
            "   ) a WHERE TYPE<>'����'"  )
    List<Spml> selectSpmlMiss();

    @Select( "  select CASE WHEN b.image_list IS NULL THEN '��ͼƬ'\n" +
            "         WHEN b.pfpj is NULL THEN '�޼۸�' \n" +
            "       ELSE '����' END  TYPE ,\n" +
            "COUNT(*) num \n" +
            "FROM ds_spml_mx (NOLOCK) a LEFT JOIN ( SELECT a.id,a.state,a.image_list,a.pfpj,b.stock_num FROM openquery(b2b,'select * from goods') a \n" +
            "inner JOIN openquery(b2b,'select * from mv_khlb_kc_hshj')b ON a.id=b.id WHERE kehulb='1' ) b \n" +
            "ON a.spid=b.id \n" +
            "WHERE b.state IN(2,3,4) AND b.stock_num>0  \n" +
            "GROUP BY  CASE WHEN b.image_list IS NULL THEN '��ͼƬ'\n" +
            "         WHEN b.pfpj is NULL THEN '�޼۸�' \n" +
            "       ELSE '����' END"  )
    List<Spml> selectSpmlStock();




    @Select( "select \n" +
            "CASE WHEN b.image_list IS NULL THEN '��ͼƬ'\n" +
            "         WHEN b.pfpj is NULL THEN '�޼۸�' \n" +
            "       ELSE '����' END  TYPE ,\n" +
            " COUNT(*) num \n" +
            "FROM ds_spml_mx (NOLOCK) a LEFT JOIN ( SELECT a.id,a.state,a.image_list,a.pfpj,b.stock_num FROM openquery(b2b,'select * from goods') a \n" +
            "inner JOIN openquery(b2b,'select * from mv_khlb_kc_hshj')b ON a.id=b.id WHERE kehulb='1' ) b \n" +
            "ON a.spid=b.id \n" +
            "WHERE b.state IN(2,3,4) AND b.stock_num=0\n" +
            "GROUP BY  CASE WHEN b.image_list IS NULL THEN '��ͼƬ'\n" +
            "         WHEN b.pfpj is NULL THEN '�޼۸�' \n" +
            "       ELSE '����' END"  )
    List<Spml> selectSpmlNoStock();


    @Select( "SELECT case when b.spid is not null THEN '��������Ʒ' ELSE '����������Ʒ'  end TYPE ,COUNT(*) num FROM  openquery(b2b,'select * from goods')a left JOIN \n" +
            " zkspkc_xs_v_wsdd b   ON a.id=b.spid   AND  kxkcshl=99999 AND rwkc=99999  AND erpkc=99999\n" +
            "WHERE a.state=1 \n" +
            "GROUP BY case when b.spid is not null THEN '��������Ʒ' ELSE '����������Ʒ' END ;"  )
    List<Spml> selectXnSpml();





    @Select( "SELECT  a.rq,a.num allnum,isnull(b.num,0) num FROM  \n" +
            "   (\n" +
            "   SELECT rq ,count(*) num FROM report_b2b_data_detail where rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "   GROUP BY  rq ) a LEFT JOIN \n" +
            "   (\n" +
            "   SELECT substring(convert(varchar(100),request_time ,20),1,10) rq,COUNT(*) num \n" +
            "   FROM openquery(b2b,'select * from enterprise_custom') \n" +
            "   WHERE STATE=2 AND convert(varchar(100),request_time ,20) BETWEEN '${begin_date} 00:00:00' AND '${end_date} 23:59:59' \n" +
            "   GROUP BY substring(convert(varchar(100),request_time ,20),1,10) \n" +
            "   ) b   ON a.rq=b.rq\n" +
            "   ORDER BY a.rq " )
    List<Cust> selectCustAdd(@Param("begin_date") String begin_date,
                             @Param("end_date") String end_date);


    @Select( " SELECT * FROM (\n" +
            "SELECT sum(CASE WHEN active_flag=1 THEN ISNULL(order_pay_price,0)  ELSE 0 END )  ��Ծ�ͻ����� ,\n" +
            "       SUM(CASE WHEN first_order_flag=1  THEN ISNULL(order_pay_price,0)  ELSE 0 END)  �׵�����,\n" +
            "          SUM(CASE WHEN new_flag=1  THEN ISNULL(order_pay_price,0)  ELSE 0 END)  �������ͻ����� ,\n" +
            "              SUM(CASE WHEN IN_date>=CONVERT(VARCHAR(10), dateadd(day, -6,  '${end_date}'),20)  THEN ISNULL(order_pay_price,0)  ELSE 0 END)  ��7�����ͻ����� \n" +
            "        FROM report_b2b_data_detail   WHERE rq='${end_date}'\n" +
            "        )a\n" +
            " unpivot \n" +
            "(   \n" +
            "     value FOR type IN ( ��Ծ�ͻ�����, �׵�����,�������ͻ�����,��7�����ͻ�����)     \n" +
            ") t     " )
    List<Cust> selectCustAddOrder(@Param("begin_date") String begin_date,
                             @Param("end_date") String end_date);



    @Select(   "SELECT *  FROM (\n" +
            " SELECT  1 id,\n" +
            "'���¶���' AS begin_name  ,sum(a.hsje-isnull(b.coupon_price,0)) �ѿ�Ʊ\n" +
            " FROM      wldwzl c  INNER join    gxkphz a(nolock)   ON a.wldwid=c.wldwid \n" +
            " LEFT    JOIN b_gxddhz b(nolock)  on a.dsdjbh=b.order_id\n" +
            "WHERE a.rq between  '${begin_date}' AND '${end_date}'\n" +
            "AND      a.is_zx <> '��'  AND a.shenhe='��'\n" +
            "and a.jigid='000'AND   a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��') AND  a.djbh like 'XHB%'\n" +
            "AND  c.is_fzjg='��'  AND  b.order_id IS NULL \n" +
            ")a\n" +
            " unpivot \n" +
            "(   \n" +
            "     value FOR end_name IN ( �ѿ�Ʊ)     \n" +
            ") t\n" +
            " WHERE VALUE>0\n" +
            "UNION ALL \n" +
            " SELECT * FROM (\n" +
            "SELECT --sum(hsje) hsje,\n" +
            "1 id,\n" +
            "'���϶���' AS begin_name,CAST(SUM(kphsje)AS DECIMAL) �ѿ�Ʊ,CAST(SUM(EXCEPT_price) AS DECIMAL) ���쿪Ʊ,CAST(SUM(nokp_price)  AS DECIMAL) δ��Ʊ FROM (\n" +
            "SELECT \n" +
            "order_pay_price hsje ,a.id,isnull(b.hsje,0) kphsje ,order_pay_price- (case when b.hsje IS NOT NULL THEN  b.hsje ELSE order_pay_price END  ) EXCEPT_price\n" +
            ",  (case when b.hsje IS NULL THEN  order_pay_price ELSE 0 END  ) nokp_price\n" +
            "FROM   openquery(b2b,'select * from order_for_goods ') a LEFT JOIN \n" +
            "( SELECT dsdjbh,(a.hsje-isnull(b.coupon_price,0)) hsje FROM  gxkphz  (nolock) a INNER join b_gxddhz b(NOLOCK) ON    a.dsdjbh=b.order_id AND    a.is_zx <> '��' AND  a.shenhe='��' AND a.djbs='XHB') b   ON a.id=b.dsdjbh\n" +
            "WHERE  is_pay = 1 and pay_time BETWEEN   '${begin_date} 00:00:00' AND '${end_date} 23:59:59'\n" +
            ")a\n" +
            ")a\n" +
            "unpivot \n" +
            "(   \n" +
            "     value FOR end_name IN ( �ѿ�Ʊ, ���쿪Ʊ,δ��Ʊ)     \n" +
            ") t  WHERE VALUE>0" +
            "UNION ALL \n" +
            "SELECT *  FROM (\n" +
            " SELECT  2 id,\n" +
            "'�ѿ�Ʊ' AS begin_name  ,sum(case when  handle<>9  then 0 ELSE a.hsje END ) �ѷ�������,sum(case when handle<>9  then a.hsje ELSE 0 END ) δ��������\n" +
            " FROM      wldwzl c  INNER join    gxkphz a(nolock)   ON a.wldwid=c.wldwid \n" +
            "WHERE a.rq between  '${begin_date}' AND '${end_date}'\n" +
            "AND      a.is_zx <> '��'  AND a.shenhe='��'\n" +
            "and a.jigid='000'AND   a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��') AND  a.djbs='XHB'\n" +
            "AND  c.is_fzjg='��'   \n" +
            ")a\n" +
            " unpivot \n" +
            "(   \n" +
            "     value FOR end_name IN ( �ѷ�������,δ��������)     \n" +
            ") t\n" +
            " WHERE VALUE>0"+
            "    UNION ALL \n" +
            "                   SELECT *  FROM (\n" +
            "             SELECT  3 id,\n" +
            "            '�ѷ�������' AS begin_name   ,sum(case when x.instruct_state=3  then a.hsje ELSE 0 END ) �Ѵ�ӡ���� ,sum(case when  x.instruct_state in (0)   then a.hsje ELSE 0  END ) �ȴ�����\n" +
            "    ,sum(case when  x.instruct_state in (1,2)   then a.hsje ELSE 0  END ) ���ڼ������"+
            "             FROM      wldwzl c  INNER join    gxkphz a(nolock)   ON a.wldwid=c.wldwid \n" +
            "                left join wms_systeminstruct_state x (nolock) on a.djbh=x.instruct_djbh\n" +
            "            WHERE a.rq between  '${begin_date}' AND '${end_date}'\n" +
            "            AND      a.is_zx <> '��'  AND a.shenhe='��' AND a.handle=9\n" +
            "            and a.jigid='000'AND   a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��') AND  a.djbs='XHB'\n" +
            "            AND  c.is_fzjg='��'   \n" +
            "            )a\n" +
            "            unpivot \n" +
            "(   \n" +
            "     value FOR end_name IN (  �Ѵ�ӡ����,���ڼ������,�ȴ�����)     \n" +
            ") t\n" +
            "      WHERE VALUE>0"+
            "UNION ALL \n" +
            "          SELECT *  FROM (\n" +
            "             SELECT  4 id,\n" +
            "            '�Ѵ�ӡ����' AS begin_name  ,sum(case when g.djbh is not null    then isnull(g.hsje,0) ELSE 0 END ) �ѳ���,sum(ISNULL(b.COUPON_PRICE,0) ) �Żݽ��,\n" +
            "           SUM(a.hsje)- sum(case when g.djbh is not null    then isnull(g.hsje,0) ELSE 0 END )-sum(ISNULL(b.COUPON_PRICE,0) ) �������\n" +
            "             FROM      wldwzl c  INNER join    gxkphz a(nolock)   ON a.wldwid=c.wldwid \n" +
            "              LEFT    JOIN b_gxddhz b(nolock)  on a.dsdjbh=b.order_id\n" +
            "             LEFT JOIN (\n" +
            "             SELECT b.xgdjbh djbh ,sum(b.hsje) hsje  FROM gxywhz(NOLOCK) a INNER JOIN gxywmx b  ON a.djbh=b.djbh  WHERE   djbs='XHC'  \n" +
            "             GROUP BY b.xgdjbh\n" +
            "             ) g  ON a.djbh=g.djbh\n" +
            "            WHERE a.rq between   '${begin_date}' AND '${end_date}'\n" +
            "            AND      a.is_zx <> '��'  AND a.shenhe='��' \n" +
            "            and a.jigid='000'AND   a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��') AND  a.djbs='XHB'\n" +
            "            AND  c.is_fzjg='��'  AND g.djbh IS NOT NULL  \n" +
            "            )a\n" +
            "             unpivot \n" +
            "            (   \n" +
            "                 value FOR end_name IN ( �ѳ���,�Żݽ��,�������)     \n" +
            "            ) t\n" +
            "             WHERE VALUE>0      \n"+
            "   UNION ALL \n" +
            "   SELECT *  FROM (\n" +
            "             SELECT  5 id,\n" +
            "            '�ѳ���' AS begin_name   , sum(CASE WHEN h.djbh IS NOT NULL AND h.is_zx='��'   \t  THEN h.hsje else 0 END ) ������ , sum(CASE WHEN h.djbh IS NOT NULL AND h.is_zx<>'��'     THEN h.hsje else 0 END ) ������\n" +
            "  FROM      wldwzl c  INNER join    gxkphz a(nolock)   ON a.wldwid=c.wldwid \n" +
            "                      \n" +
            "                         inner JOIN (\n" +
            "                         SELECT b.xgdjbh djbh ,a.djbh bh ,sum(b.hsje) hsje  FROM gxywhz(NOLOCK) a INNER JOIN gxywmx b  ON a.djbh=b.djbh  WHERE   djbs='XHC'  \n" +
            "                         GROUP BY b.xgdjbh,a.djbh\n" +
            "                         ) g  ON a.djbh=g.djbh\n" +
            "                         LEFT JOIN ( SELECT xgdjbh djbh,a.is_zx,SUM(b.hsje) hsje\n" +
            "\tfrom cr_v11_wms.dbo.wms_shtzhz a(nolock) INNER JOIN   cr_v11_wms.dbo.wms_shtzmx b(nolock) ON a.djbh=b.djbh\n" +
            "\twhere   a.is_zx<>'��'   GROUP BY xgdjbh,a.is_zx ) h ON g.bh=h.djbh\n" +
            "                         WHERE a.rq between   '${begin_date}' AND '${end_date}'\n" +
            "                        AND      a.is_zx <> '��'  AND a.shenhe='��' \n" +
            "                        and a.jigid='000'AND   a.bmname IN ('������ҵ��','�ն���ҩ��ҵ��') AND  a.djbs='XHB'\n" +
            "                        AND  c.is_fzjg='��'  \n" +
            "                        )a\n" +
            "                                    unpivot \n" +
            "(   \n" +
            "     value FOR end_name IN (   ������,������)     \n" +
            ") t\n" +
            "      WHERE VALUE>0"

          )
    List<Flow> selectFlow(@Param("begin_date") String begin_date,
                                  @Param("end_date") String end_date);

    @Select(   "SELECT top 5 search_key,COUNT(*) n FROM openquery(b2b,'select * from goods_search ') a \n" +
            " inner join openquery(b2b,'select * from enterprise_custom ') c  on \n" +
            "a.custom_id=c.custom_id AND c.state=2\n" +
            "WHERE a.search_time BETWEEN '${begin_date} 00:00:00' AND '${end_date} 23:59:59' AND len(rtrim(a.search_key))>=2\n" +
            "and  c.enterprise_id<>'WLD00005953' \n" +
            "GROUP BY search_key ORDER BY n DESC  "

    )
    List<Search> selectSearchTop5(@Param("begin_date") String begin_date,
                          @Param("end_date") String end_date);


    @Select(   "SELECT *  FROM (\n" +
            "SELECT round(SUM(ok)*0.001*1000*100/COUNT(*),2) �ҵ�,round(SUM(nook)*0.001*1000*100/COUNT(*),2) �ҵ��޿��,round(SUM(no)*0.001*1000*100/COUNT(*),2) δ�ҵ� FROM (\n" +
            "SELECT DISTINCT \n" +
            "c.enterprise_id,\n" +
            "x.wldwname,\n" +
            "a.search_key,\n" +
            "CASE WHEN y.stock_num>0 THEN 1 ELSE 0 END AS ok ,-- �п��\n" +
            "CASE WHEN y.stock_num=0 THEN 1 ELSE 0 END AS nook ,--����Ʒ�޿��\n" +
            "CASE WHEN y.stock_num is null THEN 1 ELSE 0 END AS no --����Ʒ\n" +
            " from openquery(b2b,'select * from goods_search ') a  \n" +
            " inner join openquery(b2b,'select * from enterprise_custom ') c  on \n" +
            "a.custom_id=c.custom_id AND c.state=2\n" +
            "INNER JOIN wldwzl x ON x.wldwid=c.enterprise_id\n" +
            "LEFT JOIN report_b2b_searchin_detail  y ON a.id=y.ID  AND y.rq between '${begin_date}' and '${end_date}'  \n" +
            "WHERE   a.search_time BETWEEN '${begin_date} 00:00:00' AND '${end_date} 23:59:59'  and  c.enterprise_id<>'WLD00005953'\n" +
            ")a \n" +
            ")a\n" +
            "unpivot \n" +
            "(   \n" +
            "     value FOR type IN ( �ҵ��޿��, �ҵ�,δ�ҵ�)     \n" +
            ") t\n\n "

    )
    List<Search> selectSearchBingo(@Param("begin_date") String begin_date,
                                @Param("end_date") String end_date);



    @Select(   "SELECT top 5 search_key,count(*) n FROM report_b2b_searno_detail WHERE rq between '${begin_date}' and '${end_date}' and enterprise_id<>'WLD00005953' GROUP BY search_key ORDER BY n DESC  \n "

    )
    List<Search> selectSearchno(@Param("begin_date") String begin_date,
                                 @Param("end_date") String end_date);
















}
