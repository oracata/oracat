package com.oracat.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskTest1 {
    public static final Logger LOGGER = LoggerFactory.getLogger(TaskTest1.class);

    public void run1(){
        System.out.println("ִ�з���1");
    }

    public void run2(){
        System.out.println("ִ�з���2");
    }

    public void run3(){
        System.out.println("ִ�з���3");
    }

    public void run4(){
        System.out.println("ִ�з���4");
    }


}
