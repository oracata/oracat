package com.oracat.service;

import com.oracat.model.Classify;
import com.oracat.model.ScheduleJob;
import com.oracat.util.DataGridView;

public interface ClassifyService {

    public DataGridView selectClassify(Classify classify);
    public DataGridView findFenleibybm(Classify classify);
    public DataGridView findFenlei1(Classify classify);
}
