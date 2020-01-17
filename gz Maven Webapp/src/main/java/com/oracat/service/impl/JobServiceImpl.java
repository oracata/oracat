package com.oracat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oracat.dao.*;
import com.oracat.model.JobandTrigger;
import com.oracat.service.JobService;
import com.oracat.util.DataGridView;
import com.oracat.util.DynamicDataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT)
@Service("JobService")
public class JobServiceImpl implements JobService {

    /**
     * 自动注入持久层Dao对象
     * */



    @Autowired  //自动装配
    private JobDao jobDao ;

    @Override
    public DataGridView selectAllJobAndTrigger(JobandTrigger jobandTrigger)
    {
        DynamicDataSourceHolder.setDataSource("mysql");
        Page<Object> page= PageHelper.startPage(jobandTrigger.getPage(),jobandTrigger.getLimit());
        List<JobandTrigger> data=jobDao.queryAllJob(jobandTrigger);
        return new DataGridView(page.getTotal(),data);

    }






    @Override
    public void addJobandTrigger(JobandTrigger jobandTrigger) {
        DynamicDataSourceHolder.setDataSource("mysql");
        jobDao.insertJob(jobandTrigger.getJob_name(),
                jobandTrigger.getJob_group(),
                jobandTrigger.getJob_class_name(),
                jobandTrigger.getTrigger_name(),
                jobandTrigger.getTrigger_group(),
                jobandTrigger.getRepeat_interval()
        );
    }

    @Override
    public void deleteJobandTrigger(String identity) {

    }

    @Override
    public void deleteBatchJobandTrigger(String[] identitys) {

    }

    @Override
    public void updateJobandTrigger(JobandTrigger JobandTrigger) {

    }

    @Override
    public JobandTrigger queryJobandTriggerByIdentity(String identity) {
        return null;
    }

    /*
    @Override
    public void deleteJobandTrigger(String identity) {
        DynamicDataSourceHolder.setDataSource("mysql");
   jobDao.deleteById(identity);
    }

    @Override
    public void deleteBatchJobandTrigger(String[] identitys) {
        DynamicDataSourceHolder.setDataSource("mysql");
       for (String identity : identitys) {
           jobDao.deleteJobandTrigger(identity);
       }
    }

    @Override
    public void updateJobandTrigger(JobandTrigger JobandTrigger) {
        DynamicDataSourceHolder.setDataSource("mysql");
       jobDao.updateById(JobandTrigger);
    }

    @Override
    public JobandTrigger queryJobandTriggerByIdentity(String identity) {
        DynamicDataSourceHolder.setDataSource("mysql");
      return jobDao.selectById(identity);
    }


*/

}
