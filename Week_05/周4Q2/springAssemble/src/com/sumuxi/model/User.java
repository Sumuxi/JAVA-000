package com.sumuxi.model;

public class User {

    private String name;
    private  int  age;

    public User(){
        System.out.println("...User 无参构造方法，创建了对象 " + this.toString());
    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
        System.out.println("...User 两参构造方法，创建了对象 " + this.toString());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "User[name=" + name + ", age=" + age + "]";
    }

}
