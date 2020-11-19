package org.example.multimodule.application.controllers;

import lombok.extern.log4j.Log4j2;
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
    private ResourceTaskService resourceTaskService;

    @GetMapping("/")
    public List<ResourceTask> home() {

        List<ResourceTask> tasks = resourceTaskService.findAllGroupByNameAndNotUploadedOrderByDateRevisionDescDateRevisionDesc();

        return tasks;
    }

//    @GetMapping("/version")
//    public String version() {
//        log.info("Hello");
//        return parameters.logLevel() + " " + parameters.logPath();
//
//    }
}
