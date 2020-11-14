package org.example.multimodule.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.services.client.NewResourceTaskSearcher;
import org.example.multimodule.services.client.PackageShowResourceResolver;
import org.example.multimodule.services.client.ResourceTaskCollector;
import org.example.multimodule.services.client.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class MvsPassportTaskUpdaterScheduledService {
    private final PackageShowResourceResolver packageShowResourceResolver;
    private final ResourceTaskCollector resourceTaskCollector;
    private final NewResourceTaskSearcher newResourceTaskSearcher;
    private final ResourceTaskService resourceTaskService;
    private final ConfigurationStoredParameters parameters;

    @Scheduled(cron = "#{@getCron}")
    public void saveResourceTasks() {
        List<Resource> resources = packageShowResourceResolver.getResources(parameters.packageId());
        log.info("After filtering left " + resources.size() + " resources");

        List<ResourceTask> resourceTasks = resourceTaskCollector.collect(resources);
        log.info("Open Data Portal contains " + resourceTasks.size() + " revisions");

        List<ResourceTask> newResourceTasks = newResourceTaskSearcher.getNewTask(resourceTasks);
        log.info("Among the " + resourceTasks.size() + " tasks on the portal, there are " + newResourceTasks.size() + " new ones");

        List<ResourceTask> savedResourceTasks = resourceTaskService.updateTaskList(newResourceTasks);
        log.info(Objects.nonNull(savedResourceTasks) ? savedResourceTasks.size() : 0 + " new resourceTasks saved to DB");

    }

    @Bean
    protected String getCron() {
        return parameters.cron();
    }

}
