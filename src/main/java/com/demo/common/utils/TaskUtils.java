package com.demo.common.utils;
import com.demo.domain.ScheduleJob;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author admin
 * @date 2017-11-21 ���� 16:07
 */
public class TaskUtils {
    public static Logger log = LoggerFactory.getLogger(TaskUtils.class);
    public static void invokeMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class clazz = null;
        if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                log.error("CatchException:",e);
            }
        }
        if (object == null) {
            log.error("�������� = [" + scheduleJob.getJobName() + "]---------------δ�����ɹ��������Ƿ�������ȷ������");
            System.out.println("�������� = [" + scheduleJob.getJobName() + "]---------------δ�����ɹ��������Ƿ�������ȷ������");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
        } catch (NoSuchMethodException e) {
            log.error("�������� = [" + scheduleJob.getJobName() + "]---------------δ�����ɹ������������ô��󣡣���");
            System.out.println("�������� = [" + scheduleJob.getJobName() + "]---------------δ�����ɹ������������ô��󣡣���");
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
        log.info("�������� = [" + scheduleJob.getJobName() + "]----------�����ɹ�");
    }
}
