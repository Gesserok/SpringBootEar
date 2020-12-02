package org.example.multimodule.version.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class VersionController {

    @GetMapping("/version")
    public String version() {
        return "version";
    }

}
