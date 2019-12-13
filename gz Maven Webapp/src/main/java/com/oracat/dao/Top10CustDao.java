package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.ReportYear;
import com.oracat.model.Top10Cust;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Top10CustDao {


    @Select("\n" +
            "SELECT top 10 c.wldwname\n" +
            "     \n" +
            "      ,SUM(b.hsje) AS hsje\n" +
            "      ,SUM(\n" +
            "           CASE \n" +
            "                WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                ELSE b.hsje\n" +
            "           END-(\n" +
            "               CASE \n" +
            "                    WHEN a.djlx='x40'\n" +
            "               OR a.djlx='x60' THEN (\n" +
            "                      CASE \n" +
            "                           WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                           ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                      END\n" +
            "                  ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "           )\n" +
            "       ) AS cankml\n" +
            "      ,round(SUM(\n" +
            "           CASE \n" +
            "                WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                ELSE b.hsje\n" +
            "           END-(\n" +
            "               CASE \n" +
            "                    WHEN a.djlx='x40'\n" +
            "               OR a.djlx='x60' THEN (\n" +
            "                      CASE \n" +
            "                           WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                           ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                      END\n" +
            "                  ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "           )\n" +
            "       )/SUM(CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)*100,2) AS \n" +
            "       cankmll\n" +
            "FROM   gxywhz a(NOLOCK)\n" +
            "       INNER JOIN gxywmx b(NOLOCK)\n" +
            "            ON  a.djbh = b.djbh\n" +
            "       INNER JOIN    wldwzl(NOLOCK) c\n" +
            "                       \n" +
            "            ON  a.wldwid = c.wldwid\n" +
            "       INNER JOIN spzl d(NOLOCK)\n" +
            "            ON  b.spid = d.spid\n" +
            "WHERE  a.djbh LIKE 'XH%'\n" +
            "       AND a.djbs IN ('XHC' ,'XHH' ,'XHF')\n" +
            "       AND a.bmname LIKE RTRIM('电商事业部')+'%'\n" +
            "       AND a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "       \n" +
            "GROUP BY\n" +
            "       c.wldwname     \n" +
            "HAVING SUM(b.hsje)<>0\n" +
            "ORDER BY  SUM(b.hsje) DESC "  )
    List<Top10Cust> selectTop10Cust(@Param("begin_date") String begin_date,
                                     @Param("end_date") String end_date);





    @Select("\n" +
            "SELECT top 10   d.spmch+'|'+d.shpgg+'|'+d.shengccj  spmch \n" +
            "     \n" +
            "      ,SUM(b.hsje) AS hsje\n" +
            "      ,SUM(\n" +
            "           CASE \n" +
            "                WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                ELSE b.hsje\n" +
            "           END-(\n" +
            "               CASE \n" +
            "                    WHEN a.djlx='x40'\n" +
            "               OR a.djlx='x60' THEN (\n" +
            "                      CASE \n" +
            "                           WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                           ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                      END\n" +
            "                  ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "           )\n" +
            "       ) AS cankml\n" +
            "      ,round(SUM(\n" +
            "           CASE \n" +
            "                WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                ELSE b.hsje\n" +
            "           END-(\n" +
            "               CASE \n" +
            "                    WHEN a.djlx='x40'\n" +
            "               OR a.djlx='x60' THEN (\n" +
            "                      CASE \n" +
            "                           WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                           ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                      END\n" +
            "                  ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "           )\n" +
            "       )/SUM(CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)*100,2) AS \n" +
            "       cankmll\n" +
            "FROM   gxywhz a(NOLOCK)\n" +
            "       INNER JOIN gxywmx b(NOLOCK)\n" +
            "            ON  a.djbh = b.djbh\n" +
            "       INNER JOIN    wldwzl(NOLOCK) c\n" +
            "                       \n" +
            "            ON  a.wldwid = c.wldwid\n" +
            "       INNER JOIN spzl d(NOLOCK)\n" +
            "            ON  b.spid = d.spid\n" +
            "WHERE  a.djbh LIKE 'XH%'\n" +
            "       AND a.djbs IN ('XHC' ,'XHH' ,'XHF')\n" +
            "       AND a.bmname LIKE RTRIM('电商事业部')+'%'\n" +
            "       AND a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "       \n" +
            "GROUP BY\n" +
            "      d.spmch+'|'+d.shpgg+'|'+d.shengccj     \n" +
            "HAVING SUM(b.hsje)<>0\n" +
            "ORDER BY  SUM(b.hsje) DESC "  )
    List<Top10Cust> selectTop10Goods(@Param("begin_date") String begin_date,
                                    @Param("end_date") String end_date);



    @Select(" \n" +
            "                                                 SELECT     '满'+convert(varchar(30),g.price_limit)+'减'+convert(varchar(30),g.reduce_price) coupon\n" +
            "                                    ,COUNT(DISTINCT a.djbh) num                    \n" +
            "                                                       ,SUM(b.hsje) AS hsje\n" +
            "                                                       ,SUM(\n" +
            "                                                            CASE \n" +
            "                                                                 WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                                                                 ELSE b.hsje\n" +
            "                                                            END-(\n" +
            "                                                                CASE \n" +
            "                                                                     WHEN a.djlx='x40'\n" +
            "                                                                OR a.djlx='x60' THEN (\n" +
            "                                                                       CASE \n" +
            "                                                                            WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                                                                            ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                                                                       END\n" +
            "                                                                   ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "                                                            )\n" +
            "                                                        ) AS cankml\n" +
            "                                                       ,round(SUM(\n" +
            "                                                            CASE \n" +
            "                                                                 WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                                                                 ELSE b.hsje\n" +
            "                                                            END-(\n" +
            "                                                                CASE \n" +
            "                                                                     WHEN a.djlx='x40'\n" +
            "                                                                OR a.djlx='x60' THEN (\n" +
            "                                                                       CASE \n" +
            "                                                                            WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                                                                            ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                                                                       END\n" +
            "                                                                   ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "                                                            )\n" +
            "                                                        )/SUM(CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)*100,2) AS \n" +
            "                                                        cankmll\n" +
            "                                                 FROM   gxywhz a(NOLOCK)\n" +
            "                                                        INNER JOIN gxywmx b(NOLOCK)\n" +
            "                                                             ON  a.djbh = b.djbh\n" +
            "                                                        INNER JOIN    wldwzl(NOLOCK) c\n" +
            "                                                                        \n" +
            "                                                             ON  a.wldwid = c.wldwid\n" +
            "                                                        INNER JOIN spzl d(NOLOCK)\n" +
            "                                                             ON  b.spid = d.spid\n" +
            "                                                        INNER JOIN OPENQUERY(b2b,'select * from order_for_goods') e \n" +
            "                                                        ON a.dsdjbh=e.id\n" +
            "                                                          INNER JOIN OPENQUERY(b2b,'select * from coupon') f \n" +
            "                                                        ON e.coupon_id=f.id\n" +
            "                                                         INNER JOIN OPENQUERY(b2b,'select * from coupon_definition') g \n" +
            "                                                        ON f.coupon_definition_id=g.id\n" +
            "                                                 WHERE  a.djbh LIKE 'XH%'\n" +
            "                                                        AND a.djbs IN ('XHC' ,'XHH' ,'XHF')\n" +
            "                                                        AND a.bmname LIKE RTRIM('电商事业部')+'%'\n" +
            "       AND a.rq BETWEEN '${begin_date}' AND '${end_date}'\n" +
            "                                                        AND a.dsdjbh<>''\n" +
            "                                                        \n" +
            "                                                 GROUP BY\n" +
            "                                                       '满'+convert(varchar(30),g.price_limit)+'减'+convert(varchar(30),g.reduce_price)\n" +
            "                                                      \n" +
            "                                                 HAVING SUM(b.hsje)<>0\n" +
            "                                                 \n" +
            "                                                 ORDER BY  round(SUM(\n" +
            "                                                            CASE \n" +
            "                                                                 WHEN b.ywyjsje<>0 THEN b.ywyjsje\n" +
            "                                                                 ELSE b.hsje\n" +
            "                                                            END-(\n" +
            "                                                                CASE \n" +
            "                                                                     WHEN a.djlx='x40'\n" +
            "                                                                OR a.djlx='x60' THEN (\n" +
            "                                                                       CASE \n" +
            "                                                                            WHEN b.cankcbj<>0 THEN ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2)\n" +
            "                                                                            ELSE (CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)\n" +
            "                                                                       END\n" +
            "                                                                   ) ELSE ROUND(b.cankcbj/b.xsbzjlgg*b.shl ,2) END\n" +
            "                                                            )\n" +
            "                                                        )/SUM(CASE WHEN b.ywyjsje<>0 THEN b.ywyjsje ELSE b.hsje END)*100,2) DESC \n" +
            "                                                 \n" +
            " "  )
    List<Top10Cust> selectCoupon(@Param("begin_date") String begin_date,
                                     @Param("end_date") String end_date);

}
