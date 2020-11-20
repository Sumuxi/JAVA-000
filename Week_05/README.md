# 学习笔记

## 作业题：

**Week05 作业题目（周四）：

**2.（必做）**写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。

answer:

代码在[周4Q2/springAssemble目录](周4Q2/springAssemble)

一共有五种方式：

1.通过xml配置文件完成user对象的装配。请查看[Main1.java](周4Q2/springAssemble/src/com/sumuxi/Main1.java),[spring-config-1.xml](周4Q2/springAssemble/src/spring-config-1.xml),[User.java](周4Q2/springAssemble/src/com/sumuxi/model/User.java)

2.通过java配置类创建bean，放入了Spring IOC容器中，Spring完成自动装配。请查看[Main2.java](周4Q2/springAssemble/src/com/sumuxi/Main2.java),[MyConfig.java](周4Q2/springAssemble/src/com/sumuxi/config/MyConfig.java),[User.java](周4Q2/springAssemble/src/com/sumuxi/model/User.java)

3.利用xml配置文件指定component-scan扫描的包路径，通过包扫描的方式，扫描出所有 Component 和 Java配置类中的bean,Spring完成自动装配。请查看[Main3.java](周4Q2/springAssemble/src/com/sumuxi/Main3.java),[spring-config-3.xml](周4Q2/springAssemble/src/spring-config-3.xml),[MyConfig.java](周4Q2/springAssemble/src/com/sumuxi/config/MyConfig.java),[User.java](周4Q2/springAssemble/src/com/sumuxi/model/User.java),[MyController.java](周4Q2/springAssemble/src/com/sumuxi/controller/MyController.java)

4.类似于3，在Spring ApplicationContext指定要扫描的包，扫描出所有 Component 和 Java配置类中的bean, Spring完成自动装配。请查看[Main4.java](周4Q2/springAssemble/src/com/sumuxi/Main4.java),[MyConfig.java](周4Q2/springAssemble/src/com/sumuxi/config/MyConfig.java),[User.java](周4Q2/springAssemble/src/com/sumuxi/model/User.java),[MyController.java](周4Q2/springAssemble/src/com/sumuxi/controller/MyController.java)

5.在Spring ApplicationContext指定要扫描的包，扫描出所有 Component 和 Java配置类中的bean, 在MyController通过 @Autowired 和 @Resource 注解告知Spring自动装配 User bean。请查看[Main5.java](周4Q2/springAssemble/src/com/sumuxi/Main5.java),[MyConfig.java](周4Q2/springAssemble/src/com/sumuxi/config/MyConfig.java),[User.java](周4Q2/springAssemble/src/com/sumuxi/model/User.java),[MyController.java](周4Q2/springAssemble/src/com/sumuxi/controller/MyController.java)



**Week05 作业题目（周六）：**
**4.（必做）**给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

answer:

starter代码请移步[sumuxi-spring-boot-starter目录](周6Q4/demo-spring-boot-starter/sumuxi-spring-boot-starter)

starter-test代码请移步[starter-test目录](周6Q4/demo-spring-boot-starter/starter-test)



**6.（必做）**研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

answer:

代码请查看[JDBCDemo.java](周6Q6/jdbc-demo/jdbc-demo-1/src/main/java/com/sumuxi/JDBCDemo.java)

