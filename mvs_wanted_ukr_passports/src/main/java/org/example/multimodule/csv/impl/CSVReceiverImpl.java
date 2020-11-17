package org.example.multimodule.csv.impl;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.csv.CSVReceiver;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Log4j2
@NoArgsConstructor
public class CSVReceiverImpl implements CSVReceiver {

    @Override
    public List<MigrationServiceUrkPassport> getPassports(Iterator<CSVRecord> iterator, Integer batchSize) {

        List<MigrationServiceUrkPassport> passports = new ArrayList<>();
        while (iterator.hasNext()) {
            CSVRecord next = iterator.next();
            MigrationServiceUrkPassport passport = new MigrationServiceUrkPassport(
                    next.get("nn"),
                    next.get("status"),
                    next.get("series"),
                    next.get("number"),
                    next.get("date_edit")
            );
            passports.add(passport);
            if (passports.size() == batchSize) {
                return passports;
            }
        }

        return passports;
    }
}
