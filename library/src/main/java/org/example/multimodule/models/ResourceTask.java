package org.example.multimodule.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.models.resource_show.ResourceRevision;
import org.example.multimodule.models.resource_show.Result;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@Entity
@Table(name = "resource_tasks")
@NoArgsConstructor
public class ResourceTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_task_gen")
    @SequenceGenerator(name = "resource_task_gen", sequenceName = "gen_resource_task_id", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDED_TIME")
    private LocalDateTime addedTime;

    @Column(name = "DAY_REVISION")
    private Integer dayRevision;

    @Column(name = "DATE_REVISION")
    private LocalDateTime dateRevision;

    @Column(name = "URL")
    private String url;

    @Column(name = "UPLOAD_TIME")
    private LocalDateTime uploadTime;

    public ResourceTask(Resource resource, ResourceRevision resourceRevision) {
        this.name = resource.getName();
        this.addedTime = LocalDateTime.now();
        this.url = resource.getUrl();
//        LocalDate localDate = LocalDate.parse(
//                url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("_")),
//                DateTimeFormatter.ofPattern("ddMMyyyy"));
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateRevision = LocalDateTime.parse(resourceRevision.getResourceCreated(), formatter);
        String resourceUrl = resourceRevision.getUrl();
        this.dayRevision = Integer.parseInt(resourceUrl.substring(resourceUrl.lastIndexOf("_") + 1));
    }

    public ResourceTask(Result result) {
        this.name = result.getName();
        this.addedTime = LocalDateTime.now();
        this.url = result.getUrl();
        this.dateRevision = LocalDateTime.parse(result.getLastModified(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.dayRevision = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceTask that = (ResourceTask) o;
        return dayRevision.equals(that.dayRevision) &&
                Objects.equals(name, that.name) &&
                Objects.equals(dateRevision, that.dateRevision) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dayRevision, dateRevision, url);
    }
}
