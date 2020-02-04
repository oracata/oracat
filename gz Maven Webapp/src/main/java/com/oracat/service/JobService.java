package com.oracat.service;

import com.oracat.model.JobandTrigger;
import com.oracat.model.ScheduleJob;
import com.oracat.util.DataGridView;

import java.util.List;

public interface JobService {
    /**调度**/
    public DataGridView selectAllJobAndTrigger(ScheduleJob scheduleJob);







    /**
     * 暂停定时任务
     * @param jobId
     */
    void pauseJob(int jobId);

    /**
     * 恢复一个定时任务
     * @param jobId
     */
    void resumeJob(int jobId);

    /**
     * 立即执行一个定时任务
     * @param jobId
     */
    void runOnce(int jobId);

    /**
     * 更新时间表达式
     * @param id
     * @param cronExpression
     */
    void updateCron(int id, String cronExpression);

    /**
     * 添加定时任务
     * @param scheduleJob
     * @throws Exception
     */
    void addScheduleJob(ScheduleJob scheduleJob) throws Exception;

}
