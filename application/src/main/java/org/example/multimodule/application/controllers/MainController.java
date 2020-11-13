package org.example.multimodule.application.controllers;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dto.ResponsePackageShow;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.Passport;
import org.example.multimodule.models.Region;
import org.example.multimodule.service.ODPClient;
import org.example.multimodule.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private ODPClient<ResponsePackageShow> odpClient;

    @Autowired
    private RegionService regionService;

    @GetMapping("/")
    public ResponsePackageShow home() {


        ResponsePackageShow package_show = odpClient.getResponse("package_show", "ab09ed00-4f51-4f6c-a2f7-1b2fb118be0f", ResponsePackageShow.class);

        return package_show;
    }

//    @GetMapping("/version")
//    public String version() {
//        log.info("Hello");
//        return parameters.logLevel() + " " + parameters.logPath();
//
//    }
}
