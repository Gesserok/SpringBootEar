package org.example.multimodule.services.client;

import org.example.multimodule.models.ResourceTask;

import java.util.List;

@FunctionalInterface
public interface NewResourceTaskSearcher {
    List<ResourceTask> getNewTask(List<ResourceTask> resourceTasks);
}
