package com.twl.springboot.es;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootESLeanApplicatoin {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootESLeanApplicatoin.class, args);
    }
    

}
