package org.example.multimodule.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.services.client.NewResourceTaskSearcher;
import org.example.multimodule.services.client.PackageShowResourceResolver;
import org.example.multimodule.services.client.ResourceTaskCollector;
import org.example.multimodule.services.db.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class MigrationPassportTaskUpdaterScheduledService {

    private final PackageShowResourceResolver packageShowResourceResolver;
    private final ResourceTaskCollector resourceTaskCollector;
    private final NewResourceTaskSearcher newResourceTaskSearcher;
    private final ResourceTaskService resourceTaskService;
    private final ConfigurationStoredParameters parameters;

    @Scheduled(cron = "#{@getMigrationCron}")
    @SchedulerLock(
            name = "saveMigrationServiceResourceTasks",
            lockAtLeastFor = "30m",
            lockAtMostFor = "1h")
    public void saveMigrationServiceResourceTasks() {
        List<Resource> resources = packageShowResourceResolver.getResources(parameters.migrationUkrPassportsPackageId());
        log.debug("After filtering left " + resources.size() + " resources");

        List<ResourceTask> resourceTasks = resourceTaskCollector.collect(resources);
        log.debug("MigrationService-UkrPassports Open Data Portal contains " + resourceTasks.size() + " revisions");

        List<ResourceTask> newResourceTasks = newResourceTaskSearcher.getNewTask(resourceTasks);
        log.debug("Among the " + resourceTasks.size() + " tasks on the MigrationService-UkrPassports portal, there are " + newResourceTasks.size() + " new ones");

        List<ResourceTask> savedResourceTasks = resourceTaskService.updateTaskList(newResourceTasks);
        String logAdd = " new resourceTasks saved to DB - MigrationService-UkrPassports";
        log.debug(Objects.nonNull(savedResourceTasks)
                ? savedResourceTasks.size() + logAdd
                : 0 + logAdd);
    }

    @Bean
    protected String getMigrationCron() {
        return parameters.cron();
    }

}
