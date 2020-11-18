package com.sumuxi.sumuxispringbootstarter.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@ConfigurationProperties(prefix = "spring.sumuxi.student")
public class Student implements Serializable {

//    @Value("#{new Integer('${spring.sumuxi.student.id}')}")
//    @Value("${spring.sumuxi.student.id}")
    private int id;

//    @Value("${spring.sumuxi.student.name}")
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student(101,"KK101");
    }
}
