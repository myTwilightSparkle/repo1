package com.iotek.hrmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAspectJAutoProxy
@EnableCaching
@EnableScheduling
@EnableAsync
@ComponentScan("com.iotek.hrmgr")
@SpringBootApplication
public class HrmgrApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmgrApplication.class, args);
    }
}
