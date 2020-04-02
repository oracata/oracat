package com.oracat.dao;

import com.oracat.model.Classify;
import com.oracat.model.KhjTask;

import java.util.List;

public interface TaskDao {
    public List<KhjTask> queryAllTask(KhjTask khjTask);
    public List<KhjTask> selectAllTask();
    public List<KhjTask> queryTaskByid(KhjTask khjTask);


    public int insertTask(KhjTask khjTask);
    public int updateTask(KhjTask khjTask);
    public   int deleteByPrimaryKey(String id);

}
