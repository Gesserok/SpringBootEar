package org.example.multimodule.db.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.csv.DataReceiver;
import org.example.multimodule.db.RegionCreator;
import org.example.multimodule.exceptions.ODPConnectorException;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Log4j2
public class RegionCreatorImpl implements RegionCreator {
    private final ConfigurationStoredParameters parameters;
    private final DataReceiver dataReceiver;

    @Override
    public Region create(ResourceTask resourceTask, Iterator<CSVRecord> iterator) {

        List<MigrationServiceUrkPassport> passports = dataReceiver.getPassports(iterator, parameters.passportBatchSize());

        Region region = createRegion(resourceTask);
        region.setMigrationServiceUrkPassports(passports);

        return region;
    }

    @Override
    public Region create(ResourceTask resourceTask, Reader reader) {
        List<MVSUkrPassport> passports;
        try {
            passports = dataReceiver.getPassports(reader, parameters.passportBatchSize());
        } catch (JsonProcessingException e) {
            log.error("List of passports has not created");
            throw new ODPConnectorException("Wrong JSON", e);
        }

        Region region = createRegion(resourceTask);
        region.setMvsUkrPassports(passports);

        return region;
    }

    private Region createRegion(ResourceTask resourceTask) {
        Region region = new Region();
        region.setResourceRevisionUrl(resourceTask.getUrl());
        region.setResourceId(resourceTask.getName());
        return region;
    }
}
