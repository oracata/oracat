package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.ReportMonth;
import com.oracat.model.ReportYear;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMonthDao {


    @Select("\n" +
            "SELECT a.rq,hsje,round(cankml,2) cankml,round(cankmll,2) cankmll,cust_num,login_num,pay_cust,not_pay_cust,not_pay,cart_cust,cart_price  FROM (\n" +
            "  SELECT  rq,(SELECT count(a.enterprise_id) FROM openquery(b2b,'select * from enterprise_custom') a \n" +
            "INNER JOIN wldwzl b ON a.enterprise_id=b.wldwid AND b.beactive='是' WHERE STATE=2 AND  convert(varchar(30),a.request_time,120)<t.rq+'-31 23:59:59' ) cust_num,round(hsje,2) hsje, round(cankml,2) cankml,round(t.cankmll,2) cankmll FROM (\n" +
            "    select  SUBSTRING(a.rq,1,7) rq ,    sum(b.hsje) as hsje\n" +
            " \n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "  \n" +
            "     and a.rq between '${begin_date}' and '${end_date}'\n" +
            "    GROUP BY SUBSTRING(a.rq,1,7)  \n" +
            "having sum(b.hsje)<>0)t\n" +
            "\n" +
            "UNION ALL \n" +
            "\n" +
            "  SELECT  '合计',(SELECT count(a.enterprise_id) FROM openquery(b2b,'select * from enterprise_custom') a \n" +
            "INNER JOIN wldwzl b ON a.enterprise_id=b.wldwid AND b.beactive='是' WHERE STATE=2  ) cust_num,round(hsje,2) hsje, round(cankml,2) cankml,round(t.cankmll,2) cankmll FROM (\n" +
            "    select    sum(b.hsje) as hsje\n" +
            " \n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "  \n" +
            "     and a.rq between '${begin_date}' and '${end_date}'\n" +
            "having sum(b.hsje)<>0)t\n" +
            ")a\n" +
            "left JOIN (\n" +
            "SELECT substring(convert(varchar(30),LOGIN_time,120),1,7) rq ,count(distinct b.enterprise_id) login_num  FROM openquery(b2b,'select * from login_log') a\n" +
            "left JOIN openquery(b2b,'select * from enterprise_custom') b ON a.user_id=b.custom_id AND b.state=2\n" +
            "WHERE       LOGIN_time  BETWEEN    '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59' \n" +
            "GROUP BY substring(convert(varchar(30),LOGIN_time,120),1,7)\n" +
            "UNION ALL\n" +
            "SELECT '合计' rq ,count(distinct b.enterprise_id) login_num  FROM openquery(b2b,'select * from login_log') a\n" +
            "left JOIN openquery(b2b,'select * from enterprise_custom') b ON a.user_id=b.custom_id AND b.state=2\n" +
            "WHERE       LOGIN_time  BETWEEN    '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59' \n" +
            "\t\n" +
            ") b   ON a.rq=b.rq\n" +
            "\n" +
            "LEFT JOIN  (\n" +
            "SELECT substring(convert(varchar(30),request_time,120),1,7) rq,COUNT(DISTINCT enterprise_id) not_pay_cust,sum(order_pay_price) not_pay FROM openquery(b2b,'select * from order_for_goods ') \n" +
            "WHERE state in (0,-9) and request_time   BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\n" +
            "GROUP BY substring(convert(varchar(30),request_time,120),1,7)\n" +
            "UNION ALL\n" +
            "SELECT '合计' rq,COUNT(DISTINCT enterprise_id) not_pay_cust,sum(order_pay_price) not_pay FROM openquery(b2b,'select * from order_for_goods ') \n" +
            "WHERE state in (0,-9) and request_time   BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\t\n" +
            ")c ON a.rq=c.rq\n" +
            "LEFT JOIN  (\n" +
            "SELECT substring(convert(varchar(30),pay_time,120),1,7) rq,COUNT(DISTINCT enterprise_id) pay_cust FROM openquery(b2b,'select * from order_for_goods ') \n" +
            "WHERE  is_pay = 1 and pay_time     BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\n" +
            "GROUP BY substring(convert(varchar(30),pay_time,120),1,7)\n" +
            "UNION ALL\n" +
            "SELECT '合计' rq,COUNT(DISTINCT enterprise_id) pay_cust FROM openquery(b2b,'select * from order_for_goods ') \n" +
            "WHERE  is_pay = 1 and pay_time     BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\t\n" +
            ")d ON a.rq=d.rq\n" +
            "LEFT JOIN  (\n" +
            "\n" +
            "SELECT substring(convert(varchar(30),cart_time,120),1,7) rq,COUNT(DISTINCT b.enterprise_id) cart_cust,sum(js_price*num) cart_price\n" +
            "  FROM openquery(b2b,'select * from shopping_cart') a \n" +
            "  INNER JOIN  openquery(b2b,'select * from enterprise_custom') b ON a.enterprise_custom_id=b.id \n" +
            "WHERE a.state=1 and a.cart_time   BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\n" +
            "GROUP BY substring(convert(varchar(30),cart_time,120),1,7) \n" +
            "UNION ALL\n" +
            "\n" +
            "SELECT '合计' rq,COUNT(DISTINCT b.enterprise_id) cart_cust,sum(js_price*num) cart_price\n" +
            "  FROM openquery(b2b,'select * from shopping_cart') a \n" +
            "  INNER JOIN  openquery(b2b,'select * from enterprise_custom') b ON a.enterprise_custom_id=b.id \n" +
            "WHERE a.state=1 and a.cart_time   BETWEEN   '${begin_date}'+' 00:00:00' AND '${end_date}'+' 23:59:59'\t\n" +
            ")e ON a.rq=e.rq\n" +
            " \n" +
            "\n" +
            "\n"   )
    List<ReportMonth> selectReportMonth(@Param("begin_date") String begin_date,
                                       @Param("end_date") String end_date);


}
