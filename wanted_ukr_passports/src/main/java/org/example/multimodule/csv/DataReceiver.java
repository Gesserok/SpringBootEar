package org.example.multimodule.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface DataReceiver {
    List<MigrationServiceUrkPassport> getPassports(List<String> lines);

    List<MVSUkrPassport> getPassports(Reader reader, Integer batchSize) throws JsonProcessingException;
}
