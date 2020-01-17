package com.oracat.service;

import com.oracat.model.JobandTrigger;
import com.oracat.util.DataGridView;

import java.util.List;

public interface JobService {
    /**调度**/
    public DataGridView selectAllJobAndTrigger(JobandTrigger jobandTrigger);








    /**
     * 添加客户
     * @param
     */
    void addJobandTrigger(JobandTrigger JobandTrigger);




    /**
     * 批量删除客户
     * @param
     */
    void deleteJobandTrigger(String identity);

    /**
     * 根据id删除客户
     * @param
     */
    void deleteBatchJobandTrigger(String[] identitys);


    /**
     * 修改客户
     * @param
     */
    void updateJobandTrigger(JobandTrigger JobandTrigger);


    /**
     * 根据身份证号查询客户信息
     * @param
     * @return
     */
    JobandTrigger queryJobandTriggerByIdentity(String identity);

}
