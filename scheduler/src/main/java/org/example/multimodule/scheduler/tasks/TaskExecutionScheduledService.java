package org.example.multimodule.scheduler.tasks;

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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class TaskExecutionScheduledService {

    private static AtomicInteger count = new AtomicInteger(0);

    private final ConfigurationStoredParameters parameters;
    private final ResourceTaskService resourceTaskService;
    private final ResourceTaskLoader resourceTaskLoader;
    private final LockingTaskExecutor executor;
    private final ForkJoinPool forkJoinPool;

    @Scheduled(cron = "#{@getMigrationPassportsCron}")
    public void taskExecutor() {

        log.info("taskExecutor execution count = " + count.addAndGet(1) + " stared");

        List<ResourceTask> lastTasks = resourceTaskService.findAllGroupByNameAndNotUploadedOrderByDateRevisionDescDateRevisionDesc();

        List<ResourceTaskRunnable> collect = lastTasks.stream()
                .map(resourceTask -> new ResourceTaskRunnable(resourceTaskLoader, resourceTask))
                .collect(Collectors.toList());


        List<ResourceTaskRunnable> executed = forkJoinPool.submit(() -> collect.parallelStream().peek(resourceTaskRunnable -> {

            executor.executeWithLock(resourceTaskRunnable,
                    new LockConfiguration(Instant.now(), resourceTaskRunnable.getResourceTask().getName(),
                            Duration.of(1L, ChronoUnit.HOURS),
                            Duration.of(1L, ChronoUnit.HOURS)));

        }).collect(Collectors.toList())).join();


//        List<ResourceTaskRunnable> executed = collect.parallelStream().peek(resourceTaskRunnable ->
//                executor.executeWithLock(resourceTaskRunnable,
//                        new LockConfiguration(Instant.now(), resourceTaskRunnable.getResourceTask().getName(),
//                                Duration.of(1L, ChronoUnit.HOURS),
//                                Duration.of(1L, ChronoUnit.HOURS)))).parallel().collect(Collectors.toList());

        log.info("taskExecutor execution count = " + count.get() + " finished");
        log.info("---------------------------------------------------------------------------------------------------------------------------");
    }

    @Bean
    protected String getMigrationPassportsCron() {
        return parameters.cron();
    }

}
