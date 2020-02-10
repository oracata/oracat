package com.oracat.service;


import com.oracat.model.ScheduleJob;
import com.oracat.util.DataGridView;

import java.util.List;

public interface JobService {
    /**调度**/
    public DataGridView selectAllJobAndTrigger(ScheduleJob scheduleJob);







    /**
     * 暂停定时任务,重启容器后仍然会执行。
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
     * 执行一个定时任务
     * @param jobId
     */
    void runJob(int jobId);

    /**
     * 停止一个定时任务，重启服务也不会执行
     * @param jobId
     */
    void stopJob(int jobId);

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
