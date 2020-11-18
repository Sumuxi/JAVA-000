package com.sumuxi.demospringbootstarter;

import com.sumuxi.demospringbootstarter.model.Klass;
import com.sumuxi.demospringbootstarter.model.School;
import com.sumuxi.demospringbootstarter.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@EnableConfigurationProperties({SpringBootConfiguration.class})
@ConditionalOnProperty(prefix = "spring.sumuxi", name = "enabled", havingValue = "true", matchIfMissing = true)
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@RequiredArgsConstructor
@SpringBootApplication
public class SpringBootConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfiguration.class, args);
    }

    @Bean
    public School getSchool(){
        return new School();
    }

    @Bean("class1")
    public Klass getKlass(){
        return new Klass();
    }

    @Bean("student100")
    public Student getStudent100(){
        return new Student();
    }

    @Bean("student123")
    public Student getStudent123(){
        return new Student();
    }
}
