package com.sumuxi;

import com.sumuxi.controller.MyController;
import com.sumuxi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

public class Main5 {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.sumuxi");

        //5.在Spring ApplicationContext指定要扫描的包，扫描出所有 Component 和 Java配置类中的bean,
        // 在MyController通过@Autowired和@Resource注解告知Spring自动装配 User bean
        MyController myController = ctx.getBean(MyController.class);
        myController.test();

    }

}
