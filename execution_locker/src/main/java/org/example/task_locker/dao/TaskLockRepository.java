package org.example.task_locker.dao;

import org.example.task_locker.models.TaskLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TaskLockRepository extends JpaRepository<TaskLock, String> {

    TaskLock findByName(String name);

    @Modifying
    @Query("UPDATE TaskLock tl " +
            "SET tl.lockedBy = :lockedBy, tl.lockedAt = :lockedAt, tl.lockUntil = :lockerUntil " +
            "WHERE tl.name = :name")
    TaskLock update(String name, LocalDateTime lockUntil, LocalDateTime lockedAt, LocalDateTime lockedBy);

}

