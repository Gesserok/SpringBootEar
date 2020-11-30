//package org.example.multimodule.db.impl;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.apache.commons.csv.CSVRecord;
//import org.example.multimodule.csv.DataReceiver;
//import org.example.multimodule.csv.impl.JSONReceiver;
//import org.example.multimodule.db.RegionCreator;
//import org.example.multimodule.infrastructure.ConfigurationStoredParameters;
//import org.example.multimodule.models.MigrationServiceUrkPassport;
//import org.example.multimodule.models.Passport;
//import org.example.multimodule.models.Region;
//import org.example.multimodule.models.ResourceTask;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.io.Reader;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@Service("regionCreatorJSON")
//@AllArgsConstructor(onConstructor = @__({@Autowired}))
//@Log4j2
//public class RegionCreatorJSON implements RegionCreator {
//    private final ConfigurationStoredParameters parameters;
//    private final DataReceiver dataReceiver;
//
//    @Override
//    public Region create(ResourceTask resourceTask, Iterator<CSVRecord> iterator, Reader reader) {
//
//        List<MigrationServiceUrkPassport> passports = dataReceiver.getPassports(null, reader, parameters.passportBatchSize());
//
//        Region region = new Region();
//        region.setResourceRevisionUrl(resourceTask.getUrl());
//        region.setResourceId(resourceTask.getName());
//        region.setMigrationServiceUrkPassports(passports);
//
//        return region;
//
//    }
//}
