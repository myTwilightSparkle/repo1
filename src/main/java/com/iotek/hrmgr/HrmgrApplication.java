package com.iotek.hrmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages="com.iotek.hrmgr")
public class HrmgrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmgrApplication.class, args);
    }
}
