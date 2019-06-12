package com.test.springboot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描路径，从该路径下找到标识了需要装配的类，自动装配到spring的bean容器中
@ComponentScan("com.course")
public class Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
