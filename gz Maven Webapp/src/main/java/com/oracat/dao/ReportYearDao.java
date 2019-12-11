package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.ReportYear;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportYearDao {


    @Select("\n" +
            "SELECT  n.chengshi  area,isnull(\"2019-06\",0) je201906,isnull(\"2019-07\",0) je201907,isnull(\"2019-08\",0) je201908,isnull(\"2019-09\",0) je201909,isnull(\"2019-10\",0) je201910,isnull(\"2019-11\",0)  je201911,isnull(\"2019-12\",0)  je201912,isnull(\"全年\",0) year,isnull(m.cankml,0) ml,isnull(m.cankmll,0) mll FROM (\n" +
            "select '文山州' chengshi  union all \n" +
            "select '昭通市'     union all \n" +
            "select '保山市'     union all \n" +
            "select '临沧市'     union all \n" +
            "select '楚雄州'     union all \n" +
            "select '曲靖市'     union all \n" +
            "select '普洱市'     union all \n" +
            "select '西双版纳州' union all \n" +
            "select '玉溪市'     union all \n" +
            "select '丽江市'     union all \n" +
            "select '昆明市'     union all \n" +
            "select '红河州'     union all \n" +
            "select '怒江州'     union all \n" +
            "select '迪庆州'     union all \n" +
            "select '大理州'     union all \n" +
            "select '德宏州'     UNION ALL\n" +
            "select '合计' \n" +
            ") n\n" +
            "LEFT JOIN (\n" +
            "\n" +
            " SELECT  h.chengshi,\"2019-06\",\"2019-07\",\"2019-08\",\"2019-09\",\"2019-10\",\"2019-11\",\"2019-12\",\"全年\",cankml,cankmll\n" +
            "   FROM (\n" +
            "    SELECT chengshi,sum(isnull(\"2019-06\",0)) \"2019-06\",sum(isnull(\"2019-07\",0)) \"2019-07\",sum(isnull(\"2019-08\",0)) \"2019-08\",sum(isnull(\"2019-09\",0)) \"2019-09\",sum(isnull(\"2019-10\",0)) \"2019-10\",sum(isnull(\"2019-11\",0)) \"2019-11\",sum(isnull(\"2019-12\",0)) \"2019-12\",\n" +
            "    sum(isnull(\"2019-06\",0))+sum(isnull(\"2019-07\",0))+sum(isnull(\"2019-08\",0)) +sum(isnull(\"2019-09\",0))+sum(isnull(\"2019-10\",0))+sum(isnull(\"2019-11\",0))+sum(isnull(\"2019-12\",0)) \"全年\"\n" +
            "     FROM (\n" +
            "     \t\n" +
            "     \t\n" +
            "     select * from (  \n" +
            "   select c.chengshi,substring(rq,1,7) rq\n" +
            "      ,sum(b.hsje) as hsje\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "inner  join (\n" +
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄州'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理州'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏州'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆州'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河州'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱市'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山州'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '西双版纳州' \n" +
            "  else b.chengshi END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "\n" +
            "     and a.rq between '2019-06-01' and '2019-12-31'\n" +
            "     \n" +
            "   and  NOT EXISTS(SELECT 1 FROM jgwldwzl_bm WHERE bmid IN ('BMZ00000085','BMZ00000069') and wldwid=c.wldwid AND creattime>c.creattime  )    \n" +
            "\n" +
            "group by c.chengshi,substring(rq,1,7)\n" +
            "having sum(b.hsje)<>0) t\n" +
            "PIVOT(sum(t.hsje) FOR t.rq  in  (\"2019-06\",\"2019-07\",\"2019-08\",\"2019-09\",\"2019-10\",\"2019-11\",\"2019-12\") ) AS PVT\n" +
            "    ) f  GROUP BY chengshi\n" +
            "    ) h\n" +
            "    \n" +
            "    INNER JOIN \n" +
            "    \n" +
            "    \n" +
            "    \n" +
            "        \n" +
            "   (  SELECT  chengshi ,round(cankml,2) cankml,round(t.cankmll,2) cankmll from (  \n" +
            "   select c.chengshi\n" +
            "      ,sum(b.hsje) as hsje\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "inner  join (\n" +
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄州'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理州'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏州'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆州'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河州'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱市'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山州'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '西双版纳州' \n" +
            "  else b.chengshi END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "\n" +
            "     and a.rq between '2019-06-01' and '2019-12-31'\n" +
            "     \n" +
            "   and  NOT EXISTS(SELECT 1 FROM jgwldwzl_bm WHERE bmid IN ('BMZ00000085','BMZ00000069') and wldwid=c.wldwid AND creattime>c.creattime  )    \n" +
            "\n" +
            "group by c.chengshi\n" +
            "having sum(b.hsje)<>0) t\n" +
            "   ) g  ON g.chengshi=h.chengshi\n" +
            "   \n" +
            " WHERE h.chengshi IN ( SELECT distinct chengshi FROM report_b2b_data WHERE shengfen='云南省') \n" +
            "\n" +
            "UNION ALL     \n" +
            "    \n" +
            "   SELECT chengshi ,  sum(isnull(\"2019-06\",0)) \"2019-06\",sum(isnull(\"2019-07\",0)) \"2019-07\",sum(isnull(\"2019-08\",0)) \"2019-08\",sum(isnull(\"2019-09\",0)) \"2019-09\",sum(isnull(\"2019-10\",0)) \"2019-10\",sum(isnull(\"2019-11\",0)) \"2019-11\",sum(isnull(\"2019-12\",0)) \"2019-12\",SUM(\"全年\") \"全年\",SUM(cankml) cankml,SUM(cankmll) cankmll\n" +
            "   FROM (\n" +
            "    \n" +
            "     SELECT '合计' chengshi,sum(isnull(\"2019-06\",0)) \"2019-06\" ,sum(isnull(\"2019-07\",0)) \"2019-07\",sum(isnull(\"2019-08\",0)) \"2019-08\",sum(isnull(\"2019-09\",0)) \"2019-09\",sum(isnull(\"2019-10\",0)) \"2019-10\",sum(isnull(\"2019-11\",0)) \"2019-11\",sum(isnull(\"2019-12\",0)) \"2019-12\", \n" +
            "    sum(isnull(\"2019-06\",0))+sum(isnull(\"2019-07\",0))+sum(isnull(\"2019-08\",0)) +sum(isnull(\"2019-09\",0))+sum(isnull(\"2019-10\",0))+sum(isnull(\"2019-11\",0))+sum(isnull(\"2019-12\",0)) \"全年\",0 cankml,0 cankmll\n" +
            "     FROM (\n" +
            "     \t\n" +
            "     \t\n" +
            "   select   substring(rq,1,7) rq\n" +
            "      ,sum(b.hsje) as hsje\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "inner  join (\n" +
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄州'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理州'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏州'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆州'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河州'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱市'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山州'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '西双版纳州' \n" +
            "  else b.chengshi END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "\tand chengshi IN ( SELECT distinct chengshi FROM report_b2b_data WHERE shengfen='云南省')\n" +
            "     and a.rq between '2019-06-01' and '2019-12-31'\n" +
            "     \n" +
            "   and  NOT EXISTS(SELECT 1 FROM jgwldwzl_bm WHERE bmid IN ('BMZ00000085','BMZ00000069') and wldwid=c.wldwid AND creattime>c.creattime  )    \n" +
            "\n" +
            "group by  substring(rq,1,7)\n" +
            "having sum(b.hsje)<>0\n" +
            ") t\n" +
            "PIVOT(sum(t.hsje) FOR t.rq  in  (\"2019-06\",\"2019-07\",\"2019-08\",\"2019-09\",\"2019-10\",\"2019-11\",\"2019-12\") ) AS PVT\n" +
            "\n" +
            "\n" +
            "UNION ALL\n" +
            "\n" +
            "    \n" +
            "    SELECT '合计',0,0,0,0,0,0,0,0,round(cankml,2) cankml,round(t.cankmll,2) cankmll FROM (\n" +
            "    select  sum(b.hsje) as hsje\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end)) as cankml\n" +
            "      ,sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end-(case when a.djlx='x40' or a.djlx='x60' then (case when b.cankcbj<>0 then round(b.cankcbj/b.xsbzjlgg*b.shl,2) else (case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end) end) else round(b.cankcbj/b.xsbzjlgg*b.shl,2) end))/sum(case when b.ywyjsje<>0 then b.ywyjsje else b.hsje end)*100 as cankmll\n" +
            "\n" +
            "from gxywhz a(nolock) inner join gxywmx b(nolock) on a.djbh=b.djbh\n" +
            "inner  join (\n" +
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄州'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理州'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏州'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆州'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河州'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱市'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山州'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '西双版纳州' \n" +
            "  else b.chengshi END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +
            "\tand chengshi IN ( SELECT distinct chengshi FROM report_b2b_data WHERE shengfen='云南省')\n" +
            "     and a.rq between '2019-06-01' and '2019-12-31'\n" +
            "     \n" +
            "   and  NOT EXISTS(SELECT 1 FROM jgwldwzl_bm WHERE bmid IN ('BMZ00000085','BMZ00000069') and wldwid=c.wldwid AND creattime>c.creattime  )    \n" +
            "   \tand chengshi IN ( SELECT distinct chengshi FROM report_b2b_data WHERE shengfen='云南省')\n" +
            "having sum(b.hsje)<>0)t\n" +
            "\n" +
            ")u\n" +
            "   GROUP BY chengshi \n" +
            "   \n" +
            ") m   ON n.chengshi=m.chengshi \n" +
            "ORDER BY isnull(\"全年\",0) DESC  \n"  )
    List<ReportYear> selectReportYear(@Param("begin_date") String begin_date,
                                      @Param("end_date") String end_date);


}
