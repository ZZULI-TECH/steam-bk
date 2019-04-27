package org.steam.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author mingshan
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.steam"})
@EnableTransactionManagement
public class SteamCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SteamCoreApplication.class, args);
    }
}