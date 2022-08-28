package com.jcode.fesol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.jcode.fesol")
@Import({ PersistenceConfig.class })
public class AppConfig {

}
