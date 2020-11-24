package org.example.multimodule.scheduler.tasks;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.load.impl.ResourceTaskRunnable;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.db.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class TaskExecutionScheduledService {

    private final ConfigurationStoredParameters parameters;
    private final ResourceTaskService resourceTaskService;
    private final ResourceTaskLoader resourceTaskLoader;
    private final LockingTaskExecutor executor;

    @Scheduled(cron = "#{@getMigrationPassportsCron}")
    @SchedulerLock(
            name = "taskExecutor",
            lockAtLeastFor = "10m",
            lockAtMostFor = "20m")
    public void taskExecutor() {

        log.info("taskExecutor executed" );

        List<ResourceTask> lastTasks = resourceTaskService.findAllGroupByNameAndNotUploadedOrderByDateRevisionDescDateRevisionDesc();

        List<ResourceTaskRunnable> collect = lastTasks.stream()
                .map(resourceTask -> new ResourceTaskRunnable(resourceTaskLoader, resourceTask))
                .collect(Collectors.toList());

        List<ResourceTaskRunnable> executed = collect.parallelStream().peek(resourceTaskRunnable ->
                executor.executeWithLock(resourceTaskRunnable,
                        new LockConfiguration(Instant.now(), resourceTaskRunnable.getResourceTask().getName(),
                                Duration.of(1L, ChronoUnit.HOURS),
                                Duration.of(1L, ChronoUnit.HOURS)))).parallel().collect(Collectors.toList());

        log.info("EXECUTED " + executed.size());

//        log.info("Found " + lastTasks.size() + " tasks");
//        List<ResourceTaskRunnablerImpl> collect = lastTasks.stream()
//                .map(resourceTask -> {
//                    Optional<SimpleLock> lock = lockProvider.lock(new LockConfiguration(Instant.now(), resourceTask.getName(),
//                            Duration.of(1L, ChronoUnit.HOURS), Duration.of(1L, ChronoUnit.HOURS)));
//                    return new ResourceTaskRunnablerImpl(resourceTaskLoader, resourceTask);
//                })
//                .collect(Collectors.toList());
//
//        collect.parallelStream().forEachOrdered(resourceTaskRunnable -> {
//
//            scheduler.schedule(resourceTaskRunnable, Instant.now());
//        });

//        log.info("Found " + lastTasks.size() + " tasks");
//        List<Region> savedRegions = lastTasks.parallelStream()
//                .map(resourceTaskLoader::saveRegions).parallel()
//                .collect(Collectors.toList());
//        log.info("savedRegions.size() = " + savedRegions.size());
    }

    @Bean
    protected String getMigrationPassportsCron() {
        return parameters.cron();
    }

}
