package com.sumuxi;

import com.sumuxi.controller.MyController;
import com.sumuxi.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main3 {

    public static void main(String[] args) {


        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-3.xml");

        //3.利用xml配置文件指定component-scan扫描的包路径，通过包扫描的方式，扫描出所有 Component 和 Java配置类中的bean
        MyController myController = ctx.getBean(MyController.class);
        System.out.println(myController.toString());

        User user1 = ctx.getBean("user1", User.class);
        System.out.println(user1.toString());

        User user2 = ctx.getBean("user2", User.class);
        System.out.println(user2.toString());

    }
}
