package com.sumuxi.config;

import com.sumuxi.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean(name="user1")
    public User getUser1(){
        return new User("李四", 20);
    }

    @Bean(name="user2")
    public User getUser2(){
        return new User("王五", 22);
    }
}
