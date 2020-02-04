package com.demo.dao;


import com.demo.domain.ScheduleJob;

import java.util.List;

/**
 * ��ʱ����
 * @author admin
 * @date 2017-11-20 ���� 15:52
 */
public interface ScheduleJobMapper {

    /**
     * ��ѯ���еĶ�ʱ����
     * @return List<ScheduleJob>
     */
    List<ScheduleJob> listAllJob();

    /**
     * ���¶�ʱ����״̬
     * @param scheduleJob
     */
    void updateJobStatusById(ScheduleJob scheduleJob);

    /**
     * ����������ѯ��ʱ����
     * @param id
     * @return ScheduleJob
     */
    ScheduleJob getScheduleJobByPrimaryKey(int id);

    /**
     * ����ʱ����ʽ
     * @param scheduleJob
     */
    void updateJobCronExpressionById(ScheduleJob scheduleJob);

    /**
     * ��Ӷ�ʱ����
     * @param scheduleJob
     */
    void addScheduleJob(ScheduleJob scheduleJob);
}