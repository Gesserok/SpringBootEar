package org.example.multimodule.application.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.example.multimodule.application.controllers.ContextRoot.valueOf;

@RestController
@Log4j2

public class MainController {

    private String c;

    @GetMapping(path = "/"+"{@getC}" +"/a")
    public String home() {
        return "hoem";
    }

    protected final String getC() {
        return c;
    }

}
