package org.example.multimodule.csv;

import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.models.Passport;

import java.io.Reader;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

@FunctionalInterface
public interface CSVReceiver {
    List<MigrationServiceUrkPassport> getPassports(Iterator<CSVRecord> records, Integer batchSize);
}
