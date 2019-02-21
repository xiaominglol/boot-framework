package com.gemini.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 小明不读书
 * @date 2018-11-04
 */
@MapperScan("com.gemini.admin.sys.dao")
@ComponentScan("com.gemini")
@EnableCaching
@EnableTransactionManagement
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
