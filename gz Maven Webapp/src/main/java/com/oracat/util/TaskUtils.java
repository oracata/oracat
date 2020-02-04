package com.oracat.util;

import com.oracat.model.ScheduleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;

public class TaskUtils {
    public static Logger log = LoggerFactory.getLogger(TaskUtils.class);
    public static void invokeMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class clazz = null;
        if (StringUtils.isNotBlank(scheduleJob.getBean_class())) {
            try {
                clazz = Class.forName(scheduleJob.getBean_class());
                object = clazz.newInstance();
            } catch (Exception e) {
                log.error("CatchException:",e);
            }
        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJob_name() + "]---------------未启动成功，请检查是否配置正确！！！");
            System.out.println("任务名称 = [" + scheduleJob.getJob_name() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethod_name());
        } catch (NoSuchMethodException e) {
            log.error("任务名称 = [" + scheduleJob.getJob_name() + "]---------------未启动成功，方法名设置错误！！！");
            System.out.println("任务名称 = [" + scheduleJob.getJob_name() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        log.info("任务名称 = [" + scheduleJob.getJob_name() + "]----------启动成功");
    }
}
