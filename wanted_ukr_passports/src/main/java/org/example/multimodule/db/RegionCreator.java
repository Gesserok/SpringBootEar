package org.example.multimodule.db;

import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface RegionCreator {
    Region create(ResourceTask resourceTask, List<String> lines) throws IOException;

    Region create(ResourceTask resourceTask, Reader reader) throws IOException;

    ConfigurationStoredParameters getParameters();
}
