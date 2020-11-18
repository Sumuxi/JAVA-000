package com.sumuxi;

import com.sumuxi.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main1 {
    public static void main(String[] args) {

        //创建Spring上下文（加载xml配置文件）
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-1.xml");

        //1.通过xml配置文件完成user对象的装配

        //获取User实例, 名称和xml中bean的id一致
        User user = ctx.getBean("user",User.class);
        System.out.println(user.toString());
    }
}
