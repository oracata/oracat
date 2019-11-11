package com.oracat.dao;

import com.oracat.model.RealTime;
import com.oracat.util.DynamicDataSourceHolder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportRealTimeDao {




        @Select("select * from report_b2b_data_realtime order by rq ")
        List<RealTime> selectRealTime();




}
