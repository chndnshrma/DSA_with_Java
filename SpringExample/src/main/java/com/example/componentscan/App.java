package com.example.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Employee newEmployee = (Employee) context.getBean("employee");
        Manager newManager = (Manager) context.getBean("manager");
        System.out.println(newManager.toString());
    }
}
