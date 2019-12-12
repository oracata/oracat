package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.ReportYear;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportYearDao {


    @Select("\n" +
            "SELECT  n.chengshi  area,isnull(\"2019-06\",0) je201906,isnull(\"2019-07\",0) je201907,isnull(\"2019-08\",0) je201908,isnull(\"2019-09\",0) je201909,isnull(\"2019-10\",0) je201910,isnull(\"2019-11\",0)  je201911,isnull(\"2019-12\",0)  je201912,isnull(\"全年\",0) year,isnull(m.cankml,0) ml,isnull(m.cankmll,0) mll FROM (\n" +
            "select '文山' chengshi  union all \n" +
            "select '昭通'     union all \n" +
            "select '保山'     union all \n" +
            "select '临沧'     union all \n" +
            "select '楚雄'     union all \n" +
            "select '曲靖'     union all \n" +
            "select '普洱'     union all \n" +
            "select '版纳' union all \n" +
            "select '玉溪'     union all \n" +
            "select '丽江'     union all \n" +
            "select '昆明'     union all \n" +
            "select '红河'     union all \n" +
            "select '怒江'     union all \n" +
            "select '迪庆'     union all \n" +
            "select '大理'     union all \n" +
            "select '德宏'     UNION ALL\n" +
            "select '其它'     UNION ALL\n" +
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
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '版纳' \n" +
            " when b.chengshi='文山州'     then   '文山'\n" +
            " when b.chengshi='昭通市'     then   '昭通'\n" +
            " when b.chengshi='保山市'     then   '保山'\n" +
            " when b.chengshi='临沧市'     then   '临沧'\n" +
            " when b.chengshi='楚雄州'     then   '楚雄'\n" +
            " when b.chengshi='曲靖市'     then   '曲靖'\n" +
            " when b.chengshi='普洱市'     then   '普洱'\n" +
            " when b.chengshi='西双版纳州' then   '版纳'\n" +
            " when b.chengshi='玉溪市'     then   '玉溪'\n" +
            " when b.chengshi='丽江市'     then   '丽江'\n" +
            " when b.chengshi='昆明市'     then   '昆明'\n" +
            " when b.chengshi='红河州'     then   '红河'\n" +
            " when b.chengshi='怒江州'     then   '怒江'\n" +
            " when b.chengshi='迪庆州'     then   '迪庆'\n" +
            " when b.chengshi='大理州'     then   '大理'\n" +
            " when b.chengshi='德宏州'     then   '德宏'\n"+
            "  else  '其它' END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
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
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '版纳' \n" +
            " when b.chengshi='文山州'     then   '文山'\n" +
            " when b.chengshi='昭通市'     then   '昭通'\n" +
            " when b.chengshi='保山市'     then   '保山'\n" +
            " when b.chengshi='临沧市'     then   '临沧'\n" +
            " when b.chengshi='楚雄州'     then   '楚雄'\n" +
            " when b.chengshi='曲靖市'     then   '曲靖'\n" +
            " when b.chengshi='普洱市'     then   '普洱'\n" +
            " when b.chengshi='西双版纳州' then   '版纳'\n" +
            " when b.chengshi='玉溪市'     then   '玉溪'\n" +
            " when b.chengshi='丽江市'     then   '丽江'\n" +
            " when b.chengshi='昆明市'     then   '昆明'\n" +
            " when b.chengshi='红河州'     then   '红河'\n" +
            " when b.chengshi='怒江州'     then   '怒江'\n" +
            " when b.chengshi='迪庆州'     then   '迪庆'\n" +
            " when b.chengshi='大理州'     then   '大理'\n" +
            " when b.chengshi='德宏州'     then   '德宏'\n"+
            "  else   '其它'  END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
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
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '版纳' \n" +
            " when b.chengshi='文山州'     then   '文山'\n" +
            " when b.chengshi='昭通市'     then   '昭通'\n" +
            " when b.chengshi='保山市'     then   '保山'\n" +
            " when b.chengshi='临沧市'     then   '临沧'\n" +
            " when b.chengshi='楚雄州'     then   '楚雄'\n" +
            " when b.chengshi='曲靖市'     then   '曲靖'\n" +
            " when b.chengshi='普洱市'     then   '普洱'\n" +
            " when b.chengshi='西双版纳州' then   '版纳'\n" +
            " when b.chengshi='玉溪市'     then   '玉溪'\n" +
            " when b.chengshi='丽江市'     then   '丽江'\n" +
            " when b.chengshi='昆明市'     then   '昆明'\n" +
            " when b.chengshi='红河州'     then   '红河'\n" +
            " when b.chengshi='怒江州'     then   '怒江'\n" +
            " when b.chengshi='迪庆州'     then   '迪庆'\n" +
            " when b.chengshi='大理州'     then   '大理'\n" +
            " when b.chengshi='德宏州'     then   '德宏'\n"+
            "  else   '其它'  END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +

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
            "select b.wldwid, CASE WHEN b.chengshi='楚雄彝族自治州' THEN '楚雄'\n" +
            "       WHEN b.chengshi='大理白族自治州' THEN '大理'\n" +
            "       WHEN b.chengshi='德宏傣族景颇族自治州' THEN '德宏'\n" +
            "       WHEN b.chengshi='迪庆藏族自治州' THEN '迪庆'\n" +
            "       WHEN b.chengshi='红河哈尼族彝族自治州' THEN '红河'\n" +
            "       WHEN b.chengshi='思茅地区' THEN '普洱'\n" +
            "       WHEN b.chengshi='文山壮族苗族自治州' THEN '文山'\n" +
            "       WHEN b.chengshi='西双版纳傣族自治州' THEN '版纳' \n" +
            " when b.chengshi='文山州'     then   '文山'\n" +
            " when b.chengshi='昭通市'     then   '昭通'\n" +
            " when b.chengshi='保山市'     then   '保山'\n" +
            " when b.chengshi='临沧市'     then   '临沧'\n" +
            " when b.chengshi='楚雄州'     then   '楚雄'\n" +
            " when b.chengshi='曲靖市'     then   '曲靖'\n" +
            " when b.chengshi='普洱市'     then   '普洱'\n" +
            " when b.chengshi='西双版纳州' then   '版纳'\n" +
            " when b.chengshi='玉溪市'     then   '玉溪'\n" +
            " when b.chengshi='丽江市'     then   '丽江'\n" +
            " when b.chengshi='昆明市'     then   '昆明'\n" +
            " when b.chengshi='红河州'     then   '红河'\n" +
            " when b.chengshi='怒江州'     then   '怒江'\n" +
            " when b.chengshi='迪庆州'     then   '迪庆'\n" +
            " when b.chengshi='大理州'     then   '大理'\n" +
            " when b.chengshi='德宏州'     then   '德宏'\n"+
            "  else   '其它'  END  AS chengshi , creattime  from wldwzl  b  LEFT JOIN jgwldwzl_bm c ON b.WLDWID=c.wldwid  AND  c.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            "\n" +
            "\t\n" +
            ") c  on a.wldwid=c.wldwid\n" +
            "inner join spzl d(nolock) on b.spid=d.spid\n" +
            "where    a.djbh like 'XH%' and a.djbs in ('XHC','XHH','XHF')\n" +
            "     and a.bmname like rtrim('电商事业部') +'%'\n" +

            "     and a.rq between '2019-06-01' and '2019-12-31'\n" +
            "     \n" +
            "   and  NOT EXISTS(SELECT 1 FROM jgwldwzl_bm WHERE bmid IN ('BMZ00000085','BMZ00000069') and wldwid=c.wldwid AND creattime>c.creattime  )    \n" +

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
