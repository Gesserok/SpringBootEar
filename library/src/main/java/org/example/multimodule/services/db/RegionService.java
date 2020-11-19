package org.example.multimodule.services.db;

import org.example.multimodule.models.Region;

public interface RegionService {
    Region save(Region region);

    void deleteAllByResourceId(String resourceId);
}
