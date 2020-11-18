package com.sumuxi.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class Klass { 

    @Value("spring.sumuxi.klass.students")
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
