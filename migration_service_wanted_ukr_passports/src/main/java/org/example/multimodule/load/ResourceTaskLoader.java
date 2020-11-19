package org.example.multimodule.load;

import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;

import javax.transaction.Transactional;

@FunctionalInterface
public interface ResourceTaskLoader {

    Region saveRegions(ResourceTask resourceTask);
}
