package ua.com.sbrf.multimodule.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public String str() {
        return "HHHHH";
    }
}
