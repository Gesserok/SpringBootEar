package org.example.multimodule.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.db.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private ResourceTaskLoader resourceTaskLoader;

    @GetMapping("/")
    public Region home() {
        Region region = resourceTaskLoader.saveRegions(new ResourceTask());
        return region;
    }

//    @GetMapping("/version")
//    public String version() {
//        log.info("Hello");
//        return parameters.logLevel() + " " + parameters.logPath();
//
//    }
}
