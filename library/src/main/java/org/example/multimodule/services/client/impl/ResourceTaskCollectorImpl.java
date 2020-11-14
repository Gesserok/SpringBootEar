package org.example.multimodule.services.client.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;
import org.example.multimodule.services.client.ResourceShowRevisionResolver;
import org.example.multimodule.services.client.ResourceTaskCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceTaskCollectorImpl implements ResourceTaskCollector {

    private final ResourceShowRevisionResolver resourceShowRevisionResolver;

    @Override
    public List<ResourceTask> collect(List<Resource> resources) {

        List<ResourceTask> tasks = new ArrayList<>();

        resources
                .stream()
                .map(resourceShowRevisionResolver::getAll)
                .peek(log::debug)
                .forEach(tasks::addAll);

        return null;
    }
}
