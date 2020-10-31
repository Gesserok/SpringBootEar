package org.example.multimodule.application.controllers;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.application.infrastructure.ConfigurationStoredParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private ConfigurationStoredParameters parameters;

    @GetMapping("/")
    public String home() {
        log.info("Hello");
        return parameters.logLevel() + " " + parameters.logPath();

    }
}
