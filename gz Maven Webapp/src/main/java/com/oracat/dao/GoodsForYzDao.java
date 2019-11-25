package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.GoodsForYz;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GoodsForYzDao {

    @Select("select jnd_spid,jnd_spname,yz_goods_id,yz_goods_name  from goods_for_yz \n" +
            "WHERE 1=1  \n" +
            "AND ( jnd_spid LIKE '%${jnd_spid}%' and jnd_spname LIKE '%${jnd_spname}%'  )\n"  )
    List<GoodsForYz> selectGoodsForYz(@Param("jnd_spid") String jnd_spid,
                                    @Param("jnd_spname") String jnd_spname );


    @Select("select jnd_spid,jnd_spname,yz_goods_id,yz_goods_name  from goods_for_yz \n" +
            "WHERE 1=1  \n" +
            "AND ( jnd_spid='${jnd_spid}'  )\n"  )
    GoodsForYz findGoodsForYzById(@Param("jnd_spid") String jnd_spid);


    @Update("update goods_for_yz  set jnd_spid='${jnd_spid}',jnd_spname='${jnd_spname}',yz_goods_id='${yz_goods_id}',yz_goods_name='${yz_goods_name}'  \n" +
            "WHERE 1=1  \n" +
            "AND ( jnd_spid='${jnd_spid}'  )\n"  )
    int  modifyGoodsForYz(@Param("jnd_spid") String jnd_spid,
                     @Param("jnd_spname") String jnd_spname,
                     @Param("yz_goods_id") String yz_goods_id,
                     @Param("yz_goods_name") String yz_goods_name
                     );

    @Insert(" insert into  goods_for_yz    select  '${jnd_spid}','${jnd_spname}','${yz_goods_id}','${yz_goods_name}'  from dual  where '${jnd_spid}' not in (select jnd_spid  from  goods_for_yz )\n"  )
      int addGoodsForYz(@Param("jnd_spid") String jnd_spid,
                          @Param("jnd_spname") String jnd_spname,
                          @Param("yz_goods_id") String yz_goods_id,
                          @Param("yz_goods_name") String yz_goods_name
    );


    @Delete("delete  from  goods_for_yz     \n" +
            "WHERE  jnd_spid='${jnd_spid}'  \n"  )
    int  deleteGoodsForYz(@Param("jnd_spid") String jnd_spid);


    @Select("select  a.id ,a.name from jnd_goods a \n" +
            "INNER JOIN jnd_kc_hshj e on a.id=e.id   and e.date='${date}'\n" +
            "where a.date='${date}' and a.STATE=1 AND e.kehulb='1' and e.stock_num>0\n" +
            "and a.id not in (\n" +
            "select jnd_spid from goods_for_yz )\n" +
            "and a.id not in (\n" +
            "select jnd_spid from goods_for_yz_nofind\n" +
            ") order by e.stock_num desc  limit 1\n"  )
    List<Map<String,String>> findGoodsForYzNotin(@Param("date") String date);



}
