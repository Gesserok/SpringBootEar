package org.example.multimodule.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.client.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class MigrationPassportSaveTaskScheduledService {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private final ConfigurationStoredParameters parameters;
    private final ResourceTaskExecutor resourceTaskExecutor;
    private final ResourceTaskService resourceTaskService;

    @Scheduled(cron = "#{@getMigrationPassportsCron}")
    public void saveMigrationServicePassports() {

        log.info("RUN " + atomicInteger.addAndGet(1));
        List<ResourceTask> all = resourceTaskService.findAll();

        all.forEach(resourceTask -> {
            resourceTaskExecutor.run();
        });
    }

    @Bean
    protected String getMigrationPassportsCron() {
        return parameters.cron();
    }

}
