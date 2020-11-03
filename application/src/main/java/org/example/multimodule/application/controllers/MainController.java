package org.example.multimodule.application.controllers;

import lombok.extern.log4j.Log4j2;
import org.example.multimodule.application.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.Passport;
import org.example.multimodule.models.Region;
import org.example.multimodule.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class MainController {

    @Autowired
    private ConfigurationStoredParameters parameters;

    @Autowired
    private RegionService regionService;

    @GetMapping("/")
    public Region home() {

        Region region = new Region();
        region.setResourceId("1");
        region.setResourceRevisionUrl("http://res.in");

        Passport passport = new Passport();
        passport.setDNumber("1");
        passport.setDSeries("A");
        passport.setDStatus("fail");
        passport.setDType("passport");
        passport.setInsertDate("asdasd");
        passport.setOvd("asd");
        passport.setTheftData("asdsad");
        passport.setExternalId(1L);
        List<Passport> passports = new ArrayList<>();
        passports.add(passport);
        region.setPassports(passports);
        regionService.save(region);
        return region;
    }

    @GetMapping("/version")
    public String version() {
        log.info("Hello");
        return parameters.logLevel() + " " + parameters.logPath();

    }
}
