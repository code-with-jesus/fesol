package com.jcode.fesol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan(basePackages = "com.jcode.fesol")
public class AppConfig {

    public static void main(String[] args) {
        System.out.println("Passwd: " + new BCryptPasswordEncoder().encode("123"));
    }
}
