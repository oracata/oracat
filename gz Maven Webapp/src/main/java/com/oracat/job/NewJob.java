package com.oracat.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NewJob  implements Job {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    public NewJob() {

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.error("New Job÷¥–– ±º‰: " + new Date());

    }
}
