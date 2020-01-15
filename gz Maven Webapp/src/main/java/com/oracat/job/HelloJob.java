package com.oracat.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class HelloJob implements Job {

    private static Logger log = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        log.error("Hello Job÷¥–– ±º‰: " + new Date());

    }
}
