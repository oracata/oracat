package com.oracat.service;

import com.oracat.model.JobandTrigger;
import com.oracat.util.DataGridView;

import java.util.List;

public interface JobService {
    /**����**/
    public DataGridView selectAllJobAndTrigger(JobandTrigger jobandTrigger);








    /**
     * ��ӿͻ�
     * @param
     */
    void addJobandTrigger(JobandTrigger JobandTrigger);




    /**
     * ����ɾ���ͻ�
     * @param
     */
    void deleteJobandTrigger(String identity);

    /**
     * ����idɾ���ͻ�
     * @param
     */
    void deleteBatchJobandTrigger(String[] identitys);


    /**
     * �޸Ŀͻ�
     * @param
     */
    void updateJobandTrigger(JobandTrigger JobandTrigger);


    /**
     * �������֤�Ų�ѯ�ͻ���Ϣ
     * @param
     * @return
     */
    JobandTrigger queryJobandTriggerByIdentity(String identity);

}
