package org.example.multimodule.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.models.MVSUkrPassport;
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
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public MVSUkrPassport home() {
        MVSUkrPassport passport = null;
        try {
            passport = objectMapper.readValue("{\"ID\":\"43273171\",\"OVD\":\"ЦЕНТРАЛЬНИЙ РВ СІМФЕРОПОЛЬСЬКОГО МВ ГУМВС УКРАЇНИ В АР КРИМ\",\"D_SERIES\":\"EC\",\"d_number\":\"451662\",\"D_TYPE\":\"ПАСПОРТ ГРОМАДЯНИНА УКРАЇНИ\",\"D_STATUS\":\"ВИКРАДЕНИЙ\",\"THEFT_DATA\":\"2003-12-19T00:00:00\",\"INSERT_DATE\":\"2003-12-19T00:00:00\"}",
                    MVSUkrPassport.class);
        } catch (JsonProcessingException e) {
            log.fatal("-------------------HERNYA-----------------------");
        }
        return passport;
    }

//    @GetMapping("/version")
//    public String version() {
//        log.info("Hello");
//        return parameters.logLevel() + " " + parameters.logPath();
//
//    }
}
