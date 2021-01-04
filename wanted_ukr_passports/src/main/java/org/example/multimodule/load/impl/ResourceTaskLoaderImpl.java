package org.example.multimodule.load.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class ResourceTaskLoaderImpl implements ResourceTaskLoader {
    private final ResourceConnection resourceConnection;
    private final RegionCreator regionCreator;
    private final RegionService regionService;
    private final ResourceTaskService resourceTaskService;

    @Override
    @Transactional
    public Region saveRegions(ResourceTask resourceTask) {
        log.debug("Save resourceTask " + resourceTask.getName() + " started in thread " + Thread.currentThread().getName());
        URLConnection connection = resourceConnection.connection(resourceTask);
        regionService.deleteAllByResourceId(resourceTask.getName());
        Region savedRegion;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            savedRegion = saveFromFile(resourceTask, reader);

        } catch (IOException e) {
            log.error("IT IS ERROR HERE");
            throw new ODPConnectorException("IT IS ERROR HERE", e);
        }

        resourceTaskService.updateStatus(resourceTask);
        log.debug("Region " + savedRegion.getId() + " " + savedRegion.getResourceId() + " saved");
        log.debug("Save resourceTask " + resourceTask.getName() + " finished in thread " + Thread.currentThread().getName());
        return savedRegion;
    }

    private Region saveFromFile(ResourceTask resourceTask, BufferedReader reader) throws IOException {
        Region region = null;
        while (reader.read() != -1) {

                region = regionCreator.create(resourceTask, reader);

        }
        region = regionService.save(region);

        return region;
    }

    private Region saveFromCsv(ResourceTask resourceTask, BufferedReader reader) throws IOException {
        Region savedRegion = null;
        while (reader.read() != -1) {
            Region region = regionCreator.create(resourceTask, reader);
            savedRegion = regionService.save(region);
        }
        return savedRegion;


//    private Region saveFromCSV(ResourceTask resourceTask, BufferedReader reader) throws IOException {
//        Region savedRegion = null;
//        String line;
//        List<MigrationServiceUrkPassport> passports = null;
//        while ((line = reader.readLine()) != null) {
//
//            if (Objects.isNull(passports) || passports.size() == regionCreator.getParameters().passportBatchSize()) {
//                passports = new ArrayList<>();
//            }
//            String[] arr = line.split(";");
//
//            MigrationServiceUrkPassport passport = new MigrationServiceUrkPassport(
//                    arr[0], arr[1], arr[2], arr[3], arr[4]);
//            passports.add(passport);
//            if (passports.size() == regionCreator.getParameters().passportBatchSize()) {
//
//                Region region = regionCreator.create(resourceTask, passports);
//                savedRegion = regionService.save(region);
//            }
//            log.info("Saved " + savedRegion.getId());
//        }
//        Region region = regionCreator.create(resourceTask, passports);
//        savedRegion = regionService.save(region);
//
//        log.info("Finished");
//        return savedRegion;
//    }
    }
