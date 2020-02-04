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
     * ��ѯ���еĶ�ʱ����
     * @param pageSize
     * @param pageNumber
     * @return BootstrapTableResult
     */
    BootstrapTableResult listAllJob(int pageSize, int pageNumber);

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
