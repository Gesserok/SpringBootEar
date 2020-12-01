package org.example.multimodule.load.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
@Getter
public class ResourceTaskRunnable implements Runnable {

    private final ResourceTaskLoader resourceTaskLoader;
    private final ResourceTask resourceTask;

    @Override
    public void run() {
        log.traceEntry("ResourceTaskRunnable start " + resourceTask.getName());
        resourceTaskLoader.saveRegions(this.resourceTask);
        log.traceExit("ResourceTaskRunnable finished " + resourceTask.getName());
    }
}
