package com.oracat.dao;

import com.oracat.model.B2bPrice;
import com.oracat.model.JobandTrigger;
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



    public List<JobandTrigger> queryAllJob(JobandTrigger jobandTrigger);


    @Insert(" insert into  jobandtrigger    select  '${job_name}','${job_group}','${job_class_name}','${trigger_name}','${trigger_name}','${trigger_group}','${repeat_interval}',seq_nextval('jobandtriggers')  from dual  \n"  )
    int insertJob(@Param("job_name")          String job_name,
                  @Param("job_group")    String job_group,
                  @Param("job_class_name")   String job_class_name,
                  @Param("trigger_name") String trigger_name,
                  @Param("trigger_group")    String                    trigger_group,
                  @Param("repeat_interval")   int                     repeat_interval

    );




}
