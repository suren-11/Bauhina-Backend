package com.example.bespringboot;

//import lombok.experimental.FieldNameConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BEspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BEspringbootApplication.class, args);
    }

}
