package com.practice.javapractice2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// spring boot 는 enablejparepository annotation이 없어도 알아서 scan한다.
@SpringBootApplication
public class JavaPractice2022Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaPractice2022Application.class, args);
    }

}
