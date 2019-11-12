package com.oracat.dao;

import com.oracat.dao.provider.ReportDaySqlProvider;

import com.oracat.model.ReportDay;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface ReportDayDao {
    @SelectProvider(type= ReportDaySqlProvider.class,method="selectWhitParam")
    List<ReportDay> selectByPage(Map<String, Object> params);
    @SelectProvider(type=ReportDaySqlProvider.class,method="count")
    Integer count(Map<String, Object> params);
}
