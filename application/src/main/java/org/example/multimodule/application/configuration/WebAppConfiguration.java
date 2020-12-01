package org.example.multimodule.application.configuration;

import lombok.extern.log4j.Log4j2;
import org.example.spring.binding.EnableSpringBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
@ComponentScan(basePackages = "org.example.multimodule")
@EnableSpringBinding
public class WebAppConfiguration {

}
