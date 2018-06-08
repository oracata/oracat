package com.demo.service;

import com.demo.common.result.BootstrapTableResult;
import com.demo.dao.ScheduleJobMapper;
import com.demo.domain.ScheduleJob;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 * @date 2017-11-25 18:51
 */

public interface ScheduleJobService {
    /**
     * 查询所有的定时任务
     * @param pageSize
     * @param pageNumber
     * @return BootstrapTableResult
     */
    BootstrapTableResult listAllJob(int pageSize, int pageNumber);

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

    void addScheduleJob(ScheduleJob scheduleJob) throws Exception;
}
