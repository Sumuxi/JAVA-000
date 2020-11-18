package com.sumuxi;

import com.sumuxi.controller.MyController;
import com.sumuxi.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main4 {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.sumuxi");

        //4.类似于3，在Spring ApplicationContext指定要扫描的包，扫描出所有 Component 和 Java配置类中的bean
        MyController myController = ctx.getBean(MyController.class);
        System.out.println(myController.toString());

        User user1 = ctx.getBean("user1", User.class);
        System.out.println(user1.toString());

        User user2 = ctx.getBean("user2", User.class);
        System.out.println(user2.toString());
    }
}
