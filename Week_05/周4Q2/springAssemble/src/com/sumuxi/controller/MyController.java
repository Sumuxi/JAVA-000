package com.sumuxi.controller;

import com.sumuxi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Component 类注解，表明该类为组件类，告知Spring在容器中为这个类创建bean，配合Spring的组件扫描，可完成自动装配。
 * @Controller, @Service, @Repository 注解和 @Component 注解等价，只不过为了更好的指明组件类的功能
 */
@Controller
public class MyController {

    /*
     * 声明需要进行自动装配，会在Spring应用上下文中按类型和变量名去寻找匹配某个Bean。若没找到或找到多个Bean会抛出异常。
     * 这个注解不仅能够用在构造器的方法上，还可以用在setter方法上。
     * @Autowried(required=false)，这种声明方式，先尝试去执行自动装配，若没找到对应的Bean，则会让这个Bean处于未装配的状态。
     **/
    @Autowired(required = true)
    private User user1;

    //javax引入的注解，和@Autowired注解作用类似
    @Resource(name = "user2")
    private User user2;

    public MyController() {
        System.out.println("MyController无参构造方法，创建了对象 MyController");
    }

    public void test() {
        System.out.println("Spring IOC 容器注入了两个user对象，分别是"
                + user1.toString() + ","
                + user2.toString());
    }


    @Override
    public String toString() {
        return "MyController{ 自定义的toString()方法 }";
    }
}
