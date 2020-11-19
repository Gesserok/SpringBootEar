package org.example.multimodule.services.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.client.NewResourceTaskSearcher;
import org.example.multimodule.services.db.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class NewResourceTaskSearcherImpl implements NewResourceTaskSearcher {

    private final ResourceTaskService resourceTaskService;

    @Override
    public List<ResourceTask> getNewTask(List<ResourceTask> resourceTasks) {

        List<ResourceTask> tasksFromDB = resourceTaskService.findAll();
        log.trace("Found " + tasksFromDB.size() + " task(s) in DB");

        resourceTasks.removeAll(tasksFromDB);

        return resourceTasks;
    }
}
