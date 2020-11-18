package org.example.multimodule.dao;

import org.example.multimodule.models.ResourceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResourceTaskRepository extends JpaRepository<ResourceTask, Long> {

    ResourceTask findFirstByNameIgnoreCaseOrderByDateRevisionDescDateRevisionDesc(String name);

    @Query(value = "SELECT rt.id, rt.name, rt.added_time, rt.day_revision, rt,date_revision, rt.url, rt.upload_time" +
            "FROM (SELECT id," +
            "             name," +
            "             added_time," +
            "             day_revision," +
            "             date_revision," +
            "             url," +
            "             upload_time," +
            "             rank()" +
            "             over (partition by name order by day_revision, date_revision desc) rnk" +
            "      from odp.resource_tasks) as  rt" +
            "WHERE rnk = 1", nativeQuery = true)
    List<ResourceTask> findAllGroupByNameAndUploadTimeIsNullOrderByDateRevisionDescDateRevisionDesc();

}
