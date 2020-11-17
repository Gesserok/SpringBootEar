package org.example.multimodule.services.client;

import org.example.multimodule.models.ResourceTask;

import java.util.List;

public interface ResourceTaskService {
    List<ResourceTask> updateTaskList(List<ResourceTask> newResourceTasks);

    List<ResourceTask> findAll();

    ResourceTask save(ResourceTask resourceTask);

    ResourceTask updateStatus(ResourceTask resourceTask);
}
