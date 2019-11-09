package xyz.sally.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@SpringBootApplication
@MapperScan("xyz.sally.account.dao")
@EnableFeignClients(basePackages = {"xyz.sally.account"})
@EnableEurekaClient
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
