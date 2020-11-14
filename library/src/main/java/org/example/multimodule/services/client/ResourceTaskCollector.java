package org.example.multimodule.services.client;

import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;

import java.util.List;

@FunctionalInterface
public interface ResourceTaskCollector {
    List<ResourceTask> collect(List<Resource> resources);
}
