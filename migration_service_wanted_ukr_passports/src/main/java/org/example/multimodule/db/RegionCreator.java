package org.example.multimodule.db;

import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.models.Passport;
import org.example.multimodule.models.Region;
import org.example.multimodule.models.ResourceTask;

import java.io.Reader;
import java.net.URLConnection;
import java.util.Iterator;

@FunctionalInterface
public interface RegionCreator {
    Region create(ResourceTask resourceTask, Iterator<CSVRecord> iterator);
}
