package org.example.multimodule.application;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;
//import org.example.multimodule.application.infrastructure.LoggerConfigurator;


@SpringBootApplication(scanBasePackages = "org.example.multimodule")
@RestController
public class DemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }
}
