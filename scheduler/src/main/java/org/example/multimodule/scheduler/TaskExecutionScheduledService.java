package org.example.multimodule.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
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
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class TaskExecutionScheduledService {

    private final ConfigurationStoredParameters parameters;
    private final ResourceTaskService resourceTaskService;
    private final ResourceTaskLoader resourceTaskLoader;
    private final LockingTaskExecutor executor;
    private final ForkJoinPool forkJoinPool;


    @Scheduled(cron = "#{@getMigrationPassportsCron}")
    public void taskExecutor() {

        log.traceEntry("taskExecutor execution stared");

        List<ResourceTask> lastTasks = resourceTaskService.findAllGroupByNameAndNotUploadedOrderByDateRevisionDescDateRevisionDesc();

        List<ResourceTaskRunnable> collect = lastTasks.stream()
                .map(resourceTask -> new ResourceTaskRunnable(resourceTaskLoader, resourceTask))
                .collect(Collectors.toList());

        forkJoinPool.submit(() -> collect.parallelStream().peek(resourceTaskRunnable -> {
            try {
                ChronoUnit most = ChronoUnit.valueOf(parameters.lockAtMostChronoUnit());
                ChronoUnit least = ChronoUnit.valueOf(parameters.lockAtLeastChronoUnit());
                executor.executeWithLock(resourceTaskRunnable,
                        new LockConfiguration(Instant.now(), resourceTaskRunnable.getResourceTask().getName(),
                                Duration.of(parameters.lockAtMostFor(), most/*ChronoUnit.HOURS*/),
                                Duration.of(parameters.lockAtLeastFor(), least)
                        ));
            } catch (Throwable e) {
                log.error(resourceTaskRunnable.getResourceTask().getName() + " throws " + e.getClass() + " " + e.getMessage());
            }
        }).collect(Collectors.toList())).join();

        log.traceExit("taskExecutor execution finished");
    }

    @Bean
    protected String getMigrationPassportsCron() {
        return parameters.cron();
    }

}
