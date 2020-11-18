package com.sumuxi.sumuxispringbootstarter.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data

@ConfigurationProperties(prefix = "spring.sumuxi.klass")
public class Klass { 

//    @Value("${spring.sumuxi.klass.list}")
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
