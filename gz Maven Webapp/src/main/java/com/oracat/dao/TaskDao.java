package com.oracat.dao;

import com.oracat.model.Classify;
import com.oracat.model.KhjTask;

import java.util.List;

public interface TaskDao {
    public List<KhjTask> queryAllTask(KhjTask khjTask);
    public List<KhjTask> queryTaskByid(KhjTask khjTask);
}
