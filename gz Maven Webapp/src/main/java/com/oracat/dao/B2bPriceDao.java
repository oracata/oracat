package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.Goods;
import com.oracat.model.RealTime;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface B2bPriceDao {

    @Select("SELECT  a.id,a.no,a.name ,a.spec,a.manufacturer ,a.pfpj,round(c.cankcbj,2) AS cankcbj,c.zdxshj AS zdxshj,b.stock_num\n" +
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
            "WHERE a.state=1  \n" +
            "AND ( a.id LIKE '%${id}%' and a.no LIKE '%${no}%' and a.name LIKE '%${name}%' )\n" +
            "ORDER BY b.stock_num DESC ,c.cankcbj DESC  \n ")
    List<B2bPrice> selectB2bPrice(@Param("id") String id,
                                  @Param("no") String no,
                                  @Param("name") String name);



}
