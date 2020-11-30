package org.example.multimodule.load.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.db.RegionCreator;
import org.example.multimodule.exceptions.ODPConnectorException;
import org.example.multimodule.load.ResourceTaskLoader;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.connections.ResourceConnection;
import org.example.multimodule.services.db.RegionService;
import org.example.multimodule.services.db.ResourceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
@Getter
public class ResourceTaskRunnable implements Runnable{

    private final ResourceTaskLoader resourceTaskLoader;
    private final ResourceTask resourceTask;

    @Override
    public void run () {
        log.info("ResourceTaskRunnable start " + resourceTask.getName());
        resourceTaskLoader.saveRegions(this.resourceTask);
        log.info("ResourceTaskRunnable finished " + resourceTask.getName());

    }
}
