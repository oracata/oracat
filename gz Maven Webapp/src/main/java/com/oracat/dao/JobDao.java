package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.JobandTrigger;
import com.oracat.model.ScheduleJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobDao {
    @Select("     SELECT\n" +
            "            qrtz_job_details.JOB_NAME,\n" +
            "            qrtz_job_details.JOB_GROUP,\n" +
            "            qrtz_job_details.JOB_CLASS_NAME,\n" +
            "            qrtz_triggers.TRIGGER_NAME,\n" +
            "            qrtz_triggers.TRIGGER_GROUP,\n" +
            "            qrtz_simple_triggers.REPEAT_INTERVAL,\n" +
            "            qrtz_simple_triggers.TIMES_TRIGGERED\n" +
            "        FROM\n" +
            "            qrtz_job_details\n" +
            "        JOIN qrtz_triggers\n" +
            "        JOIN qrtz_simple_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME\n" +
            "        AND qrtz_triggers.TRIGGER_NAME = qrtz_simple_triggers.TRIGGER_NAME\n" +
            "        AND qrtz_triggers.TRIGGER_GROUP = qrtz_simple_triggers.TRIGGER_GROUP\n" +
            "   \n ")
    List<JobandTrigger> getJobAndTrigger();



    @Select("     SELECT count(*) \n" +
            "        FROM\n" +
            "            qrtz_job_details\n" +
            "        JOIN qrtz_triggers\n" +
            "        JOIN qrtz_simple_triggers ON qrtz_job_details.JOB_NAME = qrtz_triggers.JOB_NAME\n" +
            "        AND qrtz_triggers.TRIGGER_NAME = qrtz_simple_triggers.TRIGGER_NAME\n" +
            "        AND qrtz_triggers.TRIGGER_GROUP = qrtz_simple_triggers.TRIGGER_GROUP\n" +
            "   \n ")
    int queryJobCount();



    public List<ScheduleJob> queryAllJob(ScheduleJob scheduleJob);
    public List<ScheduleJob> listAllJob();

    /**
     * 更新定时任务状态
     * @param scheduleJob
     */
    void updateJobStatusById(ScheduleJob scheduleJob);

    /**
     * 根据主键查询定时任务
     * @param id
     * @return ScheduleJob
     */
    ScheduleJob getScheduleJobByPrimaryKey(int id);

    /**
     * 更新时间表达式
     * @param scheduleJob
     */
    void updateJobCronExpressionById(ScheduleJob scheduleJob);

    /**
     * 添加定时任务
     * @param scheduleJob
     */
    void addScheduleJob(ScheduleJob scheduleJob);








    @Insert(" insert into  jobandtrigger    select  '${job_name}','${job_group}','${job_class_name}','${trigger_name}','${trigger_name}','${trigger_group}','${repeat_interval}',seq_nextval('jobandtriggers')  from dual  \n"  )
    int insertJob(@Param("job_name")          String job_name,
                  @Param("job_group")    String job_group,
                  @Param("job_class_name")   String job_class_name,
                  @Param("trigger_name") String trigger_name,
                  @Param("trigger_group")    String                    trigger_group,
                  @Param("repeat_interval")   int                     repeat_interval

    );



    @Delete(" insert into  jobandtrigger    select  '${job_name}','${job_group}','${job_class_name}','${trigger_name}','${trigger_name}','${trigger_group}','${repeat_interval}',seq_nextval('jobandtriggers')  from dual  \n"  )
    int deleteJob(@Param("job_name")          String job_name,
                  @Param("job_group")    String job_group,
                  @Param("job_class_name")   String job_class_name,
                  @Param("trigger_name") String trigger_name,
                  @Param("trigger_group")    String                    trigger_group,
                  @Param("repeat_interval")   int                     repeat_interval

    );





}
