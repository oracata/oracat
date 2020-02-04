package com.oracat.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements ApplicationContextAware {
    /**
     * SpringӦ�������Ļ���
     */
    public static ApplicationContext applicationContext;

    /**
     * ʵ��ApplicationContextAware�ӿڵĻص����������������Ļ���
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * ��ȡ���� ������д��bean����������Ҫ����
     * @param name
     * @return Object һ������������ע���bean��ʵ��
     * @throws BeansException
     */
    public static <T> T  getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * ��ȡ����ΪrequiredType�Ķ���
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
//    public static <T> T getBean(String name, Class<T> requiredType) {
//        return applicationContext.getBean(name, requiredType);
//    }

    /**
     * ���BeanFactory����һ������������ƥ���bean���壬�򷵻�true
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * �ж��Ը�������ע���bean������һ��singleton����һ��prototype��
     * ��������������Ӧ��bean����û�б��ҵ��������׳�һ���쳣��NoSuchBeanDefinitionException��
     * @param name
     * @return boolean
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * @param name
     * @return Class ע����������
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     *
     */
    public static Class<? extends Object> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

}
