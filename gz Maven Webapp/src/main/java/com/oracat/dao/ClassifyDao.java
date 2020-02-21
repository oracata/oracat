package com.oracat.dao;

import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;

import java.util.List;

public interface ClassifyDao {
    public List<Classify> queryAllClassify(Classify classify);
    public List<Classify> queryfenleibybm(Classify classify);

    public List<Classify> queryfenlei1(Classify classify);

    public List<Classify> queryfenlei2(Classify classify);
    public List<Classify> queryfenlei3(Classify classify);
    public List<Classify> queryfenlei4(Classify classify);

    public List<Classify> queryfenleibm(Classify classify);

    public List<Classify> queryByFlbm(String flbm);

    public int insertFenlei(Classify classify);
    public int updateFenlei(Classify classify);
}
