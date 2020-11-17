package org.example.multimodule.db.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.connections.ResourceConnection;
import org.example.multimodule.csv.CSVReceiver;
import org.example.multimodule.db.RegionCreator;
import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.models.Passport;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;
import org.example.multimodule.services.db.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor (onConstructor = @__({@Autowired}))
@Log4j2
public class MigrationRegionCreator implements RegionCreator {
    private final ConfigurationStoredParameters parameters;
    private final CSVReceiver csvReceiver;

    @Override
    public Region create(ResourceTask resourceTask, Iterator<CSVRecord> iterator) {

        List<MigrationServiceUrkPassport> passports = csvReceiver.getPassports(iterator, parameters.passportBatchSize());

        Region region = new Region();
        region.setResourceRevisionUrl(resourceTask.getUrl());
        region.setResourceId(resourceTask.getName());
        region.setMigrationServiceUrkPassports(passports);

        return region;

    }
}
