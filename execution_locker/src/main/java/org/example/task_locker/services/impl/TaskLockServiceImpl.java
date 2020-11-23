package org.example.task_locker.services.impl;

import lombok.AllArgsConstructor;
import org.example.task_locker.dao.TaskLockRepository;
import org.example.task_locker.models.TaskLock;
import org.example.task_locker.services.TaskLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TaskLockServiceImpl implements TaskLockService {

    private final TaskLockRepository taskLockRepository;

    @Override
    public List<TaskLock> findAll() {
        return taskLockRepository.findAll();
    }

    @Override
    public TaskLock findByName(String name) {
        return taskLockRepository.findByName(name);
    }

    @Override
    public TaskLock save(TaskLock taskLock) {
        return taskLockRepository.save(taskLock);
    }

    @Override
    public TaskLock update(TaskLock taskLock) {
        return taskLockRepository.update(
                taskLock.getName(),
                taskLock.getLockUntil(),
                taskLock.getLockedAt(),
                taskLock.getLockedBy()
        );
    }
}
