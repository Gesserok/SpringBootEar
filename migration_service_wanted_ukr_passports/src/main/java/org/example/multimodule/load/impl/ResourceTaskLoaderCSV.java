package org.example.multimodule.load.impl;

import lombok.AllArgsConstructor;
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

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceTaskLoaderCSV implements ResourceTaskLoader {

    private final ResourceConnection resourceConnection;
    private final RegionCreator regionCreator;
    private final RegionService regionService;
    private final ResourceTaskService resourceTaskService;

    @Override
    @Transactional
    public Region saveRegions(ResourceTask resourceTask) {
        log.info("Save resourceTask " + resourceTask.getName() + " started in thread " + Thread.currentThread().getName());
        URLConnection connection = resourceConnection.connection(resourceTask);
        regionService.deleteAllByResourceId(resourceTask.getName());
        Region savedRegion = null;
        try (Reader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)) {

            Iterator<CSVRecord> iterator = CSVFormat.DEFAULT.withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase(true)
                    .parse(reader)
                    .iterator();

            while (iterator.hasNext()) {
                Region region = regionCreator.create(resourceTask, iterator);
                savedRegion = regionService.save(region);
            }

        } catch (IOException e) {
            log.error("IT IS ERROR HERE");
            throw new ODPConnectorException("IT IS ERROR HERE", e);
        }

        resourceTaskService.updateStatus(resourceTask);
        log.info("Region " + savedRegion.getId() + " " +savedRegion.getResourceId() + " saved");
        log.info("Save resourceTask " + resourceTask.getName() + " finished in thread " + Thread.currentThread().getName());
        return savedRegion;
    }
}
