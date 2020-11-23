package org.example.task_locker.services;

import org.example.task_locker.models.TaskLock;

import java.util.List;

public interface TaskLockService {

    List<TaskLock> findAll();
    TaskLock findByName(String name);
    TaskLock save(TaskLock taskLock);
    TaskLock update(TaskLock taskLock);
}
