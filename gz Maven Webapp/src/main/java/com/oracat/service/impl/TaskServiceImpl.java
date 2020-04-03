package com.oracat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oracat.dao.ClassifyDao;
import com.oracat.dao.TaskDao;
import com.oracat.model.Classify;
import com.oracat.model.KhjTask;
import com.oracat.service.TaskService;
import com.oracat.util.DataGridView;
import com.oracat.util.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired  //×Ô¶¯×°Åä
    private TaskDao taskDao ;


    @Override
    public List<KhjTask> selectAllTask()
    {
        DynamicDataSourceHolder.setDataSource("mysql");

        return taskDao.selectAllTask();


    }




    @Override
    public DataGridView selectTask(KhjTask khjTask)
    {
        DynamicDataSourceHolder.setDataSource("mysql");
        Page<Object> page= PageHelper.startPage(khjTask.getPage(),khjTask.getLimit());
        List<KhjTask> data=taskDao.queryAllTask(khjTask);
        return new DataGridView(page.getTotal(),data);

    }



    @Override
    public DataGridView findTaskByid(KhjTask khjTask)
    {
        DynamicDataSourceHolder.setDataSource("mysql");
        Page<Object> page= PageHelper.startPage(khjTask.getPage(),khjTask.getLimit());
        List<KhjTask> data=taskDao.queryTaskByid(khjTask);
        return new DataGridView(page.getTotal(),data);

    }


    @Override
    public void updateTask(KhjTask khjTask)
    {


        DynamicDataSourceHolder.setDataSource("mysql");

        List<KhjTask> data=taskDao.queryTaskByid(khjTask);
        if(data.size()>0) {

            DynamicDataSourceHolder.setDataSource("mysql");
            taskDao.updateTask(khjTask);
        }


    }



    @Override
    public void saveTask(KhjTask khjTask)
    {


        DynamicDataSourceHolder.setDataSource("mysql");

        List<KhjTask> data=taskDao.queryTaskByid(khjTask);
        if(data.size()==0){
            DynamicDataSourceHolder.setDataSource("mysql");
            taskDao.insertTask(khjTask);
        }


    }




    @Override
    public void deleteTask(String identity) {
        DynamicDataSourceHolder.setDataSource("mysql");
        taskDao.deleteByPrimaryKey(identity);
    }

    @Override
    public void deleteBatchTask(String[] identitys) {
        for (String identity : identitys) {
            deleteTask(identity);
        }
    }

}
