package com.exampl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication 
//@ComponentScan("com.exampl.controller")
public class tee {

    public static void main(String[] args) {
        SpringApplication.run(tee.class, args);
        //System.out.println(new begin().hello());
    }

}


