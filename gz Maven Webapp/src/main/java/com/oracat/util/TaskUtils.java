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
            log.error("�������� = [" + scheduleJob.getJob_name() + "]---------------δ�����ɹ��������Ƿ�������ȷ������");
            System.out.println("�������� = [" + scheduleJob.getJob_name() + "]---------------δ�����ɹ��������Ƿ�������ȷ������");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethod_name());
        } catch (NoSuchMethodException e) {
            log.error("�������� = [" + scheduleJob.getJob_name() + "]---------------δ�����ɹ������������ô��󣡣���");
            System.out.println("�������� = [" + scheduleJob.getJob_name() + "]---------------δ�����ɹ������������ô��󣡣���");
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
        log.info("�������� = [" + scheduleJob.getJob_name() + "]----------�����ɹ�");
    }
}
