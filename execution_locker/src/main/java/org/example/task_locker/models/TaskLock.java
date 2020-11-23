package org.example.task_locker.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "task_locker")
public class TaskLock {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "lock_until")
    private LocalDateTime lockUntil;
    @Column(name = "locked_at")
    private LocalDateTime lockedAt;
    @Column(name = "locked_by")
    private LocalDateTime lockedBy;

}
