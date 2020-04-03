package com.oracat.service;

import com.oracat.model.Classify;
import com.oracat.model.KhjTask;
import com.oracat.util.DataGridView;

import java.util.List;

public interface TaskService {
    public DataGridView selectTask(KhjTask khjTask);
    public List<KhjTask> selectAllTask();
    public DataGridView findTaskByid(KhjTask khjTask);
    public void updateTask(KhjTask khjTask);
    public void saveTask(KhjTask khjTask);


    void deleteTask(String identity);


    void deleteBatchTask(String[] identitys);

}
