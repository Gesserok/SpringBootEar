package org.example.multimodule.application.controllers;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dto.ResponsePackageShow;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.client.ResourceTaskService;
import org.example.multimodule.services.db.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private ResourceTaskService resourceTaskService;

    @GetMapping("/")
    public ResourceTask home() {

        ResourceTask resourceTask = new ResourceTask();
        resourceTask.setAddedTime(
                LocalDateTime.now()
        );
        resourceTask.setDateRevision(
                LocalDateTime.now()
        );
        resourceTask.setDayRevision(0);
        resourceTask.setName("0000");
        resourceTask.setUploadTime(
                LocalDateTime.now()
        );

        resourceTask.setUrl("asdas");

        ResourceTask saved = resourceTaskService.save(resourceTask);

        return saved;
    }

//    @GetMapping("/version")
//    public String version() {
//        log.info("Hello");
//        return parameters.logLevel() + " " + parameters.logPath();
//
//    }
}
