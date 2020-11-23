//package org.example.multimodule.scheduler.tasks;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
//import org.example.multimodule.load.ResourceTaskLoader;
//import org.example.multimodule.models.Region;
//import org.example.multimodule.models.ResourceTask;
//import org.example.multimodule.services.db.ResourceTaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
//@Log4j2
//public class TaskExecutionScheduledService {
//
//    private final ConfigurationStoredParameters parameters;
//    private final ResourceTaskService resourceTaskService;
//    private final ResourceTaskLoader resourceTaskLoader;
//
//    @Scheduled(cron = "#{@getMigrationPassportsCron}")
//    public void saveMigrationServicePassports() {
//        List<ResourceTask> lastTasks = resourceTaskService.findAllGroupByNameAndNotUploadedOrderByDateRevisionDescDateRevisionDesc();
//
//        log.info("Found " + lastTasks + " tasks");
//        List<Region> savedRegions = lastTasks.parallelStream()
//                .map(resourceTaskLoader::saveRegions).parallel()
//                .collect(Collectors.toList());
//        log.info("savedRegions.size() = " + savedRegions.size());
//    }
//
//    @Bean
//    protected String getMigrationPassportsCron() {
//        return parameters.cron();
//    }
//
//}
