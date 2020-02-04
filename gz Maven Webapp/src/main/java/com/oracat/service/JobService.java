package com.oracat.service;

import com.oracat.model.JobandTrigger;
import com.oracat.model.ScheduleJob;
import com.oracat.util.DataGridView;

import java.util.List;

public interface JobService {
    /**����**/
    public DataGridView selectAllJobAndTrigger(ScheduleJob scheduleJob);







    /**
     * ��ͣ��ʱ����
     * @param jobId
     */
    void pauseJob(int jobId);

    /**
     * �ָ�һ����ʱ����
     * @param jobId
     */
    void resumeJob(int jobId);

    /**
     * ����ִ��һ����ʱ����
     * @param jobId
     */
    void runOnce(int jobId);

    /**
     * ����ʱ����ʽ
     * @param id
     * @param cronExpression
     */
    void updateCron(int id, String cronExpression);

    /**
     * ��Ӷ�ʱ����
     * @param scheduleJob
     * @throws Exception
     */
    void addScheduleJob(ScheduleJob scheduleJob) throws Exception;

}
