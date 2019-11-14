package xyz.sally.variables;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author daitechang
 * @create: 2019-11-14
 **/
@SpringBootApplication
@MapperScan("xyz.sally.variables.dao")
@EnableFeignClients(basePackages = {"xyz.sally.variables"})
@EnableEurekaClient
public class VariableApplication {

    public static void main(String[] args) {
        SpringApplication.run(VariableApplication.class, args);
    }
}
