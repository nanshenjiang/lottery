package com.scnu.lotterysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching表示可以使用caching
@SpringBootApplication
@EnableCaching
public class LotterySystem02Application {

    public static void main(String[] args) {
        SpringApplication.run(LotterySystem02Application.class, args);
    }
}
