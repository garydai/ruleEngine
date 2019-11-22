package xyz.sally.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author daitechang
 * @create: 2019-11-15
 **/
@SpringBootApplication
@MapperScan("xyz.sally.engine.dao")
@EnableFeignClients(basePackages = {"xyz.sally.engine"})
@EnableEurekaClient
public class EngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }
}
