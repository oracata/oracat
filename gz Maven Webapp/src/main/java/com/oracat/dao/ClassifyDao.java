package com.oracat.dao;

import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;

import java.util.List;

public interface ClassifyDao {
    public List<Classify> queryAllClassify(Classify classify);
}
