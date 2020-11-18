package com.sumuxi.spring.boot.starter;

import com.sumuxi.spring.boot.starter.model.Klass;
import com.sumuxi.spring.boot.starter.model.School;
import com.sumuxi.spring.boot.starter.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring boot starter configuration.
 */
@Configuration
@ComponentScan("com.sumuxi")
@EnableConfigurationProperties({SpringBootConfiguration.class,
        Student.class, Klass.class})
@ConditionalOnProperty(prefix = "spring.sumuxi", name = "enabled", havingValue = "true", matchIfMissing = true)
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
//@SpringBootApplication
public class SpringBootConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfiguration.class, args);
    }


    @Bean("class1")
    public Klass getKlass(){
        System.out.println("...创建了class1");
        return new Klass();
    }

    @Bean("student100")
    public Student getStudent100(){
        System.out.println("...创建了student100");
        return new Student();
    }

    @Bean("student123")
    public Student getStudent123(){
        System.out.println("...创建了student123");
        return new Student();
    }

    @Bean("school")
    @ConditionalOnBean(name = {"class1", "student100"})
    public School getSchool(){
        System.out.println("...创建了school");
        return new School();
    }
}
