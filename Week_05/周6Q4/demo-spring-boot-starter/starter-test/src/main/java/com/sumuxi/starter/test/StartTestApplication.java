package com.sumuxi.starter.test;

import com.sumuxi.spring.boot.starter.model.Klass;
import com.sumuxi.spring.boot.starter.model.School;
import com.sumuxi.spring.boot.starter.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StartTestApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(StartTestApplication.class, args);
        Student student100 = ctx.getBean("student100", Student.class);
        System.out.println(student100.toString());

        Student student123 = ctx.getBean("student123", Student.class);
        System.out.println(student123.toString());

        Klass klass = ctx.getBean("class1", Klass.class);
        System.out.println(klass.toString());

        School school = ctx.getBean("school", School.class);
        System.out.println(school.toString());
    }

}
