package com.oracat.dao;

import com.oracat.dao.provider.ReportDaySqlProvider;

import com.oracat.model.ReportDay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface ReportDayDao {
    @SelectProvider(type= ReportDaySqlProvider.class,method="selectWhitParam")
    List<ReportDay> selectByPage(Map<String, Object> params);
    @SelectProvider(type=ReportDaySqlProvider.class,method="count")
    Integer count(Map<String, Object> params);

    //ÏÂÀ­¿ò
    @Select("SELECT distinct shengfen FROM report_b2b_data")
    List<String> selectShengfen();

    @Select("SELECT distinct chengshi FROM report_b2b_data where shengfen=#{shengfen}")
    List<String> selectChengshi(String shengfen);

    @Select("SELECT distinct quyufl FROM report_b2b_data where shengfen='${shengfen}' and chengshi='${chengshi}'")
    List<String> selectQuyufl(@Param("shengfen") String shengfen,
                              @Param("chengshi") String chengshi);
}
