package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.Goods;
import com.oracat.model.RealTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface B2bPriceDao {

    @Select("SELECT  a.id,a.no,a.name ,a.spec,a.manufacturer ,a.pfpj,round(c.cankcbj,2) AS cankcbj,c.zdxshj AS zdxshj,d.hshj,round(e.xsj,2) as xsj,round(f.xsj,2) as zy_xsj,round((a.pfpj- d.hshj)*100/ d.hshj,2) as abs_rate,b.stock_num\n" +
            "  FROM openquery(b2b,'select * from   goods') a\n" +
            "INNER JOIN openquery(b2b,'select  * from  mv_khlb_kc_hshj') b  ON a.id=b.ID AND b.kehulb=1\n" +
            "LEFT JOIN \n" +
            "(select a.spid,\n" +
            "dbo.get_jgtx_cankcbj2('000','HZZ00000001',a.spid,'合格','BMZ00000069','CEN00000011','B','','WLD00004766','','',case when isnull(e.hshj,0)>0 then isnull(e.hshj,0) else f.chbdj*(1+a.shlv/100) end,'cankcbj')*c.xsbzjlgg*1.000000 as cankcbj,\n" +
            "round(dbo.get_jgtx('HZZ00000001',a.spid,'000','BMZ00000069','','B','WLD00004766','zdshj','CEN00000011')*c.xsbzjlgg,4) zdxshj \n" +
            " from spzl_v a(nolock) inner join sp_xsbzgx c(nolock) on a.spid=c.spid and c.is_xsbz='是'  left outer join (select spid,hshj,hshgj,shl,lasttime from lshshjb(nolock) where wldwid='WLD00004766' and bmid='BMZ00000069' and hzid='HZZ00000001' ) d  on a.spid=d.spid \n" +
            "left join SaleTaskBmHshj e(nolock) on e.jigid='000'and e.hzid='HZZ00000001' and e.centerid='CEN00000011' and a.spid=e.spid \n" +
            "inner join zkspkc_hz_v1 f(nolock) on a.spid=f.spid and f.hzid='HZZ00000001' and f.jigid='000' left join centerbkspzl ce(nolock)on a.spid=ce.spid and ce.jigid='000' and ce.hzid='HZZ00000001' and ce.centerid='CEN00000011'\n" +
            "where a.beactive='是' and a.jigid='000' and dbo.hzid_get_jgkc('HZZ00000001',a.spid,'000','全部','是 ')>0   \n" +
            ") c  ON a.id=c.spid\n" +
            "left join (SELECT b.spid,round(AVG(b.hshj),2) hshj\n" +
            "  FROM gxkphz(NOLOCK)a  INNER JOIN gxkpmx(NOLOCK) b ON a.djbh=b.djbh\n" +
            "WHERE a.centerid='CEN00000011' AND bmid='BMZ00000069'\n" +
            "AND kaiprq BETWEEN CONVERT(varchar(100), GETDATE()-7, 23) AND  CONVERT(varchar(100), GETDATE(), 23) \n" +
            "and a.djbs in ('XHB')  and b.is_zx<>'清'  \n"+
            "GROUP BY b.spid ) d on a.id=d.spid  \n"+

            "left join (SELECT spbm,xsj FROM zxiang_jg  WHERE DATE IN (SELECT MAX(DATE) FROM zxiang_jg) ) e on a.no=e.spbm  \n"+
            "left join (SELECT spbm,xsj FROM zying_jg  WHERE DATE IN (SELECT MAX(DATE) FROM zying_jg) ) f on a.no=f.spbm  \n"+

            "WHERE a.state=1  \n" +
            "AND ( a.id LIKE '%${id}%' and a.no LIKE '%${no}%' and a.name LIKE '%${name}%' )\n" +
            "ORDER BY  e.spbm desc, f.spbm desc,  ABS((a.pfpj- d.hshj)*100/ d.hshj)   DESC , b.stock_num DESC ,c.cankcbj DESC  \n ")
    List<B2bPrice> selectB2bPrice(@Param("id") String id,
                                  @Param("no") String no,
                                  @Param("name") String name);




    @Select("SELECT  a.id,a.no,a.name ,a.spec,a.manufacturer ,0 pfpj,round(c.cankcbj,2) AS cankcbj,c.zdxshj AS zdxshj,d.hshj,e.xsj,round((0- d.hshj)*100/ d.hshj,2) as abs_rate,b.stock_num\n" +
            "  FROM (  SELECT   a.spid id,a.spbh no,a.spmch name,a.shpgg spec,a.shengccj manufacturer\n" +
            "   FROM ds_spzl_v1(nolock) a left JOIN ds_spzl_hshj_jyjg(nolock) g ON a.spid=g.spid   \n" +
            " WHERE g.hshj IS NULL AND a.flag=0) a\n" +
            "INNER JOIN openquery(b2b,'select  * from  mv_khlb_kc_hshj') b  ON a.id=b.ID AND b.kehulb=1\n" +
            "LEFT JOIN \n" +
            "(select a.spid,\n" +
            "dbo.get_jgtx_cankcbj2('000','HZZ00000001',a.spid,'合格','BMZ00000069','CEN00000011','B','','WLD00004766','','',case when isnull(e.hshj,0)>0 then isnull(e.hshj,0) else f.chbdj*(1+a.shlv/100) end,'cankcbj')*c.xsbzjlgg*1.000000 as cankcbj,\n" +
            "round(dbo.get_jgtx('HZZ00000001',a.spid,'000','BMZ00000069','','B','WLD00004766','zdshj','CEN00000011')*c.xsbzjlgg,4) zdxshj \n" +
            " from spzl_v a(nolock) inner join sp_xsbzgx c(nolock) on a.spid=c.spid and c.is_xsbz='是'  left outer join (select spid,hshj,hshgj,shl,lasttime from lshshjb(nolock) where wldwid='WLD00004766' and bmid='BMZ00000069' and hzid='HZZ00000001' ) d  on a.spid=d.spid \n" +
            "left join SaleTaskBmHshj e(nolock) on e.jigid='000'and e.hzid='HZZ00000001' and e.centerid='CEN00000011' and a.spid=e.spid \n" +
            "inner join zkspkc_hz_v1 f(nolock) on a.spid=f.spid and f.hzid='HZZ00000001' and f.jigid='000' left join centerbkspzl ce(nolock)on a.spid=ce.spid and ce.jigid='000' and ce.hzid='HZZ00000001' and ce.centerid='CEN00000011'\n" +
            "where a.beactive='是' and a.jigid='000' and dbo.hzid_get_jgkc('HZZ00000001',a.spid,'000','全部','是 ')>0   \n" +
            ") c  ON a.id=c.spid\n" +
            "left join (SELECT b.spid,round(AVG(b.hshj),2) hshj\n" +
            "  FROM gxkphz(NOLOCK)a  INNER JOIN gxkpmx(NOLOCK) b ON a.djbh=b.djbh\n" +
            "WHERE a.centerid='CEN00000011' AND bmid='BMZ00000069'\n" +
            "AND kaiprq BETWEEN CONVERT(varchar(100), GETDATE()-7, 23) AND  CONVERT(varchar(100), GETDATE(), 23) \n" +
            "and a.djbs in ('XHB')  and b.is_zx<>'清'  \n"+
            "GROUP BY b.spid ) d on a.id=d.spid  \n"+

            "left join (SELECT spbm,xsj FROM zxiang_jg  WHERE DATE IN (SELECT MAX(DATE) FROM zxiang_jg) ) e on a.no=e.spbm  \n"+

            " \n" +
            "ORDER BY  b.stock_num DESC ,c.cankcbj DESC  \n ")
    List<B2bPrice> selectPriceNotin();




}
