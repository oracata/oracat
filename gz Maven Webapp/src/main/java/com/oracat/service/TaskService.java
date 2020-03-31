package com.oracat.service;

import com.oracat.model.Classify;
import com.oracat.model.KhjTask;
import com.oracat.util.DataGridView;

public interface TaskService {
    public DataGridView selectTask(KhjTask khjTask);
    public DataGridView findTaskByid(KhjTask khjTask);
}
