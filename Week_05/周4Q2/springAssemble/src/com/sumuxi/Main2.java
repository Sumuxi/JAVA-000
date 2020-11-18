package com.sumuxi;

import com.sumuxi.config.MyConfig;
import com.sumuxi.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import({MyConfig.class})
public class Main2 {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main2.class);

        //2.通过java配置类中创建bean，放入了Spring IOC容器中
        User user1 = ctx.getBean("user1", User.class);
        System.out.println(user1.toString());

        User user2 = ctx.getBean("user2", User.class);
        System.out.println(user2.toString());
    }
}
