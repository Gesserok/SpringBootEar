package org.example.multimodule.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.client.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(
        value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceTaskExecutor implements Runnable{

    private final ResourceTaskService resourceTaskService;
    private final ResourceTaskLoader resourceTaskLoader;

    @SneakyThrows
    @Override
    public void run() {

        log.info("ResourceTaskExecutor");


        Thread.currentThread().interrupt();
        Thread.sleep(10000);
    }

}
