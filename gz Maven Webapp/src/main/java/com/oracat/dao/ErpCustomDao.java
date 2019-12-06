package com.oracat.dao;

import com.oracat.model.ErpCustom;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ErpCustomDao {


    @Select(" SELECT shengfen,chengshi,quyufl,kehulb,a.wldwid,wldwbh,wldwname,a.lxdh,lxr,ds_lxr  ,  ds_lxdh ,  is_dssc ,c.shouhr,c.lxdh AS shr_lxdh,d.kpman\n" +
            "  FROM wldwzl(NOLOCK) a \n" +
            "left join jgwldwzl_bm(NOLOCK) d ON a.WLDWID=d.wldwid  AND  d.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            " LEFT JOIN (\n" +
            " \n" +
            "SELECT b.wldwid,a.shouhr,a.lxdh\n" +
            "  FROM ywfzrshxx(NOLOCK)  a  INNER  JOIN ywfzrzl(NOLOCK) b ON a.ywfzrid=b.ywfzrid\n" +
            "WHERE NOT EXISTS (SELECT 1 FROM ywfzrshxx(NOLOCK)  c WHERE a.ywfzrid=c.ywfzrid and c.lasttime>a.lasttime AND c.shid>a.shid)\n" +
            "AND NOT EXISTS (SELECT 1 FROM ywfzrshxx(NOLOCK)  c WHERE a.ywfzrid=c.ywfzrid   AND c.shid>a.shid)\n" +
            " \t\n" +
            " )c ON a.wldwid=c.wldwid\n" +
            " \n" +
            "  WHERE a.wldwid IN (\n" +
            " SELECT wldwid FROM wldwzl  WHERE wldwid IN \n" +
            " (\n" +
            " SELECT DISTINCT wldwid FROM gxkphz(NOLOCK) WHERE djbs='XHB' AND bmid IN ('BMZ00000069','BMZ00000060') AND is_zx<>'清'\n" +
            " AND rq BETWEEN '${begin_date}' AND '${end_date}')\n" +
            " EXCEPT \n" +
            " \n" +
            " SELECT DISTINCT  \n" +
            "  enterprise_id AS WLDWID \n" +
            " from openquery(b2b,'select * from enterprise_custom') a \n" +
            "WHERE   a.state=2\n" +
            " )\n" +
            " AND  beactive='是' AND   fuzr<>'耿晓琴' and  is_dssc = '否' and   isxs='是'\n"  )
    List<ErpCustom> selectErpCustom(@Param("begin_date") String begin_date,
                                     @Param("end_date") String end_date  );



    @Select("  SELECT shengfen,chengshi,quyufl,kehulb,a.wldwid,wldwbh,wldwname,a.lxdh,lxr,ds_lxr  ,  ds_lxdh ,  is_dssc ,c.shouhr,c.lxdh AS shr_lxdh ,d.kpman\n" +
            "   FROM wldwzl(NOLOCK) a \n" +
            "left join jgwldwzl_bm(NOLOCK) d ON a.WLDWID=d.wldwid  AND  d.bmid IN ('BMZ00000085','BMZ00000069') \n" +
            " LEFT JOIN (\n" +
            " \n" +
            "SELECT b.wldwid,a.shouhr,a.lxdh\n" +
            "  FROM ywfzrshxx(NOLOCK)  a  INNER  JOIN ywfzrzl(NOLOCK) b ON a.ywfzrid=b.ywfzrid\n" +
            "WHERE NOT EXISTS (SELECT 1 FROM ywfzrshxx(NOLOCK)  c WHERE a.ywfzrid=c.ywfzrid and c.lasttime>a.lasttime AND c.shid>a.shid)\n" +
            "AND NOT EXISTS (SELECT 1 FROM ywfzrshxx(NOLOCK)  c WHERE a.ywfzrid=c.ywfzrid   AND c.shid>a.shid)\n" +
            " \t\n" +
            " )c ON a.wldwid=c.wldwid\n" +
            " \n" +
            "  WHERE a.wldwid IN (\n" +
            " select  wldwid FROM wldwzl a(NOLOCK)\n" +
            "WHERE jigid='000' AND a.beactive='是' AND isxs='是'    AND oldcode<>'' AND shenhr_zlfzr<>'' AND shenhyj_zlfzr LIKE'%同意%'\n" +
            " EXCEPT \n" +
            " \n" +
            " SELECT DISTINCT  \n" +
            "  enterprise_id AS WLDWID \n" +
            " from openquery(b2b,'select * from enterprise_custom') a \n" +
            "WHERE      a.state=2 \n" +
            " )\n" +
            " AND  beactive='是' AND    isxs='是'  and is_dssc = '否';\n"  )
    List<ErpCustom> selectFgsCustom();

    @Update("update a set a.ds_lxr = '${ds_lxr}', a.ds_lxdh = '${ds_lxdh}', a.is_dssc = '是' , a.lxr='${lxr}',a.lxdh='${lxdh}' \n" +
            "FROM wldwzl a(NOLOCK)\n" +
            "WHERE a.wldwid = '${wldwid}' AND  a.is_dssc = '否'\n"  )
    int  modifyErpCustom(@Param("wldwid") String wldwid,
                          @Param("lxdh") String lxdh,
                          @Param("lxr") String lxr,
                          @Param("ds_lxdh") String ds_lxdh,
                          @Param("ds_lxr") String ds_lxr
    );

}
