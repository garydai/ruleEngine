package xyz.sally.account.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author daitechang
 * @create: 2019-11-05
 **/
@Configuration
@Import(value = xyz.sally.common.config.AppConfig.class)
public class AppConfig {

}
