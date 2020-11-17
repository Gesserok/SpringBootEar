package org.example.multimodule.services.client;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dao.ResourceTaskRepository;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceTaskServiceImpl implements ResourceTaskService {

    private final ResourceTaskRepository resourceTaskRepository;

    @Override
    public List<ResourceTask> updateTaskList(List<ResourceTask> newResourceTasks) {
        if (Objects.isNull(newResourceTasks) || newResourceTasks.isEmpty()) {
            return newResourceTasks;
        }
        return resourceTaskRepository.saveAll(newResourceTasks);
    }

    @Override
    public List<ResourceTask> findAll() {
        return resourceTaskRepository.findAll();
    }

    @Override
    public ResourceTask save(ResourceTask resourceTask) {
        return resourceTaskRepository.save(resourceTask);
    }

    @Override
    public ResourceTask updateStatus(ResourceTask resourceTask) {
        ResourceTask currentTask = resourceTaskRepository.getOne(resourceTask.getId());
        currentTask.setUploadTime(LocalDateTime.now());
        return resourceTaskRepository.save(currentTask);
    }
}
