package org.example.multimodule.repositories;

import org.example.multimodule.models.ResourceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTaskRepository extends JpaRepository<ResourceTask, Long> {
}
