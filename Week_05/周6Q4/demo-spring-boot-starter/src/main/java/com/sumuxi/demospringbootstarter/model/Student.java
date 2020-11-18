package com.sumuxi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student implements Serializable {

    @Value("spring.sumuxi.student.id")
    private int id;

    @Value("spring.sumuxi.student.name")
    private String name;
    
    public void init(){
        System.out.println("hello...........");
    }
    
    public Student create(){
        return new Student(101,"KK101");
    }
}
