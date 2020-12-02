package org.example.multimodule.version.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

@RestController
@Log4j2
public class TestController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/servlet")
    public String version() {
        return "servlet";
    }

}
