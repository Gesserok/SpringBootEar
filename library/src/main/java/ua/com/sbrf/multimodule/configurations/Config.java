package ua.com.sbrf.multimodule.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public String str() {
        return "HHHHH";
    }
}
