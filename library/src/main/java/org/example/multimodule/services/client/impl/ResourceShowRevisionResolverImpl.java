package org.example.multimodule.services.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.dto.ResponseResourceShow;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.models.resource_show.ResourceRevision;
import org.example.multimodule.services.client.ODPClient;
import org.example.multimodule.services.client.ResourceShowRevisionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceShowRevisionResolverImpl implements ResourceShowRevisionResolver {

    private final ODPClient<ResponseResourceShow> odpClient;

    @Override
    public List<ResourceTask> getAll(Resource resource) {

//        @ValidResponse
        ResponseResourceShow resourceShow = odpClient.getResponse(
                "resource_show",
                resource.getId(),
                ResponseResourceShow.class
        );

        if (Objects.isNull(resourceShow.getResult())) {
            log.warn("Field \"Result\" is null");
            return new ArrayList<>();
        }

        if (Objects.isNull(resourceShow.getResult().getResourceRevisions())) {
            log.warn("Field \"Result.ResourceRevisions\" is null");
            List<ResourceTask> resourceTasks = new ArrayList<>();
            resourceTasks.add(new ResourceTask(resourceShow.getResult()));
            return resourceTasks;
        }

        List<ResourceRevision> resourceRevisions = resourceShow.getResult().getResourceRevisions();

        return Objects.requireNonNull(resourceRevisions)
                .stream()
                .map(resourceRevision -> new ResourceTask(resource, resourceRevision))
                .peek(resourceTask -> log.debug("Resource added to list"))
                .collect(Collectors.toList());
    }
}
