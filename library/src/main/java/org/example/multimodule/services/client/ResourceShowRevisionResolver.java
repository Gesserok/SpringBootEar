package org.example.multimodule.services.client;

import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.models.package_show.Resource;

import java.util.List;

public interface ResourceShowRevisionResolver {
   List<ResourceTask> getAll(Resource resource);

}
