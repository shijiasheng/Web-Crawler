package com.tongji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tongji.mapper")
public class MyApplication
{
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
